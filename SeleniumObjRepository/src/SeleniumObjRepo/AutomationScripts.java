package SeleniumObjRepo;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AutomationScripts extends ReUsableMethods{
	public static WebDriver driver;


	public static void SFDCLogin() throws Exception {

		//reading test data
		String dt_path = "/Users/sapna/Desktop/Abhi/SFDC/SFDCLogin.xls";
		File xlFile = new File(dt_path);
		FileInputStream xlDoc = new FileInputStream(xlFile);
		HSSFWorkbook wb = new HSSFWorkbook(xlDoc);
		HSSFSheet sheet = wb.getSheet("Sheet1");

		String un = sheet.getRow(0).getCell(1).getStringCellValue();
		String pwd = sheet.getRow(0).getCell(2).getStringCellValue();

		/* Launch a Browser*/
		System.setProperty("webdriver.gecko.driver", "/Users/sapna/Desktop/gecko driver");
		driver = new FirefoxDriver(); 

		/*Launch URL*/
		driver.get("https://login.salesforce.com/");

		//read ff objectName, ff object type and ff object property from Object Rep.xls
		String objRepo_path = "/Users/sapna/Desktop/Abhi/SFDC/Object Rep.xls";
		File xlObjFile = new File(objRepo_path);
		FileInputStream xlObjDoc = new FileInputStream(xlObjFile);
		HSSFWorkbook wbObj = new HSSFWorkbook(xlObjDoc);
		HSSFSheet sheetObj = wbObj.getSheet("Sheet1");

		String x =sheetObj.getRow(1).getCell(2).getStringCellValue();
		String y = sheetObj.getRow(1).getCell(3).getStringCellValue();


		//WebElement userName= driver.findElement(By.id(sheetObj.getRow(1).getCell(3).getStringCellValue()));
		WebElement userName= driver.findElement(getLocator(x, y));
		enterText(userName, un, "username");

		String x1 =sheetObj.getRow(2).getCell(2).getStringCellValue();
		String y1 = sheetObj.getRow(2).getCell(3).getStringCellValue();
		WebElement password=driver.findElement(getLocator(x1, y1));
		enterText(password, pwd, "password");

		String x2 =sheetObj.getRow(3).getCell(2).getStringCellValue();
		String y2 = sheetObj.getRow(3).getCell(3).getStringCellValue();
		WebElement login = driver.findElement(getLocator(x2, y2));		
		clickButton(login, "Login");
		

	}

	public static void validateErrorMessage(){

	}







}
