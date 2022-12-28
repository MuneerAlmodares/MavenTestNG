package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Driver;

public class CraterDashbordPage {
	
	public CraterDashbordPage() {
		PageFactory.initElements(Driver.getDriver(), this);
		
	}
	
	@FindBy (xpath = "//span[contains( text() , 'Amount Due') ]")
	public WebElement amountdueText;

}
