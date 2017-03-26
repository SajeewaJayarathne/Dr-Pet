package Model;

import Control.GetOwnerController;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Sajeewa on 3/27/2017.
 */
public class GetOwnerModel {
    GetOwnerController getOwnerController;

    public GetOwnerModel() throws SQLException, ClassNotFoundException {
        getOwnerController = new GetOwnerController();
    }

    //get owner IDs
    public ArrayList<String> getOwnerIDs() throws SQLException {
        return getOwnerController.getOwnerIDs();
    }

    //get name of a particular owner
    public String getOwnerName(String ownerID) throws SQLException {
        return getOwnerController.getOwnerName(ownerID);
    }
}
