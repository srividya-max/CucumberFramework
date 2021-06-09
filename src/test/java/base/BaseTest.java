package base;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.cucumber.java.After;
import io.cucumber.java.Before;

import utilities.AppConstants;
import utilities.CommonUtilities;
import utilities.DataUtils;

public class BaseTest {
	
	public static WebDriver driver=null;

	public static ExtentReports extent = null;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentTest test = null; //logger

	public static String sReportTime;
	public static Properties sProperties;
	
	public static CommonUtilities oCommonUtilities = new CommonUtilities();
	public static DataUtils oDataUtils = new DataUtils();
	
	public static String sToken;

	
	@Before
	public void setup() {
		Initializereports();
	}
	
	@After
	public void tearDown() {
		extent.flush();
	}

	public void Initializereports() {
		sReportTime = new SimpleDateFormat("yyyymmddhhmm").format(new Date());
		extent = new ExtentReports();
		htmlReporter = new ExtentHtmlReporter(AppConstants.EXTENT_HTML_REPORT_PATH +"\\"+sReportTime +".html");
		extent.attachReporter(htmlReporter);
	}
	
	public void hashMap() {
		Map<String,String> map = new HashMap();
		map.put("token", sToken);
	}
	

	}
