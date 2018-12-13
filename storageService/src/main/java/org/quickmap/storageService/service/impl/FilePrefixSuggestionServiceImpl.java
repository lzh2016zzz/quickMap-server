package org.quickmap.storageService.service.impl;

import com.github.tobato.fastdfs.domain.MetaData;
import io.redisearch.Schema;
import io.redisearch.Suggestion;
import io.redisearch.client.AutoCompleter;
import io.redisearch.client.Client;
import io.redisearch.client.SuggestionOptions;
import org.quickmap.storageService.cfg.RedisConstant;
import org.quickmap.storageService.service.IFilePrefixSuggestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisDataException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static javax.script.ScriptEngine.FILENAME;
import static org.quickMap.constant.FileServiceConstant.Meta.TIMESTAMP;

@Service
public class FilePrefixSuggestionServiceImpl implements IFilePrefixSuggestionService {

    Logger logger = LoggerFactory.getLogger(FilePrefixSuggestionServiceImpl.class);

    @Autowired


    public static final int maxSuggestions = 5;

    private RedisConstant config;

    @Autowired
    public FilePrefixSuggestionServiceImpl(RedisConstant config) {
        this.config = config;
        init();
    }


    private void init() {
        //单机模式初始化
        logger.info("正在初始化自动补全服务..");
        Client rediSearchClient = new Client(config.getFsIndexName(), config.getHost(), config.getPort(), config.getTimeout(), config.getPoolSize(), config.getPassword());
        Schema schema = new Schema().addTextField(FILENAME, 5.0).addNumericField(TIMESTAMP);
        try {
            rediSearchClient.dropIndex(true);
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
        try (Jedis conn = getJedisPool().getResource()) {
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


    private JedisPool getJedisPool() {
        try {
            JedisPoolConfig conf = initPoolConfig(config.getPoolSize());
           return new JedisPool(conf, config.getHost(), config.getPort(),config.getTimeout(),config.getPassword());
        } catch (Exception e){
            throw new NullPointerException("获取jedis连接池异常");
        }
    }

    private JedisPoolConfig initPoolConfig(int poolSize) {
        JedisPoolConfig conf = new JedisPoolConfig();
        conf.setMaxTotal(poolSize);
        conf.setTestOnBorrow(false);
        conf.setTestOnReturn(false);
        conf.setTestOnCreate(false);
        conf.setTestWhileIdle(false);
        conf.setMinEvictableIdleTimeMillis(60000);
        conf.setTimeBetweenEvictionRunsMillis(30000);
        conf.setNumTestsPerEvictionRun(-1);
        conf.setFairness(true);

        return conf;
    }

    /**
     *
     * 注意 : Client 对象不是线程安全的.
     *
     * @return
     */
    private Client getRediSearchClient(){
        return new Client(config.getFsIndexName(), config.getHost(), config.getPort(), config.getTimeout(), config.getPoolSize(), config.getPassword());
    }

}
