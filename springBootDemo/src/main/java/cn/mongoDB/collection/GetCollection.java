/**
 * springBootDemo
 * @author changle
 * Create Time: 2019年4月25日 
 * Modified Time:
 * Modified by: 
 * Version: 1.0
 */
package cn.mongoDB.collection;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * @author changle 
 * Create Time: 2019年4月25日 
 * Purpose:
 */

public class GetCollection {
    
    /**
     *@author changle
     *Create Time: 2019年4月25日 
     *Purpose:
     * @param collectionName TODO
     */
    public static MongoCollection<Document> getCollection(MongoDatabase mongoDatabase, String collectionName) {
        MongoCollection<Document> collection = mongoDatabase.getCollection("test");
        System.out.println("集合 test 选择成功");
        return collection;
    }
}
