/*********
Cartwright, Stephen D.
3/19/16


ParseCmd class 
parses out the command line arguments 
*********/

public class ParseCmd {
	
	private String[] fileNames;
	public ParseCmd(String[] arguments){
		// "Usage: Recover -p passwords -d dictionary" 
		// so make sure "-p", "passwords", "-d", "dictionary"
		assert arguments.length == 4;
		
		// create helper variables 
	 String arg;
	 char flag = 'z';
	 int index = 0;
	 boolean pSet = false, dSet = false;
	 
	 // program only takes two files in 
	 fileNames = new String[2];
		
	 //check if we're < 4 times looped and we have a "-"
		while(index < arguments.length && arguments[index].startsWith("-")) {
			
			// argument is either a -p or -d
			arg = arguments[index++];
			// flag set to letter after the dash mark
			flag = arg.charAt(1);
			
				switch(flag) { // for each make sure we didnt set the password
							   // or dictionary flag already
					case 'p': if(index < arguments.length && pSet == false)
								fileNames[0] = arguments[index++];
								pSet = true;
							  break;
							
					case 'd': if(index < arguments.length && dSet == false)
								fileNames[1] = arguments[index++];
							  dSet = true;
							  break;
					
					default: System.err.println("Recover: Illegal option " + flag);
							 
							 break;
				}
		
		}
		
	}
	
	// return file name list
	public String[] getFileList() {
		return fileNames;
	}

}