package Model;

import Control.AddOwnerController;

import java.sql.SQLException;

/**
 * Created by Sajeewa on 3/26/2017.
 */
public class AddOwnerModel {
    AddOwnerController addOwnerController;

    public AddOwnerModel(String ownerID) throws SQLException, ClassNotFoundException {
        addOwnerController = new AddOwnerController(ownerID);
    }

    public void addOwner(String first_name, String middle_initials, String last_name, String genderStr, String street_no, String street_name, String city, String state, String country, String email) throws SQLException {
        int gender;

        if (genderStr.toLowerCase()=="male"){
            gender = 0;
        } else {
            gender = 1;
        }

        addOwnerController.createOwner(first_name,middle_initials, last_name, gender, street_no, street_name, city, state, country, email);
    }
}
