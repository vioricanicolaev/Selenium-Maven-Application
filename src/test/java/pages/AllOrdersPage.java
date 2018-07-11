package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AllOrdersPage {
	

	public AllOrdersPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//h1[.='Web Orders']")
	public WebElement webOrders;
	
	@FindBy(xpath = "//div[@class='login_info']")
	public WebElement welcomeText;
	
	@FindBy(xpath = "//h2[contains(.,'List of All Orders')]")
	public WebElement listOfAllOrders;
	
	@FindBy(linkText = "View all orders")
	public WebElement viewAllOrders;
	
	@FindBy(linkText = "View all products")
	public WebElement viewAllProducts;
	
	@FindBy(linkText = "Order")
	public WebElement orderTab;
	
	@FindBy(id = "ctl00_logout")
	public WebElement logOutLink;
	
	
	public void logOut() {
		logOutLink.click();
	}
	
	
	
}
