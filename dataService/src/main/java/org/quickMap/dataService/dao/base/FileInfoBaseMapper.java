package org.quickMap.dataService.dao.base;

import java.util.List;

import org.quickMap.dataService.dao.model.FileInfo;
/**
*  @author author
*/
public interface FileInfoBaseMapper {

    int insertFileInfo(FileInfo object);

    int updateFileInfo(FileInfo object);

    int update(FileInfo.UpdateBuilder object);

    List<FileInfo> queryFileInfo(FileInfo object);

    FileInfo queryFileInfoLimit1(FileInfo object);

}