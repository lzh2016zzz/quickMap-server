package org.quickMap.dataService.dao;

import org.quickMap.dataService.dao.base.FileInfoBaseMapper;

import java.util.List;

/**
*  @author author
*/
public interface FileInfoMapper extends FileInfoBaseMapper{

    List<String> queryFileNameSet();


}