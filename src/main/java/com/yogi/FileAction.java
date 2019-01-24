package com.yogi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileAction {
	
	
	
	/**
	 * Read file and prints line by line
	 * @param fileName
	 */
	public static void readLocatorFile(String fileName) {
		String directory = System.getProperty("user.dir") + "/resources/locators/";
		Path path = Paths.get(directory, fileName);
		try {
			List<String> list = Files.readAllLines(path);
			list.forEach(line -> System.out.println(line));
		} catch (IOException e) {
			// exception handling
		}
	}

	/**
	 * 
	 * Create new java file
	 *
	 * @param path
	 * @param Snippet
	 */
	public static void createFile(Path path,String Snippet) {
		try {  
			Files.write(path, Snippet.getBytes(), StandardOpenOption.CREATE_NEW);
		} catch (IOException e) {
			System.out.println("File can not be created. [ path="+path+" ]");
		}

	}
}

