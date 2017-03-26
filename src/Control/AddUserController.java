package Control;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Sajeewa on 3/26/2017.
 */
public class AddUserController {
    MySQLCon mySQLCon;
    Connection connection;
    //Statement statement;
    PreparedStatement preparedStatement;
//    ResultSet resultSet;

    private String userID;

    public AddUserController(String userID) throws SQLException, ClassNotFoundException {
        this.userID = userID;
        mySQLCon = new MySQLCon();
        connection = mySQLCon.connectDatabase();
        //statement = connection.createStatement();
    }

    public void createUser(String first_name, String middle_initials, String last_name) throws SQLException, FileNotFoundException {
        preparedStatement = connection.prepareStatement("INSERT INTO user VALUES (?, ?, ?, ?)");

        preparedStatement.setString(1, this.userID);
        preparedStatement.setString(2, first_name);
        preparedStatement.setString(3, middle_initials);
        preparedStatement.setString(4, last_name);

        preparedStatement.executeQuery();
    }

    public void addPersonalDetails(int gender, int marital_status, String street_no, String street_name, String city, String state, String country, String email, String nic, Date date_of_birth) throws SQLException {
        preparedStatement = connection.prepareStatement("INSERT INTO user_personal_profile VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

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

        preparedStatement.executeQuery();
    }

    public void addEmploymentDetails(Date start_date, Date end_date, float basic_salary, String user_type) throws SQLException {
        preparedStatement = connection.prepareStatement("INSERT INTO user_employment_profile VALUES (?, ?, ?, ?, ?)");

        preparedStatement.setString(1, this.userID);
        preparedStatement.setDate(2, start_date);
        preparedStatement.setDate(3, end_date);
        preparedStatement.setFloat(4, basic_salary);
        preparedStatement.setString(5, user_type);

        preparedStatement.executeQuery();
    }

    public void loginDetails(String hashed_password, int is_active) throws SQLException {
        preparedStatement = connection.prepareStatement("INSERT INTO user_login_details VALUES (?, ?, ?)");

        preparedStatement.setString(1, this.userID);
        preparedStatement.setString(2, hashed_password);
        preparedStatement.setInt(3, is_active);

        preparedStatement.executeQuery();
    }
}
