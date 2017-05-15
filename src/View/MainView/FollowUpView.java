package View.MainView;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Sajeewa on 3/26/2017.
 */

public class FollowUpView{

    public void displayFollowUp () throws Exception {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FollowUpView.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
