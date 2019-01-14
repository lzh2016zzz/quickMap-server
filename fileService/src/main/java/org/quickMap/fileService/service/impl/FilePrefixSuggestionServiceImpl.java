package org.quickMap.fileService.service.impl;

import com.github.tobato.fastdfs.domain.MetaData;
import io.redisearch.Schema;
import io.redisearch.Suggestion;
import io.redisearch.client.AutoCompleter;
import io.redisearch.client.Client;
import io.redisearch.client.SuggestionOptions;
import org.quickMap.constant.FileServiceConstant.Meta;
import org.quickMap.fileService.service.IFilePrefixSuggestionService;
import org.quickMap.dataService.cfg.RedisConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisDataException;

import java.util.*;
@Service
public class FilePrefixSuggestionServiceImpl implements IFilePrefixSuggestionService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public static final int maxSuggestions = 15;

    @Autowired
    private RedisTemplate<String, Boolean> redisTemplate;

    private RedisConstant config;

    private ThreadLocal<Client> clientThreadLocal = new ThreadLocal<>();

    private JedisPool jedisPool;

    @Autowired
    public FilePrefixSuggestionServiceImpl(RedisConstant config) {
        this.config = config;
        init();
    }


    private void init() {
        //单机模式初始化
        Client rediSearchClient = new Client(config.getFsIndexName(), config.getHost(), config.getPort(), config.getTimeout(), config.getPoolSize(), config.getPassword());
        try {
            synchronized (this) {
                createIndex(rediSearchClient);
            }
        } catch (JedisDataException e) {

        }
    }

    private void createIndex(Client rediSearchClient) {
        synchronized (this) {
            Schema schema = new Schema().addTextField(Meta.FILENAME, 5.0).addNumericField(Meta.TIMESTAMP);
            rediSearchClient.createIndex(schema, Client.IndexOptions.Default());
        }
    }

    public void initSugKeys(Set<String> keys, boolean rebuild) {
        synchronized (this) {
            logger.info("初始化自动补全服务..");
            if (keys == null || keys.size() == 0) {
                logger.error("字段数量为0,取消初始化..");
                return;
            }
            if (rebuild) {
                Client client = getRediSearchClient();
                client.dropIndex(true);
                createIndex(client);
                logger.info("重置自动补全数据..");
            }
            int length = 0;
            for (Iterator<String> it = keys.iterator(); it.hasNext(); ) {
                addSugKey(null, it.next());
                ++length;
            }
            logger.info("初始化成功,共{}个字符串.", length);
        }
    }


    @Override
    public void addSugKey(Set<MetaData> metaData, String fileName) {
        Assert.hasText(fileName, "字段不能为空");
        getRediSearchClient().addSuggestion(Suggestion.builder().str(fileName).build(), true);
    }


    @Override
    public void deleteSugKey(String fileName) {
        Assert.hasText(fileName, "字段不能为空");
        try (Jedis conn = getJedisPool().getResource()) {
            BinaryClient client = conn.getClient();
            client.sendCommand(AutoCompleter.Command.SUGDEL, config.getFsIndexName(), fileName);
            //logger.info("删除key结果:{}" + client.getStatusCodeReply());
        }
    }

    @Override
    public List<Suggestion> getSuggestions(String prefix) {
        Assert.hasText(prefix, "前缀不能为空");
        if (prefix.length() < 2) {
            return Collections.emptyList();
        }
        return getRediSearchClient().getSuggestion(prefix, SuggestionOptions.builder().fuzzy().max(maxSuggestions).build());
    }

    /**
     * 初始化jedis连接池
     *
     * @return
     */
    private JedisPool getJedisPool() {
        try {
            if (jedisPool == null) {
                synchronized (this) {
                    JedisPoolConfig conf = initPoolConfig(config.getPoolSize());
                    jedisPool = new JedisPool(conf, config.getHost(), config.getPort(), config.getTimeout(), config.getPassword());
                }
            }
            return jedisPool;
        } catch (Exception e) {
            throw new NullPointerException("获取jedis连接池异常");
        }
    }

    /**
     * 初始化jedis配置
     *
     * @param poolSize
     * @return
     */
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
     * 注意 : Client 对象不是线程安全的.
     *
     * @return
     */
    private Client getRediSearchClient() {
        if (clientThreadLocal.get() == null) {
            clientThreadLocal.set(new Client(config.getFsIndexName(), config.getHost(), config.getPort(), config.getTimeout(), config.getPoolSize(), config.getPassword()));
        }
        return clientThreadLocal.get();
    }

}
