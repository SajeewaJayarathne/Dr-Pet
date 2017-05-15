package View.MainView;

import Control.MedicalRecordController;
import Control.SpecialControl;
import Model.MedicalRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MedicalRecordsViewControl implements Initializable{

    private FollowUpView followUpView;
    private ObservableList<MedicalRecord> medicalRecordObservableList;
    private MedicalRecord medicalRecord;
    private MedicalRecordController medicalRecordController;
    private SpecialControl specialControl;


    @FXML
    AnchorPane rootLayout;

    @FXML
    Button addFollowUpButton;

    @FXML
    TableView<MedicalRecord> tableMedicalRecordDetails;

    @FXML
    TableColumn<MedicalRecord, String> tableColCaseNo, tableColDate, tableColPatientID, tableColDoctor, tableColCaseType, tableColComments;

    @FXML
    TabPane tabPane;

    @FXML
    Tab viewMedicalRecordsTab, addEditMedicalRecordTab, vaccineTab, sicknessTab,
            examinationTab, testTab, diagnoseTab, treatmentTab, prescriptionTab;

    @FXML
    Label caseNoLabel, dateLabel;

    @FXML
    TextField patientID, doctorID, vaccine, testDone, testResult, testNotes, treatmentName, dosage,
            issuedDrugName, issuedDosage, issuedQuantity, pharmacyDrugName, pharmacyDosage, pharmacyQuantity;

    @FXML
    RadioButton vaccinationRadio, sicknessRadio;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        specialControl = new SpecialControl();

        try {
            medicalRecordController = new MedicalRecordController();
            loadRecords();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleAddRecord() throws SQLException {
        String lastCaseID = medicalRecordController.getCurrentCaseID();
        int lastCaseIDNum = Integer.parseInt(lastCaseID.substring(2));

        int newCaseIDNum = lastCaseIDNum+1;

        String newCaseID = "ME";
        String str = Integer.toString(newCaseIDNum);

        if (str.length() == 1){
            newCaseID += ("00"+str);
        } else if (str.length() == 2) {
            newCaseID += ("0"+str);
        } else {
            newCaseID += str;
        }

        tabPane.getSelectionModel().select(addEditMedicalRecordTab);
        caseNoLabel.setText(newCaseID);
        dateLabel.setText(specialControl.getCurrentDate());

    }

    @FXML
    public void handleViewRecord(){
        MedicalRecord mr = tableMedicalRecordDetails.getSelectionModel().getSelectedItem();
        caseNoLabel.setText(mr.getCaseID());
        patientID.setText(mr.getPatientID());
        doctorID.setText(mr.getDoctorID());
        dateLabel.setText(mr.getCaseDate());

        if (mr.getCaseType().equals("Sickness")) {
            sicknessRadio.setSelected(true);
        } else {
            vaccinationRadio.setSelected(true);
        }
    }

    @FXML
    public void handleAddFollowUp() throws Exception {
        followUpView = new FollowUpView();
        followUpView.displayFollowUp();
    }

    @FXML
    public void handleSaveVaccine(){}

    @FXML
    public void handleAddVaccine(){}

    @FXML
    public void handleSaveSickness(){}

    @FXML
    public void handleTestFileChooser(){}

    @FXML
    public void handleAddTestRow(){}

    @FXML
    public void handleTreatmentType(){}

    @FXML
    public void handleAddTreatmentRow(){}

    @FXML
    public void handleAddIssuedRow(){}

    @FXML
    public void handleAddPharmacyRow(){}

    @FXML
    public void handleIssuedDeleteRow(){}

    @FXML
    public void handlePharmacyDeleteRow(){}

    @FXML
    public void handleTreatmentDeleteRow(){}

    @FXML
    public void handleTestDeleteRow(){}

    @FXML
    public void handleDeleteVaccine(){}

    @FXML
    public void handleCloseButton(){
        rootLayout.getChildren().clear();

    }

    //load medical records
    public void loadRecords() throws SQLException, ClassNotFoundException {

        //initialize observableArrayList
        medicalRecordObservableList = FXCollections.observableArrayList();

        medicalRecordObservableList = medicalRecordController.getMedicalRecords();

        tableColCaseNo.setCellValueFactory(new PropertyValueFactory<MedicalRecord, String>("caseID"));
        tableColDate.setCellValueFactory(new PropertyValueFactory<MedicalRecord, String>("caseDate"));
        tableColPatientID.setCellValueFactory(new PropertyValueFactory<MedicalRecord, String>("patientID"));
        tableColDoctor.setCellValueFactory(new PropertyValueFactory<MedicalRecord, String>("doctorID"));
        tableColCaseType.setCellValueFactory(new PropertyValueFactory<MedicalRecord, String>("caseType"));
        tableColComments.setCellValueFactory(new PropertyValueFactory<MedicalRecord, String>("comments"));

        tableMedicalRecordDetails.setItems(medicalRecordObservableList);
    }
    //-------------------------//

}
