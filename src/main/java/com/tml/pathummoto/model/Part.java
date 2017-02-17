/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tml.pathummoto.model;

import java.time.LocalDate;
import java.util.Date;

public class Part {
    public Part(){
        
    }
    private String ModelName;
    private String PartType;
    private String PartNo;
    private String PartName;
    private String ImageNo; 
    private Date date; 
    private String price;

    public Part(String string, String string0, String string1) {
        this.ImageNo = string;
        this.PartNo = string0;
   
        this.PartName = string1;
        
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getModelName() {
        return ModelName;
    }

    public void setModelName(String ModelName) {
        this.ModelName = ModelName;
    }

    public String getPartType() {
        return PartType;
    }

    public void setPartType(String PartType) {
        this.PartType = PartType;
    }

    public String getPartNo() {
        return PartNo;
    }

    public void setPartNo(String PartNo) {
        this.PartNo = PartNo;
    }

    public String getPartName() {
        return PartName;
    }

    public void setPartName(String PartName) {
        this.PartName = PartName;
    }

    public String getImageNo() {
        return ImageNo;
    }

    public void setImageNo(String ImageNo) {
        this.ImageNo = ImageNo;
    }

  

   
    
    public Object getNo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
}
