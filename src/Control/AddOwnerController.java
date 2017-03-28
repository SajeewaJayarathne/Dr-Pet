package Control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Sajeewa on 3/26/2017.
 */
public class AddOwnerController {
    MySQLCon mySQLCon;
    Connection connection;
    PreparedStatement preparedStatement;

    private String ownerID;

    public AddOwnerController(String ownerID) throws SQLException, ClassNotFoundException {
        this.ownerID = ownerID;
        mySQLCon = new MySQLCon();
        connection = mySQLCon.connectDatabase();
    }

    public void createOwner(String first_name, String middle_initials, String last_name, int gender, String street_no, String street_name, String city, String state, String country, String email) throws SQLException {
        preparedStatement = connection.prepareStatement("INSERT INTO owner_info VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        preparedStatement.setString(1, this.ownerID);
        preparedStatement.setString(2, first_name);
        preparedStatement.setString(3, middle_initials);
        preparedStatement.setString(4, last_name);
        preparedStatement.setInt(5, gender);
        preparedStatement.setString(6, street_no);
        preparedStatement.setString(7, street_name);
        preparedStatement.setString(8, city);
        preparedStatement.setString(9, state);
        preparedStatement.setString(10, country);
        preparedStatement.setString(11, email);

        preparedStatement.execute();
    }
}
