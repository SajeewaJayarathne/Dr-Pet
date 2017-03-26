package View.User_Management;

import Model.AddUserModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.swing.text.html.ImageView;
import java.sql.SQLException;

public class AddUserViewControl {

    AddUserModel addUserModel;

    @FXML
    Button createButton;

    @FXML
    Label userID;

    @FXML
    TextField first_name, middle_initials, last_name;

    @FXML
    PasswordField password;

    @FXML
    ImageView image;

    @FXML
    public void imageChooser(){
        System.out.println("Image Chooser Clicked");
    }

    @FXML
    public void handleCreateUser() throws SQLException, ClassNotFoundException {
        addUserModel = new AddUserModel(userID.getText());
    }


}
