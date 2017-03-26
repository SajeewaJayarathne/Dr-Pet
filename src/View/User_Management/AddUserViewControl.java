package View.User_Management;

import Model.AddPhoneModel;
import Model.AddUserModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class AddUserViewControl {

    AddUserModel addUserModel;
    AddPhoneModel addPhoneModel;

    private int addButtonClickCount = 0;

    @FXML
    Button createButton, clearButton, addPhone;

    @FXML
    TextField userID, first_name, middle_initials, last_name, street_no, street_name, city, state, country, nic, email, phone1, phone2, phone3, phone4, basic_salary;

    @FXML
    RadioButton maleRadio, femaleRadio, marriedRadio, unmarriedRadio, adminRadio, doctorRadio;

    @FXML
    DatePicker dob, empdate;

    @FXML
    PasswordField password, confirm_password;

    @FXML
    public void handleCreateUser() throws SQLException, ClassNotFoundException, FileNotFoundException, ParseException {

        if (userID.getText().isEmpty() || password.getText().isEmpty() || confirm_password.getText().isEmpty()
                || first_name.getText().isEmpty() || last_name.getText().isEmpty() || street_no.getText().isEmpty()
                || street_name.getText().isEmpty() || city.getText().isEmpty() || country.getText().isEmpty()
                || nic.getText().isEmpty() || dob.getValue().equals(null) || phone1.getText().isEmpty()
                || basic_salary.getText().isEmpty() || empdate.getValue().equals(null)){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Fields!");
            alert.setHeaderText(null);
            alert.setContentText("Please Fill in the Mandatory Fields.");
            alert.showAndWait();
        } else {
            if (!password.getText().equals(confirm_password.getText())){
                password.setText("");
                password.setPromptText("Password");
                confirm_password.setText("");
                confirm_password.setPromptText("Confirm Password");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Password Mismatch!");
                alert.setHeaderText(null);
                alert.setContentText("Please Enter the Password Again.");
                alert.showAndWait();
            } else {
                String gender = "male", marital_status = "married", user_type = "a";

                //phone numbers
                ArrayList<String> phoneNumbers = new ArrayList<>();
                phoneNumbers.add(phone1.getText());
                if (!phone2.getText().isEmpty()){
                    phoneNumbers.add(phone2.getText());
                }
                if (!phone3.getText().isEmpty()){
                    phoneNumbers.add(phone3.getText());
                }
                if (!phone4.getText().isEmpty()){
                    phoneNumbers.add(phone4.getText());
                }

                //getting the selected radio button
                if (femaleRadio.isSelected()){
                    gender = "female";
                }

                if (unmarriedRadio.isSelected()){
                    marital_status = "unmarried";
                }

                if (doctorRadio.isSelected()){
                    user_type = "d";
                }

                addUserModel = new AddUserModel(userID.getText());

                addUserModel.addUser(first_name.getText(), middle_initials.getText(), last_name.getText());

                addUserModel.addUserPersonalDetails(gender, marital_status, street_no.getText(), street_name.getText(), city.getText(), state.getText(), country.getText(), email.getText(), nic.getText(), dob.getValue().toString());

                addUserModel.addUserEmploymentDetails(empdate.getValue().toString(), null, Float.valueOf(basic_salary.getText()), user_type);

                addUserModel.addUserLoginDetails(password.getText(), true);

                addPhoneModel = new AddPhoneModel(userID.getText());
                addPhoneModel.addPhone(phoneNumbers);

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
}
