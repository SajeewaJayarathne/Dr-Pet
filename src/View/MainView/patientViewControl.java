package View.MainView;

import Control.OwnerController;
import Control.PatientController;
import Control.SpecialControl;
import Model.Patient;
import View.PatientOwner_Management.AddOwnerView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class patientViewControl implements Initializable{

    OwnerController ownerController;
    AddOwnerView addOwnerView;
    PatientController getPatientController, patientController;
    private ObservableList<String> ownerIDData;
    private ObservableList<Patient> patientObservableList;
    private SpecialControl specialControl;
    private Patient patient;


    @FXML
    Button createButton, clearButton, addNewOwner, editButton, addNewOwner_edit, updateButton, clearButton_edit;

    @FXML
    TextField patientID, patient_name, breed, patient_name_edit;

    @FXML
    RadioButton maleRadio, femaleRadio, maleRadio_edit, femaleRadio_edit;

    @FXML
    DatePicker dob, dob_edit, dod_edit;

    @FXML
    ComboBox ownerIDCombo, petTypeCombo, ownerIDCombo_edit;

    @FXML
    Label ownerNameLabel, patient_id;

    @FXML
    ImageView imageLoader;

    @FXML
    TableView<Patient> tablePatientDetails;

    @FXML
    TableColumn<Patient, String> tableColID, tableColName, tableColOwner, tableColGender, tableColType, tableColBreed, tableColDoB;

    @FXML
    Tab viewPatientsTab, addPatientTab, editPatientTab;

    @FXML
    TabPane tabPane;

    @FXML
    CheckBox checkDoD;

    //create patient tab//
    @FXML
    public void handleCreatePatient() throws SQLException, ClassNotFoundException, FileNotFoundException, ParseException {

        if (patientID.getText().isEmpty() || ownerIDCombo.getSelectionModel().getSelectedItem().toString().equals("")
                || ownerIDCombo.getSelectionModel().getSelectedItem().toString().isEmpty()
                || patient_name.getText().isEmpty() || petTypeCombo.getSelectionModel().getSelectedItem().toString().equals("")
                || petTypeCombo.getSelectionModel().getSelectedItem().toString().isEmpty() || breed.getText().isEmpty()
                || dob.getValue().equals(null)){

            specialControl.alertBox("Empty Fields!", "Please fill in the mandatory fields...");

        } else {

            String gender = "Male";

            //getting the selected radio button
            if (femaleRadio.isSelected()){
                gender = "Female";
            }

            patient = new Patient(patientID.getText(), ownerIDCombo.getSelectionModel().getSelectedItem().toString(),
                    patient_name.getText(), specialControl.getGenderInt(gender), petTypeCombo.getSelectionModel().getSelectedItem().toString(),
                    breed.getText(), dob.getValue().toString());

            patientController = new PatientController(patientID.getText());


            System.out.println("patient created!");
            patientController.createPatient(patient);
        }
    }

    @FXML
    public void handleClearFields(){
        patient_name.setText("");
        maleRadio.setSelected(true);
        breed.setText("");
    }

    @FXML
    public void handleNewOwner(){
        //load addNewOwner UX
        addOwnerView = new AddOwnerView();

    }

    @FXML
    public void handleOwnerDetails() throws SQLException {
        //when an owner ID is clicked
        String ownerName = ownerController.getOwnerName(ownerIDCombo.getSelectionModel().getSelectedItem().toString());
        ownerNameLabel.setText(ownerName);
    }

    // end of create patient tab //

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        specialControl = new SpecialControl();

        dod_edit.setDisable(true);
        //set all current Owner IDs
        setOwnerIDs();

        //set types to combo box
        petTypeCombo.getItems().removeAll(petTypeCombo.getItems());
        petTypeCombo.getItems().addAll("Dog", "Cat", "Rabbit", "Bird");

        //patients
        patientObservableList = FXCollections.observableArrayList();
        try {
            getPatientController = new PatientController();
            patientObservableList = getPatientController.getPatientDetails();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        loadPatients();
    }

    private void setOwnerIDs() {
        try {
            ownerController = new OwnerController();

            ownerIDData = FXCollections.observableArrayList();
            ownerIDData = ownerController.getOwnerIDs();

            ownerIDCombo.setItems(ownerIDData);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //view patient tab//

    private void loadPatients(){
        editButton.setOnAction(e -> editButtonClicked());

        tableColID.setCellValueFactory(new PropertyValueFactory<>("pet_id"));
        tableColName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColOwner.setCellValueFactory(new PropertyValueFactory<>("owner_name"));
        tableColGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        tableColType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tableColBreed.setCellValueFactory(new PropertyValueFactory<>("breed"));
        tableColDoB.setCellValueFactory(new PropertyValueFactory<>("date_of_birth"));
        tablePatientDetails.setItems(patientObservableList);
    }

    private void editButtonClicked() {
        Patient p = tablePatientDetails.getSelectionModel().getSelectedItem();
        tabPane.getSelectionModel().select(editPatientTab);
        patient_id.setText(p.getPet_id());
        patient_name_edit.setText(p.getName());
        if (p.getGender().equals("Male")){
            maleRadio_edit.setSelected(true);
        } else {
            femaleRadio_edit.setSelected(true);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate localDate = LocalDate.parse(p.getDate_of_birth(), formatter);
        dob_edit.setValue(localDate);
    }

    //edit patient tab
    @FXML
    private void checkBoxHandler(){
        if (checkDoD.isSelected()){
            dod_edit.setDisable(false);
        } else {
            dod_edit.setDisable(true);
        }
    }

    @FXML
    public void handleUpdatePatient() throws SQLException, ClassNotFoundException {

        String gender = "male";
        if (femaleRadio_edit.isSelected()){
            gender = "female";
        }
        patientController = new PatientController(patient_id.getText());

        if (checkDoD.isSelected()){
            patientController.updatePatient(ownerIDCombo_edit.getSelectionModel().getSelectedItem().toString(),
                    patient_name_edit.getText(), specialControl.getGenderInt(gender),
                    specialControl.getSQLDate(dob_edit.getValue().toString()), specialControl.getSQLDate(dod_edit.getValue().toString()));
        } else {
            patientController.updatePatient(ownerIDCombo_edit.getSelectionModel().getSelectedItem().toString(),
                    patient_name_edit.getText(), specialControl.getGenderInt(gender),
                    specialControl.getSQLDate(dob_edit.getValue().toString()), null);
        }
    }


    @FXML
    public void handleCloseButton(){
        MainViewControl mainViewControl = new MainViewControl();
        mainViewControl.handleCloseButton();
    }
}
