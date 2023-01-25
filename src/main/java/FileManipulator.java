package main.java;

import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.swing.JFileChooser;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class FileManipulator {
	
	
	
	private Map<String, String> allFiles;
	
	private String currentPrefix;
	
	public FileManipulator() {
		this.allFiles = new TreeMap<String, String>();
		currentPrefix = new JFileChooser().getFileSystemView().getDefaultDirectory().toString();
	}
	
	public void displayAllFiles() {
		if (allFiles.isEmpty()) {
			System.out.println("There are no files registered in the system yet.");
		} else {
			int count = allFiles.size();
			System.out.println(count + " file" + (count != 1? "s" : "") + " found.");
			for (Map.Entry<String, String> file: allFiles.entrySet()) {
				String print = "- " + file.getKey();
				if (!file.getKey().equals(file.getValue()))
					print += ", " + file.getValue();
				System.out.println(print);
			}
		}
	}
	
	private void displayOptions() {
		Utilities.read("src/main/resources/options_manipulation.txt");
		System.out.println("\t\t\tCurrent searchpath is " + this.currentPrefix);
	}
	
	private void addFile() {
		boolean continues = true;
		do {
			System.out.println("Type the name of the file. Searching in " + currentPrefix);
			String name = currentPrefix.isEmpty() ? "" : currentPrefix + "\\" + Utilities.askInputString();
			if (Utilities.isValidPath(name)) {
				File f = new File(name);
				if(f.isFile() && !f.isDirectory()) { 
					System.out.println("Found file on the specified path! (" + name + ")");
					System.out.println("Type a name to create a shortcut to this path. Leave empty to just register the path.");
					createShortcut(name);
					continues = false;
				} else {
					System.out.println("File does not exist. Press + \"Y\" to create it.");
					char c = Utilities.askInputString().charAt(0);
					if (c == 'Y' || c == 'y') {
						try {
							f.createNewFile();
							System.out.println("File created succesfully! ");
							System.out.println("Type a name to create a shortcut to this path. Leave empty to just register the path.");
							createShortcut(name);
							
						} catch (IOException e) {
							System.out.println("File could not be created.");
						}
					} else {
						System.out.println("Aborting file creation.");
					}
					continues = false;
				}
			}
		} while (continues);
	}
	
	private void createShortcut(String name) {
		boolean shortcutFound = false;
		do {
			String shortcut = Utilities.askInputString();
			if (shortcut.isEmpty() && allFiles.containsKey(name)) {
				System.out.println("The file is already registered without a shortcut.");
				shortcutFound = true;
			} else if (shortcut.isEmpty()){
				allFiles.put(name, name);
				System.out.println("File registered on the system without a shortcut.");
				shortcutFound = true;
			} else if (allFiles.containsKey(shortcut)) {
				System.out.println("That shortcut is already registered. Please, write a different one.");
			} else {
				allFiles.put(shortcut, name);
				System.out.println("File registered succesfully!");
				shortcutFound = true;
			}
		} while (!shortcutFound);
	}
	
	private void changePath() {
		System.out.println("The current search path is " + currentPrefix);
		System.out.println("Introduce a new search path, or leave blank to cancel");
		String name = Utilities.askInputString();
		if (!name.isBlank()) {
			if (Utilities.isValidPath(name) && new File(name).isDirectory()) {
				this.currentPrefix = name;
				System.out.println("Search path registered succesfully!");
			} else System.out.println("Invalid search directory.");
		}
	}
	
	private void deleteFile() {
		if (!allFiles.isEmpty()) {
			displayAllFiles();
			System.out.println("Please write the shortcut of the file you want to remove");
			String name = Utilities.askInputString();
			if (allFiles.containsKey(name)) {
				allFiles.remove(name);
				System.out.println("File deleted succesfully");
			} else System.out.println("File couldn't be found.");
			
		} else System.out.println("There are no files in the system.");
	}
	
	private void searchFile() {
		System.out.println("Please write the name of the file you'd like to search.");
		String name = Utilities.askInputString();
		
		// To also add by path and not only by shortcut
		Set<String> results = allFiles.keySet().stream().filter(key -> name.equals(allFiles.get(key))).collect(Collectors.toSet());
		
		if (allFiles.containsKey(name))
			results.add(allFiles.get(name));
		
		if (results.size() > 0) {
			System.out.println("Found " + results.size() + " result" + (results.size() != 1? "s": ""));
			for (String s: results) {
				System.out.println("- " + s);
			}
		} else {
			System.out.println("No results found.");
		}
	}
	
	public void manipulateFiles() {
		boolean continues = true;
		do {
			displayOptions();
			int option = Utilities.askInput();
			switch (option) {
				case 0:
					changePath();
				break;
				case 1:
					addFile();
				break;
				case 2:
					deleteFile();
				break;
				case 3:
					searchFile();
				break;
				case 4:
					continues = false;
				break;
			}
		} while (continues);
	}
}
