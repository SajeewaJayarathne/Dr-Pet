package View.Login;/**
 * Created by Sajeewa on 3/24/2017.
 */

import Model.LoginAuthenticationModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;


public class LoginView extends Application{

    LoginAuthenticationModel loginAuthenticationModel;
    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Dr. Pet Login");

        primaryStage.setHeight(450);
        primaryStage.setWidth(500);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10,50,50,50));

        //Adding VBox
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(20,20,20,30));


        VBox vBox1 = new VBox();
        vBox1.setSpacing(6);


        //Implementing Nodes for GridPane
        final TextField usernameInput = new TextField();
        usernameInput.setPromptText("Username");

        final PasswordField passwordInput = new PasswordField();
        passwordInput.setPromptText("Password");
        Button loginButton = new Button("Login");

        //Action for Login Button
        loginButton.setOnAction(e -> {
            try {
                Parent root;
                loginAuthenticationModel = new LoginAuthenticationModel();

                boolean authenticated = loginAuthenticationModel.authenticate(usernameInput.getText(), passwordInput.getText().toString());

                if (authenticated){
                    System.out.println("login successful!");
                    primaryStage.close();

                    FXMLLoader fxmlLoader = new FXMLLoader();
                    Stage mainStage = new Stage();
                    try {
                        root = fxmlLoader.load(getClass().getResource("../MainView/MainView.fxml"));
                        mainStage.setTitle("Dr.Pet");

                        Scene scene = new Scene(root, 1280, 800);
                        mainStage.setScene(scene);


                        mainStage.setMaximized(true);
                        mainStage.show();

                        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
                        mainStage.setX((primScreenBounds.getWidth() - mainStage.getWidth()) / 2);
                        mainStage.setY((primScreenBounds.getHeight() - mainStage.getHeight()) / 2);

                    } catch (IOException evt) {
                        evt.printStackTrace();
                    }

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR LOGIN");
                    alert.setHeaderText(null);
                    alert.setContentText("Username and/or Password Mismatch!");
                    alert.showAndWait();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        });

        //Adding Nodes to vBox1
        vBox1.getChildren().addAll(usernameInput, passwordInput, loginButton);
        vBox1.setAlignment(Pos.CENTER);


        //Reflection for pane
        Reflection reflection = new Reflection();
        reflection.setFraction(0.7f);
        vBox1.setEffect(reflection);

        //DropShadow effect
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(5);
        dropShadow.setOffsetY(5);


        Image image = new Image(getClass().getResourceAsStream("../full_logo_small.PNG"));
        Label logoLabel = new Label("", new ImageView(image));
        logoLabel.setMaxSize(50, 50);

        //Adding text and DropShadow effect to it
        Text text = new Text("WELCOME");
        text.setFont(Font.font("Tahoma", FontWeight.BOLD, 28));
        text.setEffect(dropShadow);
        text.setTextAlignment(TextAlignment.CENTER);

        //Adding text to HBox
        vBox.getChildren().addAll(logoLabel, text);
        vBox.setAlignment(Pos.CENTER);

        //Add ID's to Nodes
        borderPane.setId("borderPane");
        vBox1.setId("root");
        loginButton.setId("loginButton");
        text.setId("text");
        logoLabel.setId("logo");

        //Add HBox and GridPane layout to BorderPane Layout
        BorderPane.setAlignment(vBox, Pos.CENTER);
        borderPane.setTop(vBox);
        borderPane.setCenter(vBox1);

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

//        primaryStage.setResizable(false);

        primaryStage.setResizable(false);
        primaryStage.show();
    }

//    private void login(String username, String password, Stage stage) throws SQLException, ClassNotFoundException {
//        loginAuthenticationModel = new LoginAuthenticationModel();
//
//        boolean authenticated = loginAuthenticationModel.authenticate(username, password);
//
//        if (authenticated){
//            System.out.println("login successful!");
//            stage.close();
//
//            FXMLLoader fxmlLoader = new FXMLLoader();
//            Stage mainStage = new Stage();
//            try {
//                fxmlLoader.load(getClass().getResource("MainView.fxml"));
//                mainStage.setTitle("Dr.Pet");
//
//                Scene scene = new Scene(root, 1280, 800);
//                mainStage.setScene(scene);
//
//
//                mainStage.setMaximized(true);
//                mainStage.show();
//
//                Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
//                mainStage.setX((primScreenBounds.getWidth() - mainStage.getWidth()) / 2);
//                mainStage.setY((primScreenBounds.getHeight() - mainStage.getHeight()) / 2);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        } else {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("ERROR LOGIN");
//            alert.setHeaderText(null);
//            alert.setContentText("Username and/or Password Mismatch!");
//            alert.showAndWait();
//        }
//    }
}
