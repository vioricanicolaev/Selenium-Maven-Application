package web_elements;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WarmUpFindElements {

	WebDriver driver;

	@BeforeClass
	public void setUpClass() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
	}

	@Test
	public void printItemsDescription() {
		driver.get(
				"https://www.amazon.com/s/ref=nb_sb_noss_1?url=search-alias%3Daps&field-keywords=wooden+spoon\n" + "");

		List<WebElement> description = driver.findElements(By.xpath("//h2[@data-attribute]"));
		// for (WebElement eachDescription : description) {
		// System.out.println(eachDescription.getText() + " ");
		// }

		List<WebElement> prices = driver.findElements(By.xpath("//span[@class='sx-price sx-price-large']"));
		// for (WebElement eachPrice : prices) {
		// System.out.println(eachPrice.getText() + " ");
		// }
		//
		// for (int i = 0; i < description.size(); i++) {
		// System.out.println(description.get(i).getText() + "\n" +
		// prices.get(i).getText());
		//
		// }
		// }

		List<WebElement> wholeItems = driver.findElements(By.xpath("//div[@class='s-item-container']"));
		System.out.println(wholeItems.size());
		int n=0;

		for (int i = 0; i < wholeItems.size(); i++) {
			if (wholeItems.get(i).getText().isEmpty())
				continue;

			String desXpath = "(//div[@class='s-item-container'])[" + (i + 1) + "]//h2";
			String priceXpath = "(//div[@class='s-item-container'])[" + (i + 1)
					+ "]//span[@class='sx-price sx-price-large']";
			++n;

			System.out.println(driver.findElement(By.xpath(desXpath)).getText());
			System.out.println(driver.findElement(By.xpath(priceXpath)).getText());
			System.out.println("------------");

		}
              System.out.println(n);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
