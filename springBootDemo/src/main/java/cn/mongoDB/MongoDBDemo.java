package cn.mongoDB;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import cn.Util;
import cn.mongoDB.collection.GetCollection;
import cn.mongoDB.connection.GetMongoDBConnection;
import cn.mongoDB.document.DeleteDocument;
import cn.mongoDB.document.InsertDocument;
import cn.mongoDB.document.QueryDocument;
import cn.mongoDB.document.UpdateDocument;

/**
 * @author changle 
 * Create Time: 2019年4月25日 
 * Purpose:
 */

public class MongoDBDemo {

    public static void main(String[] args) {
        MongoDatabase mongoDatabase = GetMongoDBConnection.getConnection("localhost", 27017, "test");
        
//        CreateCollection.createCollection(mongoDatabase, "test");
        
        //获取集合
        MongoCollection<Document> collection=  GetCollection.getCollection(mongoDatabase, "test");
        
        //插入文档
        InsertDocument.insertDocument(collection);
        
        //查询
        QueryDocument.queryDocument(collection);

        //更新
        UpdateDocument.updateDocument(collection);
        
        //删除
        DeleteDocument.deleteDocument(collection);
        
        Util.print("main执行完成");
    }

   

}
