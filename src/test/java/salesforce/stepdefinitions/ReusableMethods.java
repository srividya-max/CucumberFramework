package salesforce.stepdefinitions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


import base.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.DataUtils;

public class ReusableMethods {
	
	public static DataUtils oDataUtils = new DataUtils();
	protected static WebDriver driver;
	//static ExtentReports reports;
	static ExtentTest logger;
	static  String reportFolder = "C:\\Users\\cvidy\\eclipse-workspace\\CUCUMBER_AUTOMATION\\src\\test\\java\\Reports\\";
	
	public static void InitializeDriver(){

		WebDriverManager.chromedriver().setup();

		driver =new ChromeDriver();
		driver.get("https://login.salesforce.com");
		driver.manage().window().maximize();
	}
	
	public static void enterText(WebElement element, String textToEnter, String elementName) {
		if (element.isDisplayed()) {
			element.sendKeys(textToEnter);
			BaseTest.test.log(Status.INFO, elementName + " is entered");
		} else
			BaseTest.test.log(Status.FAIL, elementName + " is not displayed");
	}

	public static void clickonElement(WebElement element, String elementName) {
		element.click();
		BaseTest.test.log(Status.INFO, elementName + " is clicked");
	}

	public void verifyText(String ActualText, String ExpectedText, String msg) throws IOException {
		if (ActualText.equals(ExpectedText)) {
			BaseTest.test.pass(msg + "is verified successfully");
		} else {
			BaseTest.test.fail(msg + " verification is failed");
			//			BaseTest.test.addScreenCaptureFromPath(takeScreenshot());
		}
	}

	public static boolean waitForElementVisible(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(BaseTest.driver, 50);
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static String takeScreenshot() throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot) BaseTest.driver;
		String addDate = new SimpleDateFormat("yyyymmddhhmm").format(new Date());
		String destinationPath = System.getProperty("user.dir")+"\\Reports\\Screenshots\\" + addDate + ".PNG";
		File srcfile = screenshot.getScreenshotAs(OutputType.FILE);
		File dstfile = new File(destinationPath);
		FileUtils.copyFile(srcfile, dstfile);
		return destinationPath;
	}
	
	 public static void compareStrings(String S1, String S2) {
		  Assert.assertEquals(S1, S2); 
		}

	public static String[][] readXlData(String path, String sheetName) throws IOException {
		FileInputStream fs=new FileInputStream(new File(path));
		HSSFWorkbook wb=new HSSFWorkbook(fs);
		HSSFSheet sheet=wb.getSheet(sheetName);
		int rowCount=sheet.getLastRowNum()+1;
		int colCount=sheet.getRow(0).getLastCellNum();
		String[][] data=new String[rowCount][colCount];
		for(int i=0;i<rowCount;i++){
			for(int j=0;j<colCount;j++){
				int cellType=sheet.getRow(i).getCell(j).getCellType();
				if(cellType== HSSFCell.CELL_TYPE_NUMERIC){
					int val=(int)sheet.getRow(i).getCell(j).getNumericCellValue();
					data[i][j]=String.valueOf(val);
				}
				else
					data[i][j]=sheet.getRow(i).getCell(j).getStringCellValue();
			}
		}
		return (data);
	}
	
	public static void writeXlData(String fileName, String sheetName, int row, int col, String value) throws IOException{
		File file=new File(fileName);
		FileInputStream fs=new FileInputStream(file);
		HSSFWorkbook wb=new HSSFWorkbook(fs);
		HSSFSheet sheet=wb.getSheet(sheetName);
		sheet.createRow(row).createCell(col).setCellValue(value);
		FileOutputStream fo=new FileOutputStream(file);
		wb.write(fo);
		fo.flush();
		fo.close();
	}


}

