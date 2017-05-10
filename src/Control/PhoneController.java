package Control;

import Model.Phone;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Sajeewa on 3/26/2017.
 */
public class PhoneController {

    MySQLCon mySQLCon;
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    private String userID;

    public PhoneController(String userID) throws SQLException, ClassNotFoundException {
        this.userID = userID;
        mySQLCon = new MySQLCon();
        connection = mySQLCon.connectDatabase();
    }

    public void addPhoneDetails(ObservableList<Phone> phoneNumbers) throws SQLException {
        for (Phone phoneNumber: phoneNumbers){
            preparedStatement = connection.prepareStatement("INSERT INTO user_phone VALUES (?, ?)");
            preparedStatement.setString(1, phoneNumber.getClientID());
            preparedStatement.setString(2, phoneNumber.getPhoneNumber());

            preparedStatement.execute();
        }
    }

    public ArrayList<String> getPhoneNumbers(String id) throws SQLException {
        ArrayList<String> phoneNumbers = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM user_phone WHERE user_id = ?");
        preparedStatement.setString(1, id);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            phoneNumbers.add(resultSet.getString("phone"));
            System.out.println(resultSet.getString("phone"));
        }
        return phoneNumbers;
    }
}
