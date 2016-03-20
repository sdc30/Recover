public class Account {

	private String name, salt, enc;
	private boolean hasSalt;
	public Account (String _name, String _salt, String _enc) {
		name = _name;
		salt = _salt;
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