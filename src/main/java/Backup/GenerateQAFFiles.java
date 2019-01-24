package Backup;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import Backup.GenerateQAFFiles.enumFileType;

public class GenerateQAFFiles {
	public static enum enumFileType {
		Bean, FormBean, Page, Test;
	}

	private static final String CLOSING_BRACKET = "}";
	private static final String OPENING_BRACKET = "{";
	private static final String NEW_LINE = "\n";
	private static final String SINGLE_TAB = "\t";

	private static final String PAGE_FILENAME = "public class [0] extends WebDriverBaseTestPage<WebDriverTestPage>";
	private static final String FINDBY = "@FindBy(locator = \"[0]\")\n\tprivate QAFWebElement [1];";

	private static final String FORM_BEAN_FILENAME ="private class [0] extends BaseFormDataBean";
	private static final String UI_ELEMENT = "@UiElement(fieldLoc = \"[0]\", fieldType = Type.textbox)\n\tprivate String [1];";

	private static final String BEAN_FILENAME ="private class RegisterBean extends BaseDataBean";
	private static final String BEAN_VARIABLE = "private String [0];";

	private static final String TEST_FILENAME = "public class [0] extends WebDriverTestCase ";
	private static final String TEST_ANNOTAION = "@Test(description=\"Verify page\")";
	private static final String TEST_METHOD_NAME = "public void verifyPage() {\n\t//Write your code...\n\t}";

	private static final String RANDOMIZER = "@Randomizer(length=0)";

	private static final String GET_METHOD = "private QAFWebElement get[0]() {\n\t\t return [1]; \n\t}";
	private static final String SET_METHOD = "public void set[0](String [1]) {\n\t\t  this.[2]=[3]; \n\t}";

	public static void main(String[] args) {
		String fileName = "C:\\Users\\yogesh.ingale\\workspace\\qaf-onboarding-appium-yogeshingale\\resources\\locators\\createaccount.loc";
		System.out.println(generateSnippetOfFile(fileName, enumFileType.Test));
	}

	/**
	 * Generate Snippet of given file
	 * {@code enum enumFileType: 
		Bean, FormBean, Page, Test;}
	 * 
	 * @param fileName  String
	 * @param test enumFileType
	 * @return 
	 */
	public static String generateSnippetOfFile(String fileName, enumFileType test) {
		Path path = Paths.get(fileName);
		String Snippet="Nothing.....";
		try {
			List<String> linesList = Files.readAllLines(path);
			switch (test) {
			case Page:
				Snippet=yogiUtil.getFormattedString(PAGE_FILENAME,yogiUtil.CreateFileName(path))+OPENING_BRACKET+NEW_LINE;
				Snippet=generateCode(linesList,Snippet,test);	
				Snippet+=CLOSING_BRACKET;break;

			case Bean:
				Snippet=yogiUtil.getFormattedString(BEAN_FILENAME,yogiUtil.CreateFileName(path))+OPENING_BRACKET+NEW_LINE;
				Snippet=generateCode(linesList,Snippet,test);
				Snippet+=CLOSING_BRACKET;break;

			case FormBean:
				Snippet=yogiUtil.getFormattedString(FORM_BEAN_FILENAME,yogiUtil.CreateFileName(path))+OPENING_BRACKET+NEW_LINE;
				Snippet=generateCode(linesList,Snippet,test);
				Snippet+=CLOSING_BRACKET;break;
			case Test:
				Snippet=yogiUtil.getFormattedString(TEST_FILENAME,yogiUtil.CreateFileName(path))+OPENING_BRACKET+NEW_LINE;
				Snippet=generateCode(linesList,Snippet,test);
				Snippet+=SINGLE_TAB+TEST_ANNOTAION+NEW_LINE+SINGLE_TAB+TEST_METHOD_NAME+NEW_LINE;
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
					Snippet+=SINGLE_TAB+RANDOMIZER+NEW_LINE;
					Snippet+=SINGLE_TAB+createLocator(enumfileType, line.split("=")[0], line.split("=")[0].split("[.]", 3)[1])+NEW_LINE;
					break;
				case Bean:
					Snippet+=SINGLE_TAB+RANDOMIZER+NEW_LINE;
					Snippet+=SINGLE_TAB+createLocator(enumfileType,line.split("=")[0].split("[.]", 3)[1])+NEW_LINE;

					break;
				case Page:
					Snippet+=SINGLE_TAB+createLocator(enumfileType, line.split("=")[0], line.split("=")[0].split("[.]", 3)[1])+NEW_LINE;
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
		case Bean:line[0]=yogiUtil.getFormattedString(BEAN_VARIABLE, line);
		break;
		case FormBean:line[0]=yogiUtil.getFormattedString(UI_ELEMENT, line);
		break;
		case Page:line[0]=yogiUtil.getFormattedString(FINDBY, line);
		break;
		}
		return line[0];
	}
}
