/* This class does the basic CRUD operations for the Effort log table
 * created by the DB_TableCreation.java class.
 */

//importing the required packages
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;


public class EffortLogTableOps {
    
    //the insert method for the insert operation
    public static void insertEffortLog(String projectName, Timestamp startTime, Timestamp endTime, String lifeCycleStep, String effortCategory, String deliverable) {
        Connection connection = DatabaseConnection.getConnection(); //for getting the connection
        PreparedStatement preparedStatement = null; //prepared statement object
        try {
            String insertSQL = "INSERT INTO EFFORT_LOGS (PROJECT_NAME, START_TIME, END_TIME, LIFE_CYCLE_STEP, EFFORT_CATEGORY, DELIVERABLE) VALUES (?, ?, ?, ?, ?, ?);"; //the insert query
            preparedStatement = connection.prepareStatement(insertSQL); //creating the prepared statement object
            preparedStatement.setString(1, projectName);
            preparedStatement.setTimestamp(2, startTime);
            preparedStatement.setTimestamp(3, endTime);
            preparedStatement.setString(4, lifeCycleStep);
            preparedStatement.setString(5, effortCategory);
            preparedStatement.setString(6, deliverable);
            preparedStatement.executeUpdate();
            System.out.println("Effort log inserted successfully");
        } 
        catch (SQLException e) { //catching the exception
            e.printStackTrace();
        }
        finally{ //closing the prepared statement and connection
            try {
                if(preparedStatement != null)
                    preparedStatement.close();
            } 
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //the read method for the read operation
    public static String readEffortLog(){
        Connection connection = DatabaseConnection.getConnection(); //for getting the connection
        PreparedStatement preparedStatement = null; //prepared statement object
        ResultSet resultSet = null; //result set object
        String str = "";
        try{
            String readSQL = "SELECT * FROM EFFORT_LOGS;"; //the read query
            preparedStatement = connection.prepareStatement(readSQL); //initializing the prepared statement object
            resultSet = preparedStatement.executeQuery(); //executing the query
            while(resultSet.next()){ //iterating through the result set
                //getting the values and making a string out of it
                str += "Project Name: " + resultSet.getString("PROJECT_NAME") + "\n";
                str += "Start Time: " + resultSet.getTimestamp("START_TIME") + "\n";
                str += "End Time: " + resultSet.getTimestamp("END_TIME") + "\n";
                str += "Life Cycle Step: " + resultSet.getString("LIFE_CYCLE_STEP") + "\n";
                str += "Effort Category: " + resultSet.getString("EFFORT_CATEGORY") + "\n";
                str += "Deliverable: " + resultSet.getString("DELIVERABLE") + "\n";
                str += "\n";
            }
        }
        catch (SQLException err){
            err.printStackTrace();
        }
        return str;
    }

    //the delete method for the delete operation
    public static void deleteEffortLog(int id){
        Connection connection = DatabaseConnection.getConnection(); //for getting the connection
        PreparedStatement preparedStatement = null; //prepared statement object
        try{
            String deleteSQL = "DELETE FROM EFFORT_LOGS WHERE ID = ?;"; //the delete query
            preparedStatement = connection.prepareStatement(deleteSQL); //initializing the prepared statement object
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate(); //executing the query
            System.out.println("Effort log deleted successfully");
        }
        catch (SQLException err){
            err.printStackTrace();
        }
    }
}
