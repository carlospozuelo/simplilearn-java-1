package main.java;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainMenu {
	
	private static final String SERIALIZATION_PATH = "fileManipulator.ser"; 
	
	private FileManipulator fileManipulator;
	
	public MainMenu() {
		fileManipulator = new FileManipulator();
	}
	
	
	public void serialize() throws IOException {
	     FileOutputStream fileOut =
	     new FileOutputStream(SERIALIZATION_PATH);
	     ObjectOutputStream out = new ObjectOutputStream(fileOut);
	     out.writeObject(fileManipulator);
	     out.close();
	     fileOut.close();
		  
	}
	
	private void deSerialize() {
		try {
	         FileInputStream fileIn = new FileInputStream(SERIALIZATION_PATH);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         fileManipulator = (FileManipulator) in.readObject();
	         in.close();
	         fileIn.close();
	         System.out.println("Previously stored files found. Restoring configuration...");
	      } catch (Exception e) { }
	}
	
	public void run() {
		boolean continues = true;
		deSerialize();
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
				case 3:
					continues = false;
				break;
			}
		} while (continues);
	}
	
	
	private void displayOptions() {
		Utilities.read("src/main/resources/options.txt");
	}
	
	
}
