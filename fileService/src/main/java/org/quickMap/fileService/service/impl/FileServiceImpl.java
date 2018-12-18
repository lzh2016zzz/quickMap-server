package org.quickMap.fileService.service.impl;

import com.github.tobato.fastdfs.domain.MetaData;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.domain.ThumbImageConfig;
import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.codec.digest.DigestUtils;
import org.quickMap.Utils.EncryptDesUtil;
import org.quickMap.Utils.FileOperatorUtil;
import org.quickMap.constant.FileServiceConstant;
import org.quickMap.constant.FileServiceConstant.Meta;
import org.quickMap.fileService.cfg.FdfsConstant;
import org.quickMap.fileService.model.FileInfoData;
import org.quickMap.fileService.service.IFilePrefixSuggestionService;
import org.quickMap.fileService.service.IFileService;
import org.quickmap.dataService.dao.FileInfoMapper;
import org.quickmap.dataService.dao.model.FileInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class FileServiceImpl implements IFileService {


    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Autowired
    protected FastFileStorageClient client;

    @Autowired
    protected IFilePrefixSuggestionService sug;

    @Autowired
    protected FileInfoMapper fileInfoMapper;

    @Autowired
    protected FdfsConstant fdfsConstant;

    @Autowired
    protected ThumbImageConfig thumbImageCfg;

    @Value("${des.seed}")
    private String desSeed;

    private EncryptDesUtil encrypter;

    @Override
    public FileInfoData uploadFile(InputStream file, String uploadFileName, long fileLength, boolean genThumbImage) throws Exception {
        return uploadFile(file, uploadFileName, fileLength, 0, genThumbImage);
    }

    @Override
    public FileInfoData uploadFile(InputStream file, String uploadFileName, long fileLength, Integer author, boolean genThumbImage) throws Exception {
        Assert.hasText(uploadFileName, "文件名不能为空");
        Assert.notNull(file, "文件不能为空");

        FileInfo fileInfo = new FileInfo();
        Set<MetaData> metaData = genMeta(fileInfo);

        StorePath storePath = genThumbImage && isSupportThumbImageType(uploadFileName) ?
                client.uploadImageAndCrtThumbImage(file, fileLength, FileOperatorUtil.getSuffix(uploadFileName), metaData) :
                client.uploadFile(file, fileLength, genStoreFileName(uploadFileName), metaData);

        fileInfo.setSize(fileLength);//文件大小
        fileInfo.setPath(storePath.getFullPath());//完整路径
        fileInfo.setTimestamp(System.currentTimeMillis());//时间戳
        fileInfo.setAuthor(author);//创建者
        fileInfo.setFilename(uploadFileName);//文件名
        fileInfo.setSuffix(FileOperatorUtil.getSuffix(uploadFileName));//后缀
        fileInfo.setThumbImagePath(genThumbImage ? thumbImageCfg.getThumbImagePath(storePath.getFullPath()) : null);//缩略图地址
        sug.addSugKey(metaData, uploadFileName);

        fileInfoMapper.insertFileInfo(fileInfo);
        return solveFileInfoData(fileInfo);
    }

    @Override
    public FileInfoData uploadB64File(String b64, String uploadFileName, Integer author, boolean genThumbImage) throws Exception {
        Assert.hasText(uploadFileName, "文件名不能为空");
        Assert.hasText(b64, "base64不能为空");
        byte[] b = Base64Utils.decodeFromString(b64);
        try (InputStream ins = new ByteArrayInputStream(b)) {
            return uploadFile(ins, uploadFileName, b.length, author, genThumbImage);
        }
    }

    @Override
    public FileInfoData uploadB64File(String b64, String uploadFileName, boolean genThumbImage) throws Exception {
        return uploadB64File(b64, uploadFileName, 0, genThumbImage);
    }

    @Override
    public byte[] downloadFile(String fullPath) {
        Assert.hasText(fullPath, "完整路径不能为空");
        return downloadFile(getStorePath(fullPath));
    }

    @Override
    public byte[] downloadFile(StorePath storePath) {
        Assert.notNull(storePath, "存储路径不能为空");
        return client.downloadFile(storePath.getGroup(), storePath.getPath(), new DownloadByteArray());
    }

    @Override
    public void deleteFile(String delParam) {
        Assert.notNull(delParam, "删除参数不能为空");
        Integer id = Integer.valueOf(getEncrypter().decrypt(delParam));
        FileInfo fileInfo = fileInfoMapper.queryFileInfoLimit1(FileInfo.QueryBuild().id(id));
        if (fileInfo != null) {
            logicalDelete(fileInfo.getId());
            client.deleteFile(fileInfo.getPath());
            if(fileInfo.getThumbImagePath() != null) client.deleteFile(fileInfo.getThumbImagePath());
        }
    }

    @Override
    public List<FileInfoData> search(String name,Long before,Long after,String suffix,Integer author) {
        if((!StringUtils.hasText(name)) && (!StringUtils.hasText(suffix)) && before == null && after == null && author == null){
            return Collections.emptyList();
        }
        suffix = suffix != null ? suffix.replace(".","") : null;
        List<FileInfo> f = fileInfoMapper.queryFileInfo(FileInfo.QueryBuild().timestampBetWeen(after,before).suffix(suffix).filename(name).author(author).isdel(FileServiceConstant.DelStatus.common).build());
        if (f.size() == 0 && StringUtils.hasText(name)) {
            sug.deleteSugKey(name);
        }
        return solveFileInfoData(f);
    }

    @Override
    public void initFileNameSearchText(boolean rebuild) {
        List<String> fileNames = fileInfoMapper.queryFileNameSet();
        sug.initSugKeys(fileNames, rebuild);
    }

    /**
     * 是否支持生成缩略图
     *
     * @param uploadFileName
     * @return
     */
    protected boolean isSupportThumbImageType(String uploadFileName) {
        final List<String> list = Arrays.asList("jpeg", "jpg", "bmp", "png");
        return list.indexOf(FileOperatorUtil.getSuffix(uploadFileName)) > -1;
    }

    /**
     * 获取加密工具
     *
     * @return
     */
    protected EncryptDesUtil getEncrypter() {
        if (encrypter == null) {
            encrypter = new EncryptDesUtil(desSeed);
        }
        return encrypter;
    }

    /**
     * 获取下载地址
     *
     * @param uploadFileName
     * @param fullPath
     * @return
     */
    protected String getDownloadUrl(String uploadFileName, String fullPath) {
        return fdfsConstant.getDownloadServer() + fullPath + "?attname=" + uploadFileName;
    }

    /**
     * 获取路径对象
     *
     * @param fullPath
     * @return
     */
    protected StorePath getStorePath(String fullPath) {
        if (!StringUtils.hasText(fullPath)) {
            return null;
        }
        int i;
        String group = fullPath.substring(0, (i = fullPath.indexOf("/")));
        String path = fullPath.substring(i + 1);
        StorePath storePath = new StorePath();
        storePath.setPath(path);
        storePath.setGroup(group);
        return storePath;
    }

    /**
     * 获取删除地址
     * @param param
     * @return
     */
    protected String getDelParam(Object param) {
        Assert.notNull(param, "参数不能为空");
        if (param instanceof String) {
            Assert.hasText((String) param, "参数不能为空");
        }
        return getEncrypter().encrypt(String.valueOf(param));
    }


    /**
     * 生成元数据
     *
     * @return
     */
    protected Set<MetaData> genMeta(FileInfo fileInfo) {
        Set<MetaData> metaDataSet = new HashSet<>();
        metaDataSet.add(new MetaData(Meta.FILENAME, fileInfo.getFilename()));
        return metaDataSet;
    }

    /**
     * 重新编码文件存储名称,解决上传失败,错误码22问题
     *
     * @param original
     * @return
     */
    protected String genStoreFileName(String original) {
        if (StringUtils.hasText(original)) {
            String separator = ".";
            int i;
            String s = original.substring(0, (i = original.indexOf(separator)) == -1 ? original.length() : i);
            String suffix = i != -1 ? original.substring(i) : "";
            return DigestUtils.sha1Hex(s) + suffix;
        } else return "";
    }


    protected List<FileInfoData> solveFileInfoData(List<FileInfo> fileInfos) {
        Assert.notNull(fileInfos, "");
        return fileInfos.stream().map(f -> solveFileInfoData(f)).collect(Collectors.toList());
    }


    protected FileInfoData solveFileInfoData(FileInfo info) {
        FileInfoData data = new FileInfoData();
        data.setAuthor(info.getAuthor());
        data.setSize(info.getSize());
        data.setTimestamp(info.getTimestamp());
        data.setDelParam(getDelParam(info.getId()));
        data.setFilename(info.getFilename());
        data.setPath(info.getPath());
        data.setDownloadUrl(getDownloadUrl(info.getFilename(), info.getPath()));
        data.setThumbImagePath(info.getThumbImagePath() != null ? fdfsConstant.getDownloadServer() + info.getThumbImagePath() : null);
        return data;
    }


    /**
     * 逻辑删除
     *
     * @param id
     */
    protected void logicalDelete(int id) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setIsdel(FileServiceConstant.DelStatus.del);
        fileInfoMapper.update(FileInfo.UpdateBuild().set(fileInfo).where(FileInfo.ConditionBuild().idList(id)).build());
    }

}
