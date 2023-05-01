/**
 * DBConnection.java
 *
 * @author Justin Diaz
 * @version 1.0
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnection {
    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String JDBC_URL = "jdbc:derby:C:/Users/justi/Desktop/Java 2 - Spring 2023/babynames-derby/babynames";

    Connection conn;

    public DBConnection() {
        try {
            this.conn = DriverManager.getConnection(JDBC_URL);
            if (this.conn != null) {
                System.out.println("Connection Successful");
            }
        } catch (SQLException e) {
            System.err.println("Connection Failed.");
        }
    }
}
