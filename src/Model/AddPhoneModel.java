package Model;

import Control.AddPhoneController;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Sajeewa on 3/26/2017.
 */
public class AddPhoneModel {

    AddPhoneController addPhoneController;

    public AddPhoneModel(String userID) throws SQLException, ClassNotFoundException {
        addPhoneController = new AddPhoneController(userID);
    }

    public void addPhone(ArrayList<String> phone) throws SQLException {
        addPhoneController.phoneDetails(phone);
    }
}
