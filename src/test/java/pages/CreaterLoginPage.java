package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Driver;

public class CreaterLoginPage {
	
	public CreaterLoginPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}
		
		@FindBy(name = "email")
		public WebElement Email;
		
		@FindBy(name = "passowrd")
		public WebElement passowrd;
		
		@FindBy(linkText = "")
		public WebElement loginButton;
		
		@FindBy(xpath = "")
		public WebElement copyRightText;
		
		@FindBy(xpath = "")
		public WebElement businessTagLine;
		
		@FindBy(xpath = "")
		public WebElement businesssubText;
		
	

}
