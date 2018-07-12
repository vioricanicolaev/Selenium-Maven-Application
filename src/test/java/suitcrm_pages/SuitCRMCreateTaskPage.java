package suitcrm_pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SuitCRMCreateTaskPage {
	
private WebDriver driver;
	
	public SuitCRMCreateTaskPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	@FindBy(id = "name")
	public WebElement subject;


	public WebElement status;
	
	public WebElement description;
	
	public WebElement SAVE;

}
