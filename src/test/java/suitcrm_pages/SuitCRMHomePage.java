package suitcrm_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SuitCRMHomePage {
	
	private WebDriver driver;
	
	public SuitCRMHomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	@FindBy(linkText = "CREATE")
	public WebElement create;

	public void createNewItem(String itemType) {
		
		Actions action = new Actions(driver);
		action.moveToElement(create).perform();
		driver.findElement(By.linkText(itemType)).click();
		
	}
	
	
}
