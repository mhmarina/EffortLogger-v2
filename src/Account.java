//each account will be tied to a generated username and chosen password
//class serves to create variables of Account type
public class Account {
	private String username;
	private String password;
	public Account(String username, String password) {
		username = username;
		password = password;
	}
	public String getUsername() {
		return username;
	}
	public String getPass() {
		return password;
	}
}