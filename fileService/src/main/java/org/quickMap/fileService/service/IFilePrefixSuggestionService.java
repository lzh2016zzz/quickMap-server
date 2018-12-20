package org.quickMap.fileService.service;

import com.github.tobato.fastdfs.domain.MetaData;
import io.redisearch.Suggestion;

import java.util.List;
import java.util.Set;

/**
 * 自动补全建议
 */
public interface IFilePrefixSuggestionService {

    /**
     * 添加记录
     *
     * @param metaData  元数据
     * @param fileName key
     */
    void addSugKey(Set<MetaData> metaData, String fileName) throws Exception;

    /**
     * 删除一条记录
     *
     * @param fileName 文件名称
     */
    void deleteSugKey(String fileName);

    /**
     * 获取自动补全建议
     *
     * @param prefix 前缀
     * @return
     */
    List<Suggestion> getSuggestions(String prefix);

    /**
     * 初始化自动补全数据
     * @param keys 值
     * @param rebuild true : 替换 false :添加
     */
    void initSugKeys(Set<String> keys, boolean rebuild);
}
