package Control;

import java.sql.*;

/**
 * Created by Sajeewa on 3/24/2017.
 */
public class MySQLCon {

    public Connection connectDatabase() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/drpet", "root", "root");

        return connection;
    }
}
