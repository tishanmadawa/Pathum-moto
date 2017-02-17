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
import com.mongodb.WriteResult;
import com.tml.pathummoto.model.MainPart;
import com.tml.pathummoto.model.Model;
import com.tml.pathummoto.model.Part;
import java.util.ArrayList;


public class PartDao {
    public void addPart(Part part){
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
			
             // Now connect to your databases
             DB db = mongoClient.getDB( "pathumdb" );
             System.out.println("Connect to database successfully");
             
              DBCollection coll = db.getCollection("part");
              
             BasicDBObject doc = new BasicDBObject("title", "part").
                append("_id", part.getPartNo()).
                     append("Part Name", part.getPartName()).
                     append("Model Name", part.getModelName()).
                     append("Image No", part.getImageNo()).
                      append("Part Type", part.getPartType());
                   
                     
                     
                     
                     
        coll.insert(doc);
             System.out.println("Document inserted successfully");
    }	
             
          public Part searchPart(String partNo){
     Part part=new Part();
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
			
         // Now connect to your databases
         DB db = mongoClient.getDB( "pathumdb" );
         System.out.println("Connect to database successfully");
         
         
        DBCollection coll = db.getCollection("part");
        System.out.println("Collection part selected successfully");

        BasicDBObject find = new BasicDBObject();
        find.put("_id", partNo);
        DBCursor cursor = coll.find(find);
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
        BasicDBObject doc=(BasicDBObject) cursor.curr();
        if(doc!=null){
            
            
     
        }
        
        return part;
            
        }

    public Part singlePart(String partNumber) {
        Part part = new Part();
        // To connect to mongodb server
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        // Now connect to your databases
        DB db = mongoClient.getDB("pathumdb");
        System.out.println("Connect to database successfully");
        
        

        DBCollection coll = db.getCollection("part");
        System.out.println("Collection user selected successfully");

        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("_id", partNumber);
        DBCursor cursor = coll.find(whereQuery);
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
        BasicDBObject doc=(BasicDBObject) cursor.curr();
        if(doc!=null){
            part.setPartName(doc.getString("Part Name"));
            part.setPartNo(doc.getString("_id"));
            
        }
        
        return part;
    }
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
          
     public void addMainPart(MainPart mainpart){
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
			
         // Now connect to your databases
         DB db = mongoClient.getDB( "pathumdb" );
         System.out.println("Connect to database successfully");
         
          DBCollection coll = db.getCollection("mainpart");
        
         BasicDBObject doc = new BasicDBObject("title", "mainpart").
            append("path",mainpart.getImageName()).
                 append("Model Name", mainpart.getModelName()).
                 append("Part Type", mainpart.getPartType());
                
                 
                 
                 
                 
        coll.insert(doc);
         System.out.println("Document inserted successfully");
				
         
        
    }
    
     public ArrayList<String> getModelData(){
          MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
			
         // Now connect to your databases
         DB db = mongoClient.getDB( "pathumdb" );
         System.out.println("Connect to database successfully");
         
         
         
        BasicDBObject query = new BasicDBObject();
        BasicDBObject field = new BasicDBObject();
        field.put("_id", 1);
        DBCursor cursor = db.getCollection("model").find(query,field);
        ArrayList arr = new ArrayList();
    while (cursor.hasNext()) {
        BasicDBObject obj = (BasicDBObject) cursor.next();
        arr.add(obj.getString("_id"));
    }
           return (arr);
     }
     
     public ArrayList getMainPartData(String name){
          MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
			
         // Now connect to your databases
         DB db = mongoClient.getDB( "pathumdb" );
         System.out.println("Connect to database successfully");
         
         DBCollection coll = db.getCollection("mainpart");
        System.out.println("Collection user selected successfully");
         
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("Model Name", name);
        DBCursor cursor = coll.find(whereQuery);
        ArrayList arr = new ArrayList();
    while (cursor.hasNext()) {
        BasicDBObject obj = (BasicDBObject) cursor.next();
        arr.add(obj.getString("Part Type"));
    }
           return (arr);
     }
     
     public String getImage(String model,String mainPart){
         String path="C:\\Users\\Tishan Madhawa\\Pictures\\WP_20160118_17_31_47_Pro.jpg";
           MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
			
         // Now connect to your databases
         DB db = mongoClient.getDB( "pathumdb" );
         System.out.println("Connect to database successfully");
         
         DBCollection coll = db.getCollection("mainpart");
        System.out.println("Collection user selected successfully");
         
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("Model Name", model);
        whereQuery.put("Part Type", mainPart);
        DBCursor cursor = coll.find(whereQuery);
        
    while (cursor.hasNext()) {
        BasicDBObject obj = (BasicDBObject) cursor.next();
        
        path=obj.getString("path");
        
    }
    
        System.out.println(path);
           return path;
     }
     
      public ArrayList searchParts(String model,String mainPart){
          MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
			
         // Now connect to your databases
         DB db = mongoClient.getDB( "pathumdb" );
         System.out.println("Connect to database successfully");
         
         DBCollection coll = db.getCollection("part");
        System.out.println("Collection user selected successfully");
         
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("Model Name", model);
        whereQuery.put("Part Type", mainPart);
        
        DBCursor cursor = coll.find(whereQuery);
        ArrayList<Part> arr = new ArrayList<Part>();
    while (cursor.hasNext()) {
        BasicDBObject obj = (BasicDBObject) cursor.next();
        Part part=new Part(obj.getString("Image No"),obj.getString("_id"),obj.getString("Part Name"));
         
        
        arr.add(part);
        System.out.println(arr);
    }
           return (arr);
     }
      
     public ArrayList<String> getMainPart(){
          MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
			
         // Now connect to your databases
         DB db = mongoClient.getDB( "pathumdb" );
         System.out.println("Connect to database successfully");
         
         
         
        BasicDBObject query = new BasicDBObject();
        BasicDBObject field = new BasicDBObject();
        field.put("Part Type", 1);
        DBCursor cursor = db.getCollection("mainpart").find(query,field);
        ArrayList arr = new ArrayList();
    while (cursor.hasNext()) {
        BasicDBObject obj = (BasicDBObject) cursor.next();
        arr.add(obj.getString("Part Type"));
    }
           return (arr);
     }

}