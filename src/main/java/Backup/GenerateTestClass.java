package Backup;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import Backup.GenerateQAFFiles.enumFileType;

public class GenerateTestClass {
	public static void main(String[] args) {
		createTestClass("C:\\Users\\yogesh.ingale\\workspace\\zPractise\\src\\main\\java\\locators\\Demo.Test");
	}

	private static final String CLOSING_CURLY_BRACKET = "}";
	private static final String OPENING_CURLY_BRACKET = "{";
	private static final String CLOSING_ROUND_BRACKET = ")";
	private static final String OPENING_ROUND_BRACKET = "(";
	private static final String NEW_LINE = "\n";
	private static final String SINGLE_TAB = "\t";
	private static final String DOUBLE_TAB = "\t\t";
	private static final String TEST_FILENAME = "public class [0] extends WebDriverTestCase ";
	private static final String TEST_ANNOTAION = "@Test";
	private static final String TEST_DESC = "description=\"[0]\"";
	private static final String TEST_METHOD_NAME = "public void [0]()";
	// private static final String TEST_METHOD_NAME = "public void verify[0]()
	// {\n\t//Write your code...\n\t}";
	private static final String QAF_data_provider_method = "@QAFDataProvider";
	private static final String dateFile = "dataFile=[0]";
	private static final String coma = ",";
	private static final String key = "key=[0]";
	private static final String Page_click_method = "public void clickOn[0]() {		[1].click();	}";
	private static final String Test_method_call = "[0].[1]()";
	private static final String Test_click_call = "[0].clickOn[1]()";
	private static final String Test_sendKey_call = "[0].get[1].sendKeys(\"\")";
	private static final String Test_verify_visible_call = "[0].get[1]().verifyVisible(\"[2]\");";
	private static final String Create_page_object = "[0] [1]=new [2]();";
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
						datapro = SINGLE_TAB + QAF_data_provider_method + replaceString(line, "DATAPRO");
					else
						datapro = "error" + lineNumber;
					break;
				case "TEST":
					if (line.trim().split("=").length > 1){
						
						if(testMethodCnt>=0){
							tempTest = datapro + NEW_LINE + SINGLE_TAB + TEST_ANNOTAION + OPENING_ROUND_BRACKET + testdesc
									+ CLOSING_ROUND_BRACKET + NEW_LINE + test + OPENING_CURLY_BRACKET + NEW_LINE + innercode
									+ SINGLE_TAB + CLOSING_CURLY_BRACKET;

							snippet.add(testMethodCnt, tempTest);
							test = ""; testdesc = ""; datapro = ""; innercode = "";
							tempTest="";

						}
						testMethodCnt++;

						test = SINGLE_TAB + yogiUtil.getFormattedString(TEST_METHOD_NAME, line.trim().split("=")[1]);
					}
					else
						test = "error" + lineNumber;
					break;
				case "DESC":
					if (line.trim().split("=").length > 1)
						testdesc = yogiUtil.getFormattedString(TEST_DESC, line.trim().split("=")[1]);
					else
						testdesc = "error" + lineNumber;
					break;
				case "OPEN":
					if (line.trim().split("=").length > 1) {
						rememberPageObject = line.trim().split("=")[1].toLowerCase();
						innercode += DOUBLE_TAB
								+ yogiUtil.getFormattedString(Create_page_object, line.trim().split("=")[1],
										line.trim().split("=")[1].toLowerCase(), line.trim().split("=")[1])
								+ NEW_LINE;
					} else
						innercode += "error" + lineNumber;
					break;
				case "CLICK":
					if (line.trim().split("=").length > 1)
						innercode += DOUBLE_TAB + yogiUtil.getFormattedString(Test_click_call, rememberPageObject,
								line.trim().split("=")[1]) + NEW_LINE;
					else
						innercode += "error" + lineNumber;
					break;
				case "METHOD":
					if (line.trim().split("=").length > 1)
						innercode += DOUBLE_TAB + yogiUtil.getFormattedString(Test_method_call, rememberPageObject,
								line.trim().split("=")[1]) + NEW_LINE;
					else
						innercode += "error" + lineNumber;
					break;
				case "SENDKEY":
					if (line.trim().split("=").length > 1)
						innercode += DOUBLE_TAB + yogiUtil.getFormattedString(Test_sendKey_call, rememberPageObject,
								line.trim().split("=")[1]) + NEW_LINE;
					else
						innercode += "error" + lineNumber;
					break;
				case "VERIFY":
					if (line.trim().split("=").length > 1)
						innercode += DOUBLE_TAB + yogiUtil.getFormattedString(Test_verify_visible_call,
								rememberPageObject, line.trim().split("=")[1], line.trim().split("=")[1]) + NEW_LINE;
					else
						innercode += "error" + lineNumber;
					break;
				}
			}
			
			if(!tempTest.equals("Nothing....")){
				tempTest = datapro + NEW_LINE + SINGLE_TAB + TEST_ANNOTAION + OPENING_ROUND_BRACKET + testdesc
						+ CLOSING_ROUND_BRACKET + NEW_LINE + test + OPENING_CURLY_BRACKET + NEW_LINE + innercode
						+ SINGLE_TAB + CLOSING_CURLY_BRACKET;

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
				snippet = OPENING_ROUND_BRACKET
						+ yogiUtil.getFormattedString(dateFile, line.split("=")[1].split(",")[0]) + coma;
				snippet += yogiUtil.getFormattedString(key, line.split("=")[2]) + CLOSING_ROUND_BRACKET;
			} else
				snippet = OPENING_ROUND_BRACKET + yogiUtil.getFormattedString(key, line.split("=")[1])
				+ CLOSING_ROUND_BRACKET;
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
		tempStr=tempStr.split("[{]")[0]+OPENING_CURLY_BRACKET+NEW_LINE;
		for(String testMethod:snippet){
			tempStr+=testMethod;
		}
		tempStr+=CLOSING_CURLY_BRACKET;
		System.err.println(tempStr);
	}
}
