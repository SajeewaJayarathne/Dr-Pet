package Control;

import java.sql.*;

/**
 * Created by Sajeewa on 3/24/2017.
 */
public class LoginController {

    MySQLCon mySQLCon;
    Connection connection;
    Statement statement;
    ResultSet resultSet;

    public LoginController() throws SQLException, ClassNotFoundException {
        mySQLCon = new MySQLCon();
        connection = mySQLCon.connectDatabase();
        statement = connection.createStatement();
    }

    public boolean findUser(String userName) throws SQLException {

        boolean foundUser = false;
        resultSet = statement.executeQuery("SELECT username FROM user_login_details");

        while (resultSet.next()){
            if (resultSet.getString(1).equals(userName)){
                foundUser = true;
                break;
            }
        }

        return foundUser;
    }

    public String[] getUserdetails(String userName) throws SQLException {
        String[] userDetails = new String[2];
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT hashed_password, is_active FROM user_login_details WHERE username = ?");
        preparedStatement.setString(1, userName);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            userDetails[0] = resultSet.getString("hashed_password");
            userDetails[1] = String.valueOf(resultSet.getBoolean("is_active"));
        }

        return userDetails;
    }

}
