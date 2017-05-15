package Control;

import Model.Owner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Sajeewa on 3/26/2017.
 */
public class OwnerController {
    MySQLCon mySQLCon;
    Connection connection;
    PreparedStatement preparedStatement;
    Statement statement;
    ResultSet resultSet;
    private ObservableList<Owner> ownerObservableList;
    private Owner owner;
    private SpecialControl specialControl;
    private PhoneController phoneController;

    private String ownerID;
    private String phone;

    public OwnerController(String ownerID) throws SQLException, ClassNotFoundException {
        this.ownerID = ownerID;
        mySQLCon = new MySQLCon();
        connection = mySQLCon.connectDatabase();
    }

    public OwnerController() throws SQLException, ClassNotFoundException {
        mySQLCon = new MySQLCon();
        connection = mySQLCon.connectDatabase();
        statement = connection.createStatement();
    }

    //create owner
    public void createOwner(Owner owner) throws SQLException {
        specialControl = new SpecialControl();

        preparedStatement = connection.prepareStatement("INSERT INTO owner_info VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        preparedStatement.setString(1, this.ownerID);
        preparedStatement.setString(2, owner.getFirst_name());
        preparedStatement.setString(3, owner.getMiddle_initials());
        preparedStatement.setString(4, owner.getLast_name());
        preparedStatement.setInt(5, specialControl.getGenderInt(owner.getGender()));
        preparedStatement.setString(6, owner.getStreet_no());
        preparedStatement.setString(7, owner.getStreet_name());
        preparedStatement.setString(8, owner.getCity());
        preparedStatement.setString(9, owner.getState());
        preparedStatement.setString(10, owner.getCountry());
        preparedStatement.setString(11, owner.getEmail());

        preparedStatement.execute();
    }

    //get Owner IDs
    private ObservableList<String> ownerIDs;

    public ObservableList<String> getOwnerIDs() throws SQLException {
        ownerIDs  = FXCollections.observableArrayList();
        resultSet = statement.executeQuery("SELECT own_id FROM owner_info");

        while (resultSet.next()){
            ownerIDs.add(resultSet.getString("own_id"));
        }

        return ownerIDs;
    }

    //get name of a particular owner
    public String getOwnerName(String ownerID){
        String ownerFName = "";
        String ownerMName = "";
        String ownerLName = "";
        String oName;
        try {
            preparedStatement = connection.prepareStatement("SELECT first_name, middle_initials, last_name FROM owner_info WHERE own_id=?");
            preparedStatement.setString(1, ownerID);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                ownerFName = resultSet.getString("first_name");
                ownerMName = resultSet.getString("middle_initials");
                ownerLName = resultSet.getString("last_name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        oName = ownerFName+" "+ownerMName+" "+ownerLName;
        System.out.println(oName);
        return oName;
    }

    //get owner details//
    public ObservableList<Owner> getOwnerDetails() throws SQLException, ClassNotFoundException {

        ArrayList<String> phoneNumbers;
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM owner_info");

        while (resultSet.next()){
            phoneController = new PhoneController(resultSet.getString("own_id"));
            phoneNumbers = phoneController.getPhoneNumbers(resultSet.getString("own_id"));

            phone = phoneNumbers.get(0);
            for (int i = 1; i < phoneNumbers.size(); i++){
                phone += ", "+phoneNumbers.get(i);
            }

            owner = new Owner(resultSet.getString("own_id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("middle_initials"),
                    resultSet.getString("last_name"),
                    resultSet.getInt("gender"),
                    resultSet.getString("street_no"),
                    resultSet.getString("street_name"),
                    resultSet.getString("city"),
                    resultSet.getString("state"),
                    resultSet.getString("country"),
                    resultSet.getString("email"),
                    phone);

            ownerObservableList.add(owner);
        }
        return ownerObservableList;
    }


    //--get patient details--//
}
