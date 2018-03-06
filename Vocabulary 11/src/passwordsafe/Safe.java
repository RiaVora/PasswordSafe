package passwordsafe;
import java.util.ArrayList;

public class Safe {
	
	//fatal error: doesn't check if the key is four to eight letters or not or not
	
	//variables
	private ArrayList<Password> pws = new ArrayList<Password>();
	public int key;
	
	//no argument constructor
	public Safe(int key) {
		this.key = key;
	}
	
	public void addPassword(String site, String pw) {
		Password p = new Password(site, pw);
		pws.add(p);
	}
	
	//displays the passwords encrypted or open depending on the key inputed
	public void display(int key) {
		if (this.key == key) {
			displayWithKey();
		} else {
			displayWithoutKey();
		}
	}
	
	//displays the passwords and sites with no encryption
	private void displayWithKey() {
		for (int i = 0; i < pws.size(); i++) {
			pln("\n" + (i + 1) + ":");
			pln("Site Name: " + pws.get(i).site);
			pln("Password: " + pws.get(i).pw);
		}
	}
	
	//displays the passwords and sites with encryption
	private void displayWithoutKey() {
		for (int i = 0; i < pws.size(); i++) {
			pln("\n" + (i + 1) + ":");
			pln("Site Name: " + pws.get(i).site);
			pln("Password: " + pws.get(i).encrypt());
		}
	}
	
	//creates a new key for safe
	public void newKey(int newKey) {
		this.key = newKey;
	}

	
	public static void p(String s) {
		System.out.print(s);
	}
	
	public static void pln(String s) {
		System.out.println(s);
	}
}
