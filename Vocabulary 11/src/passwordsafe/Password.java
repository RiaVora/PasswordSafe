package passwordsafe;

public class Password {
	//One fatal error: does not account for the password being empty or less than three characters
	
	//variables
	public String site;
	public String pw;
	
	//two argument constructor
	public Password(String site, String pw) {
		this.site = site;
		this.pw = pw;
	}
	
	//method that returns encrypted password
	public String encrypt() {
		String epw = pw.substring(0, 2);
		for (int i = 0; i < (pw.length() - 2); i++) {
			epw += "*";
		}
		return epw;
	}
	
	
	//print methods
	public static void p(String s) {
		System.out.print(s);
	}
	
	public static void pln(String s) {
		System.out.println(s);
	}
}
