package org.quickMap.fileService.service;

import com.github.tobato.fastdfs.domain.StorePath;
import org.quickMap.fileService.model.FileInfoData;

import java.io.InputStream;
import java.util.List;

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
     * @param genThumbImage 生成图片缩略图
     * @return
     * @throws Exception
     */
    FileInfoData uploadFile(InputStream file, String uploadFileName, long fileLength,boolean genThumbImage) throws Exception;

    /**
     * 上传文件
     *
     * @param file           文件输入流
     * @param uploadFileName 文件名称
     * @param fileLength     文件长度
     * @param author         用户id
     * @param genThumbImage 生成图片缩略图
     * @return
     * @throws Exception
     */
    FileInfoData uploadFile(InputStream file, String uploadFileName, long fileLength, Integer author,boolean genThumbImage) throws Exception;

    /**
     * 上传base64
     *
     * @param b64            base64
     * @param uploadFileName 文件名称
     * @param genThumbImage 生成图片缩略图
     * @return
     * @throws Exception
     */
    FileInfoData uploadB64File(String b64, String uploadFileName,boolean genThumbImage) throws Exception;

    /**
     * 上传base64
     *
     * @param b64            base64
     * @param uploadFileName 文件名称
     * @param author         用户id
     * @param genThumbImage 生成图片缩略图
     * @return
     * @throws Exception
     */
    FileInfoData uploadB64File(String b64, String uploadFileName, Integer author,boolean genThumbImage) throws Exception;

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

    /**
     * 根据名称查找
     * @param name
     * @param author
     * @return
     * @throws Exception
     */
    List<FileInfoData> searchByFileName(String name, Integer author)throws Exception;

    /**
     * 初始化自动补全文本
     * @param rebuild
     * @throws Exception
     */
    void initFileNameSearchText(boolean rebuild)throws Exception;
}
