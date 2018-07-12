package suitecrm;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import suitcrm_pages.SuitCRMCreateTaskPage;
import suitcrm_pages.SuitCRMHomePage;
import suitcrm_pages.SuitCRMLoginPage;
import suitcrm_pages.SuitCRMTaskOverviewPage;
import static utilities.ConfigReader.*;

public class SuiteCRMTest extends TestBase{
	
	
	SuitCRMLoginPage loginPage;
	SuitCRMHomePage homePage;
	SuitCRMCreateTaskPage createTaskPage;
	SuitCRMTaskOverviewPage taskOverviewPage;
	
	@BeforeMethod
	public void setUp(){
		
		driver.get( getProperty("suitecrm.url") );
		loginPage = new SuitCRMLoginPage(driver);
		homePage = new SuitCRMHomePage(driver);
		createTaskPage = new SuitCRMCreateTaskPage(driver);
		taskOverviewPage = new SuitCRMTaskOverviewPage(driver);
	}
	
	@Test
	public void createTaskInSuitCRM() {
		
		
		loginPage.login(getProperty("suitcrm.username"), getProperty("suitcrm.password"));
		
		homePage.createNewItem("Create Task");
		
		createTaskPage.subject.sendKeys(getProperty("suitcrm.tasksubject"));
		new Select(createTaskPage.status).selectByVisibleText(getProperty("suitcrm.taskstatus"));
		createTaskPage.description.sendKeys(getProperty("suitcrm.description") + "-" + LocalDateTime.now());
		createTaskPage.SAVE.click(); 
		
	
		
	}
	

}
