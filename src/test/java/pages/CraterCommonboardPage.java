package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeTest;

import utils.Driver;

public class CraterCommonboardPage {
	
	
	public CraterCommonboardPage() {
		PageFactory.initElements(Driver.getDriver(), this);
		
	}	
		
		@FindBy(xpath = "//a[contains(text(),' Items')]")
		public WebElement ItemsIcon;
		
		@FindBy(xpath = "//button[text()=' Add Item']")
		public WebElement addItemButton;
		
		@FindBy(xpath = "")
		public WebElement filterNameField;
		
		@BeforeTest
		public void setup() {
			Driver.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}
	
	

}
