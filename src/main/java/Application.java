package main.java;


public class Application {
	public static void main(String[] args) {
		Utilities.openScanner();
		Utilities.read("src/main/resources/greeting.txt");
	
		MainMenu m = new MainMenu();
		m.run();
		Utilities.closeScanner();
	}
}
