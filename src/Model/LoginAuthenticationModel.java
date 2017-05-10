package Model;

import Control.LoginController;

import java.sql.SQLException;

/**
 * Created by Sajeewa on 3/24/2017.
 */
public class LoginAuthenticationModel {

    private String inputUserName;
    private String inputPassword;
    private String inputHashedPassword;

    private LoginController loginController;
    private HashPasswordModel hashPasswordModel;

    public LoginAuthenticationModel() throws SQLException, ClassNotFoundException {
        loginController = new LoginController();
    }


    public String getInputHashedPassword(){
        return inputHashedPassword;
    }

    public String getInputUserName() {
        return inputUserName;
    }

    public void setInputUserName(String inputUserName) {
        this.inputUserName = inputUserName;
    }

    private String getInputPassword() {
        return inputPassword;
    }

    public void setInputPassword(String inputPassword) {
        this.inputPassword = inputPassword;
    }


    //from database
    private boolean findUserName() throws SQLException {
        //call LoginController to find the given username
        boolean foundUser = loginController.findUser(this.getInputUserName());

        if(foundUser){
            return true;
        }

        return false;
    }

    private String[] getUserDetails() throws SQLException {
        //call LoginController class to find the given hashed password and the status of the user
        String[] userDetails = loginController.getUserdetails(this.getInputUserName());

        return userDetails;
    }

    //--

    public boolean authenticate(String inputUserName, String inputPassword) throws SQLException {

        hashPasswordModel = new HashPasswordModel();

        //default authentication
        boolean authenticated = false;

        //set input details
        setInputUserName(inputUserName);
        setInputPassword(inputPassword);

        //call hash function
        inputHashedPassword = hashPasswordModel.hashFunction(inputPassword);

        //get details from the Database
        boolean foundUser = this.findUserName();

        if (foundUser){
            String[] userDetails = getUserDetails();


            if (inputHashedPassword.equals(userDetails[0])){
                if (userDetails[1].equals("true")){
                    authenticated = true;
                }
            }
        }

        return authenticated;
    }
}
