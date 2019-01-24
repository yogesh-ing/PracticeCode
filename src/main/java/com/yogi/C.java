package com.yogi;

public class C {
	
	//----------------Test class constants
	public static final String CLOSING_CURLY_BRACKET = "}";
	public static final String OPENING_CURLY_BRACKET = "{";
	public static final String CLOSING_ROUND_BRACKET = ")";
	public static final String OPENING_ROUND_BRACKET = "(";

	public static final String NEW_LINE = "\n";
	public static final String SINGLE_TAB = "\t";
	public static final String DOUBLE_TAB = "\t\t";
	public static final String TEST_FILENAME = "public class [0] extends WebDriverTestCase ";
	public static final String TEST_ANNOTAION = "@Test";
	public static final String TEST_DESC = "description=\"[0]\"";
	public static final String TEST_METHOD_NAME = "public void [0]()";
	// public static final String TEST_METHOD_NAME = "public void verify[0]()
	// {\n\t//Write your code...\n\t}";
	public static final String QAF_data_provider_method = "@QAFDataProvider";
	public static final String dateFile = "dataFile=\"[0]\"";
	public static final String coma = ",";
	public static final String key = "key=\"[0]\"";
	public static final String Page_click_method = "public void clickOn[0]() {		[1].click();	}";
	public static final String Test_method_call = "[0].[1]()";
	public static final String Test_click_call = "[0].clickOn[1]()";
	public static final String Test_sendKey_call = "[0].get[1].sendKeys(\"\")";
	public static final String Test_verify_visible_call = "[0].get[1]().verifyVisible(\"[2]\");";
	public static final String Create_page_object = "[0] [1]=new [2]();";
	
	
	
	//----------------All class snippet
	//Bean,FormBean,Test,Page,Component

	public static final String PAGE_FILENAME = "public class [0] extends WebDriverBaseTestPage<WebDriverTestPage>";
	public static final String FINDBY = "@FindBy(locator = \"[0]\")\n\tpublic QAFWebElement [1];";

	public static final String FORM_BEAN_FILENAME ="public class [0] extends BaseFormDataBean";
	public static final String UI_ELEMENT = "@UiElement(fieldLoc = \"[0]\", fieldType = Type.textbox)\n\tpublic String [1];";

	public static final String BEAN_FILENAME ="public class RegisterBean extends BaseDataBean";
	public static final String BEAN_VARIABLE = "public String [0];";

	public static final String TEST_FILENAME_LAY = "public class [0] extends WebDriverTestCase ";
	public static final String TEST_ANNOTAION_LAY = "@Test(description=\"Verify page\")";
	public static final String TEST_METHOD_NAME_LAY = "public void verifyPage() {\n\t//Write your code...\n\t}";

	public static final String RANDOMIZER = "@Randomizer(length=0)";

	public static final String GET_METHOD = "public QAFWebElement get[0]() {\n\t\t return [1]; \n\t}";
	public static final String SET_METHOD = "public void set[0](String [1]) {\n\t\t  this.[2]=[3]; \n\t}";

}
