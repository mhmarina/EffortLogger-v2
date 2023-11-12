/* This class does the basic CRUD operations for the Planning Poker table
   created by the DB_TableCreation.java class.
 */

 //importing the required packages
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PlanningPokerTableOps {
    
    //the insert method for the insert operation
    public static void insertPlanningPokerData(String effortEstimate, String comments){
        Connection connection = DatabaseConnection.getConnection(); //for getting the connection
        PreparedStatement preparedStatement = null; //prepared statement object
        try{
            String insertSQL = "INSERT INTO PLANNING_POKER (EFFORT_ESTIMATE, INFO) VALUES (?, ?);"; //the insert query
            preparedStatement = connection.prepareStatement(insertSQL); //preparing the statement))
            preparedStatement.setString(1, effortEstimate);
            preparedStatement.setString(2, comments); 
            preparedStatement.executeUpdate();
            System.out.println("Planning Poker data inserted successfully");
        }
        catch(SQLException e){ //catching the exception
            e.printStackTrace(); 
        }
        finally{ //closing the prepared statement
            try{
                if(preparedStatement != null)
                    preparedStatement.close();
            }
            catch(SQLException e){
                e.printStackTrace();
            }

        }
    }

    //the read method for the read operation to display the planning poker data
    public static String readPlanningPokerData(){
        Connection connection = DatabaseConnection.getConnection(); //for getting the connection
        PreparedStatement preparedStatement = null; //prepared statement object
        ResultSet resultSet = null; //result set object
        String str = "";
        try{
            String readSQL = "SELECT * FROM PLANNING_POKER;"; //the read query
            preparedStatement = connection.prepareStatement(readSQL); //preparing the statement
            resultSet = preparedStatement.executeQuery(); //executing the query
            while(resultSet.next()){ //while loop to iterate through the result set
                str += "Effort Estimate: " + resultSet.getString("EFFORT_ESTIMATE") + ". " + resultSet.getString("INFO") + "\n";
            }
        }
        catch(SQLException e){ //catching the exception
            e.printStackTrace();
        }
        finally{ //closing the result set and prepared statement
            try{
                if(resultSet != null)
                    resultSet.close();
                if(preparedStatement != null)
                    preparedStatement.close();
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
        return str;
    }
}
