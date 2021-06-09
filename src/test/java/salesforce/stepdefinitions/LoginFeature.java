package salesforce.stepdefinitions;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.junit.Assert;
import base.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginFeature extends ReusableMethods {

	@Given("User launches the application")
	public void user_launches_the_application() {
		ReusableMethods.InitializeDriver();
		System.out.println("This is the browser launch");

	}

	@When("User should be able to enter the required credentials")
	public void user_should_be_able_to_enter_the_required_credentials() {
		String s1= driver.getTitle();
		String s2= "Login | Salesforce";
		Assert.assertEquals(s1,s2);
		System.out.println("This is the login page");
	}

	@When("User enters valid username and blank password and clicks login")
	public void user_enters_valid_username_and_blank_password_and_clicks_login() throws IOException {

		WebElement username =driver.findElement(By.id("username"));
		username.sendKeys("karthik.test@abc.com");

		WebElement login = driver.findElement(By.xpath("//*[@id='Login']"));
		login.click();
	}
	
	@Then("User should see the error message for the blank password entered and the browser should close")
	public void user_should_see_the_error_message_for_the_blank_password_entered_and_the_browser_should_close() throws IOException {
		
		WebElement errormsg = driver.findElement(By.xpath("//*[@id='error']"));

		System.out.println("user sees the error message1:"+errormsg);
		driver.close();
		}


	@When("User enters wrong username and valid password and clicks login")
	public void user_enters_wrong_username_and_valid_password_and_clicks_login() {

		WebElement username =driver.findElement(By.id("username"));
		username.sendKeys("karthik@abc.com");

		WebElement pwd =driver.findElement(By.id("password"));
		pwd.sendKeys("Srinidhi@2017");

		WebElement login = driver.findElement(By.xpath("//*[@id='Login']"));
		login.click();

		System.out.println("User enters wrong username and valid password");
	}
	
	@Then("User should see the error message for the wrong credentials entered and the browser should close")
	public void user_should_see_the_error_message_for_the_wrong_credentials_entered_and_the_browser_should_close() throws IOException { 

		WebElement msg = driver.findElement(By.id("error"));
		//Assert.assertEquals(msg.getText(), oDataUtils.ReadWebElementProperties("wrongpwd.errormsg"));
		driver.close();
		
		System.out.println("user sees the error message2:"+msg);
	}


	@When("User enters valid username and valid password and clicks login")
	public void user_enters_valid_username_and_valid_password_and_clicks_login() {

		WebElement username =driver.findElement(By.id("username"));
		username.sendKeys("karthik.test@abc.com");

		WebElement pwd =driver.findElement(By.id("password"));
		pwd.sendKeys("Srinidhi@2017");

		WebElement login = driver.findElement(By.xpath("//*[@id='Login']"));
		login.click();
		System.out.println("User enters valid username and valid password");
	}

	@Then("User should be able to see the Homepage of the application and the browser should close")
	public void user_should_be_able_to_see_the_homepage_of_the_application_and_the_browser_should_close() {
		driver.close();
		System.out.println("the homepage is opened with usermenu");
	}

	/*****************************************Remember me check box********************************/

	@When("User enters valid username and password")
	public void user_enters_valid_username_and_password() {

		WebElement username =driver.findElement(By.id("username"));
		username.sendKeys("karthik.test@abc.com");

		WebElement pwd =driver.findElement(By.id("password"));
		pwd.sendKeys("Srinidhi@2017");
	}

	@When("User clicks on Remember me checkbox and clicks login")
	public void user_clicks_on_remember_me_checkbox_and_clicks_login() {

		WebElement Remchk = driver.findElement(By.xpath("//*[@id='rememberUn']"));
		Remchk.click();

		WebElement login = driver.findElement(By.xpath("//*[@id='Login']"));
		login.click();	
	}

	@Then("User should be able to see the Homepage of the application")
	public void user_should_be_able_to_see_the_homepage_of_the_application() {
		System.out.println("the homepage is opened with usermenu");
	}

	@Then("User should see the usermenu dropdown")
	public void user_should_see_the_usermenu_dropdown() {
		WebElement usermenu =driver.findElement(By.xpath("//*[@id='userNavLabel']"));
		usermenu.click();	
	}

	@Then("User should select the logout option and click on it")
	public void user_should_select_the_logout_option_and_click_on_it() {
		WebElement logout =driver.findElement(By.xpath("//*[@id='userNav-menuItems']/a[5]"));
		logout.click();
	}

	@Then("User should close the browser")
	public void user_should_close_the_browser() {
		driver.close();
	}


	/*********************************Forgot password link********************************/

	@When("User clicks on Forgot password link")
	public void user_clicks_on_forgot_password_link() {
		driver.findElement(By.id("forgot_password_link")).click();
	}

	@Then("Salesforce Forgot password page should be displayed")
	public void salesforce_forgot_password_page_should_be_displayed() {
		System.out.println("the forgot password page should be opened");
	}

	@When("User enters valid username in username field and clicks on continue button")
	public void user_enters_valid_username_in_username_field_and_clicks_on_continue_button() {
		driver.findElement(By.xpath("//*[@id='un']")).sendKeys("karthik.test@abc.com");
		driver.findElement(By.id("continue")).click();
	}

	@Then("Check your email page should be diaplayed")
	public void check_your_email_page_should_be_diaplayed() {
		WebElement msg = driver.findElement(By.xpath("//*[@id='resend-email']"));

		String s2= "resend the email";

		Assert.assertEquals(msg.getText(), s2);
		System.out.println("Resend the email"+ msg.getText());
	}

	@Then("Close the browser")
	public void close_the_browser() {
		driver.close();
	}

}
