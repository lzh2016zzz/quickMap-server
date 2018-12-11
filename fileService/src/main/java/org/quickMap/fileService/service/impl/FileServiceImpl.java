package org.quickMap.fileService.service.impl;

import com.github.tobato.fastdfs.domain.MetaData;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.codec.digest.DigestUtils;
import org.quickMap.Utils.EncryptDes;
import org.quickMap.Utils.FileOperatorUtil;
import org.quickMap.constant.FileServiceConstant;
import org.quickMap.constant.FileServiceConstant.Meta;
import org.quickMap.fileService.cfg.FdfsConstant;
import org.quickMap.fileService.model.FileInfoData;
import org.quickMap.fileService.service.IFileService;
import org.quickmap.storageService.dao.FileInfoMapper;
import org.quickmap.storageService.dao.model.FileInfo;
import org.quickmap.storageService.service.IFilePrefixSuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;


@Service
public class FileServiceImpl implements IFileService {

    @Autowired
    protected FastFileStorageClient client;

    @Autowired
    protected IFilePrefixSuggestionService sug;

    @Autowired
    protected FileInfoMapper fileInfoMapper;

    @Autowired
    protected FdfsConstant fdfsConstant;

    @Value("${des.seed}")
    protected String desSeed;

    @Override
    public FileInfoData uploadFile(InputStream file, String uploadFileName, long fileLength) throws Exception {
        return uploadFile(file, uploadFileName, fileLength, 0);
    }

    @Override
    public FileInfoData uploadFile(InputStream file, String uploadFileName, long fileLength, Integer author) throws Exception {
        Assert.hasText(uploadFileName, "文件名不能为空");
        Assert.notNull(file, "文件不能为空");
        Set<MetaData> metaData;
        FileInfoData fileInfoData = new FileInfoData();
        fileInfoData.setFilename(uploadFileName);//文件名
        fileInfoData.setSize(fileLength);//文件大小
        fileInfoData.setAuthor(author);//创建者
        fileInfoData.setTimestamp(System.currentTimeMillis());//时间戳

        StorePath storePath = client.uploadFile(file, fileLength, uploadFileName, (metaData = genMeta(fileInfoData)));
        fileInfoData.setDownloadUrl(fdfsConstant.getDownloadServer() + storePath.getFullPath() + "?attname=" + uploadFileName);//下载地址
        fileInfoData.setPath(storePath.getFullPath());
        fileInfoData.setDelParam(new EncryptDes(desSeed).Encrypt(String.valueOf(insertRecord(fileInfoData))));//删除链接
        sug.addSugKey(metaData, uploadFileName);

        return fileInfoData;
    }

    @Override
    public FileInfoData uploadB64File(String b64, String uploadFileName, Integer author) throws Exception {
        Assert.hasText(uploadFileName, "文件名不能为空");
        Assert.hasText(b64, "base64不能为空");
        byte[] b = Base64Utils.decodeFromString(b64);
        try (InputStream ins = new ByteArrayInputStream(b)) {
            return uploadFile(ins, uploadFileName, b.length,author);
        }
    }

    @Override
    public FileInfoData uploadB64File(String b64, String uploadFileName) throws Exception {
        return uploadB64File(b64,uploadFileName,0);
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
    public void deleteFile(String delParam)throws Exception{
        Integer id = Integer.valueOf(new EncryptDes(desSeed).Decrypt(delParam));
        FileInfo fileInfo = fileInfoMapper.queryFileInfoLimit1(FileInfo.QueryBuild().id(id));
        if(fileInfo != null) {
            logicalDelete(fileInfo.getId());
            client.deleteFile(fileInfo.getPath());
        }
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
     * 生成元数据
     *
     * @return
     */
    protected Set<MetaData> genMeta(FileInfoData fileInfoData) {
        Set<MetaData> metaDataSet = new HashSet<>();
        metaDataSet.add(new MetaData(Meta.FILENAME, fileInfoData.getFilename()));
        return metaDataSet;
    }

    /**
     * 获取文件存储名称
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

    /**
     * 添加一条记录
     * @param fileInfoData
     */
    protected int insertRecord(FileInfoData fileInfoData){
        FileInfo $fileInfo = new FileInfo();
        $fileInfo.setAuthor(fileInfoData.getAuthor());
        $fileInfo.setFilename(fileInfoData.getFilename());
        $fileInfo.setPath(fileInfoData.getPath());
        $fileInfo.setSize(fileInfoData.getSize());
        $fileInfo.setTimestamp(fileInfoData.getTimestamp());
        fileInfoMapper.insertFileInfo($fileInfo);
        return $fileInfo.getId();
    }

    /**
     * 逻辑删除
     * @param id
     */
    protected void logicalDelete(int id){
        FileInfo fileInfo = new FileInfo();
        fileInfo.setIsdel(FileServiceConstant.DelStatus.del);
        fileInfoMapper.update(new FileInfo.UpdateBuilder().set(fileInfo).where(FileInfo.ConditionBuild().idList(id)).build());
    }

}
