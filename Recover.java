/*********
Cartwright, Stephen D.
3/19/16


Recover class 
contains all helper functions for dictionary attack 
	sha1 implementation
	reverse string input
	remove vowels string input
*********/

import java.security.*;
import java.io.IOException;
import javax.xml.bind.DatatypeConverter;

public class Recover {

	public static String sha1(String p) {
		// init temp byte[]
	 	 byte[] sha1Bytes = new byte[0];
	  try {  
		  // init a sha1 MessageDigest 
		MessageDigest sha1Instance = MessageDigest.getInstance("SHA-1");
		// fill buffer with string bytes
		sha1Instance.update(p.getBytes());
		// output sha1 alg instance to bytes[]
		sha1Bytes = sha1Instance.digest();

	  }
	  catch (Exception e) {
	    e.printStackTrace();
	  }
	  // return a hex string lowercase 
	   return new String(DatatypeConverter.printHexBinary(sha1Bytes).toLowerCase());
	}
	
	public static String sha1(String s, String p) {
	   // init temp byte[]
		byte[] sha1Bytes = new byte[0];	  
	 try {  
		  // init a sha1 MessageDigest 
		MessageDigest sha1Instance = MessageDigest.getInstance("SHA-1");
		// update first with the salt in form of hex bytes
		sha1Instance.update(DatatypeConverter.parseHexBinary(s));
		// fill buffer with string bytes afterward
		sha1Instance.update(p.getBytes());
		// output sha1 alg instance to bytes[]
		sha1Bytes = sha1Instance.digest();

	  }
	  catch (Exception e) {
	    e.printStackTrace();
	  }
	  // return a hex string lowercase
	  	return new String(DatatypeConverter.printHexBinary(sha1Bytes).toLowerCase());
	}
	
	public static String reverse(String s) {
		// reverse the string
		return new StringBuilder(s).reverse().toString();
	}
	
	public static String remove(String s) {
		// replace all vowels with nothing
		return new String(s.replaceAll("[AaEeIiOoUu]", ""));
	}

}