/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tml.pathummoto.Dao;


import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;

import com.mongodb.ServerAddress;
import java.util.Arrays;

public class Connection {

   public static DBCollection main( String args[] ) {
	
      try{   
		
         // To connect to mongodb server
         MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
			
         // Now connect to your databases
         DB db = mongoClient.getDB( "pathumdb" );
         System.out.println("Connect to database successfully");
         
          DBCollection coll = db.getCollection("user");
         System.out.println("Collection mycol selected successfully");
		return coll;	
         		
         }catch(Exception e){
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         DBCollection coll = null;
         return coll;
      }
      
      
   }
}