package pom_design;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.WebOrdersLoginPage;

public class WebOrdersLogInTest {
	/*
	 * 1. open browser
	 */
	
	WebDriver driver;
	
	@BeforeClass
	public void setUpClass() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
	}
	
	
	@Test( enabled=false )
	public void possitivLogInTest() {
		
		driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
		driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test");
		driver.findElement(By.id("ctl00_MainContent_login_button")).click();
	}
	
	@Test (priority=2)
	public void possitivLogInUsingPOM() {
		
		// create a object from WebOrdersLoginPage class  
		WebOrdersLoginPage loginPage = new WebOrdersLoginPage(driver);
		loginPage.userName.sendKeys("Tester");
		loginPage.password.sendKeys("test");
		loginPage.loginButton.click();
	}
	
	@Test (priority=1)
	public void invalidUserName() {
		WebOrdersLoginPage loginPage = new WebOrdersLoginPage(driver);
		
		loginPage.userName.sendKeys("invalid");
		loginPage.password.sendKeys("test");
		loginPage.loginButton.click();
		String errMsg = loginPage.erorMessage.getText();
		assertEquals(errMsg, "Invalid Login or Password.");
		loginPage.userName.clear();
		
	}
	
	
	@AfterClass
	public void tearDown() {
		driver.close();
	}


}
