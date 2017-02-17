/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tml.pathummoto;

import com.mongodb.BasicDBObject;
import com.tml.pathummoto.Dao.ModelDao;
import com.tml.pathummoto.Dao.PartDao;
import com.tml.pathummoto.model.MainPart;
import com.tml.pathummoto.model.Model;
import com.tml.pathummoto.model.Part;
import com.tml.pathummoto.model.User;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JFileChooser;

/**
 *
 * @author Tishan Madhawa
 */
public class BillController {

    @FXML
    TextField type;
    @FXML
    TextField modell;
    @FXML
    TextField mainPartt;

    static ComboBox comboBox1 = new ComboBox();

    static ComboBox comboBox2 = new ComboBox();

    static ImageView image = new ImageView();
    static TableView<Part> partTable = new TableView<Part>();
    static TableView<Part> billPartTable = new TableView<Part>();

    @FXML
    public void home(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/home.fxml"));
        final PartDao partdao = new PartDao();
        ArrayList arr1 = new ArrayList();
        arr1 = partdao.getModelData();
        ObservableList<String> observableListOfObjects = FXCollections.observableList(arr1);
        comboBox1.setItems(observableListOfObjects);
        comboBox1.setLayoutX(170.0);
        comboBox1.setLayoutY(60.0);
        comboBox1.setMinSize(150, 25);
        comboBox1.setId("modelName");
        comboBox2.setLayoutX(170.0);
        comboBox2.setLayoutY(90.0);
        comboBox2.setMinSize(150, 25);
        comboBox2.setId("MainPartName");
        comboBox1.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                comboBox1.getEditor().setText(t1);
                
            }

        });

        
        comboBox2.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                comboBox2.getEditor().setText(t1);

            }

        });
        
         billPartTable.setLayoutX(575);
        billPartTable.setLayoutY(215);
        billPartTable.setPrefWidth(769);
        billPartTable.setPrefHeight(330);
        
        partTable.setLayoutX(0);
        partTable.setLayoutY(450);
        partTable.setPrefWidth(500);
        partTable.setPrefHeight(250);

        AnchorPane anchorPane = (AnchorPane) root;
        anchorPane.getChildren().addAll(comboBox1, comboBox2, image, partTable,billPartTable);

        Scene scene = new Scene(anchorPane);

        stage.setMaximized(true);

        stage.setTitle("Pathum Motors");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void searchMainPart() throws IOException {
        String model = comboBox1.getEditor().getText();

        System.out.println("selected model is " + model);
        final PartDao partdao = new PartDao();

        ArrayList arr = new ArrayList();
        arr = partdao.getMainPartData(model);
        ObservableList<String> mainPartList = FXCollections.observableList(arr);
        comboBox2.setItems(mainPartList);
        comboBox2.setLayoutX(170.0);
        comboBox2.setLayoutY(90.0);
        comboBox2.setMinSize(150, 25);
        comboBox2.setId("MainPartName");
        comboBox2.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                comboBox2.getEditor().setText(t1);

            }

        });

    }

    @FXML
    public void searchPart(ActionEvent event) throws IOException {
        partTable.getColumns().clear();
        String model = comboBox1.getEditor().getText();
        String mainPart = comboBox2.getEditor().getText();
        final PartDao partdao = new PartDao();
        Image img = new Image("file:" + partdao.getImage(model, mainPart));
         //partTable.getColumns().clear();
        image.setImage(img);
        image.setLayoutX(0);
        image.setLayoutY(130.0);
        image.setFitHeight(300);
        image.setFitWidth(500);
          
        System.out.println("file:" + partdao.getImage(model, mainPart));
        ArrayList<Part> arr = new ArrayList<Part>();
        arr = partdao.searchParts(model, mainPart);
        Part partt=new Part();
       final ObservableList<Part> data =
        FXCollections.observableArrayList();
        for(int i=0;i<arr.size();i++){
           data.add(arr.get(i));
           
        }
        
       

        TableColumn Number = new TableColumn("Image Number");
        Number.setMinWidth(100);
        Number.setCellValueFactory(new PropertyValueFactory<Part, String>("ImageNo"));

        TableColumn partId = new TableColumn("Part No");
        partId.setMinWidth(120);
        partId.setCellValueFactory(
                new PropertyValueFactory<Part, String>("PartNo")
        );

        TableColumn partName = new TableColumn("Part Name");
        partName.setMinWidth(170);
        partName.setCellValueFactory(
                new PropertyValueFactory<Part, String>("PartName")
        );
        TableColumn Price = new TableColumn("Price");
        Price.setMinWidth(110);
        Price.setCellValueFactory(
                new PropertyValueFactory<Part, String>("price")
        );


        partTable.getColumns().addAll(Number, partId, partName, Price);
        partTable.setItems(data);
        

    

    }
    @FXML
    public void addcustomer() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/addCustomer.fxml"));
        AnchorPane anchorPane = (AnchorPane) root;
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) type.getScene().getWindow();
        stage.setMaximized(true);

        stage.setTitle("Pathum Motors");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void addModel1() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/model.fxml"));
        AnchorPane anchorPane = (AnchorPane) root;
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) type.getScene().getWindow();
        stage.setMaximized(true);

        stage.setTitle("Pathum Motors");
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    final Label lable = new Label();

    @FXML
    Button button;
    @FXML
    private TextField part_type;

    @FXML
    private TextField path;

    static ComboBox<String> comboBox;

    @FXML
    public void addMainPart() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainPart.fxml"));
        PartDao partdao = new PartDao();
        ArrayList arr1 = new ArrayList();
        arr1 = partdao.getModelData();
        ObservableList<String> observableListOfObjects = FXCollections.observableList(arr1);
        comboBox = new ComboBox<String>(observableListOfObjects);
        comboBox.setLayoutX(250.0);
        comboBox.setLayoutY(20.0);
       
        comboBox.setId("modelName");

        comboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {

                comboBox.getEditor().setText(t1);

            }

        });
//        System.out.println("see" + com);
//
//        Button button1 = new Button();
//        button1.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("tisan" + com);
//
//            }
//
//        });

        AnchorPane anchorPane = (AnchorPane) root;
        anchorPane.getChildren().addAll(lable, comboBox);

        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) type.getScene().getWindow();
        stage.setMaximized(true);

        stage.setTitle("Pathum Motors");
        stage.setScene(scene);
        stage.show();

    }
//add main parts
      @FXML
      private Label labelmodel2;
      @FXML
      private Label labelparttype2;
      @FXML
      private Label labelimage2;
    @FXML
    public void addMainParts(ActionEvent event) {
        labelmodel2.setText("");
        labelparttype2.setText("");
        labelimage2.setText("");
        
        String PartType = part_type.getText();
        String model1 = comboBox.getEditor().getText();
        String path1 = path.getText();
        if(PartType.length()==0 ){
            labelparttype2.setText("Fill this Field");
        }else if(model1.length()==0){
            labelmodel2.setText("Fill this Field");
        }else if(path1.length()==0){
            labelimage2.setText("Fill this Field");

        }else if(!PartType.matches("^[ a-zA-Z]*$")){
             labelparttype2.setText("Part type is invalid");
        }else{
        MainPart mainpart = new MainPart();
        mainpart.setImageName(path1);
        mainpart.setModelName(model1);
        mainpart.setPartType(PartType);

        PartDao partdao = new PartDao();
        partdao.addMainPart(mainpart);
        System.out.println("senali=" + model1);
        }
    }

    public void browsePart(ActionEvent event) {
        final FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) part_type.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        if (file.getPath() != null) {
            path.setText(file.getPath());
        }

    }
    static ComboBox<String> comboBox3;
    static ComboBox<String> comboBox4;
    @FXML
    TextField part_no;
    @FXML
    TextField image_no;
    @FXML
    TextField part_nam;
     @FXML
     Label labelparttype3;
     @FXML
     Label labelmodel3;
     @FXML
     Label labelpartno;
     @FXML
     Label labelpartname;
     @FXML
     Label labelimageno;
    @FXML
    public void addPart1(ActionEvent event) throws IOException {
        String ModelName = comboBox3.getEditor().getText();

        String PartNo;
        PartNo = part_no.getText();
        String ImageNo;
        ImageNo = image_no.getText();
        String PartType;
        PartType = comboBox4.getEditor().getText();
        String PartNam = part_nam.getText();
        
        if(ModelName.length()==0){
            labelmodel3.setText("Fill this field");
        }else if(PartNo.length()==0){
            labelpartno.setText("Fill this field");
        }else if(ImageNo.length()==0){
            labelimageno.setText("Fill this field");
        }else if(PartType.length()==0){
            labelparttype3.setText("Fill this field");
        }else if(PartNam.length()==0){
            labelpartname.setText("Fill this field");
        }else if(!ImageNo.matches("[0-9]+")){
            labelimageno.setText("Invalid");
        }else if(!PartType.matches("^[ a-zA-Z]*$")){
            labelparttype3.setText("Invalid part type");
        }else if(!PartNam.matches("^[ a-zA-Z]*$")){
             labelpartname.setText("Invalid part type"); 

        }else{
        Part part = new Part();
        part.setImageNo(ImageNo);
        part.setModelName(ModelName);
        part.setPartName(PartNam);
        part.setPartNo(PartNo);
        part.setPartType(PartType);
        PartDao partdao = new PartDao();
        partdao.addPart(part);
        }
    }
    static ObservableList<String> observableListOfObjects1;

    @FXML
    public void addPart() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/addPart.fxml"));
        final PartDao partdao = new PartDao();
        ArrayList arr1 = new ArrayList();

        arr1 = partdao.getModelData();
        ObservableList<String> observableListOfObjects = FXCollections.observableList(arr1);
        comboBox3 = new ComboBox<String>(observableListOfObjects);
        comboBox3.setLayoutX(245.0);
        comboBox3.setLayoutY(27.0);

        FileChooser chooser = new FileChooser();

        final AnchorPane anchorPane = (AnchorPane) root;
        anchorPane.getChildren().add(comboBox3);

        // comboBox.setId("modelName");
        comboBox3.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {

                comboBox3.getEditor().setText(t1);
                ArrayList arr2 = new ArrayList();

                arr2 = partdao.getMainPartData(t1);
                System.out.println(t1);
                observableListOfObjects1 = FXCollections.observableList(arr2);
                System.out.println(observableListOfObjects1);
                comboBox4 = new ComboBox<String>(observableListOfObjects1);
                comboBox4.setLayoutX(245.0);
                comboBox4.setLayoutY(75.0);
                comboBox4.valueProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue ov, String t, String t1) {

                        comboBox4.getEditor().setText(t1);

                    }

                });
                anchorPane.getChildren().add(comboBox4);

            }

        });
        System.out.println(observableListOfObjects1);

        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) type.getScene().getWindow();
        stage.setMaximized(true);

        stage.setTitle("Pathum Motors");
        stage.setScene(scene);
        stage.show();

    }
    //add model
    
    @FXML
    private TextField model_Name;
    @FXML 
    private Label labelmodel;
    @FXML
    public void addModel(ActionEvent event){
            String ModelName=model_Name.getText();
            
            labelmodel.setText("");
            if(ModelName.length()== 0){
                
                labelmodel.setText("Fill this field");
            }else{
            Model model = new Model();
            model.setModelName(ModelName);
            ModelDao modeldao=new ModelDao();
            modeldao.addModel(model);

            }
    }
    
    
    
    @FXML
    TextField partNo;
    @FXML
    TableView billParts;
    @FXML
    public void singlePart(ActionEvent event){
        String partNumber=partNo.getText();
        PartDao partDao=new PartDao();
        Part part=partDao.singlePart(partNumber);
        final ObservableList<Part> data =
        FXCollections.observableArrayList(part);
        
        
             TableColumn Number = new TableColumn("Image Number");
        Number.setMinWidth(100);
        Number.setCellValueFactory(new PropertyValueFactory<Part, String>("ImageNo"));

        TableColumn partId = new TableColumn("Part No");
        partId.setMinWidth(120);
        partId.setCellValueFactory(
                new PropertyValueFactory<Part, String>("PartNo")
        );

        TableColumn partName = new TableColumn("Part Name");
        partName.setMinWidth(170);
        partName.setCellValueFactory(
                new PropertyValueFactory<Part, String>("PartName")
        );
        TableColumn Price = new TableColumn("Price");
        Price.setMinWidth(110);
        Price.setCellValueFactory(
                new PropertyValueFactory<Part, String>("price")
        );


        partTable.getColumns().addAll(Number, partId, partName, Price);
        partTable.setItems(data);

            }
    }



