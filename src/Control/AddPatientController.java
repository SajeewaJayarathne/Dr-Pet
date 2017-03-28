package Control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Sajeewa on 3/27/2017.
 */
public class AddPatientController {
    MySQLCon mySQLCon;
    Connection connection;
    PreparedStatement preparedStatement;

    private String patientID;

    public AddPatientController(String patientID) throws SQLException, ClassNotFoundException {
        this.patientID = patientID;
        mySQLCon = new MySQLCon();
        connection = mySQLCon.connectDatabase();
    }

    public void createPatient(String ownerID, String name, String type, String breed, Date dob) throws SQLException {
        preparedStatement = connection.prepareStatement("INSERT INTO patient VALUES (?, ?, ?, ?, ?, ?, ?)");

        preparedStatement.setString(1, this.patientID);
        preparedStatement.setString(2, ownerID);
        preparedStatement.setString(3, name);
        preparedStatement.setString(4, type);
        preparedStatement.setString(5, breed);
        preparedStatement.setDate(6, dob);

        preparedStatement.execute();
    }
}
