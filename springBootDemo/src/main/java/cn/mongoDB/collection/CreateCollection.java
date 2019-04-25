/**
 * springBootDemo
 * @author changle
 * Create Time: 2019年4月25日 
 * Modified Time:
 * Modified by: 
 * Version: 1.0
 */
package cn.mongoDB.collection;

import com.mongodb.client.MongoDatabase;

/**
 * @author changle 
 * Create Time: 2019年4月25日 
 * Purpose:
 */

public class CreateCollection {
    
    /**
     *@author changle
     *Create Time: 2019年4月25日 
     *Purpose:
     * @param collectionName TODO
     */
    public static void createCollection(MongoDatabase mongoDatabase, String collectionName) {
        mongoDatabase.createCollection(collectionName);
    }
}
