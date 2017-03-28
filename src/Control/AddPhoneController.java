package Control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Sajeewa on 3/26/2017.
 */
public class AddPhoneController {

    MySQLCon mySQLCon;
    Connection connection;
    PreparedStatement preparedStatement;

    private String userID;

    public AddPhoneController(String userID) throws SQLException, ClassNotFoundException {
        this.userID = userID;
        mySQLCon = new MySQLCon();
        connection = mySQLCon.connectDatabase();
    }

    public void phoneDetails(ArrayList<String> phone) throws SQLException {
        for (int i = 0; i < phone.size(); i++){
            preparedStatement = connection.prepareStatement("INSERT INTO user_phone VALUES (?, ?)");
            preparedStatement.setString(1, this.userID);
            preparedStatement.setString(2, phone.get(i));

            preparedStatement.execute();
        }
    }
}
