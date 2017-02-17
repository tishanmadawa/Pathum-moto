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
import com.tml.pathummoto.model.Customer;


public class CustomDao {
    
    public void addCustomer(Customer customer){
         MongoClient mongoClient = new MongoClient("localhost", 27017);

        // Now connect to your databases
        DB db = mongoClient.getDB("pathumdb");
        System.out.println("Connect to database successfully");
        
        
        String date=""+customer.getDateOfDelivery();
        DBCollection coll = db.getCollection("customer");
        System.out.println("Collection user selected successfully");
         BasicDBObject doc = new BasicDBObject("title", "customer").
                 append("name", customer.getName()).
                 append("address", customer.getAddress()).
                 append("vehicleNo", customer.getVehicleNo()).
                 append("vehicletype", customer.getVehicleType()).
                 append("dateOfDelivery", date).
                 append("engineNo", customer.getEngineNo()).
                 append("chassisNo", customer.getChassisNo()).
                 append("phoneNo", customer.getPhoneNo());

            coll.insert(doc);
                System.out.println("successfully");


    }
}
