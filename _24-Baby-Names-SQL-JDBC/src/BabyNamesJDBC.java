/**
 * BabyNamesJDBC.java
 * Uses sample Apache Derby program to use SQL query BABYNAMES database
 *
 * @author Justin Diaz
 * @version 1.0
 * <p>
 * Source: Apache Derby
 * https://db.apache.org/derby/integrate/plugin_help/derby_app.html
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;


public class BabyNamesJDBC {
    private static String dbURL = "jdbc:derby:C:\\Users\\justi\\Desktop\\Java 2 - Spring 2023\\babynames-derby\\babynames\\BABYNAMES;create=true;user=USER;password=PASSWORD";
    private static String tableName = "restaurants";
    // jdbc Connection
    private static Connection conn = null;
    private static Statement stmt = null;

    public static void main(String[] args) {
        createConnection();
        numberOne();
        numberTwo();
        numberThree();
        numberFour();
        numberFive();
        numberSix();
        numberSeven();
        numberEight(10000000);
        numberNine();
        shutdown();
    }

    private static void createConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            //Get a connection
            conn = DriverManager.getConnection(dbURL);
        } catch (Exception except) {
            except.printStackTrace();
        }
    }

    // 7. Write a query to add the following row:
    /*
        id: 10000000
        name: ‘Joseph’
        date_year: 2016
        gender: ‘M’
        us_state: ‘PA’
        num_babies: ‘476’
     */
    private static void numberSeven() {
        try {
            stmt = conn.createStatement();
            stmt.execute("INSERT INTO " + tableName +
                    "(ID, NAME, DATE_YEAR, GENDER, US_STATE, NUM_BABIES)" +
                    " VALUES (10000000, 'Joseph', 2016, 'M', 'PA', 476)");
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
    }

    // 8. Write a query to delete the row you just added.
    private static void numberEight(int id) {
        try {
            stmt = conn.createStatement();
            stmt.execute("DELETE FROM " + tableName + "WHERE ID = " + id);
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
    }

    // 9. (Bonus) What was the most common boy’s name across the United States in 1991
    // (Hint: use “SUM” and "GROUP BY")
    private static void numberNine() {
        try {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from " + tableName);
            ResultSetMetaData rsmd = results.getMetaData();
            int numberCols = rsmd.getColumnCount();
            for (int i = 1; i <= numberCols; i++) {
                //print Column Names
                System.out.print(rsmd.getColumnLabel(i) + "\t\t");
            }

            System.out.println("\n-------------------------------------------------");

            while (results.next()) {
                int id = results.getInt(1);
                String restName = results.getString(2);
                String cityName = results.getString(3);
                System.out.println(id + "\t\t" + restName + "\t\t" + cityName);
            }
            results.close();
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
    }

    private static void shutdown() {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                DriverManager.getConnection(dbURL + ";shutdown=true");
                conn.close();
            }
        } catch (SQLException sqlExcept) {

        }

    }
}