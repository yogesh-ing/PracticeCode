package com.yogi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.yogi.GenerateQAFFiles.enumFileType;

public class GenerateTestClass {
	public static void main(String[] args) {
		createTestClass("C:\\Users\\yogesh.ingale\\workspace\\zPractise\\src\\main\\java\\locators\\Demo.Test");
	}

	
	private static String test = "", testdesc = "", datapro = "", innercode = "";

	public static ArrayList<String> generateTestSnippetOfFile(String fileName) {
		Path path = Paths.get(fileName);
		ArrayList<String> snippet = new ArrayList<>();
		String rememberPageObject = "";
		int testMethodCnt=-1;
		String tempTest="Nothing....";

		try {
			List<String> linesList = Files.readAllLines(path);
			long lineNumber = 0;
			for (String line : linesList) {
				lineNumber++;
				switch (line.trim().split("=")[0].toUpperCase()) {
				case "DATAPRO":
					if (line.trim().split("=").length > 1)
						datapro = C.SINGLE_TAB + C.QAF_data_provider_method + replaceString(line, "DATAPRO");
					else
						datapro = "error" + lineNumber;
					break;
				case "TEST":
					if (line.trim().split("=").length > 1){
						
						if(testMethodCnt>=0){
							tempTest = datapro + C.NEW_LINE + C.SINGLE_TAB + C.TEST_ANNOTAION + C.OPENING_ROUND_BRACKET + testdesc
									+ C.CLOSING_ROUND_BRACKET + C.NEW_LINE + test + C.OPENING_CURLY_BRACKET + C.NEW_LINE + innercode
									+ C.SINGLE_TAB + C.CLOSING_CURLY_BRACKET;

							snippet.add(testMethodCnt, tempTest);
							test = ""; testdesc = ""; datapro = ""; innercode = "";
							tempTest="";

						}
						testMethodCnt++;

						test = C.SINGLE_TAB + yogiUtil.getFormattedString(C.TEST_METHOD_NAME, line.trim().split("=")[1]);
					}
					else
						test = "error" + lineNumber;
					break;
				case "DESC":
					if (line.trim().split("=").length > 1)
						testdesc = yogiUtil.getFormattedString(C.TEST_DESC, line.trim().split("=")[1]);
					else
						testdesc = "error" + lineNumber;
					break;
				case "OPEN":
					if (line.trim().split("=").length > 1) {
						rememberPageObject = line.trim().split("=")[1].toLowerCase();
						innercode += C.DOUBLE_TAB
								+ yogiUtil.getFormattedString(C.Create_page_object, line.trim().split("=")[1],
										line.trim().split("=")[1].toLowerCase(), line.trim().split("=")[1])
								+ C.NEW_LINE;
					} else
						innercode += "error" + lineNumber;
					break;
				case "CLICK":
					if (line.trim().split("=").length > 1)
						innercode += C.DOUBLE_TAB + yogiUtil.getFormattedString(C.Test_click_call, rememberPageObject,
								line.trim().split("=")[1]) + C.NEW_LINE;
					else
						innercode += "error" + lineNumber;
					break;
				case "METHOD":
					if (line.trim().split("=").length > 1)
						innercode += C.DOUBLE_TAB + yogiUtil.getFormattedString(C.Test_method_call, rememberPageObject,
								line.trim().split("=")[1]) + C.NEW_LINE;
					else
						innercode += "error" + lineNumber;
					break;
				case "SENDKEY":
					if (line.trim().split("=").length > 1)
						innercode += C.DOUBLE_TAB + yogiUtil.getFormattedString(C.Test_sendKey_call, rememberPageObject,
								line.trim().split("=")[1]) + C.NEW_LINE;
					else
						innercode += "error" + lineNumber;
					break;
				case "VERIFY":
					if (line.trim().split("=").length > 1)
						innercode += C.DOUBLE_TAB + yogiUtil.getFormattedString(C.Test_verify_visible_call,
								rememberPageObject, line.trim().split("=")[1], line.trim().split("=")[1]) + C.NEW_LINE;
					else
						innercode += "error" + lineNumber;
					break;
				}
			}
			
			if(!tempTest.equals("Nothing....")){
				tempTest = datapro + C.NEW_LINE + C.SINGLE_TAB + C.TEST_ANNOTAION + C.OPENING_ROUND_BRACKET + testdesc
						+ C.CLOSING_ROUND_BRACKET + C.NEW_LINE + test + C.OPENING_CURLY_BRACKET + C.NEW_LINE + innercode
						+ C.SINGLE_TAB + C.CLOSING_CURLY_BRACKET;

				snippet.add(testMethodCnt, tempTest);
			}
			

			// snippet+=CLOSING_CURLY_BRACKET;
			// createFile(Paths.get(path.getParent().toString(),
			// CreateFileName(path)+".java"), Snippet);
		} catch (IOException e) {
			System.out.println("File doesnot exist.[ path = " + path + " ]");
		}
	//	System.out.println(snippet);
		return snippet;
	}

	private static String replaceString(String line, String strKey) {
		String snippet = "";
		switch (strKey) {
		case "DATAPRO":
			line = line.replaceAll("[" + strKey + "() ]", "").replaceFirst("=", "");
			if (line.contains("FILE")) {
				snippet = C.OPENING_ROUND_BRACKET
						+ yogiUtil.getFormattedString(C.dateFile, line.split("=")[1].split(",")[0]) + C.coma;
				snippet += yogiUtil.getFormattedString(C.key, line.split("=")[2]) + C.CLOSING_ROUND_BRACKET;
			} else
				snippet = C.OPENING_ROUND_BRACKET + yogiUtil.getFormattedString(C.key, line.split("=")[1])
				+ C.CLOSING_ROUND_BRACKET;
			break;
		default:
		}
		return snippet;
	}

	private static String generateCode(List<String> linesList, String snippet2) {
		// TODO Auto-generated method stub
		return null;
	}

	private static void createTestClass(String fileName) {
		ArrayList<String> snippet=generateTestSnippetOfFile(fileName);
		String tempStr=GenerateQAFFiles.generateSnippetOfFile(fileName, enumFileType.Test);
		tempStr=tempStr.split("[{]")[0]+C.OPENING_CURLY_BRACKET+C.NEW_LINE;
		for(String testMethod:snippet){
			tempStr+=testMethod;
		}
		tempStr+=C.NEW_LINE+C.CLOSING_CURLY_BRACKET;
		System.err.println(tempStr);
	}
	
	private static String elementVerification(String code) {
		
		switch(code.split("[(]")[0].toUpperCase()){
		case "WAITFORVISIBLE": code+=".waitForVisible()";break;

		case "WAITFORNOTVISIBLE":code+=".waitForNotVisible()";break;

		case "WAITFORDISABLED":code+=".waitForDisabled()";break;

		case "WAITFORENABLED":code+=".waitForEnabled()";break;

		case "WAITFORPRESENT":code+=".waitForPresent()";break;

		case "WAITFORNOTPRESENT":code+=".waitForNotPresent()";break;

		case "WAITFORTEXT":code+=".waitForText(String text/StringMatcher matcher)";break;

		case "WAITFORNOTTEXT":code+=".waitForNotText(String text/StringMatcher matcher)";break;

		case "WAITFORVALUE":code+=".waitForValue(Object value)";break;

		case "WAITFORNOTVALUE":code+=".waitForNotValue(Object value)";break;

		case "WAITFORSELECTED":code+=".waitForSelected()";break;

		case "WAITFORNOTSELECTED":code+=".waitForNotSelected()";break;

		case "WAITFORATTRIBUTE":code+=".waitForAttribute(String attr, String value/StringMatcher value)";break;

		case "WAITFORNOTATTRIBUTE":code+=".waitForNotAttribute(String attr, String value/StringMatcher value)";break;

		case "WAITFORCSSCLASS":code+=".waitForCssClass(String className)";break;

		case "WAITFORNOTCSSCLASS":code+=".waitForNotCssClass(String className)";break;

		case "WAITFORCSSSTYLE":code+=".waitForCssStyle(String prop, String value)";break;

		case "WAITFORNOTCSSSTYLE":code+=".waitForCssStyle(String prop, String value)";break;

		// verifications
		case "VERIFYPRESENT":code+=".verifyPresent()";break;

		case "VERIFYNOTPRESENT":code+=".verifyNotPresent()";break;

		case "VERIFYVISIBLE":code+=".verifyVisible()";break;

		case "VERIFYNOTVISIBLE":code+=".verifyNotVisible()";break;

		case "VERIFYENABLED":code+=".verifyEnabled()";break;

		case "VERIFYDISABLED":code+=".verifyDisabled()";break;

		case "VERIFYTEXT":code+=".verifyText(String text/StringMatcher matcher)";break;

		case "VERIFYNOTTEXT":code+=".verifyNotText(String text/StringMatcher matcher)";break;

		case "VERIFYVALUE":code+=".verifyValue(T t)";break;

		case "VERIFYNOTVALUE":code+=".verifyNotValue(T t)";break;

		case "VERIFYSELECTED":code+=".verifySelected()";break;

		case "VERIFYNOTSELECTED":code+=".verifyNotSelected()";break;

		case "VERIFYATTRIBUTE":code+=".verifyAttribute(String attr, String text/StringMatcher matcher)";break;

		case "VERIFYNOTATTRIBUTE":code+=".verifyNotAttribute(String attr, String text/StringMatcher matcher)";break;

		case "VERIFYCSSCLASS":code+=".verifyCssClass(String className)";break;

		case "VERIFYNOTCSSCLASS":code+=".verifyNotCssClass(String className)";break;

		case "VERIFYCSSSTYLE":code+=".verifyCssStyle(String prop, String value)";break;

		case "VERIFYNOTCSSSTYLE":code+=".verifyNotCssStyle(String prop, String value)";break;

		// preconditions
		case "GIVENPRESENT":code+=".givenPresent()";break;

		case "GIVENNOTPRESENT":code+=".givenNotPresent()";break;

		case "ISPRESENT":code+="isPresent()";break;
		
		default:
			code+=".waitForVisible()";
		}
				
		return code;
	}
	
	
}
