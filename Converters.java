public final class Converters {
	
	 static final protected char[] hexArray = "0123456789abcdef".toCharArray();
	 static final int hexRad = 16, binRad = 2;
 	 //	 binary to hex method
 	 // 		  takes binary string of length 56 bits
 	 // 			  takes substrings of every 2 hex
 	 //  
	  
	  public static String binToHexStr(String bin){
		  
		StringBuilder hex = new StringBuilder();
		String s = null;
			 for(int i = 0; i < bin.length()-1; i = i + binRad){
				  s = bin.substring(i, i + 1);
				  hex.append(Short.parseShort(s, hexRad));
					  
			 } 
			return hex.toString();
	  }
	  
	  // get bytes from hex string
	  // 	take in a string and parse every 2 hex digits into a byte
	  //
	  // 
/*	  
	  public static String byteArrToHexStr(byte[] bytes) {
	      char[] hex = new char[bytes.length * 2];
	      for (int i = 0; i < bytes.length; i++) {
	          int bits = bytes[i] & 0xFF;
	          hex[i * 2] = hexArray[bits >>> 4];
	          hex[i * 2 + 1] = hexArray[bits & 0x0F];
	      }
	      return new String(hex);
	  }    
*/
	  
	  public static String byteArrToHexStr(byte[] bytes) {
	      StringBuffer buf = new StringBuffer();
	      for (int j=0; j < bytes.length; j++) {
	         buf.append(hexArray[(bytes[j] >> 4) & 0x0f]);
	         buf.append(hexArray[bytes[j] & 0x0f]);
	      }
	      return buf.toString();
	  }   
	  
	  
	  	  
	  // get bytes from hex str
  	  // 		  take in hex string and build byte array

      public static byte[] hexStrToByteArr(String s){
		  
		  int len = s.length();
		  byte f = 0xF;
		  byte[] temp = new byte[len], out = new byte[len/2];
		 	  
		  for(int i = 0; i < len; i++){
		  	if(i % 2 == 0) temp[i] = (byte)((Byte.parseByte(s.substring(i, i+1), hexRad) & f) << 0x4);
			else if (i % 2 == 1) temp[i] = (byte)(Byte.parseByte(s.substring(i, i+1), hexRad) & f) ; 
			
		  }	
		  
		  for(int i = 0; i < len/2; i++){
			  out[i] = (byte)(temp[2*i] | temp[2*i + 1]);
		  }	
		  
			return out;
      }	
		  
	  public static byte[] binStrToByteArr(String s)
	  {
	      int len = s.length();
	      byte[] out = new byte[(len + Byte.SIZE - 1) / Byte.SIZE];
	      char c;
	      for( int i = 0; i < len; i++ )
	          if( (c = s.charAt(i)) == '1' )
	              out[i / Byte.SIZE] = (byte) (out[i / Byte.SIZE] | (0x80 >>> (i % Byte.SIZE)));
	          else if ( c != '0' )
	              throw new IllegalArgumentException();
	      return out;
	  }			 
		 
		  
	  public static String byteArrToBinStr(byte[] bytes){
	      StringBuilder sb = new StringBuilder(bytes.length * Byte.SIZE);
	      for(int i = 0; i < Byte.SIZE * bytes.length; i++)
	          sb.append((bytes[i / Byte.SIZE] << i % Byte.SIZE & 0x80) == 0 ? '0' : '1');
	      return sb.toString();
	  }
	  
	  // binary string adjust 
	  // We start a loop from the beginning to end moving up every 7 bits
	  // we check the string to see if when we remove a 1 if it is an even or odd number
	  // depending on the answer we will add a 1 or not as parity
	  
	  public static String binStrParityAdj(String bin){
				  
				  StringBuilder binary = new StringBuilder();
		  		  String s;
				  int len;
				  for(int i = 0; i < bin.length(); i+= Byte.SIZE-1){
					  s = bin.substring(i, i + (Byte.SIZE-1));
					  
					  StringBuilder build = new StringBuilder(s);
					  len = s.length() - s.replace("1", "").length();
					  if(len % 2 == 0) binary.append(build.append('1'));
					  else if (len % 2 == 1) binary.append(build.append('0'));
					  				  
				  } 
				  
			return binary.toString();  
	  }
	  
	  public static String hexStrToAsciiStr(String hex){
		  StringBuilder output = new StringBuilder();
		  String str;
		      for (int i = 0; i < hex.length(); i+=2) {
		           str = hex.substring(i, i+2);
		           output.append((char)Integer.parseInt(str, 16));
		      }
		  return output.toString();
	  }

}