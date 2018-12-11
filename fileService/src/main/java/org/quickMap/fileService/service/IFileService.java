package org.quickMap.fileService.service;

import com.github.tobato.fastdfs.domain.StorePath;
import org.quickMap.fileService.model.FileInfoData;

import java.io.InputStream;

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
    FileInfoData uploadFile(InputStream file, String uploadFileName, long fileLength) throws Exception;

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
    FileInfoData uploadFile(InputStream file, String uploadFileName, long fileLength, Integer author) throws Exception;

    /**
     * 上传base64
     *
     * @param b64            base64
     * @param uploadFileName 文件名称
     * @return
     * @throws Exception
     */
    FileInfoData uploadB64File(String b64, String uploadFileName) throws Exception;

    /**
     * 上传base64
     *
     * @param b64            base64
     * @param uploadFileName 文件名称
     * @param author         用户id
     * @return
     * @throws Exception
     */
    FileInfoData uploadB64File(String b64, String uploadFileName, Integer author) throws Exception;

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
     * @param delParam
     * @throws Exception
     */
    void deleteFile(String delParam) throws Exception;


}
