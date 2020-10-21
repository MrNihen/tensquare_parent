package com.zelin.test;


import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Creat by nihen on 2020/10/21 18:01
 */

public class TestMongoDb {
    private MongoClient mongoClient;
    private MongoCollection<Document> collection;
    @Before
    public void init(){
        //1.得到mongo的客户端
        mongoClient = new MongoClient("192.168.25.130");
        //2.得到mongo的数据库
        MongoDatabase spitdb = mongoClient.getDatabase("spitdb");
        //3.得到集合（表）
        collection = spitdb.getCollection("spit");
    }

    //1.查询所有
    @Test
    public void test01(){
        //1.1)查询所有的文档
        FindIterable<Document> documents = collection.find();
        //1.2)打印文档内容
        printInfo(documents);
        //1.3)关闭客户端
        mongoClient.close();
    }

    //2.条件查询
    @Test
    public void test02(){
        //2.1)根据userid查询记录
        Bson bson = new BasicDBObject("userid","1013");
        //2.2)得到查询结果
        FindIterable<Document> documents = collection.find(bson);
        //2.3)打印信息
        printInfo(documents);
        //2.4)关闭客户端
        mongoClient.close();
    }

    //3.条件查询二（浏览量大于1000）
    @Test
    public void test03(){
        //3.1)构造查询条件
        Bson bson = new BasicDBObject("visits",new BasicDBObject("$gt",1000));
        //3.2)得到查询结果
        FindIterable<Document> documents =   collection.find(bson);
        //3.3)打印信息
        printInfo(documents);
        //3.4)关闭客户端
        mongoClient.close();
    }

    //4.添加数据
    @Test
    public void test04(){
        //4.1)定义要添加的数据map
        Map<String, Object> map = new HashMap<>();
        map.put("_id","8");
        map.put("content","这是一个二货3");
        map.put("userid","1015");
        map.put("nickname","柠檬精3");
        map.put("visits",1003);
        map.put("parentid","5");
        //4.2)要添加的文档对象（相当于一条记录）
        Document doc = new Document(map);
        //4.3)添加到mongo数据库
        collection.insertOne(doc);
        //4.4)关闭客户端
        mongoClient.close();
    }
    //打印集合的内容
    private void printInfo(FindIterable<Document> documents) {
        for (Document doc : documents) {
            Object id = doc.get("_id");                         //_id
            String content = (String) doc.get("content");       //content
            String userid = (String) doc.get("userid");         //userid
            String nickname = (String) doc.get("nickname");     //nickname
            Integer visits = (Integer) doc.get("visits");       //visits
            System.out.println("id:" + id);
            System.out.println("content:" + content);
            System.out.println("userid:" + userid);
            System.out.println("nickname:" + nickname);
            System.out.println("visits:" + visits);
            System.out.println("--------------------------------------------------------");
        }
    }

}
