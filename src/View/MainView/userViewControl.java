package View.MainView;

import Control.PhoneController;
import Control.SpecialControl;
import Control.UserController;
import Model.Phone;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class userViewControl implements Initializable{
    User user, user2;
    Phone phone;
    private SpecialControl specialControl;
    PhoneController phoneController;
    private UserController userController;
    private ObservableList<User> userObservableList;

    private int addButtonClickCount = 0;

    @FXML
    Button createButton, clearButton, addPhone;

    @FXML
    TextField userID, first_name, middle_initials, last_name, street_no, street_name, city, state, country, nic, email, phone1, phone2, phone3, phone4, basic_salary;

    @FXML
    RadioButton maleRadio, femaleRadio, marriedRadio, unmarriedRadio, adminRadio, doctorRadio;

    @FXML
    DatePicker dob, employment_date;

    @FXML
    PasswordField password, confirm_password;

    @FXML
    public void handleCreateUser() throws SQLException, ClassNotFoundException, FileNotFoundException, ParseException {


        if (userID.getText().isEmpty() || password.getText().isEmpty() || confirm_password.getText().isEmpty()
                || first_name.getText().isEmpty() || last_name.getText().isEmpty() || street_no.getText().isEmpty()
                || street_name.getText().isEmpty() || city.getText().isEmpty() || country.getText().isEmpty()
                || nic.getText().isEmpty() || dob.getValue().equals(null) || phone1.getText().isEmpty()
                || basic_salary.getText().isEmpty() || employment_date.getValue().equals(null)){

            specialControl.alertBox("Empty Fields!", "Please fill in the mandatory fields...");

        } else {
            if (!password.getText().equals(confirm_password.getText())){
                password.setText("");
                password.setPromptText("Password");
                confirm_password.setText("");
                confirm_password.setPromptText("Confirm Password");

                specialControl.alertBox("Password Mismatch!", "Please Enter the Password Again.");

            } else {
                String gender = "Male", marital_status = "Married", user_type = "a";

                //getting the selected radio button
                if (femaleRadio.isSelected()){
                    gender = "Female";
                }

                //getting the selected marital_status radio button
                if (unmarriedRadio.isSelected()){
                    marital_status = "Unmarried";
                }

                //getting the selected user_type radio button
                if (doctorRadio.isSelected()){
                    user_type = "d";
                }

                //phone numbers
                ArrayList<String> phoneArrayList = new ArrayList<>();
                ObservableList<Phone> phoneNumberList = FXCollections.observableArrayList();

                phoneArrayList.add(phone1.getText());
                phoneNumberList.add(new Phone(userID.getText(), phone1.getText()));


                if (!phone2.getText().isEmpty()){
                    phoneArrayList.add(phone2.getText());
                    phoneNumberList.add(new Phone(userID.getText(), phone2.getText()));
                }
                if (!phone3.getText().isEmpty()){
                    phoneArrayList.add(phone3.getText());
                    phoneNumberList.add(new Phone(userID.getText(), phone3.getText()));
                }
                if (!phone4.getText().isEmpty()){
                    phoneArrayList.add(phone4.getText());
                    phoneNumberList.add(new Phone(userID.getText(), phone4.getText()));
                }

                phoneController = new PhoneController(userID.getText());
                phoneController.addPhoneDetails(phoneNumberList);

                //--phone numbers--//

                //add user//
                user = new User(userID.getText(), first_name.getText(), middle_initials.getText(), last_name.getText(),
                        specialControl.getGenderInt(gender), marital_status, street_no.getText(), street_name.getText(), city.getText(), state.getText(),
                        country.getText(), email.getText(), nic.getText(), dob.getValue().toString(),
                        employment_date.getValue().toString(), Float.valueOf(basic_salary.getText()),
                        phoneArrayList);

                user2 = new User(userID.getText(), user_type, password.getText(), true);

                userController = new UserController(userID.getText());

                userController.createUser(user, user2);
                //--add user--//
            }
        }
    }

    @FXML
    public void handleClearFields(){
        password.setText("");
        confirm_password.setText("");

        first_name.setText("");
        middle_initials.setText("");
        last_name.setText("");

        maleRadio.setSelected(true);
        marriedRadio.setSelected(true);

        street_no.setText("");
        street_name.setText("");
        city.setText("");
        state.setText("");
        country.setText("");

        nic.setText("");
        email.setText("");
        phone1.setText("");
        phone2.setText("");
        phone2.setVisible(false);
        phone3.setText("");
        phone3.setVisible(false);
        phone4.setText("");
        phone4.setVisible(false);

        addPhone.setDisable(false);

        basic_salary.setText("");
        adminRadio.setSelected(true);
    }

    @FXML
    public void handlePhoneNumber(){
        addButtonClickCount++;
        switch (addButtonClickCount) {
            case 1:
                phone2.setVisible(true);
                break;
            case 2:
                phone3.setVisible(true);
                break;
            case 3:
                phone4.setVisible(true);
                addPhone.setDisable(true);
                break;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        specialControl = new SpecialControl();

        //users
        userObservableList = FXCollections.observableArrayList();
        try {
            userController = new UserController();
            userObservableList = userController.getUserDetails();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        loadUsers();
    }

    private void loadUsers() {

    }
}
