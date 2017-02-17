/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tml.pathummoto;

import com.tml.pathummoto.Dao.CustomDao;
import com.tml.pathummoto.model.Customer;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;


public class AddCustomerController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField name;
    @FXML
    private TextField address;
    @FXML
    private TextField vehicleNo;
    @FXML
    private TextField vehicleType;
    @FXML
    private DatePicker dateOfDelivery;
    @FXML
    private TextField engineNo;
    @FXML
    private TextField phoneNo;
    @FXML
    private TextField chassisNo;
    
    
    
    @FXML
    public void addCustomer(ActionEvent event){
        String customerName=name.getText();
        String customerAdress=address.getText();
        String customerVehicleN0=vehicleNo.getText();
        String customerVehicleType=vehicleType.getText();
        LocalDate customerDateOfDelivery=dateOfDelivery.getValue();
        String customerEngineNo=engineNo.getText();
        String customerPhoneNo=phoneNo.getText();
        String customerChassisNo=chassisNo.getText();
        
        Customer customer=new Customer();
        customer.setAddress(customerAdress);
        customer.setChassisNo(customerChassisNo);
        customer.setDateOfDelivery(customerDateOfDelivery);
        customer.setEngineNo(customerEngineNo);
        customer.setName(customerName);
        customer.setPhoneNo(customerPhoneNo);
        customer.setVehicleNo(customerVehicleN0);
        customer.setVehicleType(customerVehicleType);
        
        CustomDao customerDao=new CustomDao();
        customerDao.addCustomer(customer);
        
        
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
