package Control;


/**
 * Created by Sajeewa on 3/24/2017.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MySQLCon {

    public Connection connectDatabase() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/drpet", "root", "root");

        return connection;
    }
}
