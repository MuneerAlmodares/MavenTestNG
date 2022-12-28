package tests;

import org.testng.annotations.Test;

import pages.CraterCommonboardPage;
import utils.Driver;
import utils.PropertiesReader;

public class CraterItemsTest {
	
	CraterCommonboardPage  CraterElements;
	
	
	@Test
	public void creatItem() {
		/*
		 * Create an Estimate on UI.
          Then go to database, and query from the items table using select * to get the information
          Then verify the information that you have provided on UI is correct.
          Then update your Estimate on the UI, come back to database and verify the update is in effect. 
          Then delete the Estimate on the UI, come back to database and verify the estimate no longer
          exist.
		 */
		 CraterElements = new CraterCommonboardPage();
		Driver.getDriver().get(PropertiesReader.getData("createrUrl"));
		
		CraterElements.ItemsIcon.click();
		
		
	}
	
	
	
	
}
