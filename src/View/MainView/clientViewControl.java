package View.MainView;

import Control.OwnerController;
import Control.PhoneController;
import Control.SpecialControl;
import Model.Owner;
import Model.Phone;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;

public class clientViewControl implements Initializable{

    PhoneController phoneController;
    OwnerController ownerController;
    Owner owner;
    private SpecialControl specialControl;
    private ObservableList<Owner> ownerObservableList;

    private int addButtonClickCount = 0;

    @FXML
    Button createButton, clearButton, addPhone, editButton;

    @FXML
    TextField clientID, first_name, middle_initials, last_name, street_no, street_name, city, state, country, email, phone1, phone2, phone3;

    @FXML
    RadioButton maleRadio, femaleRadio;

    @FXML
    TableView<Owner> tableClientDetails;

    @FXML
    TableColumn<Owner, String> tableColID, tableColName, tableColAddress, tableColPhone, tableColEmail, tableColGender;

    @FXML
    Tab viewClientsTab, addClientTab, editClientTab;

    @FXML
    TabPane tabPane;

    //---------------//
    //add client tab//
    //--------------//
    @FXML
    public void handleCreateClient() throws SQLException, ClassNotFoundException, FileNotFoundException, ParseException {

        if (clientID.getText().isEmpty() || first_name.getText().isEmpty() || last_name.getText().isEmpty()
                || street_no.getText().isEmpty() || city.getText().isEmpty() || country.getText().isEmpty() || phone1.getText().isEmpty()){

            specialControl.alertBox("Empty Fields!", "Please fill in the mandatory fields...");

        } else {

            String gender = "Male";

            //getting the selected radio button
            if (femaleRadio.isSelected()){
                gender = "Female";
            }

            //phone numbers
            String phone;
            ObservableList<Phone> phoneNumberList = FXCollections.observableArrayList();

            phone = phone1.getText();
            phoneNumberList.add(new Phone(clientID.getText(), phone1.getText()));


            if (!phone2.getText().isEmpty()){
                phone += ", " + phone2.getText();
                phoneNumberList.add(new Phone(clientID.getText(), phone2.getText()));
            }
            if (!phone3.getText().isEmpty()){
                phone += ", " + phone3.getText();
                phoneNumberList.add(new Phone(clientID.getText(), phone3.getText()));
            }
            phoneController = new PhoneController(clientID.getText());
            phoneController.addPhoneDetails(phoneNumberList);

            //--phone numbers--//

            //add owner//
            owner = new Owner(clientID.getText(), first_name.getText(), middle_initials.getText(), last_name.getText(),
                    specialControl.getGenderInt(gender), street_no.getText(), street_name.getText(), city.getText(),
                    state.getText(), country.getText(), email.getText(), phone);

            ownerController = new OwnerController(clientID.getText());
            ownerController.createOwner(owner);

            //--add owner--//
        }
    }

    @FXML
    public void handleClearFields(){
        first_name.setText("");
        middle_initials.setText("");
        last_name.setText("");
        street_no.setText("");
        street_name.setText("");
        city.setText("");
        state.setText("");
        country.setText("");
        maleRadio.setSelected(true);
        email.setText("");
        phone1.setText("");
        phone2.setText("");
        phone2.setVisible(false);
        phone3.setText("");
        phone3.setVisible(false);
        addPhone.setDisable(false);
    }

    @FXML
    public void handlePhone(){
        addButtonClickCount++;
        switch (addButtonClickCount) {
            case 1:
                phone2.setVisible(true);
                break;
            case 2:
                phone3.setVisible(true);
                addPhone.setDisable(true);
                break;
        }
    }
    //-----add client tab------//
    //-------------------------//


    //view client tab//

    private void loadClients(){
        editButton.setOnAction(e -> editButtonClicked());

        tableColID.setCellValueFactory(new PropertyValueFactory<>("own_id"));
        tableColName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        tableColAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tableColPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        tableColEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tableClientDetails.setItems(ownerObservableList);
    }

    private void editButtonClicked() {
        Owner o = tableClientDetails.getSelectionModel().getSelectedItem();
        tabPane.getSelectionModel().select(editClientTab);
    }

    @FXML
    public void handleCloseButton(){
        MainViewControl mainViewControl = new MainViewControl();
        mainViewControl.handleCloseButton();
    }

    //--view client tab--//




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        specialControl = new SpecialControl();

        //clients
        ownerObservableList = FXCollections.observableArrayList();
        try {
            ownerController = new OwnerController();
            ownerObservableList = ownerController.getOwnerDetails();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        loadClients();
    }

}
