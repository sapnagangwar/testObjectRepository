package SeleniumObjRepo;
import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;


public class SFDCForgotPWD {
	public WebDriver driver;
	private String baseUrl;


	@BeforeTest
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "/Users/sapna/Desktop/gecko driver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void testSFDCForgotPassword() throws Exception{
		//reading test data
		String dt_path = "/Users/sapna/Desktop/Abhi/SFDC/SFDCLogin.xls";
		File xlFile = new File(dt_path);
		FileInputStream xlDoc = new FileInputStream(xlFile);
		HSSFWorkbook wb = new HSSFWorkbook(xlDoc);
		HSSFSheet sheet = wb.getSheet("Sheet1");

		baseUrl = sheet.getRow(0).getCell(0).getStringCellValue();
		driver.get(baseUrl + "/");

		String un = sheet.getRow(0).getCell(1).getStringCellValue();

		//read ff objectName, ff object type and ff object property from Object Rep.xls
		String objRepo_path = "/Users/sapna/Desktop/Abhi/SFDC/Object Rep.xls";
		File xlObjFile = new File(objRepo_path);
		FileInputStream xlObjDoc = new FileInputStream(xlObjFile);
		HSSFWorkbook wbObj = new HSSFWorkbook(xlObjDoc);
		HSSFSheet sheetObj = wbObj.getSheet("Sheet1");

		String UNobjType =sheetObj.getRow(1).getCell(2).getStringCellValue();
		String UNobjProperty = sheetObj.getRow(1).getCell(3).getStringCellValue();

		WebElement userName= driver.findElement(getLocator(UNobjType, UNobjProperty));
		enterText(userName, un, "username");


		String forgotPasswordLinkobjType =sheetObj.getRow(5).getCell(2).getStringCellValue();
		String forgotPasswordLinkobjProperty = sheetObj.getRow(5).getCell(3).getStringCellValue();

		WebElement forgotPasswordLink = driver.findElement(getLocator(forgotPasswordLinkobjType, forgotPasswordLinkobjProperty));
		click(forgotPasswordLink,"forgotPassword" );


	}

	/* Name of the Method: enterText
	 * Brief Description: Enter the text value to the text box
	 * Arguments: TextBox object,textVal--->value to be entered;objName--->name of the object
	 * created by :Automation team
	 * Creation date: Aug 23,2017
	 * last modified:Aug 23 ,2017
	 */
	public static void enterText(WebElement obj,String textVal,String objName){
		if(obj.isDisplayed()){
			obj.sendKeys(textVal);
			System.out.println("Pass: " + textVal + " is entered in " + objName + " field");
		}
		else{
			System.out.println("Fail : password is not displayed, please check your application");
		}
	}

	/* Name of the Method: clearText
	 * Brief Description: Clear the text value to the text box
	 * Arguments: TextBox object,textVal--->value to be entered;objName--->name of the object
	 * created by :Automation team
	 * Creation date: Aug 23,2017
	 * last modified:Aug 23 ,2017
	 */
	public static void clearText(WebElement obj, String objName){
		if(obj.isDisplayed()){
			obj.clear();
			System.out.println("Pass: " + " cleared " + objName + " field");
		}
		else{
			System.out.println("Fail : password is not displayed, please check your application");
		}
	}

	/* Name of the Method: login
	 * Brief Description: Click login text box
	 * Arguments: TextBox object ;objName--->name of the object
	 * created by :Automation team
	 * Creation date: Aug 23,2017
	 * last modified:Aug 23 ,2017
	 */
	public static void click(WebElement obj, String objName){
		if(obj.isDisplayed()){
			obj.click();
			System.out.println("Pass: " +  " user successfully logged in ;" + objName + " is clicked");
		}
		else{
			System.out.println("Fail : user login failed" + objName + " is not dispalyed");
		}
	}

	//Return a instance of By class based on type of locator
	public static By getLocator(String locatorType, String locatorValue) throws Exception{

		if(locatorType.toLowerCase().equals("id"))
			return By.id(locatorValue);
		else if(locatorType.toLowerCase().equals("name"))
			return By.name(locatorValue);
		else if((locatorType.toLowerCase().equals("classname")) || (locatorType.toLowerCase().equals("class")))
			return By.className(locatorValue);
		else if((locatorType.toLowerCase().equals("tagname")) || (locatorType.toLowerCase().equals("tag")))
			return By.className(locatorValue);
		else if((locatorType.toLowerCase().equals("linktext")) || (locatorType.toLowerCase().equals("link")))
			return By.linkText(locatorValue);
		else if(locatorType.toLowerCase().equals("partiallinktext"))
			return By.partialLinkText(locatorValue);
		else if((locatorType.toLowerCase().equals("cssselector")) || (locatorType.toLowerCase().equals("css")))
			return By.cssSelector(locatorValue);
		else if(locatorType.toLowerCase().equals("xpath"))
			return By.xpath(locatorValue);
		else
			throw new Exception("Locator type '" + locatorType + "' not defined!!");
	}

	@AfterTest
	public void tearDown() throws Exception {
		// driver.close();
	}


}