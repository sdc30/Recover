import java.util.*;
import java.lang.StringBuilder;
import java.util.HashMap;
import java.util.Map;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;

class runMain {
	static final ArrayList<Account> accounts = new ArrayList<Account>();
	static final HashMap<String, String> dictionaryHashes = new HashMap<String, String>();
	static final ArrayList<String> dictionary = new ArrayList<String>();
	static final ArrayList<String> vals = new ArrayList<String>();
	
	public static void main (String[] args) {
		
		System.out.println("Started..\n");
		
		ParseCmd parsed = new ParseCmd (args);
		String[] files = parsed.getFileList();
		
		File pass = new File (files[0]);
		File dict = new File (files[1]);
		
		try (BufferedReader br = new BufferedReader(new FileReader(pass))) {
			
		    for (String line; (line = br.readLine()) != null; ) {
				
				String[] ar = line.split(" ");
				
				if (ar[1].equals("1")) 
					accounts.add(new Account(ar[0], ar[2], ar[3]));
				else 
					accounts.add(new Account(ar[0], "", ar[2]));
		    }
		}  catch (FileNotFoundException fnf_e) {
            fnf_e.printStackTrace();
        }  catch (IOException io_e) {
            io_e.printStackTrace();
        }
		
		try (BufferedReader br = new BufferedReader(new FileReader(dict))) {
			
		    for(String line; (line = br.readLine()) != null; ) {
				dictionary.add(line);
		    }
		}  catch (FileNotFoundException fnf_e) {
            fnf_e.printStackTrace();
        }  catch (IOException io_e) {
            io_e.printStackTrace();
        }  
		

	 try{
		for (String key: dictionary) {
			
				dictionaryHashes.put(Recover.sha1(key), key);
			
			String noVowels = new String(key.replaceAll("[AaEeIiOoUu]", ""));
				dictionaryHashes.put(Recover.sha1(noVowels), noVowels);
			
			String reversed = new StringBuilder(key).reverse().toString();
				dictionaryHashes.put(Recover.sha1(reversed), reversed);
			
			String revNoVowels = new String(reversed.replaceAll("[AaEeIiOoUu]", ""));
				dictionaryHashes.put(Recover.sha1(revNoVowels), revNoVowels);
			
			
			
			for(int i = 0; i < accounts.size(); i++) {
				
			 if(accounts.get(i).isSalt()) { 
				String s = accounts.get(i).getSalt();

				String saltRegular = Recover.sha1(s, key);
					dictionaryHashes.put(saltRegular, key);
					
				String saltNoVowels = Recover.sha1(s, noVowels);
					dictionaryHashes.put(saltNoVowels, noVowels);
					
				String saltReversed = Recover.sha1(s, reversed);
					dictionaryHashes.put(saltReversed, reversed);
					
				String saltRevNoVowels = Recover.sha1(s, revNoVowels);
					dictionaryHashes.put(saltRevNoVowels, revNoVowels);


				// vals.add(saltRegular + " : " + key + " : " + s+key);
				// vals.add(saltNoVowels + " : " + noVowels + " : " + s+key);
				// vals.add(saltReversed + " : " + reversed + " : " + s+key);
				//vals.add(saltRevNoVowels + " : " + revNoVowels + " : " + s+key);

				// vals.add(saltRegular);
				// vals.add(saltNoVowels);
				// vals.add(saltReversed);
				// vals.add(saltRevNoVowels);

				
				
			 }
			}
			

		}
		printer();
	  }	
	  catch(Exception e) {
			
	  }
		
		

	}
	
	public static void printer (){
		System.out.println ("Printing..\n");
		
        File output = new File ("hashes.txt");
		PrintWriter pw = null;
        try {
            pw = new PrintWriter (output);
			
			// for(String s: vals)
			// 		    pw.println (s);
				
			
			for(Account acc: accounts) {
				//System.out.println(acc.getEnc());
				//for (Map.Entry<String, String> entry : sor.entrySet())
			    //	pw.println (entry.getKey()+" : "+entry.getValue());
				if(dictionaryHashes.containsKey(acc.getEnc()))
						pw.format ("%s (pass) == [%s] -- (with hash %s)\n", acc.getName(), dictionaryHashes.get(acc.getEnc()), acc.getEnc());
				
				}

				//}
            pw.close();
        } catch (FileNotFoundException fnf_e) {
            fnf_e.printStackTrace();
        }
        finally
        {
            if ( pw != null ) 
            {
                pw.close();
                System.exit(0);
		
            }
        }
		
	}
	


	
}



