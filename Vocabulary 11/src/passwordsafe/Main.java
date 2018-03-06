package passwordsafe;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
	public static void main(String[] args) {
		
		//scanner
		Scanner input = new Scanner(System.in);
		
		//introduction
		pln("Time to make a safe!");
		
		//obtains user inputed key
		Safe safe = new Safe(makeKey(input));
		
		//asks the user if they are ready are to continue, and doesn't continue until they are ready
		check(input, "Ready to continue");
		
		//adds the desired sites and passwords to the user's safe
		initializeSafe(safe, input);
		
		//displays main menu
		pln("Here is your main menu: ");
		mainMenu(safe, input);
		
		//reruns main menu while game is true, and allows exiting
		boolean game = true;
		while (game) {
			p("\nPlease input e to exit, and m to see the main menu again: ");
			String ans = input.next().toLowerCase();
			if (ans.equals("e")) {
				game = false;
				break;
			}
			mainMenu(safe, input);
		}
		
		
	}
	
	
	
	//obtains a user key
	public static int makeKey(Scanner input) {
		p("\nPlease input your 4 - 8 number key: ");
		int key = input.nextInt();
		pln("Please save and remember your key.");
		return key;
	}
	
	//checks if the user is ready to continue
	public static void check(Scanner input, String ask) {
		boolean check = false;
		while (check == false) {
			p("\n" + ask + "?: ");
			String user = input.next().toLowerCase();
			if (user.equals("y") || user.equals("yes")) {
				check = true;
			}
		}
	}
	
	//asks user for the number of initial passwords, and adds them to safe
	public static void initializeSafe(Safe safe, Scanner input) {
		int num = 2;
		boolean ask = true;
		while (ask){
			try {
				p("\nHow many sites and corresponding passwords would you like to input?: ");
				Scanner scanner = new Scanner(System.in);
				num = scanner.nextInt();
				if (num > 0) {
					ask = false;
				}
			} catch (InputMismatchException e) {
				p("That is not a integer. Please enter a number");
			} finally {
				pln("\nRunning: ");
			}
		}
		
		addIntialPW(num, safe, input);
		
	}
	
	//adds initial sites and passwords to safe
	private static void addIntialPW(int num, Safe safe, Scanner input) {
		for (int i = 0; i < num; i++) {
			p("\nPlease enter Site " + (i + 1) + ": ");
			String site = input.next();
			p("Please enter Password " + (i + 1) + ": ");
			String pw = input.next();
			safe.addPassword(site, pw);
		}
	}
	
	//displays main menu and performs corresponding functions
	public static void mainMenu(Safe safe, Scanner input) {
		continueMainMenu(safe, input);
		
	}
	
	//checks if the user inputed value for Main Menu is true and runs it through execute
	private static void continueMainMenu(Safe safe, Scanner input) {
		boolean cont = false;
		int choice = 0;
		while (cont == false) {
			displayMainMenu();
			p("\nPlease input the single digit option here: ");
			choice = input.nextInt();
			cont = ((choice == 1) || (choice == 2) || (choice == 3)) ? true : false;
		}
		executeChoice(choice, safe, input);
	}
	
	//displays the main menu
	private static void displayMainMenu() {
		pln("\nMain Menu:");
		pln("1: View Sites and Passwords");
		pln("2: Add Site and Password");
		pln("3: Change Key");
	}
	
	//executes the choice given by the user
	private static void executeChoice(int choice, Safe safe, Scanner input) {
		if (choice == 1) {
			choiceOne(safe, input);
		} else if (choice == 2) {
			initializeSafe(safe, input);
		} else {
			choiceThree(safe, input);
		}
	}
	
	//executes choice one by running safe display with or without the correct key, and asking for a redo
	private static void choiceOne(Safe safe, Scanner input) {
		boolean cont = true;
		while (cont) {
			pln("\nIn order to display, I need the correct key.");
			pln("If your key is incorrect, the passwords will display with encrytions.");
			pln("If you want to display the passwords with their encryptions, please enter 0 as your key.");
			p("\nKey: ");
			int key = input.nextInt();
			safe.display(key);
			p("\nWant to display the passwords again? (and re-input the key)? Please answer yes or no: ");
			String ans = input.next().toLowerCase();
			cont = (ans.equals("y") || ans.equals("yes")) ? true : false;
		}
	}
	
	//executes choice three by changing the key if given a past correct key, and if not asking the user to continue
	private static void choiceThree(Safe safe, Scanner input) {
		boolean cont = true;
		while (cont) {
			p("\nIn order to change your key, I need your old key: ");
			if (input.nextInt() == safe.key) {
				cont = false;
				pln("Congrats, that was correct! Now for your new key.");
				safe.newKey(makeKey(input));
			} else {
				p("\nWrong key. Would you like to try again?: ");
				String ans = input.next().toLowerCase();
				if (!(ans.equals("y")) && !(ans.equals("yes"))) {
					cont = false;
					break;
				}
			}
		}
	//TO FIX THE COMPILE TIME ERROR, UNCOMMENT THE BRACKET BELOW THIS LINE
	}
	
	
	//print methods
	public static void p(String s) {
		System.out.print(s);
	}
	
	public static void pln(String s) {
		System.out.println(s);
	}
}
