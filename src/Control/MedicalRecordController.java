package Control;

import Model.MedicalRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

/**
 * Created by Sajeewa on 5/15/2017.
 */
public class MedicalRecordController {
    MySQLCon mySQLCon;
    Connection connection;
    PreparedStatement preparedStatement;
    Statement statement;
    ResultSet resultSet;

    private ObservableList<MedicalRecord> medicalRecordObservableList;
    private MedicalRecord medicalRecord;
    private SpecialControl specialControl;

    public MedicalRecordController() throws SQLException, ClassNotFoundException {
        mySQLCon = new MySQLCon();
        connection = mySQLCon.connectDatabase();

        specialControl = new SpecialControl();
    }

    //load current records//
    public ObservableList<MedicalRecord> getMedicalRecords() throws SQLException {
        medicalRecordObservableList = FXCollections.observableArrayList();
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM medical_case");

        while (resultSet.next()){
            medicalRecord = new MedicalRecord(resultSet.getString("caseID"),
                    resultSet.getDate("caseDate").toString(), resultSet.getString("patientID"),
                    resultSet.getString("doctorID"), resultSet.getString("case_type"), resultSet.getString("comments"));

            medicalRecordObservableList.add(medicalRecord);
        }

        return medicalRecordObservableList;
    }
    //--------//


    //get current medical case ID//
    public String getCurrentCaseID() throws SQLException {
        String currentCaseID = "";

        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT caseID FROM medical_case ORDER BY caseID DESC LIMIT 1");

        while (resultSet.next()){
            currentCaseID = resultSet.getString("caseID");
        }

        return currentCaseID;
    }
    //---------//


}
