/*********
Cartwright, Stephen D.
3/19/16


Account class 
contains all helper functions for dictionary attack 
	get name of user
	get string if salted
	is the string salted
	get encrypted hash of password
*********/

public class Account {

	private String name, salt, enc;
	private boolean hasSalt;
	public Account (String _name, String _salt, String _enc) {
		name = _name;
		salt = _salt;
		// check if salted or not 
		if (!salt.equals("")) hasSalt = true;
		else hasSalt = false;
		enc = _enc;
	}

	public String getName () {
		return name;
	}
	
	public String getEnc () {
		return enc;
	}
	
	public String getSalt () {
		return salt;
	}
	
	public boolean isSalt() {
		return hasSalt;
	}
}