package main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Utilities {
	
	private static Scanner sc;
	
	public static void openScanner() {
		sc = new Scanner(System.in);
	}
	
	public static void closeScanner() {
		if (sc != null) sc.close();
	}
	
	public static void read(String filename) {
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			   String line;
			   while ((line = br.readLine()) != null) {
			       System.out.println(line);
		   }
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	public static int askInput(){
		try {
			int input = sc.nextInt();
			sc.nextLine();
			return input;
		} catch (InputMismatchException e) {
			sc.nextLine();
			System.out.println("Invalid option.");
		}
		return -1;
	}
	
	public static String askInputString() {
		String input = sc.nextLine();
		return input;
	}
	
	public static boolean isValidPath(String path) {
	    try {
	        Paths.get(path);
	    } catch (InvalidPathException | NullPointerException ex) {
	        return false;
	    }
	    return true;
	}
}
