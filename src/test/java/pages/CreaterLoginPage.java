package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Driver;
import utils.PropertiesReader;

public class CreaterLoginPage {
	
	public CreaterLoginPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}
		
		@FindBy(xpath = "//input[@name='email']")
		public WebElement Email;
		
		@FindBy(xpath = "//input[@name='password']")
		public WebElement passowrd;
		
		@FindBy(xpath = "//button[text()='Login']")
		public WebElement loginButton;
		
		@FindBy(xpath = "//span[text()='Amount Due']")
		public WebElement amountdueText;
		
		@FindBy(xpath = "//p[contains(text(), 'These credentials do not match our records.')]")
		public WebElement invalidUserErrorMessage;
		
		@FindBy(xpath = "//span[text()='Field is required']")
		public WebElement FieldrequiredError;
	
		
		public void login () {
			Email.sendKeys(PropertiesReader.getData("validEmail"));;
			passowrd.sendKeys(PropertiesReader.getData("passowrd"));
			loginButton.click();
		}
		
		
		
	

}
