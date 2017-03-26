package View.User_Management;/**
 * Created by Sajeewa on 3/26/2017.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AddUserView extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AddUserView.fxml"));
//        AnchorPane rootLayout = (AnchorPane) root.load();
        Scene scene = new Scene(root, 421, 526);

//        scene.getStylesheets().add(getClass().getClassLoader().getResource("/style/myStyle.css").toExternalForm());

//        URL url = this.getClass().getClassLoader().getResource("/UserStyleSheet.css");
//        if (url == null) {
//            System.out.println("Resource not found. Aborting.");
//            System.exit(-1);
//        }
//        String css = url.toExternalForm();
//        scene.getStylesheets().add(css);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
