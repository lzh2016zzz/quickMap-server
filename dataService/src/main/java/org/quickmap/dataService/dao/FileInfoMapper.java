package org.quickmap.dataService.dao;

import org.quickmap.dataService.dao.base.FileInfoBaseMapper;

import java.util.List;

/**
*  @author author
*/
public interface FileInfoMapper extends FileInfoBaseMapper{

    List<String> queryFileNameSet();


}