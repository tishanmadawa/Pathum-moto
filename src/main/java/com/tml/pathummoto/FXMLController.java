package com.tml.pathummoto;

import com.tml.pathummoto.Dao.PartDao;
import com.tml.pathummoto.Dao.UserDao;
import com.tml.pathummoto.model.User;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

public class FXMLController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Label addName;
    @FXML
    private Label addPassword;
    @FXML
    private TextField name;
    @FXML
    private PasswordField password;
    


    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        String name1 = name.getText();
        String password1 = password.getText();
//        String  originalPassword = password1;
//        String generatedSecuredPasswordHash = BCrypt.hashpw(originalPassword, BCrypt.gensalt(12));
//        System.out.println("password=="+generatedSecuredPasswordHash);
        addName.setText("");
        addPassword.setText("");

        if (name1.length() == 0) {
            addName.setText("Enter User Name");
        } else if (!name1.matches("^[ a-zA-Z]*$")) {
            addName.setText("User Name is Invalid");

        } else if (password1.length() == 0) {
            addPassword.setText("Enter Your Password");
        } else {
            UserDao userdao = new UserDao();
            User user = userdao.login(name1);
            label.setText("name=" + user.getName() + name1 + "   password=" + user.getPassword() + password1);

            if (name1 == null ? user.getName() != null : !name1.equals(user.getName())) {
                addName.setText("User Name is Invalid");

            } else if (password1 == null ? user.getPassword() != null : !password1.equals(user.getPassword())) {
                addPassword.setText("Password is Invalid");
            } else {
                System.out.println("load");

                BillController bill=new BillController();
                        Stage stage = (Stage) name.getScene().getWindow();

                bill.home(stage);
                
                System.out.println("load");

            }

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    
}
