package com.yogi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class GenerateQAFFiles {
	public static enum enumFileType {
		Bean, FormBean, Page, Test;
	}

	
	public static void main(String[] args) {
		String fileName = "C:\\Users\\yogesh.ingale\\workspace\\qaf-onboarding-appium-yogeshingale\\resources\\locators\\createaccount.loc";
		System.out.println(generateSnippetOfFile(fileName, enumFileType.Page));
	}

	/**
	 * Generate Snippet of given file
	 * {@code enum enumFileType: 
		Bean, FormBean, Page, Test;}
	 * 
	 * @param fileName  String
	 * @param enumfileType enumFileType
	 * @return 
	 */
	public static String generateSnippetOfFile(String fileName, enumFileType enumfileType) {
		Path path = Paths.get(fileName);
		String Snippet="Nothing.....";
		try {
			List<String> linesList = Files.readAllLines(path);
			switch (enumfileType) {
			case Page:
				Snippet=yogiUtil.getFormattedString(C.PAGE_FILENAME,yogiUtil.CreateFileName(path))+C.OPENING_CURLY_BRACKET+C.NEW_LINE;
				Snippet=generateCode(linesList,Snippet,enumfileType);	
				Snippet+=C.CLOSING_CURLY_BRACKET;break;

			case Bean:
				Snippet=yogiUtil.getFormattedString(C.BEAN_FILENAME,yogiUtil.CreateFileName(path))+C.OPENING_CURLY_BRACKET+C.NEW_LINE;
				Snippet=generateCode(linesList,Snippet,enumfileType);
				Snippet+=C.CLOSING_CURLY_BRACKET;break;

			case FormBean:
				Snippet=yogiUtil.getFormattedString(C.FORM_BEAN_FILENAME,yogiUtil.CreateFileName(path))+C.OPENING_CURLY_BRACKET+C.NEW_LINE;
				Snippet=generateCode(linesList,Snippet,enumfileType);
				Snippet+=C.CLOSING_CURLY_BRACKET;break;
			case Test:
				Snippet=yogiUtil.getFormattedString(C.TEST_FILENAME_LAY,yogiUtil.CreateFileName(path))+C.OPENING_CURLY_BRACKET+C.NEW_LINE;
				Snippet=generateCode(linesList,Snippet,enumfileType);
				Snippet+=C.SINGLE_TAB+C.TEST_ANNOTAION_LAY+C.NEW_LINE+C.SINGLE_TAB+C.TEST_METHOD_NAME_LAY+C.NEW_LINE;
				break;
			}
			
			//	createFile(Paths.get(path.getParent().toString(), CreateFileName(path)+".java"), Snippet);

		} catch (IOException e) {
			System.out.println("File doesnot exist.[ path = "+path+" ]");
		}
		
		return Snippet;
	}

	private static String generateCode(List<String> linesList,String Snippet,enumFileType enumfileType) {
		for (String line : linesList) {
			if(line.split("=").length>2){
				switch (enumfileType) {
				case FormBean:
					Snippet+=C.SINGLE_TAB+C.RANDOMIZER+C.NEW_LINE;
					Snippet+=C.SINGLE_TAB+createLocator(enumfileType, line.split("=")[0], line.split("=")[0].split("[.]", 3)[1])+C.NEW_LINE;
					break;
				case Bean:
					Snippet+=C.SINGLE_TAB+C.RANDOMIZER+C.NEW_LINE;
					Snippet+=C.SINGLE_TAB+createLocator(enumfileType,line.split("=")[0].split("[.]", 3)[1])+C.NEW_LINE;

					break;
				case Page:
					Snippet+=C.SINGLE_TAB+createLocator(enumfileType, line.split("=")[0], line.split("=")[0].split("[.]", 3)[1])+C.NEW_LINE;
					break;
				}
			}
		}
		return Snippet;
	}

	/**
	 * 
	 * @param enumfileType
	 * @param line
	 * @return
	 */
	private static String createLocator(enumFileType enumfileType, String... line) {

		switch(enumfileType){
		case Bean:line[0]=yogiUtil.getFormattedString(C.BEAN_VARIABLE, line);
		break;
		case FormBean:line[0]=yogiUtil.getFormattedString(C.UI_ELEMENT, line);
		break;
		case Page:line[0]=yogiUtil.getFormattedString(C.FINDBY, line);
		break;
		}
		return line[0];
	}

}
