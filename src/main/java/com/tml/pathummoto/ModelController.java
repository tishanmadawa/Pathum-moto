/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tml.pathummoto;

import com.tml.pathummoto.Dao.ModelDao;
import com.tml.pathummoto.model.Model;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;



public class ModelController implements Initializable {
   @FXML
    private TextField model_Name;
   @FXML
    public void addModel(ActionEvent event){
            String ModelName=model_Name.getText();
            Model model = new Model();
            model.setModelName(ModelName);
            ModelDao modeldao=new ModelDao();
            modeldao.addModel(model);


    }
   

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
   
   
}
