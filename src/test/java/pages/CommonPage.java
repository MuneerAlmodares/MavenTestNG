package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Driver;

public class CommonPage {
	
	public CommonPage() {
		PageFactory.initElements(Driver.getDriver(), this);
		
	}
	@FindBy(linkText = "Welcome!")
	public WebElement WelcomeDropDown;
	
	@FindBy(xpath = "//button[text()='Sign Up']")
	public WebElement SignUpButton;
	
	@FindBy(xpath = "//button[text()='Login']")
	public WebElement LogInButton;

}
