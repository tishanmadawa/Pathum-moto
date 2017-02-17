/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tml.pathummoto.Dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.tml.pathummoto.model.User;


public class UserDao {

    public void signup(User user) {

        try {

            // To connect to mongodb server
            MongoClient mongoClient = new MongoClient("localhost", 27017);

            // Now connect to your databases
            DB db = mongoClient.getDB("pathumdb");
            System.out.println("Connect to database successfully");

            DBCollection coll = db.getCollection("user");
            System.out.println("Collection mycol selected successfully");

            BasicDBObject doc = new BasicDBObject("title", user.getName()).
                    append("password", user.getPassword());

            coll.insert(doc);
            System.out.println("Document inserted successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

    }

    public User login(String name) {
        User user = new User();
        // To connect to mongodb server
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        // Now connect to your databases
        DB db = mongoClient.getDB("pathumdb");
        System.out.println("Connect to database successfully");
        
        

        DBCollection coll = db.getCollection("user");
        System.out.println("Collection user selected successfully");

        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("title", name);
        DBCursor cursor = coll.find(whereQuery);
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
        BasicDBObject doc=(BasicDBObject) cursor.curr();
        if(doc!=null){
            user.setName(doc.getString("title"));
            user.setPassword(doc.getString("password"));
        }
        
        return user;
    }
}
