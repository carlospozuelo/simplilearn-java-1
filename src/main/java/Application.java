package main.java;

import java.io.IOException;

public class Application {
	public static void main(String[] args) {
		Utilities.openScanner();
		Utilities.read("src/main/resources/greeting.txt");
		
		MainMenu m = new MainMenu();
		m.run();
		Utilities.closeScanner();
		
		System.out.println("Thank you for using LockedMe.com. Your data is being saved...");
		
		try {
			m.serialize();
			System.out.println("Your data was saved succesfully.");
		} catch (IOException e) {
			System.out.println("Your data couldn't be saved! " + e);
		}
		System.exit(0);
	}
}
