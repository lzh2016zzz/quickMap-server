package org.quickMap.fileServer;

import io.redisearch.Document;
import io.redisearch.Query;
import io.redisearch.Schema;
import io.redisearch.SearchResult;
import io.redisearch.client.Client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {

    public static void main(String[] args) {
        // 初始化客户端
        Client client = new Client("any-search", "192.168.1.166", 6379);

        // 定义一个索引模式
        Schema schema = new Schema().addTextField("title", 5.0).addTextField("body", 1.0).addNumericField("star");

        // 创建索引
        client.dropIndex();
        client.createIndex(schema, Client.IndexOptions.Default());

        // 向索引中添加文档
        Map<String, Object> fields1 = createDocument("any video", "视频", 1000);
        Map<String, Object> fields2 = createDocument("any chat", "即时通信", 500);
        Map<String, Object> fields3 = createDocument("any security", "安全", 10000);
        Map<String, Object> fields4 = createDocument("any video github", "视频项目", 10000);

        client.addDocument("doc1", fields1);
        client.addDocument("doc2", fields2);
        client.addDocument("doc3", fields3);
        client.addDocument("doc4", fields4);

        // 搜索 "any", 并且 star 数目在 0 ~ 2000 的
        System.out.println("search 'any', result:");
        Query query = new Query("any").addFilter(new Query.NumericFilter("star", 0, 2000)).setWithScores().limit(0, 10);
        SearchResult result = client.search(query);
        printResult(result);

        // 搜索 "视频"
        System.out.println("\r\nsearch '视频', result:");
        Query other = new Query("视频").limitFields("body").limit(0, 10);
        result = client.search(other);
        printResult(result);
    }

    private static Map<String, Object> createDocument(String title, String body, Integer price) {
        Map<String, Object> fields = new HashMap<String, Object>();
        fields.put("title", title);
        fields.put("body", body);
        fields.put("star", price);
        return fields;
    }

    private static void printResult(SearchResult result) {
        List<Document> documentList = result.docs;
        for (Document document : documentList) {
            System.out.println(document.toString());
        }
    }

}