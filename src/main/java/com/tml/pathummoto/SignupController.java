/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tml.pathummoto;

import com.tml.pathummoto.Dao.UserDao;
import com.tml.pathummoto.model.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class SignupController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField name;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField Cpassword;
    
    @FXML
    Label label;
    
    @FXML
    private void signupAction(ActionEvent event)  {
        String name1=name.getText();
        String password1=password.getText();
        String cpassword1=Cpassword.getText();
        
        if(password1 == null ? cpassword1 == null : password1.equals(cpassword1)){
            System.out.println("dsfsdf");
            User user=new User();
            user.setName(name1);
            user.setPassword(password1);
            UserDao userdao=new UserDao();
            userdao.signup(user);
        
            
        }else{
            label.setText("Password is not match");
            
        }
        
      
 
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
