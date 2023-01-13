package tests;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.CraterItemsElementsPage;
import pages.CreaterLoginPage;
import utils.Driver;
import utils.PropertiesReader;

public class CraterItemsTest {
	
	CraterItemsElementsPage itemsPage;
	CreaterLoginPage  craterLoginElements;
	/*
	 * Create an item on UI.
       Then go to database, and query from the items table using select * to get the information
	   Then verify the information that you have provided on UI is correct. 
	   Then update your Item on the UI, come back to database and verify the update is in effect.
       Then delete the Item on the UI,  come back to database and verify the estimate no longer exist. 
	 */
	
	@Test
	public void CreatItem() {
		
		itemsPage = new CraterItemsElementsPage();
		
		
		//verify that item button is exist
		Assert.assertTrue(itemsPage.itembutton.isDisplayed());
		//click on the items tap
		itemsPage.itembutton.click();
		
		// verify that the user is on the addItem page 
		Assert.assertTrue(itemsPage.addItemButton.isDisplayed());
		itemsPage.addItemButton.click();
		//verify that the item modle is displayed 
		Assert.assertTrue(itemsPage.newItemHeaderText.isDisplayed());
		// provide item information 
		itemsPage.addItemNameField.sendKeys("Tennis Shoes");
		itemsPage.addItemPriceField.sendKeys("160");
		itemsPage.addItemUnitField.sendKeys("box");
		itemsPage.addItemDescription.sendKeys("suitable for hard floors");
		
		
		
	}
	
	@BeforeMethod
	public void setup() {
		Driver.getDriver().get(PropertiesReader.getData("craterUrl"));
		Driver.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		craterLoginElements = new CreaterLoginPage();
		craterLoginElements.Email.sendKeys(PropertiesReader.getData("validEmail"));;
		craterLoginElements.passowrd.sendKeys(PropertiesReader.getData("passowrd"));
		craterLoginElements.loginButton.click();
		
		
		
	}
	
	@AfterMethod
	public void cleanup() {
		Driver.quitDriver();
	}
	

}
