/* This class does the basic CRUD operations for the Planning Poker table
   created by the DB_TableCreation.java class.
 */

 //importing the required packages
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PlanningPokerTableOps {
    
    //the insert method for the insert operation
    public static void insertPlanningPokerData(String effortEstimate, String comments){
        Connection connection = DatabaseConnection.getConnection(); //for getting the connection
        PreparedStatement preparedStatement = null; //prepared statement object
        try{
        	// also insert project name 
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

	public static ArrayList<Integer> readPlanningPokerPoints() {
		ArrayList<Integer> points = new ArrayList<Integer>();
        Connection connection = DatabaseConnection.getConnection(); //for getting the connection
        PreparedStatement preparedStatement = null; //prepared statement object
        ResultSet resultSet = null; //result set object
        String str = "";
        try{
            String readSQL = "SELECT EFFORT_ESTIMATE FROM PLANNING_POKER;"; //the read query
            preparedStatement = connection.prepareStatement(readSQL); //preparing the statement
            resultSet = preparedStatement.executeQuery(); //executing the query
            while(resultSet.next()){ //while loop to iterate through the result set
            	try {
            		String myPoints = resultSet.getString("EFFORT_ESTIMATE");
            		switch(myPoints) {
            		case "Ace":
            			points.add(1);
            			break;
            		case "Jack":
            			points.add(11);
            			break;
            		case "Queen":
            			points.add(12);
            			break;
            		case "King":
            			points.add(13);
            			break;
            		default:
                		points.add(Integer.parseInt(myPoints));
                		break;
            		}
            	}
            	catch (NumberFormatException e) {
                    System.err.println("Invalid integer: " + resultSet.getString("EFFORT_ESTIMATE"));
            	}
            }            
        }
        catch(SQLException e){ //catching the exception
            e.printStackTrace();
        }
        return points;
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
    
    // in my implementation this table's lifecycle is the planning poker session. When a project is finalized, this table should be cleared and ready for the next session.
    public static void clearPlanningPokerTable() {
    	 Connection connection = DatabaseConnection.getConnection();
         PreparedStatement deleteStatement = null;

         try {
             String deleteSQL = "DELETE FROM PLANNING_POKER;"; // delete query, should delete everything from this table.
             deleteStatement = connection.prepareStatement(deleteSQL); // preparing the statement
             deleteStatement.executeUpdate(); 
         } catch (SQLException e) {
             e.printStackTrace();
         } finally {
             try {
                 if (deleteStatement != null)
                     deleteStatement.close();
             } catch (SQLException e) {
                 e.printStackTrace();
             }
         }
    }
}
