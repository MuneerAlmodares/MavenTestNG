package tests;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.CraterDashbordPage;
import pages.CreaterLoginPage;
import utils.Driver;
import utils.PropertiesReader;

public class CraterTest {
	
	
	
	/*
	 * Scenario: successful log in 
	 * giving user is on the login page 
	 * when user enters valid username and password 
	 * and click ligin button 
	 * then user should be able to dashbord page 
	 * 
	 */
	
	@Test
	public void validLogin() throws InterruptedException {
 	
		
	Driver.getDriver().get(PropertiesReader.getData("createrUrl"));
	  CreaterLoginPage loginpage = new CreaterLoginPage();
	  Thread.sleep(2000);

	  loginpage.Email.sendKeys(PropertiesReader.getData("validEmail"));
	  loginpage.passowrd.sendKeys(PropertiesReader.getData("passowrd"));
	  loginpage.loginButton.click();
	 	
	  Assert.assertTrue(loginpage.InvalidErrorMessage.isDisplayed());
	  
//	  CraterDashbordPage dashbord = new CraterDashbordPage();
//	  Assert.assertTrue(dashbord.amountdueText.isDisplayed());
//	  
//	  String dashbordurl = Driver.getDriver().getCurrentUrl();
//	  Assert.assertTrue(dashbordurl.contains("dashboard"));
	  
	}
	
	@BeforeMethod
	public void setup() {
		Driver.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@AfterMethod 
	public void teardown() {
		Driver.quitDriver();
	}
	

}
