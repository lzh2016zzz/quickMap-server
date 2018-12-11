package org.quickMap.fileService.service;

import com.github.tobato.fastdfs.domain.MetaData;
import com.github.tobato.fastdfs.domain.StorePath;
import org.quickMap.fileService.model.FileInfo;

import java.io.InputStream;
import java.util.Set;

/**
 * 文件服务
 */
public interface IFileService {

    /**
     * 上传文件
     *
     * @param file           文件输入流
     * @param uploadFileName 文件名称
     * @param fileLength     文件长度
     * @return
     * @throws Exception
     */
    FileInfo uploadFile(InputStream file, String uploadFileName, long fileLength) throws Exception;

    /**
     * 上传文件
     *
     * @param file           文件输入流
     * @param uploadFileName 文件名称
     * @param fileLength     文件长度
     * @param author         用户id
     * @return
     * @throws Exception
     */
    FileInfo uploadFile(InputStream file, String uploadFileName, long fileLength, String author) throws Exception;

    /**
     * 上传base64
     *
     * @param b64            base64
     * @param uploadFileName 文件名称
     * @return
     * @throws Exception
     */
    FileInfo uploadB64File(String b64, String uploadFileName) throws Exception;

    /**
     * 上传base64
     *
     * @param b64            base64
     * @param uploadFileName 文件名称
     * @param author         用户id
     * @return
     * @throws Exception
     */
    FileInfo uploadB64File(String b64, String uploadFileName,String author) throws Exception;

    /**
     * 下载文件
     *
     * @param storePath
     * @return
     * @throws Exception
     */
    byte[] downloadFile(StorePath storePath) throws Exception;

    /**
     * 下载文件
     *
     * @param fullPath
     * @return
     * @throws Exception
     */
    byte[] downloadFile(String fullPath) throws Exception;

    /**
     * 删除文件
     *
     * @param storePath
     * @throws Exception
     */
    void deleteFile(StorePath storePath) throws Exception;


    /**
     * 删除文件
     *
     * @param fullPath
     * @throws Exception
     */
    void deleteFile(String fullPath) throws Exception;
}
