package org.quickmap.storageService.dao;

import org.quickmap.storageService.dao.base.FileInfoBaseMapper;

import java.util.List;

/**
*  @author author
*/
public interface FileInfoMapper extends FileInfoBaseMapper{

    List<String> queryFileNameSet();
}