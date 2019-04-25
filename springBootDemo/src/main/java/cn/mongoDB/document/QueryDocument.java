package cn.mongoDB.document;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;

/**
 * @author changle 
 * Create Time: 2019年4月25日 
 * Purpose:
 */

public class QueryDocument {
    
    /**
     *@author changle
     *Create Time: 2019年4月25日 
     *Purpose:检索所有文档  
     * @param collectionName TODO
     */
    public static MongoCollection<Document> queryDocument(MongoCollection<Document> collection) {
        collection.find(eq("status", "D"));
        FindIterable<Document> findIterable = collection.find();  
        MongoCursor<Document> mongoCursor = findIterable.iterator();  
        while(mongoCursor.hasNext()){  
           System.out.println(mongoCursor.next());  
        }  
        return collection;
    }
}
