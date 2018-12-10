package org.quickMap.fileService.service.impl;

import com.github.tobato.fastdfs.domain.MetaData;
import com.github.tobato.fastdfs.domain.StorePath;
import io.redisearch.Query;
import io.redisearch.Schema;
import io.redisearch.SearchResult;
import io.redisearch.Suggestion;
import io.redisearch.client.AutoCompleter;
import io.redisearch.client.Client;
import io.redisearch.client.SuggestionOptions;
import org.quickMap.Utils.FileOperatorUtil;
import org.quickMap.constant.FileServiceConstant;
import org.quickMap.fileService.constant.RedisConfig;
import org.quickMap.fileService.service.IFileSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisDataException;

import java.lang.reflect.Field;
import java.util.*;

import static org.quickMap.constant.FileServiceConstant.Meta.FILENAME;
import static org.quickMap.constant.FileServiceConstant.Meta.TIMESTAMP;


@Service()
/**
 * api 文档参考:
 * https://oss.redislabs.com/redisearch/Commands/
 */
public class FileSearchServiceImpl implements IFileSearchService {

    public static final int maxSuggestions = 5;
    private Client rediSearchClient;
    private JedisPool jedisPool;

    private RedisConfig config;


    @Autowired
    public FileSearchServiceImpl(RedisConfig config) {
        this.config = config;
        init();
    }

    private void init() {
        if (rediSearchClient == null) {
            //单机模式初始化
            rediSearchClient = new Client(config.getIndexName(), config.getHost(), config.getPort(), config.getTimeout(), config.getPoolSize(), config.getPassword());
            setJedisPool(rediSearchClient);
            Schema schema = new Schema().addTextField(FILENAME, 5.0).addNumericField(FileServiceConstant.Meta.TIMESTAMP);
            try {
                rediSearchClient.createIndex(schema, Client.IndexOptions.Default());
            } catch (JedisDataException e) {

            }
        }
    }


    public SearchResult findByOriginalName(String originalName) {
        return findByOriginalName(originalName, 0, 10);
    }


    public SearchResult findByOriginalName(String originalName, int offset, int limit) {
        Query query = new Query(originalName).limitFields(FILENAME).returnFields(FILENAME, TIMESTAMP).limit(offset <= 0 ? 0 : offset, limit <= 0 ? 10 : limit);
        SearchResult searchResult;
        if ((searchResult = rediSearchClient.search(query)).totalResults == 0) {
            deleteSuggest(originalName);
        }
        return searchResult;
    }

    public void addRecord(Set<MetaData> metaData, StorePath storePath) throws Exception {
        Map<String, Object> objectMap = createObject(metaData);
        rediSearchClient.addDocument(storePath.getGroup().concat("/").concat(FileOperatorUtil.encodePath(storePath.getPath())), objectMap);
        rediSearchClient.addSuggestion(Suggestion.builder().str((String) objectMap.get(FILENAME)).build(), true);
    }


    public void delete(String id) {
        rediSearchClient.deleteDocument(id);
    }

    @Override
    public List<Suggestion> getSuggestions(String prefix) {
        Assert.hasText(prefix, "前缀不能为空");
        if (prefix.length() < 2) {
            return new ArrayList<>();
        }
        return rediSearchClient.getSuggestion(prefix, SuggestionOptions.builder().fuzzy().max(maxSuggestions).build());
    }

    protected void setJedisPool(Client client) {
        Field field;
        try {
            field = client.getClass().getDeclaredField("pool");
            field.setAccessible(true);
            this.jedisPool = (JedisPool) field.get(client);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }


    /**
     * 删除对应的补全记录
     *
     * @param key
     */
    protected void deleteSuggest(String key) {
        try (Jedis conn = jedisPool.getResource()) {
            BinaryClient client = conn.getClient();
            client.sendCommand(AutoCompleter.Command.SUGDEL, config.getIndexName(), key);
        }
    }

    /**
     * 创建存储对象
     *
     * @param metadatas
     * @return
     */
    protected HashMap<String, Object> createObject(Set<MetaData> metadatas) {
        HashMap<String, Object> map = new HashMap<>();
        MetaData meta;
        for (Iterator<MetaData> it = metadatas.iterator(); it.hasNext(); ) {
            meta = it.next();
            map.put(meta.getName().intern(), meta.getValue());
        }
        return map;
    }
}
