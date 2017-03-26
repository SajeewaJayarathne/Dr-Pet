package View.Patient_Management;

import Model.AddPatientModel;
import Model.GetOwnerModel;
import View.PatientOwner_Management.AddOwnerView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddPatientViewControl implements Initializable{

    AddPatientModel addPatientModel;
    GetOwnerModel getOwnerModel;
    AddOwnerView addOwnerView;

    @FXML
    Button createButton, clearButton, addNewOwner, homeButton;

    @FXML
    TextField patientID, patient_name, breed;

    @FXML
    RadioButton maleRadio, femaleRadio;

    @FXML
    DatePicker dob;

    @FXML
    ComboBox ownerIDCombo, petTypeCombo;

    @FXML
    Label ownerNameLabel;

    @FXML
    private ObservableList<String> ownerIDData;

    @FXML
    public void handleCreatePatient() throws SQLException, ClassNotFoundException, FileNotFoundException, ParseException {

        if (patientID.getText().isEmpty() || ownerIDCombo.getSelectionModel().getSelectedItem().toString().equals("")
                || ownerIDCombo.getSelectionModel().getSelectedItem().toString().isEmpty()
                || patient_name.getText().isEmpty() || petTypeCombo.getSelectionModel().getSelectedItem().toString().equals("")
                || petTypeCombo.getSelectionModel().getSelectedItem().toString().isEmpty() || breed.getText().isEmpty()
                || dob.getValue().equals(null)){

//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Empty Fields!");
//            alert.setHeaderText(null);
//            alert.setContentText("Please Fill in the Mandatory Fields.");
//            alert.showAndWait();

            System.out.println("jfksjk");
        } else {

            String gender = "male";

            //getting the selected radio button
            if (femaleRadio.isSelected()){
                gender = "female";
            }

            addPatientModel.addPatient(ownerIDCombo.getSelectionModel().getSelectedItem().toString(), patient_name.getText(), petTypeCombo.getSelectionModel().getSelectedItem().toString(), breed.getText(), dob.getValue().toString());
        }
    }

    @FXML
    public void handleClearFields(){
        patient_name.setText("");
        maleRadio.setSelected(true);
        breed.setText("");
    }

    @FXML
    public void handleHomeButton(){

    }

    @FXML
    public void handleNewOwner(){
        //load addNewOwner UX
        addOwnerView = new AddOwnerView();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //get all current Owner IDs
        try {
            getOwnerModel = new GetOwnerModel();
            ArrayList<String> ownerIDs = getOwnerModel.getOwnerIDs();

            ownerIDData = FXCollections.observableArrayList();

            //adding data to the comboBoxList
            for (int i = 0; i < ownerIDs.size(); i++){
                ownerIDData.add(ownerIDs.get(i));
            }

            ownerIDCombo.setItems(ownerIDData);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleOwnerDetails() throws SQLException {
        //when an owner ID is clicked
        String ownerName = getOwnerModel.getOwnerName(ownerIDCombo.getSelectionModel().getSelectedItem().toString());
        ownerNameLabel.setText(ownerName);
    }
}
