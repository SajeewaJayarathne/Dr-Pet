package Control;

import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Sajeewa on 3/26/2017.
 */
public class UserController {
    MySQLCon mySQLCon;
    Connection connection;
    PreparedStatement preparedStatement;
    Statement statement;
    ResultSet resultSet;
    private ObservableList<User> userObservableList;
    private User user;
    private SpecialControl specialControl;
    private PhoneController phoneController;

    private String userID;

    public UserController(String userID) throws SQLException, ClassNotFoundException {
        this.userID = userID;
        mySQLCon = new MySQLCon();
        connection = mySQLCon.connectDatabase();
    }

    public UserController() throws SQLException, ClassNotFoundException {
        mySQLCon = new MySQLCon();
        connection = mySQLCon.connectDatabase();
        statement = connection.createStatement();
    }

    //create new user
    public void createUser(User user, User user2) throws FileNotFoundException, SQLException {
        specialControl = new SpecialControl();
        this.createUserProfile(user.getFirst_name(), user.getMiddle_initials(), user.getLast_name());
        this.addPersonalDetails(specialControl.getGenderInt(user.getGender()), specialControl.getMaritalStatusInt(user.getMarital_status()),
                user.getStreet_no(), user.getStreet_name(), user.getCity(), user.getState(), user.getCountry(),
                user.getEmail(), user.getNic(), specialControl.getSQLDate(user.getDob()));
        this.addEmploymentDetails(specialControl.getSQLDate(user.getStart_date()), user.getBasic_salary(),
                user2.getUser_type());
        this.loginDetails(user2.getHashPassword(), specialControl.getIsActiveInt(user2.isIs_active()));
    }

    private void createUserProfile(String first_name, String middle_initials, String last_name) throws SQLException, FileNotFoundException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user VALUES (?, ?, ?, ?)");

        preparedStatement.setString(1, this.userID);
        preparedStatement.setString(2, first_name);
        preparedStatement.setString(3, middle_initials);
        preparedStatement.setString(4, last_name);

        preparedStatement.execute();
    }

    private void addPersonalDetails(int gender, int marital_status, String street_no, String street_name, String city,
                                    String state, String country, String email, String nic, Date date_of_birth) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user_personal_profile VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

        preparedStatement.setString(1, this.userID);
        preparedStatement.setInt(2, gender);
        preparedStatement.setInt(3, marital_status);
        preparedStatement.setString(4, street_no);
        preparedStatement.setString(5, street_name);
        preparedStatement.setString(6, city);
        preparedStatement.setString(7, state);
        preparedStatement.setString(8, country);
        preparedStatement.setString(9, email);
        preparedStatement.setString(10, nic);
        preparedStatement.setDate(11, date_of_birth);

        preparedStatement.execute();
    }

    private void addEmploymentDetails(Date start_date, float basic_salary, String user_type) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user_employment_profile VALUES (?, ?, ?, ?, ?)");

        preparedStatement.setString(1, this.userID);
        preparedStatement.setDate(2, start_date);
        preparedStatement.setDate(3, null);
        preparedStatement.setFloat(4, basic_salary);
        preparedStatement.setString(5, user_type);

        preparedStatement.execute();
    }


    public void loginDetails(String hashed_password, int is_active) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user_login_details VALUES (?, ?, ?)");

        preparedStatement.setString(1, this.userID);
        preparedStatement.setString(2, hashed_password);
        preparedStatement.setInt(3, is_active);

        preparedStatement.execute();
    }
    //--create user--//

    //get user details//
    public ObservableList<User> getUserDetails() throws SQLException, ClassNotFoundException {
        ArrayList<String> phoneNumbers;
        userObservableList = FXCollections.observableArrayList();
        preparedStatement = connection.prepareStatement("SELECT * FROM user_profile");
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            phoneController = new PhoneController(resultSet.getString("user_id"));
            phoneNumbers = phoneController.getPhoneNumbers(resultSet.getString("user_id"));

            user = new User(resultSet.getString("user_id"), resultSet.getString("first_name"),
                    resultSet.getString("middle_initials"), resultSet.getString("last_name"),
                    resultSet.getInt("gender"), resultSet.getString("marital_status"),
                    resultSet.getString("street_no"), resultSet.getString("street_name"),
                    resultSet.getString("city"), resultSet.getString("state"),
                    resultSet.getString("country"), resultSet.getString("email"),
                    resultSet.getString("nic"), resultSet.getDate("date_of_birth").toString(),
                    resultSet.getDate("start_date").toString(), resultSet.getFloat("basic_salary"),
                    phoneNumbers);

            userObservableList.add(user);
        }
        return userObservableList;
    }
    //--get user details--//
}