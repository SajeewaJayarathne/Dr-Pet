package Control;

import Model.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

/**
 * Created by Sajeewa on 3/27/2017.
 */
public class PatientController {
    MySQLCon mySQLCon;
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    private String patientID;
    private Patient patient;
    private ObservableList<Patient> patientObservableList;
    private String ownerID, ownerName;
    private OwnerController ownerController;
    private SpecialControl specialControl;


    public PatientController(String patientID) throws SQLException, ClassNotFoundException {
        this.patientID = patientID;
        mySQLCon = new MySQLCon();
        connection = mySQLCon.connectDatabase();
    }

    public PatientController() throws SQLException, ClassNotFoundException {
        mySQLCon = new MySQLCon();
        connection = mySQLCon.connectDatabase();
        ownerController = new OwnerController();
    }

    //create patient//
    public void createPatient(Patient patient) throws SQLException {
        Date dob = specialControl.getSQLDate(patient.getDate_of_birth());

        preparedStatement = connection.prepareStatement("INSERT INTO patient VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

        preparedStatement.setString(1, this.patientID);
        preparedStatement.setString(2, patient.getOwner_ID());
        preparedStatement.setString(3, patient.getName());
        preparedStatement.setInt(4, specialControl.getGenderInt(patient.getGender()));
        preparedStatement.setString(5, patient.getType());
        preparedStatement.setString(6, patient.getBreed());
        preparedStatement.setDate(7, dob);
        preparedStatement.setDate(8, null);

        preparedStatement.execute();
    }
    //--create patient--//

    //update patient//
    public void updatePatient(String ownerID, String name, int gender, Date dob, Date dod) throws SQLException {
        preparedStatement = connection.prepareStatement("UPDATE patient SET owner_id=?, name=?, gender=?, date_of_birth=?, date_of_death=?");
        preparedStatement.setString(1, ownerID);
        preparedStatement.setString(2, name);
        preparedStatement.setInt(3, gender);
        preparedStatement.setDate(4, dob);
        preparedStatement.setDate(5, dod);

        preparedStatement.execute();
    }
    //--update patient--//

    //get patient details//
    public ObservableList<Patient> getPatientDetails() throws SQLException {

        patientObservableList = FXCollections.observableArrayList();
        preparedStatement = connection.prepareStatement("SELECT * FROM patient WHERE date_of_death IS NULL");
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            ownerID = resultSet.getString("owner_id");
            this.ownerName = ownerController.getOwnerName(ownerID);

            patient = new Patient(resultSet.getString("pet_id"),
                    resultSet.getString("name"),
                    ownerName, resultSet.getInt("gender"), resultSet.getString("type"),
                    resultSet.getString("breed"),
                    resultSet.getDate("date_of_birth").toString());

            patientObservableList.add(patient);
        }
        return patientObservableList;
    }
    //--get patient details--//
}
