/* This class does the basic CRUD operations for the User Accounts table
   created by the DB_TableCreation.java class.
 */

//importing the required packages
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserAccountsTableOps {

    //the insert method for the insert operation
    public static void insertUserAccount(String username, String password, String role){
        Connection connection = DatabaseConnection.getConnection(); //for getting the connection
        PreparedStatement preparedStatement = null; //prepared statement object
        try{
            String insertSQL = "INSERT INTO USER_ACCOUNTS (USERNAME, PASSWORD, ROLE) VALUES (?, ?, ?);"; //the insert query
            preparedStatement = connection.prepareStatement(insertSQL); //creating the prepared statement object
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, role);
            preparedStatement.executeUpdate();
            System.out.println("User account inserted successfully");
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

    //the read method for the read operation to check if the username exists
    public static boolean readUserAccount(String username){
        Connection connection = DatabaseConnection.getConnection(); //for getting the connection
        PreparedStatement preparedStatement = null; //prepared statement object
        ResultSet resultSet = null; //result set object
        try{
            //find the user account with the given username
            String readSQL = "SELECT * FROM USER_ACCOUNTS WHERE USERNAME = ?;";
            preparedStatement = connection.prepareStatement(readSQL); //creating the prepared statement object
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery(); //executing the query
            //if the result set is not empty, then the user account exists
            if(resultSet.next()){
                System.out.println("User account exists");
                return true;
            }
            else{
                System.out.println("User account does not exist");
                return false;
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
        return false;
    }
    
    //the read method for the read operation to authenticate the user
    public static boolean readUserAccount(String username, String password){
        Connection connection = DatabaseConnection.getConnection(); //for getting the connection
        PreparedStatement preparedStatement = null; //prepared statement object
        ResultSet resultSet = null; //result set object
        try{
            //find the user account with the given username and password
            String readSQL = "SELECT * FROM USER_ACCOUNTS WHERE USERNAME = ? AND PASSWORD = ?;";
            preparedStatement = connection.prepareStatement(readSQL); //creating the prepared statement object
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery(); //executing the query
            //if the result set is not empty, then the user account exists
            if(resultSet.next()){
                System.out.println("User account exists");
                return true;
            }
            else{
                System.out.println("User account does not exist");
                return false;
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
        return false;
    }

    //the read method to read the role of the user
    public static int readUserRole(String username){
        Connection connection = DatabaseConnection.getConnection(); //for getting the connection
        PreparedStatement preparedStatement = null; //prepared statement object
        ResultSet resultSet = null; //result set object
        try{
            //find the user account with the given username
            String readSQL = "SELECT * FROM USER_ACCOUNTS WHERE USERNAME = ?;";
            preparedStatement = connection.prepareStatement(readSQL); //creating the prepared statement object
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery(); //executing the query
            //if the result set is not empty, then the user account exists
            if(resultSet.next()){
                System.out.println("User account exists");
                if(resultSet.getString("ROLE") == "Supervisor")
                    return 1;
                else
                    return 0;
            }
            else{
                System.out.println("User account does not exist");
                return -1;
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
        return -1;
    }
    //the delete method for the delete operation
    public static void deleteUserAccount(String username){
        Connection connection = DatabaseConnection.getConnection(); //for getting the connection
        PreparedStatement preparedStatement = null; //prepared statement object
        try{
            String deleteSQL = "DELETE FROM USER_ACCOUNTS WHERE USERNAME = ?;"; //the delete query
            preparedStatement = connection.prepareStatement(deleteSQL); //creating the prepared statement object
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
            System.out.println("User account deleted successfully");
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
}
