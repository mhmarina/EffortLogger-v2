/* This class makes use of the DatabaseConnection.java to establish connection
 * to the H2 database and create the tables in the database. 
 */

//importing the required packages
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_TableCreation {
    
    //creating the method to create the EffortLog table in the database which holds the effort logs
    public static void createTableEffortLog(){

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

    //a method that creates the UserAccounts table in the database which holds the user accounts and permissions
    public static void createTableUserAccounts(){

        //creating connection object
        Connection connection = DatabaseConnection.getConnection();
        Statement statement = null; //creating statement object

        try{
            statement = connection.createStatement(); //initializing statement object
            // making the CREATE command to create the tables in the database
            String createTableSQL = "CREATE TABLE IF NOT EXISTS USER_ACCOUNTS (" +
                                    "ID INT PRIMARY KEY AUTO_INCREMENT, " +
                                    "USERNAME VARCHAR(255), " +
                                    "PASSWORD VARCHAR(255), " +
                                    "ROLE VARCHAR(255));";
            statement.executeUpdate(createTableSQL); //executing the CREATE command
            System.out.println("User Accounts table created successfully");

            // Insert default user if not exists
            String insertDefaultUserSQL = "INSERT INTO USER_ACCOUNTS (USERNAME, PASSWORD, ROLE) " +
                                          "SELECT '84b6a07a297d8ed9b747e2243e44de7fbb33a761fe8b660737f3c8538b8717d', '2bebb3cbf0a4d3499dbf67be79d91b4a385a52a1bf93874edd4c412fe11946', 'Supervisor' FROM DUAL " +
                                          "WHERE NOT EXISTS (SELECT 1 FROM USER_ACCOUNTS WHERE USERNAME = 'team38');";
            statement.executeUpdate(insertDefaultUserSQL); //executing the INSERT command
            System.out.println("Default user inserted successfully");
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

    //a method that creates the Planning Poker table in the database which holds the planning poker data
    public static void createTablePlanningPoker(){

        //creating connection object
        Connection connection = DatabaseConnection.getConnection();
        Statement statement = null; //creating statement object

        try{
            statement = connection.createStatement(); //initializing statement object
            // making the CREATE command to create the tables in the database
            String createTableSQL = "CREATE TABLE IF NOT EXISTS PLANNING_POKER (" +
                                    "ID INT PRIMARY KEY AUTO_INCREMENT, " +
                                    "EFFORT_ESTIMATE VARCHAR(255), " +
                                    "INFO TEXT);";
            statement.executeUpdate(createTableSQL); //executing the CREATE command
            System.out.println("Planning Poker table created successfully");
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
    
    public static void createTableProjectsBacklog(){

        //creating connection object
        Connection connection = DatabaseConnection.getConnection();
        Statement statement = null; //creating statement object

        try{
            statement = connection.createStatement(); //initializing statement object
            // making the CREATE command to create the tables in the database
            String createTableSQL = "CREATE TABLE IF NOT EXISTS PROJECTS_BACKLOG (" +
                                    "ID INT PRIMARY KEY AUTO_INCREMENT, " +
                                    "PROJECT_NAME VARCHAR(255), " +
                                    "STORY_POINTS INT);";
            statement.executeUpdate(createTableSQL); //executing the CREATE command
            System.out.println("Projects Backlog table created successfully");
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