package web_elements;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FindElementsInTheForm {

	WebDriver driver;
	String name;
	Faker faker = new Faker();

	
	@BeforeClass
	public void setUpClass() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
	}
	
	@BeforeMethod
		public void setUpMethod() {
		driver.get(
				"https://forms.zohopublic.com/murodil/form/SeleniumWebElements/formperma/eCecYgX4WMcmjxvXVq6UdhA2ABXIoqPAxnAF8H8CCJg");
		name = faker.name().firstName();

		}
	

	@Test
	public void myLinks() throws InterruptedException {
		

		List<WebElement> inputBoxes = driver.findElements(By.xpath("//input[@type='text']"));
		assertEquals(inputBoxes.size(), 2);
		System.out.println("inputBoxes: " + inputBoxes.size());
		
		// Loop through each inputBox and enter random names
		for (WebElement eachInput : inputBoxes) {
			eachInput.sendKeys(name);
		}

		List<WebElement> dropDown = driver.findElements(By.tagName("select"));
		assertEquals(dropDown.size(), 3);
		System.out.println("dropDown: " + dropDown.size());
		
		// Loop through each dropDown and randomly select by index
		for (WebElement eachDropDown : dropDown) {
			Select selectropDown = new Select(eachDropDown);
			selectropDown.selectByIndex(faker.number().numberBetween(1, 4));
		}

		List<WebElement> checkBox = driver.findElements(By.xpath("//input[@type='checkbox']"));
		assertEquals(checkBox.size(), 9);
		System.out.println("checkBox: " + checkBox.size());
		
		// Loop through each checkBoxes and select each one
		for (WebElement eachCheckBox : checkBox) {
			eachCheckBox.click();
		}

		List<WebElement> radioBox = driver.findElements(By.xpath("//input[@type='radio']"));
		assertEquals(radioBox.size(), 9);
		System.out.println("radioBox: " + radioBox.size());
		
		// Loop through each radioButton one by one by waiting one second
		for (WebElement eachRadioBox : radioBox) {
			eachRadioBox.click();
			Thread.sleep(1234);
		}

		List<WebElement> button = driver.findElements(By.tagName("button"));
		assertEquals(button.size(), 1);
		System.out.println("button: " + button.size());
		
		// Click all buttons
		for (WebElement eachButton : button) {
			eachButton.click();
		}

	}
	
	
	
}