package org.quickmap.storageService.service.impl;

import com.github.tobato.fastdfs.domain.MetaData;
import io.redisearch.Schema;
import io.redisearch.Suggestion;
import io.redisearch.client.AutoCompleter;
import io.redisearch.client.Client;
import io.redisearch.client.SuggestionOptions;
import org.quickmap.storageService.cfg.RedisConstant;
import org.quickmap.storageService.service.IFilePrefixSuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisDataException;

import java.lang.reflect.Field;
import java.util.*;

import static javax.script.ScriptEngine.FILENAME;
import static org.quickMap.constant.FileServiceConstant.Meta.TIMESTAMP;

@Component
public class FilePrefixSuggestionServiceImpl implements IFilePrefixSuggestionService {

    public static final int maxSuggestions = 5;

    private JedisPool jedisPool;

    private RedisConstant config;

    private Client rediSearchClient;


    @Autowired
    public FilePrefixSuggestionServiceImpl(RedisConstant config) {
        this.config = config;
        init();
    }


    private void init() {
        //单机模式初始化
        rediSearchClient = new Client(config.getFsIndexName(), config.getHost(), config.getPort(), config.getTimeout(), config.getPoolSize(), config.getPassword());
        setJedisPool(rediSearchClient);
        Schema schema = new Schema().addTextField(FILENAME, 5.0).addNumericField(TIMESTAMP);
        try {
            rediSearchClient.createIndex(schema, Client.IndexOptions.Default());
        } catch (JedisDataException e) {

        }
    }


    @Override
    public void addSugKey(Set<MetaData> metaData, String fileName){
        getRediSearchClient().addSuggestion(Suggestion.builder().str(fileName).build(), true);
    }


    @Override
    public void deleteSugKey(String fileName) {
        try (Jedis conn = jedisPool.getResource()) {
            BinaryClient client = conn.getClient();
            client.sendCommand(AutoCompleter.Command.SUGDEL, config.getFsIndexName(), fileName);
        }
    }

    @Override
    public List<Suggestion> getSuggestions(String prefix) {
        Assert.hasText(prefix, "前缀不能为空");
        if (prefix.length() < 2) {
            return new ArrayList<>();
        }
        return getRediSearchClient().getSuggestion(prefix, SuggestionOptions.builder().fuzzy().max(maxSuggestions).build());
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


    protected Client getRediSearchClient(){
        return this.rediSearchClient;
    }

}
