package Control;

import javafx.scene.control.Alert;

import java.sql.Date;

/**
 * Created by Sajeewa on 5/1/2017.
 */
public class SpecialControl {

    public int getGenderInt(String gender){
        if (gender.equals("Male")){
            return 0;
        } else {
            return 1;
        }
    }

    public int getMaritalStatusInt(String marital_status){
        if (marital_status.equals("Unmarried")){
            return 0;
        } else {
            return 1;
        }
    }
    public Date getSQLDate(String date){
        return Date.valueOf(date);
    }

    public void alertBox(String title, String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    public int getIsActiveInt(boolean is_active){
        if (is_active){
            return 1;
        } else {
            return 0;
        }
    }
}
