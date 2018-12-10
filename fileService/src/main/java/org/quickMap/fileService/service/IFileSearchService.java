package org.quickMap.fileService.service;

import com.github.tobato.fastdfs.domain.MetaData;
import com.github.tobato.fastdfs.domain.StorePath;
import io.redisearch.SearchResult;
import io.redisearch.Suggestion;

import java.util.List;
import java.util.Set;

/**
 * 文件搜索
 */
public interface IFileSearchService {

    /**
     * 根据文件名查找
     *
     * @param originalName
     */
    SearchResult findByOriginalName(String originalName);

    /**
     * 添加记录
     *
     * @param metaData  元数据
     * @param storePath 存储地址
     */
    void addRecord(Set<MetaData> metaData, StorePath storePath) throws Exception;

    /**
     * 删除
     *
     * @param id
     */
    void delete(String id);

    /**
     * 获取自动补全建议
     *
     * @param prefix 前缀
     * @return
     */
    List<Suggestion> getSuggestions(String prefix);

}
