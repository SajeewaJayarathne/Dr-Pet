package View.MainView;

import Control.FollowUpController;
import Model.FollowUp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FollowUpViewControl implements Initializable{

    private String caseID;
    private FollowUpController followUpController;
    private FollowUp followUp;

    public FollowUpViewControl(String caseID){
        this.caseID = caseID;
    }


    @FXML
    Label followUpID;

    @FXML
    TextField reason;

    @FXML
    DatePicker followUpDate;

    @FXML
    TextArea followUpDescription;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        followUpID.setText(this.caseID);
    }

    @FXML
    private void handleCreateFollowUp() throws SQLException, ClassNotFoundException {
        followUp = new FollowUp(this.caseID, reason.getText(), followUpDate.getValue().toString(), followUpDescription.getText(), true);
        followUpController = new FollowUpController();
        followUpController.createFollowUp(followUp);
    }

}
