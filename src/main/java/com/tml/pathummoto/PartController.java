///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package com.tml.pathummoto;

import com.tml.pathummoto.Dao.ModelDao;
import com.tml.pathummoto.Dao.PartDao;
import com.tml.pathummoto.Dao.PartDao;
import com.tml.pathummoto.model.MainPart;
import com.tml.pathummoto.model.Model;
import com.tml.pathummoto.model.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import static jdk.nashorn.internal.objects.NativeRegExp.source;


public class PartController implements Initializable {
    @FXML
    private ComboBox model;
    
    @FXML
    private TextField Part_Name;
    
    @FXML
    private TextField Part_No;
    
    @FXML
    private TextField Part_Price;
    
    @FXML
    private DatePicker Part_Date;
    
   
    
    

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
    
    
   
       
     /*  public void add(ActionEvent event) throws FileNotFoundException, IOException{
           System.out.println(PartController.class.getProtectionDomain().getCodeSource().getLocation().getPath()+"akak");
           String partType=part_type.getText();
           String modelName=model_name.toString();
           String path1=path.getText();
           FileInputStream is = new FileInputStream(path1);
        FileOutputStream os;
        os = new FileOutputStream(PartController.class.getProtectionDomain().getCodeSource().getLocation().getPath()+"Digital World.jpg");
        byte[] buffer = new byte[1024];
        
        int length;
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
          
       
        
       }*/
    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private TextField part_type;

    @FXML
    private TextField path;
    public void browsePart(ActionEvent event){
          final FileChooser fileChooser = new FileChooser();
          Stage stage = (Stage) part_type.getScene().getWindow();
          File file = fileChooser.showOpenDialog(stage);
          path.setText(file.getPath());  
         
     }
    
   
    
}
