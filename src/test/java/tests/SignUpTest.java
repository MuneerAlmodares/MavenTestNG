package tests;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.CommonPage;
import utils.Driver;
import utils.PropertiesReader;

public class SignUpTest {
	
	@BeforeMethod
	public void setup() {
		Driver.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
	}
	
	@Test (description = "verify signp page compnents")
	public void signUpPageTest() {
		CommonPage common = new CommonPage();
		Driver.getDriver().get(PropertiesReader.getData("appurl"));
		//verify welcomeDrop is exist 
		Assert.assertTrue(common.WelcomeDropDown.isDisplayed());
		//click on welcome dropdown
		common.WelcomeDropDown.click();
		//verify that sign up displayed
		Assert.assertTrue(common.SignUpButton.isDisplayed());
		//click on siugnup 
		common.SignUpButton.click();
		
	
	}
	
	@AfterMethod
	public void quitDriver() {
		Driver.quitDriver();
	}

}
