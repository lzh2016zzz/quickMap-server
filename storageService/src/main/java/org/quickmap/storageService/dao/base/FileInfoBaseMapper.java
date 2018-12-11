package org.quickmap.storageService.dao.base;

import org.quickmap.storageService.dao.model.FileInfo;

import java.util.List;
/**
*  @author author
*/
public interface FileInfoBaseMapper {

    int insertFileInfo(FileInfo object);

    int updateFileInfo(FileInfo object);

    List<FileInfo> queryFileInfo(FileInfo object);

    FileInfo queryFileInfoLimit1(FileInfo object);

}