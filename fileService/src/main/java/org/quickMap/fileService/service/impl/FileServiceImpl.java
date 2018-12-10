package org.quickMap.fileService.service.impl;

import com.github.tobato.fastdfs.domain.MetaData;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.codec.digest.DigestUtils;
import org.quickMap.constant.FileServiceConstant.Meta;
import org.quickMap.fileService.model.UploadB64;
import org.quickMap.fileService.service.IFileSearchService;
import org.quickMap.fileService.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class FileServiceImpl implements IFileService {

    @Autowired
    protected FastFileStorageClient storageClient;

    @Autowired
    protected IFileSearchService fileSearchService;


    public StorePath uploadFile(InputStream file, String uploadFileName, long fileLength) throws Exception {
        return uploadFile(file, uploadFileName, fileLength, "sys");
    }

    public StorePath uploadFile(InputStream file, String uploadFileName, long fileLength, String userId) throws Exception {
        Assert.hasText(uploadFileName, "文件名不能为空");
        Assert.notNull(file, "文件不能为空");
        Set<MetaData> metaData;
        StorePath storePath = storageClient.uploadFile(file, fileLength, genStoreFileName(uploadFileName), (metaData = genMeta(userId, uploadFileName)));
        fileSearchService.addRecord(metaData, storePath);
        return storePath;
    }

    public UploadB64 uploadB64File(String b64, String uploadFileName) throws Exception {
        Assert.hasText(uploadFileName, "文件名不能为空");
        Assert.hasText(b64, "base64不能为空");
        byte[] b = Base64Utils.decodeFromString(b64);
        try (InputStream ins = new ByteArrayInputStream(b)) {
            StorePath storePath = uploadFile(ins, uploadFileName, b.length);
            UploadB64 uploadB64 = new UploadB64();
            uploadB64.setLength(b.length);
            uploadB64.setStorePath(storePath);
            return uploadB64;
        }
    }

    public byte[] downloadFile(String fullPath) {
        Assert.hasText(fullPath, "完整路径不能为空");
        return downloadFile(getStorePath(fullPath));
    }

    public byte[] downloadFile(StorePath storePath) {
        Assert.notNull(storePath, "存储路径不能为空");
        return storageClient.downloadFile(storePath.getGroup(), storePath.getPath(), new DownloadByteArray());
    }

    public Set<MetaData> getMeta(StorePath storePath) {
        Assert.notNull(storePath, "存储路径不能为空");
        return storageClient.getMetadata(storePath.getGroup(), storePath.getPath());
    }

    public Set<MetaData> getMeta(String fullPath) {
        Assert.hasText(fullPath, "完整路径不能为空");
        return getMeta(getStorePath(fullPath));
    }

    public String getOriginalFileName(String fullPath) {
        Assert.hasText(fullPath, "完整路径不能为空");
        return getOriginalFileName(getStorePath(fullPath));
    }

    public String getOriginalFileName(StorePath storePath) {
        Assert.notNull(storePath, "存储路径不能为空");
        Set<MetaData> metaDataSet;
        if ((metaDataSet = getMeta(storePath)) == null) {
            return null;
        }
        Optional<MetaData> metaData;
        if ((metaData = metaDataSet.stream().filter(meta -> Meta.FILENAME.equals(meta.getName())).findFirst()).isPresent()) {
            return metaData.get().getValue();
        }
        return null;
    }


    public void deleteFile(StorePath storePath) {
        Assert.notNull(storePath, "存储路径不能为空");
        Optional<MetaData> metaData = storageClient.getMetadata(storePath.getGroup(), storePath.getPath()).stream().filter(e -> Meta.FILENAME.equals(e.getName())).findFirst();
        if (metaData.isPresent()) {
            storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
            fileSearchService.delete(storePath.getFullPath());
        }
    }

    public void deleteFile(String fullPath) {
        Assert.hasText(fullPath, "路径不能为空");
        deleteFile(getStorePath(fullPath));
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
        String path = fullPath.substring(i + 1, fullPath.length());
        StorePath storePath = new StorePath();
        storePath.setPath(path);
        storePath.setGroup(group);
        return storePath;
    }


    /**
     * 生成元数据
     *
     * @param author
     * @return
     */
    protected Set<MetaData> genMeta(String author, String originalFileName) {
        Set<MetaData> metaDataSet = new HashSet<>();
        metaDataSet.add(new MetaData(Meta.AUTHOR, author));
        metaDataSet.add(new MetaData(Meta.FILENAME, originalFileName));
        metaDataSet.add(new MetaData(Meta.TIMESTAMP, new BigDecimal(System.currentTimeMillis()).toPlainString()));
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

}
