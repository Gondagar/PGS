package pl.well.pool;

import pl.well.web.Proporties.MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Serfer on 08.10.2016.
 */
public class pool {
    static Connection connection = null;

    public static Connection getconnection() {
        if (connection == null)

        {
            try {

                connection = DriverManager.getConnection(MySQL.url, MySQL.user, MySQL.password);
                return connection;
            } catch (SQLException ex) {
                ex.printStackTrace();

            }
        }

        return connection;
    }

}