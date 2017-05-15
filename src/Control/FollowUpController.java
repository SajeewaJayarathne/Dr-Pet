package Control;

import Model.FollowUp;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Sajeewa on 5/15/2017.
 */
public class FollowUpController {
    MySQLCon mySQLCon;
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    private FollowUp followUp;
    private ArrayList<FollowUp> followUpArrayList;

    private SpecialControl specialControl;

    public FollowUpController() throws SQLException, ClassNotFoundException {
        mySQLCon = new MySQLCon();
        connection = mySQLCon.connectDatabase();

        specialControl = new SpecialControl();

        followUpArrayList = new ArrayList<>();
    }

    //create follow-up//
    public void createFollowUp(FollowUp followUp){

        try {
            preparedStatement = connection.prepareStatement("INSERT INTO follow_up VALUES(?, ?, ?, ?)");
            preparedStatement.setString(1, followUp.getCaseID());
            preparedStatement.setString(2, followUp.getReason());
            preparedStatement.setDate(3, specialControl.getSQLDate(followUp.getFollowUpDate()));
            preparedStatement.setString(4, followUp.getDescription());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    //--create follow-up--//


    //load all follow-ups//
    public ArrayList<FollowUp> loadFollowUps(String currentDate) throws SQLException {
            preparedStatement = connection.prepareStatement("SELECT * FROM follow_up WHERE due_date=?");
            preparedStatement.setDate(1, specialControl.getSQLDate(currentDate));

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                followUp = new FollowUp(resultSet.getString("med_id"), resultSet.getString("reason"),
                        resultSet.getDate("due_date").toString(), resultSet.getString("description"),
                        specialControl.getIsActiveBoolean(resultSet.getInt("isActive")));

                followUpArrayList.add(followUp);
            }

            return followUpArrayList;

    }
    //--load all follow-ups--//
}
