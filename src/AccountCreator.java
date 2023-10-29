//class serves to create an account for a user
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class AccountCreator {

	private  ArrayList<Account> ListOfAccounts;//contains accounts
	private boolean initialized ;
	public AccountCreator() {
		initialized = false;
		ListOfAccounts = new ArrayList<Account>();
	}
	//subject to change: using record.csv file to store current account records
	//takes the information from the csv and puts in the list of accounts
	public void initialize() {
		ListOfAccounts.clear();
		initialized = true;
		try {
		BufferedReader csvReader = new BufferedReader(new FileReader("../record.csv"));
		String row = "";
		while (( row = csvReader.readLine()) != null) {
		    String[] data = row.split(",");
		    ListOfAccounts.add(new Account(data[0],data[1]));
		    
		}
		csvReader.close();
		}
		catch(IOException e) {
			System.out.println("Error during initialization");
		}
	}
	//checks if account already exists 
	public boolean AccountPresent(Account check) {
		
		for(Account curr: ListOfAccounts) {
			if(curr.getPass().compareTo(check.getPass())==0 &&curr.getUsername().compareTo(check.getUsername())==0) {
				return true;
			}
		}
		return false;
	}
	//checks if account already exists with the same username
	public boolean usernamePresent(Account check) {
		
		for(Account curr: ListOfAccounts) {
			if(curr.getUsername().compareTo(check.getUsername())==0) {
				return true;
			}
		}
		return false;
	}
	
	//adds an account to the csv and list of accounts
	public String addAccount(/*String accessReq, */String username, String password) {
		//checks if the Arraylist is upto date
		if(!initialized) {
			initialize();
		}
		
		Account currAcc = new Account(username,password);
		//check if username already exists
		if(!usernamePresent(currAcc)) {
			ListOfAccounts.add(currAcc);//add current account to arraylist
			try {
				enterAccount(currAcc);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			System.out.println("ACCOUNT CREATED SUCCESSFULLY");
			return "Account " + username + " added";
			}
		
			else {
				System.out.println("USERNAME ALREADY PRESENT");
				return "Username use already present";
			}
			
		}

	//adds current account to csv file prototype
	public static void enterAccount(Account acc) throws IOException{
	     
	        File csvFile = new File("../record.csv");
	        FileWriter fileWriter = new FileWriter(csvFile,true);
	        String[] data = {acc.getUsername(),acc.getPass()};
	        StringBuilder line = new StringBuilder();
            for (int i = 0; i < data.length; i++) {
                line.append(data[i].replaceAll("\"","\"\""));
                if (i != data.length - 1) {
                    line.append(',');
                }
            }
            line.append("\n");
            fileWriter.write(line.toString());
            fileWriter.close();

	    }
	
	
}	
	
	/***subject to change***
	 * public void create_username() {
		
		System.out.print("Enter your first name: ");
		first_name = scan.nextLine();
		System.out.print("Enter your last name: ");
		last_name = scan.nextLine();
		System.out.print("Enter your employee ID: ");
		ID = scan.nextLine();
		
		while(!stop) {
			username = first_name.toLowerCase() + last_name.substring(0, Math.min(3, last_name.length())).toLowerCase() + ID.substring(Math.max(0, ID.length()-3));
			
	        if(usernames.contains(username)) {
	            System.out.println("This username already exists. Please enter another username");
	         } 
	        else {
	            usernames.add(username);
	            System.out.println("Username created successfully.");
	            System.out.println("Your Username is: " + username);
	            stop = true;
	         }
		}    
	}
	
	
	public void create_password() {
	    System.out.print("Enter a password: ");
	    password = scan.nextLine();
	    //perhaps add password restrictions on next use?
	    System.out.printf("\nYour password has been set!");
	}*/

