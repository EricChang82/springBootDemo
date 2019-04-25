package cn.mongoDB.connection;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * @author changle 
 * Create Time: 2019年4月25日 
 * Purpose:
 */

public class GetMongoDBConnection {

    /**
     *@author changle
     *Create Time: 2019年4月25日 
     *Purpose:获得连接
     * @param ipAddress TODO
     * @param port TODO
     * @param collection TODO
     */
    public static MongoDatabase getConnection(String ipAddress, int port, String collection) {
        // 连接到 mongodb 服务
        MongoClient mongoClient = new MongoClient(ipAddress, port);
        // 连接到数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase(collection);
        
        return mongoDatabase;

    }

}
