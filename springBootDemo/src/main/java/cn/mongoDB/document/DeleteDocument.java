/**
 * springBootDemo
 * @author changle
 * Create Time: 2019年4月25日 
 * Modified Time:
 * Modified by: 
 * Version: 1.0
 */
package cn.mongoDB.document;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

/**
 * @author changle 
 * Create Time: 2019年4月25日 
 * Purpose:
 */

public class DeleteDocument {

    /**
     *@author changle
     *Create Time: 2019年4月25日 
     *Purpose:
     * @param collectionName TODO
     */
    public static void deleteDocument(MongoCollection<Document> collection) {

        //删除符合条件的第一个文档  
        collection.deleteOne(Filters.eq("likes", 200));  
        //删除所有符合条件的文档  
        collection.deleteMany (Filters.eq("likes", 200));  
        //检索查看结果  
        FindIterable<Document> findIterable = collection.find();  
        MongoCursor<Document> mongoCursor = findIterable.iterator();  
        while(mongoCursor.hasNext()){  
          System.out.println(mongoCursor.next());  
        }  
    }
}
