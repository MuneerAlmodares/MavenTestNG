package tests;

import org.testng.annotations.Test;

import pages.CreaterLoginPage;
import utils.Driver;
import utils.PropertiesReader;

public class CreaterTest {
	
	
	@Test
	public void validLogin() {
		Driver.getDriver().get(PropertiesReader.getData("createrUrl"));
		
		CreaterLoginPage run = new CreaterLoginPage();
		
		run.Email.sendKeys(PropertiesReader.getData("validEmail"));
		
		
		
	}

}
