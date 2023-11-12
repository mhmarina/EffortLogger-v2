//class serves to create an account for a user
import java.io.IOException;
import java.util.*;

public class AccountCreator {
	private boolean initialized;//keep track of whether account has been initialized
	private static String AccessKey = "4su&yw5";//verification key for authorization

	public AccountCreator() {
		initialized = false;
	}
	//subject to change: now using db instead of csv file
	//takes the information from the db and puts in the list of accounts
	public void initialize() {
		initialized = true;
		//try {
			//initialize db
		//}
		//catch(IOException e) {
			//System.out.println("INITIALIZATION ERROR");
		//}
	}
	//checks if account already exists 
	public static boolean AccountAuthenticate(String username, String password) {
		
		//encrypt username & password
		String encryptedUsername="";
		try {
			encryptedUsername = Encryptor.encrypt(username);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		String encryptedPassword="";
		try {
			encryptedPassword = Encryptor.encrypt(password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//calling the read method from UserAccountsTableOps class to check if the user account exists and 
		if(UserAccountsTableOps.readUserAccount(encryptedUsername, encryptedPassword)){
			return true;
		}
		else{
			return false;
		}
	}
	//checks if account already exists with the same username
	//no two accounts can have same username
	public boolean usernamePresent(String username) {
		
		//calling the read method from UserAccountsTableOps class to check if the user account exists and 
		if(UserAccountsTableOps.readUserAccount(username)){
			return true;
		}
		else{
			return false;
		}
	}
	
	//adds an account to the db and list of accounts
	public String addAccount(String accessReq, String username, String password) {
		//checks if the Arraylist is up to date
		if(!initialized) {
			initialize();
		}
		//check authorization
		if(accessReq.compareTo(AccessKey)==0) {
			//encrypt username & password
			String encryptedUse="";
			try {
				encryptedUse = Encryptor.encrypt(username);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			String encryptedPass="";
			try {
				encryptedPass = Encryptor.encrypt(password);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//ensure no duplicate username
			if(!usernamePresent(username)) {
				try {
					enterAccount(username, password);//adds account to db w/ encrypted username+pw
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
		else {
			System.out.println("ACCESS DENIED");
			return "Access denied, incorrect key";
		}
			
	}

	//adds current account to db
	public static void enterAccount(String username, String password) throws IOException{
	     
	        //add account to database by calling the insert method from UserAccountsTableOps class
			UserAccountsTableOps.insertUserAccount(username, password,"Employee");
	    }
	
//add print method? to print contents of db after account is created
	
	
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



	


