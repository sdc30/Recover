import java.security.*;
import java.io.IOException;
import javax.xml.bind.DatatypeConverter;

public class Recover {

	public static String sha1(String p) {
	  
	 	 byte[] sha1Bytes = new byte[0];
	  try {  
		MessageDigest sha1Instance = MessageDigest.getInstance("SHA-1");
		
		sha1Instance.update(p.getBytes());
		sha1Bytes = sha1Instance.digest();
		
		//sha1 = Converters.byteArrToHexStr(sha1Bytes);
		
	  }
	  catch (Exception e) {
	    e.printStackTrace();
	  }
	   return new String(DatatypeConverter.printHexBinary(sha1Bytes).toLowerCase());
	}
	
	public static String sha1(String s, String p) {
  	  
		byte[] sha1Bytes = new byte[0];	  
	 try {  
		MessageDigest sha1Instance = MessageDigest.getInstance("SHA-1");
		
		sha1Instance.update(DatatypeConverter.parseHexBinary(s));
		sha1Instance.update(p.getBytes());
		sha1Bytes = sha1Instance.digest();
		
		//sha1 = Converters.byteArrToHexStr(sha1Bytes);
		//sha1 = DatatypeConverter.printHexBinary(sha1Bytes).toLowerCase();
	  }
	  catch (Exception e) {
	    e.printStackTrace();
	  }
	  	return new String(DatatypeConverter.printHexBinary(sha1Bytes).toLowerCase());
	}
	

}