package main.java;

public class MainMenu {
	
	private FileManipulator fileManipulator;
	
	public MainMenu() {
		fileManipulator = new FileManipulator();
	}
	
	public void run() {
		boolean continues = true;
		do {
			
			displayOptions();
			int option = Utilities.askInput();
			switch (option) {
				case 1:
					fileManipulator.displayAllFiles();
				break;
				case 2:
					fileManipulator.manipulateFiles();
				break;
			}
		} while (continues);
	}
	
	
	private void displayOptions() {
		Utilities.read("src/main/resources/options.txt");
	}
	
	
}
