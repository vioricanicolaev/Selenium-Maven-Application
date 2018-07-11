package web_tables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ReadWebTables {

	WebDriver driver;
	String url = "file:///Users/viorica/automation-eclipse-workspace/Selenium-Maven-Application/src/test/java/web_tables/webtable.html";

	@BeforeClass
	public void setUpClass() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
	}

	@Test
	public void readScores() {
		driver.get(url);

		// Read whole date and print

		WebElement table = driver.findElement(By.tagName("table"));
		System.out.println(table.getText());

		// find out how many row in the table
		List<WebElement> nrOfDataRows = driver.findElements(By.xpath("//table[@id='world_cup']/tbody/tr"));
		System.out.println("Number of data rows: " + nrOfDataRows.size());

		List<WebElement> headers = driver.findElements(By.xpath("//table[@id='world_cup']/thead/tr/th"));
		for (WebElement eachHeaders : headers) {
			System.out.println(eachHeaders.getText());
		}

		List<String> expectedHeaders = Arrays.asList("Team1", "Score", "Team2");
		List<String> actualHeaders = new ArrayList<>();

		for (WebElement h : headers) {
			actualHeaders.add(h.getText());
		}

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(actualHeaders, expectedHeaders);

		// find Egypt
		String egiptXpath = driver.findElement(By.xpath("//table[@id='world_cup']/tbody/tr[3]/td[3]")).getText();
		softAssert.assertEquals(egiptXpath, "Egypt");

		// loop it aqnd print all data
		// get number of rows, columns and then nested loops

		int rowsCount = driver.findElements(By.xpath("//table[@id='world_cup']/tbody/tr")).size();
		int colsCount = driver.findElements(By.xpath("//table[@id='world_cup']/thead/tr/th")).size();
		System.out.println("===================");

		for (int rowNum = 1; rowNum <= rowsCount; rowNum++) {
			for (int col = 1; col <= colsCount; col++) {
				String xpath = "//table[@id='world_cup']/tbody/tr[" + rowNum + "]/td[" + col + "]";
				String tdData = driver.findElement(By.xpath(xpath)).getText();
				System.out.println(tdData + " \t");
			}
			System.out.println();
		}

		softAssert.assertAll();

	}

	@Test
	public void applicantsData() {
		driver.get(
				"https://forms.zohopublic.com/murodil/report/Applicants/reportperma/DibkrcDh27GWoPQ9krhiTdlSN4_34rKc8ngubKgIMy8");

		printTableData("reportTab");

	}

	public void printTableData(String id) {
		int rowsCount = driver.findElements(By.xpath("//table[@id='" + id + "']/tbody/tr")).size();
		int colsCount = driver.findElements(By.xpath("//table[@id='" + id + "']/thead/tr/th")).size();
		System.out.println("===================");

		for (int rowNum = 1; rowNum <= rowsCount; rowNum++) {
			for (int col = 1; col <= colsCount; col++) {
				String xpath = "//table[@id='"+id+"']/tbody/tr[" + rowNum + "]/td[" + col + "]";
				String tdData = driver.findElement(By.xpath(xpath)).getText();
				System.out.println(tdData + " \t");
			}
			System.out.println();
		}

	}

	
	
	
	
}
