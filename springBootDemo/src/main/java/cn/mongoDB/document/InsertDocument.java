/**
 * springBootDemo
 * @author changle
 * Create Time: 2019年4月25日 
 * Modified Time:
 * Modified by: 
 * Version: 1.0
 */
package cn.mongoDB.document;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

/**
 * @author changle 
 * Create Time: 2019年4月25日 
 * Purpose:
 */

public class InsertDocument {

    /**
     *@author changle
     *Create Time: 2019年4月25日 
     *Purpose:
     * @param collectionName TODO
     */
    public static void insertDocument(MongoCollection<Document> collection) {

        Document document = new Document("title", "MongoDB").append("description", "database").append("likes", 100).append("by", "Fly");
        List<Document> documents = new ArrayList<Document>();
        documents.add(document);
        collection.insertMany(documents);
    }
}
