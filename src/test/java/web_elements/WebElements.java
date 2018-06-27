package web_elements;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebElements {
	

	WebDriver driver;
	
	@BeforeClass
	public void setUpClass() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
	}

	@Test
	public void myLinks() {
		driver.get("https://github.com");
		List<WebElement> links = driver.findElements(By.tagName("a"));
		int numberOfLinks = links.size();
		System.out.println("Number of links:  " + numberOfLinks);

		for (WebElement eachLink : links) {
			if (!eachLink.getText().isEmpty())
				System.out.println(eachLink.getText());
		}

		List<String> linkNames = new ArrayList<>();
		for (WebElement link : links) {
			if(!link.getText().isEmpty()) {
				linkNames.add(link.getText());
			}
		}
		System.out.println(linkNames.toString());
	}
	
	
	
	
	
	
	
	
	

}
