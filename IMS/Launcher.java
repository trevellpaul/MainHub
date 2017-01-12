package IMS;
import java.util.Scanner;

public class Launcher {
	static Scanner scanner;
	//static InventorySystem system;

	//entry point for the system
	public static void main(String[] args) {
		//system = new InventorySystem();

		scanner = new Scanner(System.in);
		System.out.println("\nWelcome to the IMS.");
		System.out.println("Type 'gui' to enable the GUI, or 'command' for commandLine.");
		UserInput gui;

		while (true){
			String getNextLine = scanner.nextLine();
			if (getNextLine.equals("gui")){
				System.out.println("Preferences selected. Preparing to launch the GUI...");
				gui = new UserInput(true);
				gui.runGUI();
				gui.setVisible(true);
				break;
			}
			else if (getNextLine.equals("command")){
				System.out.println("Preferences selected. Type 'help' for a list of commands.");
				gui = new UserInput(false);
				gui.runCommandLine();
				break;
			}
		}
	}
}