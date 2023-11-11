//class serves to create an account for a user
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
//add role parameter in account creator


public class AccountCreator {
	private  ArrayList<Account> ListOfAccounts;//contains accounts
	private boolean initialized;//keep track of whether account has been initialized
	private static String AccessKey = "4su&yw5";//verification key for authorization

	public AccountCreator() {
		initialized = false;
		ListOfAccounts = new ArrayList<Account>();
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
	public boolean AccountPresent(Account check) {
		
		//for loop iterating thru accounts in db
		//assume Account curr = i
		for (Account curr : ListOfAccounts) {
			if(curr.getPass().compareTo(check.getPass())==0 &&curr.getUsername().compareTo(check.getUsername())==0) {
				return true;
			}
		}
		return false;
	}
	//checks if account already exists with the same username
	//no two accounts can have same username
	public boolean usernamePresent(Account check) {
		
		//for loop iterating thru accounts in db as well
		//assume Account curr = i
		for (Account curr : ListOfAccounts) {
			if(curr.getUsername().compareTo(check.getUsername())==0) {
				return true;
			}
		}
		return false;
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
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			String encryptedPass="";
			try {
				encryptedPass = Encryptor.encrypt(password);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Account currAcc = new Account(encryptedUse,encryptedPass);
			//ensure no duplicate username
			if(!usernamePresent(currAcc)) {
				ListOfAccounts.add(currAcc);//add account to arraylist
				try {
					enterAccount(currAcc);//adds account to db w/ encrypted username+pw
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
	public static void enterAccount(Account acc) throws IOException{
	     
	        //add account to db

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



	


