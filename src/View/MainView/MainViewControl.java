package View.MainView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewControl implements Initializable {


    @FXML
    Pane contentLoader;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void gotoPatientView(ActionEvent event){
        try {
            contentLoader.getChildren().clear();
            contentLoader.getChildren().add(FXMLLoader.load(getClass().getResource("patientView.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void gotoClientView(ActionEvent event){
        try {
            contentLoader.getChildren().clear();
            System.out.println("before");
            contentLoader.getChildren().add(FXMLLoader.load(getClass().getResource("clientView.fxml")));
            System.out.println("after");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void gotoDoctorView(ActionEvent event){
        try {
            contentLoader.getChildren().clear();
            contentLoader.getChildren().add(FXMLLoader.load(getClass().getResource("userView.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleCloseButton(){
        contentLoader.getChildren().clear();
    }
}
