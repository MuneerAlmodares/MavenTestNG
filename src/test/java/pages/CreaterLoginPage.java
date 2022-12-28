package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Driver;

public class CreaterLoginPage {
	
	public CreaterLoginPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}
		
		@FindBy(xpath = "//div[@name='email']/input")
		public WebElement Email;
		
		@FindBy(xpath = "//div[@name='password']/input")
		public WebElement passowrd;
		
		@FindBy (linkText = "Forgot Password?")
		public WebElement forgotPasswordLink;
		
		@FindBy(xpath = "//p[contains(text(), 'These credentials do not match our records.')]")
		public WebElement InvalidErrorMessage;

		@FindBy (xpath = "//button[text()='Login']")
		public WebElement loginButton;

		@FindBy (xpath = "//p[contains(text(), 'Copyright @')]")
		public WebElement copyRightText;

		@FindBy (xpath = "//h1[contains(text(), 'Simple Invoicing for')]")
		public WebElement businessTagline;

		@FindBy (xpath = "//p[contains(text(), 'Crater helps you track expenses')]")
		public WebElement businessSubtext;

		@FindBy (xpath = "//p[contains(text(), 'These credentials do not match our records.')]")
		public WebElement invalidUserErrorMessage;
		
		
		
	

}
