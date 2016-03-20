public class ParseCmd {
	//protected ArrayList<String> fileNames;
	protected String[] fileNames;
	public ParseCmd(String[] arguments){
		System.err.println("Usage: Recover -p passwords -d dictionary");
		assert arguments.length == 4;
		
	 String arg;
	 char flag = 'z';
	 int index = 0;
	 fileNames = new String[2];
		
		while(index < arguments.length && arguments[index].startsWith("-")) {
		
			arg = arguments[index++];
			flag = arg.charAt(1);
			
				switch(flag) {
					case 'p': if(index < arguments.length)
								fileNames[0] = arguments[index++];
							  break;
							
					case 'd': if(index < arguments.length)
								fileNames[1] = arguments[index++];
							  break;
					
					default: System.err.println("Recover: Illegal option " + flag);
							 
							 break;
				}
		
		}
		
	}
	
	public String[] getFileList() {
		return fileNames;
	}

}