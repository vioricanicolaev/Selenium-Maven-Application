package suitcrm_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SuitCRMLoginPage {

	public SuitCRMLoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	
	public WebElement user_name; // if the id is the same as the name of the variable we don't need the @FyndBy
	
	public WebElement username_password;
	
	@FindBy(id = "bigbutton")
	public WebElement loginButton;
	
	public void login(String username, String pasword) {
		user_name.sendKeys(username);
		username_password.sendKeys(pasword);
		loginButton .click();
	}
	
	
}
