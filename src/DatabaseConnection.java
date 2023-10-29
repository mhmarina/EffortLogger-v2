/* This class establishes a database connection to the H2 database we are going 
 * to use for this project. It will be used by the other classes to access the
 * database. We are going to establish the connection to the database using
 * JDBC. 
 */

// Importing necessary packages
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    
    // creating the JDBC URL, username and password for the database
    private static final String JDBC_URL = "jdbc:h2:~/EffortLoggerDB"; //creates a database in the user's home directory
    private static final String JDBC_USERNAME = "sa"; //default usernam(system administrator)
    private static final String JDBC_PASSWORD = "12345678"; //a password to ensure additional database security

    // creating a connection object
    private static Connection connection;
    
    // establishing the connection to the database
    public static Connection getConnection() {
        if(connection == null) {
            try {
                Class.forName("org.h2.Driver"); //loads the h2 driver
                connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD); //establishes the connection
            }
            catch(ClassNotFoundException | SQLException err) {
                err.printStackTrace();
                throw new RuntimeException("Failed to establish connection to the database", err);
            }
        }
        return connection;
    }
}
