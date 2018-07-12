package suitcrm_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SuitCRMTaskOverviewPage extends SuitCRMCreateTaskPage {
	
	public SuitCRMTaskOverviewPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		
	}

	
	
	
	
	
}
