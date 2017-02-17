/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tml.pathummoto.Dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.tml.pathummoto.model.Model;


public class ModelDao {
    public void addModel(Model model){
          MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
			
         // Now connect to your databases
         DB db = mongoClient.getDB( "pathumdb" );
         System.out.println("Connect to database successfully");
         
          DBCollection coll = db.getCollection("model");

         BasicDBObject doc = new BasicDBObject("title","model").
            append("_id", model.getModelName());
                 
                 
                 
                 
        coll.insert(doc);
         System.out.println("Document inserted successfully");
				
         
    }
}
