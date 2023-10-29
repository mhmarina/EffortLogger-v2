/* This class makes use of the DatabaseConnection.java to establish connection
 * to the H2 database and create the tables in the database. 
 */

//importing the required packages
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_TableCreation {
    
    //creating the method to create the tables in the database
    public static void createTable(){

        //creating connection object
        Connection connection = DatabaseConnection.getConnection();
        Statement statement = null; //creating statement object

        try{
            statement = connection.createStatement(); //initializing statement object
            // making the CREATE command to create the tables in the database
            String createTableSQL = "CREATE TABLE IF NOT EXISTS EFFORT_LOGS (" +
                                    "ID INT PRIMARY KEY AUTO_INCREMENT, " +
                                    "PROJECT_NAME VARCHAR(255), " +
                                    "START_TIME TIMESTAMP, " +
                                    "END_TIME TIMESTAMP, " +
                                    "LIFE_CYCLE_STEP VARCHAR(255), " +
                                    "EFFORT_CATEGORY VARCHAR(255), " +
                                    "DELIVERABLE VARCHAR(255));";
            statement.executeUpdate(createTableSQL); //executing the CREATE command
            System.out.println("Effort Log table created successfully");
        }
        catch(SQLException err){ //catching any SQL exception
            err.printStackTrace();
        }
        finally{ //closing the statement object
            try{
                if(statement != null){ 
                    statement.close();//closing the statement object
                }
            }
            catch(SQLException err){//catching any SQL exception
                err.printStackTrace();
            }
        }
 }
}