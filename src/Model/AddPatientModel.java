package Model;

import Control.AddPatientController;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Sajeewa on 3/27/2017.
 */
public class AddPatientModel {
    AddPatientController addPatientController;

    public AddPatientModel(String patientID) throws SQLException, ClassNotFoundException {
        addPatientController = new AddPatientController(patientID);
    }

    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

    public void addPatient(String ownerID, String name, String type, String breed, String dob) throws ParseException, SQLException {

        //converting the input date into sql Date data type
        java.util.Date util_dob = format.parse(dob);
        java.sql.Date sql_dob = new java.sql.Date(util_dob.getTime());

        addPatientController.createPatient(ownerID, name, type, breed, sql_dob);
    }
}
