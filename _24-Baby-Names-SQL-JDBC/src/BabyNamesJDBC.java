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


public class BabyNamesJDBC {
    private static String dbURL = "jdbc:derby:C:\\Users\\justi\\Desktop\\Java 2 - Spring 2023\\babynames-derby\\babynames;create=true";
    private static String tableName = "restaurants";
    // jdbc Connection
    private static Connection conn = null;
    private static Statement stmt = null;

    private static String[] questions = {"",
            "1. What was the most common boy’s name in Maryland in 1991",
            "2. In which year were the most baby boy’s named “Christopher” born in any state?",
            "3. In which year were the most baby girls named “Rosemary” born in any state?",
            "4. In 2000, which baby names were used more than than 500 times in Maryland?",
            "5. In 2014, which state had the fewest boys named “Xavier”?",
            "6. In 1997, which state had the most girls named “Hannah”?",
            "7. Write a query to add the following row: (10000000, ‘Joseph’, 2016, ‘M’, ‘PA’, ‘476’)",
            "8. Write a query to delete the row you just added.",
            "9. (Bonus) What was the most common boy’s name across the United States in 1991?"};

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

    // 1. What was the most common boy’s name in Maryland in 1991
    private static void numberOne() {
        System.out.println(questions[1]);
        try {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("SELECT NAME " +
                    "FROM BABYNAMES " +
                    "WHERE GENDER = 'M' " +
                    "AND US_STATE = 'MD' " +
                    "AND DATE_YEAR = 1991 " +
                    "ORDER BY NUM_BABIES DESC " +
                    "FETCH FIRST ROW ONLY");
            System.out.println(results);
            results.close();
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
    }

    // 2. In which year were the most baby boy’s named “Christopher” born in any state?
    private static void numberTwo() {
        System.out.println(questions[2]);
        try {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("SELECT DATE_YEAR " +
                    "FROM BABYNAMES " +
                    "WHERE GENDER = 'M' " +
                    "AND NAME = 'Christopher' " +
                    "FETCH FIRST ROW ONLY");
            System.out.println(results);
            results.close();
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
    }

    // 3. In which year were the most baby girls named “Rosemary” born in any state?
    private static void numberThree() {
        System.out.println(questions[3]);
        try {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("SELECT DATE_YEAR " +
                    "FROM BABYNAMES " +
                    "WHERE GENDER = 'F' " +
                    "AND NAME = 'Rosemary' " +
                    "FETCH FIRST ROW ONLY");
            System.out.println(results);
            results.close();
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
    }

    // 4. In 2000, which baby names were used more than than 500 times in Maryland?
    private static void numberFour() {
        System.out.println(questions[4]);
        try {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("SELECT NAME " +
                    "FROM BABYNAMES " +
                    "WHERE DATE_YEAR = 2000 " +
                    "AND NUM_BABIES > 500 " +
                    "AND US_STATE = 'MD'");
            System.out.println(results);
            results.close();
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
    }

    // 5. In 2014, which state had the fewest boys named “Xavier”?
    private static void numberFive() {
        System.out.println(questions[5]);
        try {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("SELECT US_STATE " +
                    "FROM BABYNAMES " +
                    "WHERE DATE_YEAR = 2014 " +
                    "AND GENDER = 'M' " +
                    "AND NAME = 'Xavier' " +
                    "ORDER BY NUM_BABIES " +
                    "FETCH FIRST ROW ONLY");
            System.out.println(results);
            results.close();
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
    }

    // 6. In 1997, which state had the most girls named “Hannah”?
    private static void numberSix() {
        System.out.println(questions[6]);
        try {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("SELECT US_STATE " +
                    "FROM BABYNAMES " +
                    "WHERE GENDER = 'F' " +
                    "AND NAME = 'Hannah' " +
                    "ORDER BY NUM_BABIES DESC " +
                    "FETCH FIRST ROW ONLY");
            System.out.println(results);
            results.close();
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
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
        System.out.println(questions[7]);
        try {
            stmt = conn.createStatement();
            stmt.execute("INSERT INTO BABYNAMES" +
                    "(ID, NAME, DATE_YEAR, GENDER, US_STATE, NUM_BABIES) " +
                    "VALUES (10000000, 'Joseph', 2016, 'M', 'PA', 476)");
            System.out.println("Row added.");
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
    }

    // 8. Write a query to delete the row you just added.
    private static void numberEight(int id) {
        System.out.println(questions[8]);
        try {
            stmt = conn.createStatement();
            stmt.execute("DELETE FROM BABYNAMES WHERE ID = " + id);
            System.out.println("Row removed.");
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
    }

    // 9. (Bonus) What was the most common boy’s name across the United States in 1991
    // (Hint: use “SUM” and "GROUP BY")
    private static void numberNine() {
        System.out.println(questions[9]);
        try {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("SELECT NAME FROM BABYNAMES " +
                    "WHERE GENDER = 'M' AND DATE_YEAR = 1991 " +
                    "ORDER BY NUM_BABIES DESC " +
                    "FETCH FIRST ROW ONLY");
            System.out.println(results);
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