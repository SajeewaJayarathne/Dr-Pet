package Control;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Sajeewa on 3/27/2017.
 */
public class GetOwnerController {
    MySQLCon mySQLCon;
    Connection connection;
    PreparedStatement preparedStatement;
    Statement statement;
    ResultSet resultSet;

    public GetOwnerController() throws SQLException, ClassNotFoundException {
        mySQLCon = new MySQLCon();
        connection = mySQLCon.connectDatabase();
        statement = connection.createStatement();
    }

    //get Owner IDs
    private ArrayList<String> ownerIDs;

    public ArrayList<String> getOwnerIDs() throws SQLException {
        ownerIDs  = new ArrayList<>();
        resultSet = statement.executeQuery("SELECT own_id FROM owner_info");

        while (resultSet.next()){
            ownerIDs.add(resultSet.getString("own_id"));
        }

        return ownerIDs;
    }

    //get name of a particular owner
    private String ownerName;

    public String getOwnerName(String ownerID) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT first_name, middle_initials, last_name FROM owner_info WHERE own_id = ?");
        preparedStatement.setString(1, ownerID);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.getString("middle_initials").isEmpty() || resultSet.getString("middle_initials") == null){
            ownerName = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
        } else {
            ownerName = resultSet.getString("first_name") + " " + resultSet.getString("middle_initials") + " " + resultSet.getString("last_name");
        }

        return ownerName;
    }
}
