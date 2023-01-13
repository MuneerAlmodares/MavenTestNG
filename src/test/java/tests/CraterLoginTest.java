package tests;

import java.util.concurrent.TimeUnit;

import javax.security.auth.login.CredentialException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.CreaterLoginPage;
import utils.BrowserUtils;
import utils.Driver;
import utils.PropertiesReader;

public class CraterLoginTest {
	CreaterLoginPage run;
	BrowserUtils utils;
	
	
	@Test
	public void validLogin() throws InterruptedException {
		
		run = new CreaterLoginPage();
		
		run.Email.sendKeys(PropertiesReader.getData("validEmail"));
		run.passowrd.sendKeys(PropertiesReader.getData("passowrd"));
		run.loginButton.click();
		// verify that you are on the dashboard home page:
		Assert.assertTrue(run.amountdueText.isDisplayed());
		// verify by current URL that you are on dashboard
		String dashboardUrl = Driver.getDriver().getCurrentUrl(); 
		Assert.assertTrue(dashboardUrl.contains("dashboard"));
		
	}
	@Test (dataProvider = "credential")
	public void invalidLogin(String email, String password) throws InterruptedException {
		run = new CreaterLoginPage();
		Thread.sleep(1000);
		utils = new BrowserUtils();
		
		if(email.isBlank() || password.isBlank()) {
			run.Email.sendKeys(email);
			run.passowrd.sendKeys(password);
			run.loginButton.click();
			
			utils.waitUntilElementVisible(run.FieldrequiredError);
			Assert.assertTrue(run.FieldrequiredError.isDisplayed());
		}else {
			run.Email.sendKeys(email);
			run.passowrd.sendKeys(password);
			run.loginButton.click();
			
			utils.waitUntilElementVisible(run.invalidUserErrorMessage);
			Assert.assertTrue(run.invalidUserErrorMessage.isDisplayed());
		}
		
		
		
		
	}
	
	
	@BeforeMethod
	public void setup() {
		Driver.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Driver.getDriver().get(PropertiesReader.getData("craterUrl"));
	}
	
	@AfterMethod
	public void clean() {
		Driver.quitDriver();
	}
	
	@DataProvider
	public String[][] credential() {
		String[][] names = new String[4][2];
		names[0][0] = "helil@primetechschool.com";
		names[0][1] = "standEjks88";
		
		names[1][0] = "nothing@primetechschool.com";
		names[1][1] = "testing34523";
		
		names[2][0] = "";
		names[2][1] = "tEsting123";
		
		names[3][0] = "nothing@primetechschool.com";
		names[3][1] = "";
				return names;
	}

}
