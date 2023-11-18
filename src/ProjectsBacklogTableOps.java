 //importing the required packages
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProjectsBacklogTableOps {
	public static void insertProjectToBacklog(String projectName, int storyPoints) {
        Connection connection = DatabaseConnection.getConnection(); //for getting the connection
        PreparedStatement preparedStatement = null; //prepared statement object
        try{
        	// also insert project name 
            String insertSQL = "INSERT INTO PROJECTS_BACKLOG (PROJECT_NAME, STORY_POINTS) VALUES (?, ?);"; //the insert query
            preparedStatement = connection.prepareStatement(insertSQL); //preparing the statement))
            preparedStatement.setString(1, projectName);
            preparedStatement.setLong(2, storyPoints); 
            preparedStatement.executeUpdate();
            System.out.println("Backlog data data inserted successfully");
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
	
	public static String readProjectsBacklog(){
        Connection connection = DatabaseConnection.getConnection(); //for getting the connection
        PreparedStatement preparedStatement = null; //prepared statement object
        ResultSet resultSet = null; //result set object
        String str = "";
        try{
            String readSQL = "SELECT * FROM PROJECTS_BACKLOG;"; //the read query
            preparedStatement = connection.prepareStatement(readSQL); //preparing the statement
            resultSet = preparedStatement.executeQuery(); //executing the query
            while(resultSet.next()){ //while loop to iterate through the result set
                str += "Project Name: " + resultSet.getString("PROJECT_NAME") + ". Story Points:" + resultSet.getString("STORY_POINTS") + "\n";
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
	
	// return an arrayList of project names. To be used as options in the comboboxes in the main console
	public static ArrayList<String> readProjectNames(){
		ArrayList<String> projectNames = new ArrayList<String>();
        Connection connection = DatabaseConnection.getConnection(); //for getting the connection
        PreparedStatement preparedStatement = null; //prepared statement object
        ResultSet resultSet = null; //result set object
        try{
            String readSQL = "SELECT PROJECT_NAME FROM PROJECTS_BACKLOG;"; //the read query
            preparedStatement = connection.prepareStatement(readSQL); //preparing the statement
            resultSet = preparedStatement.executeQuery(); //executing the query
            while(resultSet.next()){ //while loop to iterate through the result set
            	if(resultSet.getString("PROJECT_NAME") != "") {
                	projectNames.add(resultSet.getString("PROJECT_NAME"));
            	}
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
        return projectNames;
    }
}
