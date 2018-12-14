package org.quickmap.storageService.dao.base;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.quickmap.storageService.dao.model.FileInfo;
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