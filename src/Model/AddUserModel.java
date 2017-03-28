package Model;

import Control.AddUserController;

import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * Created by Sajeewa on 3/26/2017.
 */
public class AddUserModel {

    AddUserController addUserController;
    HashPasswordModel hashPasswordModel;

    public AddUserModel(String userID) throws SQLException, ClassNotFoundException {
        addUserController = new AddUserController(userID);
        hashPasswordModel = new HashPasswordModel();
    }

    //sending data to the controller

    public void addUser(String first_name, String middle_initials, String last_name) throws SQLException, FileNotFoundException {
        addUserController.createUser(first_name, middle_initials, last_name);
    }

//    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyy");

    public void addUserPersonalDetails(String genderStr, String marital_statusStr, String street_no, String street_name, String city, String state, String country, String email, String nic, String date_of_birth) throws ParseException, SQLException {
        int gender, marital_status;
        if (genderStr.toLowerCase()=="male"){
            gender = 0;
        } else {
            gender = 1;
        }

        if (marital_statusStr.toLowerCase()=="unmarried"){
            marital_status = 0;
        } else {
            marital_status = 1;
        }
//
//        java.util.Date util_date_of_birth = format.parse(date_of_birth);
//        java.sql.Date sql_date_of_birth = new java.sql.Date(util_date_of_birth.getTime());

        addUserController.addPersonalDetails(gender, marital_status, street_no, street_name, city, state, country, email, nic, Date.valueOf(date_of_birth));
    }

    public void addUserEmploymentDetails(String start_date, String end_date, float basic_salary, String user_type) throws ParseException, SQLException {
//        java.util.Date util_start_date = format.parse(start_date);
//        java.sql.Date sql_start_date = new java.sql.Date(util_start_date.getTime());
//
//        java.util.Date util_end_date = format.parse(end_date);
//        java.sql.Date sql_end_date = new java.sql.Date(util_end_date.getTime());

        addUserController.addEmploymentDetails(Date.valueOf(start_date), null, basic_salary, user_type);
    }

    public void addUserLoginDetails(String password, boolean is_active_bool) throws SQLException {
        int is_active;
        if (is_active_bool){
            is_active = 1;
        } else {
            is_active = 0;
        }

        String hashedPassword = hashPasswordModel.hashFunction(password);

        addUserController.loginDetails(hashedPassword, is_active);

    }

}
