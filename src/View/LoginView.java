package View;/**
 * Created by Sajeewa on 3/24/2017.
 */
import Model.LoginAuthentication;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;


public class LoginView extends Application{

    LoginAuthentication loginAuthentication;
    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Dr. Pet Login");

        primaryStage.setHeight(320);
        primaryStage.setWidth(500);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10,50,50,50));

        //Adding HBox
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(20,20,20,30));

        //GridPane with 10px padding around edge
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20,20,20,20));
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        gridPane.getColumnConstraints().add(new ColumnConstraints(100));

        //Implementing Nodes for GridPane
        Label usernameLabel = new Label("Username");
        final TextField usernameInput = new TextField();
        usernameInput.setPrefWidth(210);
        Label passwordLabel = new Label("Password");
        final PasswordField passwordInput = new PasswordField();
        passwordInput.setPrefWidth(200);
        Button loginButton = new Button("Login");
        final Label messageLabel = new Label();

        //Action for Login Button
        loginButton.setOnAction(e -> {
            try {
                login(usernameInput.getText(), passwordInput.getText().toString(), primaryStage);
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        });

        //Adding Nodes to GridPane layout
        gridPane.add(usernameLabel, 0, 0);
        gridPane.add(usernameInput, 1, 0);
        gridPane.add(passwordLabel, 0, 1);
        gridPane.add(passwordInput, 1, 1);
        gridPane.add(loginButton, 1, 3);
        gridPane.add(messageLabel, 1, 2);

        gridPane.setHalignment(usernameLabel, HPos.RIGHT);
        gridPane.setHalignment(passwordLabel, HPos.RIGHT);

        //Reflection for gridPane
        Reflection reflection = new Reflection();
        reflection.setFraction(0.7f);
        gridPane.setEffect(reflection);

        //DropShadow effect
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(5);
        dropShadow.setOffsetY(5);

        //Adding text and DropShadow effect to it
        Text text = new Text("WELCOME");
        text.setFont(Font.font("Tahoma", FontWeight.BOLD, 28));
        text.setEffect(dropShadow);
        text.setTextAlignment(TextAlignment.CENTER);

        //Adding text to HBox
        hBox.getChildren().add(text);
        hBox.setAlignment(Pos.CENTER);

        //Add ID's to Nodes
        borderPane.setId("borderPane");
        gridPane.setId("root");
        loginButton.setId("loginButton");
        text.setId("text");

        //Add HBox and GridPane layout to BorderPane Layout
        BorderPane.setAlignment(hBox, Pos.CENTER);
        borderPane.setTop(hBox);
        borderPane.setCenter(gridPane);

        Scene scene = new Scene(borderPane);

        //set stylesheet to the view
        URL url = this.getClass().getResource("LoginStyleSheet.css");
        if (url == null) {
            System.out.println("Resource not found. Aborting.");
            System.exit(-1);
        }
        String css = url.toExternalForm();
        scene.getStylesheets().add(css);

        primaryStage.setScene(scene);
//        primaryStage.titleProperty().bind(scene.widthProperty().asString().concat(" : ").concat(scene.heightProperty().asString()));

        primaryStage.setResizable(false);

        primaryStage.show();
    }

    private void login(String username, String password, Stage stage) throws SQLException, ClassNotFoundException {
        loginAuthentication = new LoginAuthentication();

        boolean authenticated = loginAuthentication.authenticate(username, password);

        if (authenticated){
            System.out.println("login successful!");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR LOGIN");
            alert.setHeaderText(null);
            alert.setContentText("Username and/or Password Mismatch!");
            alert.showAndWait();
        }
    }
}
