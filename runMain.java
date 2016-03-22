/*********
Cartwright, Stephen D.
3/19/16


runMain class 
runs everything for the dictionary attack
*********/


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
	
	// create array lists and hashmap for its named components 
	static final ArrayList<Account> accounts = new ArrayList<Account>();
	static final HashMap<String, String> dictionaryHashes = new HashMap<String, String>();
	static final ArrayList<String> dictionary = new ArrayList<String>();
	
	
	public static void main (String[] args) {
		
		System.out.println("Started..\n");
		
		// parse out the command line 
		ParseCmd parsed = new ParseCmd (args);
		
		// get the filenames from ParseCmd
		String[] files = parsed.getFileList();
		
		
		File p = new File (files[0]), d = new File (files[1]);
		
		parseAccounts(p);
		makeDictionary(d);
		attackDictionary();

	

	}
	
	public static void parseAccounts(File pass) {
		// read in the password text file 
		try (BufferedReader br = new BufferedReader(new FileReader(pass))) {
			// take the file by each line
		    for (String line; (line = br.readLine()) != null; ) {
				// split the line on spaces
				String[] ar = line.split(" ");
				// if the "second" element is a 1 it is salted so
				if (ar[1].equals("1")) 
					accounts.add(new Account(ar[0], ar[2], ar[3]));
				else 
					// just add a blank for the salt
					accounts.add(new Account(ar[0], "", ar[2]));
		    }
		}  catch (FileNotFoundException fnf_e) {
            fnf_e.printStackTrace();
        }  catch (IOException io_e) {
            io_e.printStackTrace();
        }
		
		
	}
	
	public static void makeDictionary(File dict) {
		// read in the dictionary
		try (BufferedReader br = new BufferedReader(new FileReader(dict))) {
			// read into a dictionary array list each string word
		    for(String line; (line = br.readLine()) != null; ) {
				dictionary.add(line);
		    }
		}  catch (FileNotFoundException fnf_e) {
            fnf_e.printStackTrace();
        }  catch (IOException io_e) {
            io_e.printStackTrace();
        } 
		
	
	}
	
	public static void attackDictionary() {
		
   	 try{ // iterate over the dictionary
   		for (String key: dictionary) {
			// rem for removed vowels 
			// rev for reversed string
			// reg for continuity 
   			String rem, rev, reg = key;
   					dictionaryHashes.put(Recover.sha1(reg), reg);
					
   				rem = Recover.remove(key);
   					dictionaryHashes.put(Recover.sha1(rem), rem);
					
   				rev = Recover.reverse(key);
   					dictionaryHashes.put(Recover.sha1(rev), rev);			
			
					// go over the accounts and check if we have a salt to hash
					// since there is only 2 salts this shouldnt take very long
   			for(int i = 0; i < accounts.size(); i++) {
				
   			 if(accounts.get(i).isSalt()) { 
				// sReg for salted regular string
				// sRev for salted reverse 
				// sRem for salted remove
   				String s = accounts.get(i).getSalt();
   				String sReg, sRem, sRev;
				
   				sReg = Recover.sha1(s, reg);
   					dictionaryHashes.put(sReg, reg);
					
   				sRem = Recover.sha1(s, rem);
   					dictionaryHashes.put(sRem, rem);
					
   				sRev = Recover.sha1(s, rev);
   					dictionaryHashes.put(sRev, rev);

				
   			 }
   			}
			

   		}
		// output to file
   		printer();
   	  }	  catch(Exception e) {
			
	  }
		
	}
	
	public static void printer (){
		System.out.println ("Printing..\n");
		
        File output = new File ("hashes.txt");
		PrintWriter pw = null;
        try {
            pw = new PrintWriter (output);

			// iterate over the accounts
			for(Account acc: accounts) {
				// if the dictionary hashmap has a key of the hashed value associated with
				// the account get its name from accounts; value and password from the hashmap
				if(dictionaryHashes.containsKey(acc.getEnc()))
						pw.format ("%s (pass) == [%s] -- (with hash %s)\n", acc.getName(), dictionaryHashes.get(acc.getEnc()), acc.getEnc());
				
				}

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



