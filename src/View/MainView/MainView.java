package View.MainView;/**
 * Created by Sajeewa on 3/26/2017.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class MainView extends Application {

    public static String mainViewID = "mainVIew";
    public static String mainViewFile = "mainView.fxml";
    public static String patientViewID = "patientView";
    public static String patientViewFile = "patientView.fxml";
    public static String clientViewID = "clientView";
    public static String clientViewFile = "clientView.fxml";
    public static String userViewID = "userView";
    public static String userViewFile = "userView.fxml";
    public static String medicalRecordViewID = "medicalRecordView";
    public static String medicalRecordViewFile = "medicalRecordView.fxml";
    public static String paymentViewID = "paymentView";
    public static String paymentViewFile = "paymentView.fxml";
    public static String notificationViewID = "notificationView";
    public static String notificationViewFile = "notificationView.fxml";
    public static String messageViewID = "messageView";
    public static String messageViewFile = "messageView.fxml";
    public static String myProfileViewID = "myProfileView";
    public static String myProfileViewFile = "myProfileView.fxml";
    public static String searchViewID = "searchView";
    public static String searchViewFile = "searchView.fxml";
    public static String backupViewID = "backupView";
    public static String backupViewFile = "backupView.fxml";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

//        ScreensController mainContainer = new ScreensController();
//
//        mainContainer.loadScreen(MainView.mainViewID, MainView.mainViewFile);
//        mainContainer.loadScreen(MainView.patientViewID, MainView.patientViewFile);
//        mainContainer.loadScreen(MainView.clientViewID, MainView.clientViewFile);
//        mainContainer.loadScreen(MainView.userViewID, MainView.userViewFile);
//        mainContainer.loadScreen(MainView.medicalRecordViewID, MainView.medicalRecordViewFile);
//        mainContainer.loadScreen(MainView.paymentViewID, MainView.paymentViewFile);
//        mainContainer.loadScreen(MainView.notificationViewID, MainView.notificationViewFile);
//        mainContainer.loadScreen(MainView.messageViewID, MainView.messageViewFile);
//        mainContainer.loadScreen(MainView.myProfileViewID, MainView.myProfileViewFile);
//        mainContainer.loadScreen(MainView.searchViewID, MainView.searchViewFile);
//        mainContainer.loadScreen(MainView.backupViewID, MainView.backupViewFile);
//
//        mainContainer.setScreen(MainView.mainViewID);
//
//

        Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
//        AnchorPane rootLayout = (AnchorPane) root.load();
        Scene scene = new Scene(root, 1280, 800);
        primaryStage.setScene(scene);


        primaryStage.setMaximized(true);
        primaryStage.show();

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
    }
}
