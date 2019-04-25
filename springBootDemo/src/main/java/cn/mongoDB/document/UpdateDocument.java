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

public class UpdateDocument {
    
    /**
     *@author changle
     *Create Time: 2019年4月25日 
     *Purpose:
     * @param collectionName TODO
     */
    public static MongoCollection<Document> updateDocument(MongoCollection<Document> collection) {
        //更新文档   将文档中likes=100的文档修改为likes=200   
        collection.updateMany(Filters.eq("likes", 100), new Document("$set",new Document("likes",200)));  
        //检索查看结果  
        FindIterable<Document> findIterable = collection.find();  
        MongoCursor<Document> mongoCursor = findIterable.iterator();  
        while(mongoCursor.hasNext()){  
           System.out.println(mongoCursor.next());  
        }  
        return collection;
    }
}
