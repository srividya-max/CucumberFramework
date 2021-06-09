package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

import base.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CommonUtilities {
	
	public static void InitializeDriver(){
		WebDriver driver;
		WebDriverManager.chromedriver().setup();

		driver =new ChromeDriver();
		driver.get("https://login.salesforce.com");
		driver.manage().window().maximize();
	}

	
	public void enterText(WebElement element, String textToEnter, String elementName) {
		if (element.isDisplayed()) {
			element.sendKeys(textToEnter);
			BaseTest.test.log(Status.INFO, elementName + " is entered");
		} else
			BaseTest.test.log(Status.FAIL, elementName + " is not displayed");
	}

	public void clickonElement(WebElement element, String elementName) {
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

	public boolean waitForElementVisible(WebElement element) {
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

	
}
