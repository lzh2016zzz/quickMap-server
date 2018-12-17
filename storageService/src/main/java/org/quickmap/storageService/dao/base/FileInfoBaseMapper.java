package org.quickmap.storageService.dao.base;

import org.quickmap.storageService.dao.model.FileInfo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
*  @author author
*/
@CacheConfig(cacheNames = "fileInfos")
public interface FileInfoBaseMapper {

    @CacheEvict(allEntries = true)
    int insertFileInfo(FileInfo object);

    @CacheEvict(allEntries = true)
    int updateFileInfo(FileInfo object);

    @CacheEvict(allEntries = true)
    int update(FileInfo.UpdateBuilder object);

    @Cacheable()
    List<FileInfo> queryFileInfo(FileInfo object);

    @Cacheable()
    FileInfo queryFileInfoLimit1(FileInfo object);

}