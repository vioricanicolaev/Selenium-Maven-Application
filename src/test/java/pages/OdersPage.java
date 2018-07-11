package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OdersPage {
	
	public OdersPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "ctl00_MainContent_fmwOrder_ddlProduct")
	public WebElement product;
	
	@FindBy(id = "ctl00_MainContent_fmwOrder_txtQuantity")
	public WebElement quantity;
	
	@FindBy(id = "ctl00_MainContent_fmwOrder_txtName")
	public WebElement customerName;
	
	@FindBy(id = "ctl00_MainContent_fmwOrder_TextBox2")
	public WebElement street;
	
	@FindBy(id = "ctl00_MainContent_fmwOrder_TextBox3")
	public WebElement city;
	
	@FindBy(id = "ctl00_MainContent_fmwOrder_TextBox4")
	public WebElement state;
	
	@FindBy(id = "ctl00_MainContent_fmwOrder_TextBox5")
	public WebElement zipCode;
	
	@FindBy(xpath = "//input[@type='radio']")
	public List<WebElement> card;
	
	@FindBy(id = "ctl00_MainContent_fmwOrder_TextBox6")
	public WebElement cardNr;
	
	
	
	
	
	
	
	
	
	
	
}
