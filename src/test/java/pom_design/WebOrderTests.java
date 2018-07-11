package pom_design;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.AllOrdersPage;
import pages.ProductsPage;
import pages.WebOrdersLoginPage;

public class WebOrderTests {
	WebDriver driver;
	WebOrdersLoginPage loginPage;
	AllOrdersPage allOrdersPage;
	String userId = "Tester";
	String password = "test";
	ProductsPage productsPages;

	@BeforeClass
	public void setUpClass() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
	}

	@BeforeMethod
	public void setUp() {
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");

		loginPage = new WebOrdersLoginPage(driver);
	}

	@Test(description = "Verify if labels and tab links are displayed", priority = 1)
	public void labelsVerification() {

		assertEquals(driver.getTitle(), "Web Orders Login", "Login page is not displayed. Aplication is down.");

		/*
		 * loginPage.userName.sendKeys(userId); 
		 * loginPage.password.sendKeys(password);
		 * loginPage.loginButton.click();
		 */

		loginPage.logIn(userId, password);

		allOrdersPage = new AllOrdersPage(driver);

		assertTrue(allOrdersPage.webOrders.isDisplayed(), "WebOrder is not displayed.");

		assertTrue(allOrdersPage.listOfAllOrders.isDisplayed(), "List Of All Orders is not displayed.");

		assertEquals(allOrdersPage.welcomeText.getText().replace(" | Logout", ""), "Welcome, " + userId + "!");

		assertTrue(allOrdersPage.viewAllOrders.isDisplayed(), "View all orders is not displayed.");

		assertTrue(allOrdersPage.viewAllProducts.isDisplayed(), "View all products is not displayed.");

		assertTrue(allOrdersPage.orderTab.isDisplayed(), "Order tab is not displayed.");
	}
	
	
	@Test(description = "Verify default Products and prices", priority=2)
	public void availableProductsTest() {

		assertEquals(driver.getTitle(), "Web Orders Login", "Login page is not displayed. Aplication is down.");
		loginPage.logIn(userId, password);

		allOrdersPage = new AllOrdersPage(driver);

		allOrdersPage.viewAllProducts.click();

		productsPages = new ProductsPage(driver);

		List<String> expectedProducts = Arrays.asList("MyMoney", "FamilyAlbum", "ScreenSaver");
		List<String> actualProducts = new ArrayList<>();
		
		//productsPages.productNames.forEach(elem -> actualProducts.add(elem.getText())); --> lambda
		for(WebElement prod : productsPages.productNames) {
			actualProducts.add(prod.getText());
		}
		
		assertEquals(actualProducts, expectedProducts);

		for (WebElement row : productsPages.productRows) {
			//
			System.out.println(row.getText());
			String[] prodData = row.getText().split(" ");
			
			switch (prodData[0]) {
			case "MyMoney":
				assertEquals(prodData[1], "$100");
				assertEquals(prodData[2], "8%");
				break;
			case "FamilyAlbum":
				assertEquals(prodData[1], "$80");
				assertEquals(prodData[2], "15%");
				break;
			case "ScreenSaver":
				assertEquals(prodData[1], "$20");
				assertEquals(prodData[2], "10%");
				break;

			}
		}

	}
	
	// HOMEWORK
	
	@Test(description = "Create an order", priority=3)
	public void orderTabTest() {
		
		assertEquals(driver.getTitle(), "Web Orders Login", "Login page is not displayed. Aplication is down.");
		loginPage.logIn(userId, password);

		allOrdersPage = new AllOrdersPage(driver);
		allOrdersPage.orderTab.click();
		
		
	}

	@AfterMethod
	public void logOut() {

		allOrdersPage.logOut();
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	
	
	
	
	
	
	
	
	

}
