//class serves to encrypt username and password in accountcreator class
import java.security.MessageDigest;

public class Encryptor {
	
	public static String encrypt(String operator) throws Exception{
		
		MessageDigest md = MessageDigest.getInstance("SHA-256"); //creates a messDigest object for the specified algorithm
		md.update(operator.getBytes()); //passes data through object
		byte[] digest = md.digest(); //computes the hash function on the string and returns a byte array
		
		StringBuffer hexString = new StringBuffer();//stores byte array converted to hexString
	    for (int i = 0;i<digest.length;i++) {
	         hexString.append(Integer.toHexString(0xFF & digest[i]));
	      }
	      //System.out.println("Hex format : " + hexString.toString()); 
		return hexString.toString();//returns encrypted string
	}


}