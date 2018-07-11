package com.jobappliation;

import java.sql.DriverManager;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import static org.testng.Assert.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PersonalInfoTest {

	WebDriver driver;
	String firstName;
	String lastName;
	int gender;
	String dateOfBirth;
	String email;
	String phoneNumber;
	String city;
	String state;
	String country;
	int annualSalary;
	List<String> technologies;
	int yearsOfExperience;
	String education;
	String github;
    List<WebElement> certifications;
    String additionalSkills;
    Faker faker = new Faker();
    
    
	

	@BeforeClass
	public void setUpClass() {

		System.out.println("Seting up webDriver in @BeforeClass");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
	}

	@BeforeMethod
	public void setUpMethod() {
		System.out.println("Navigating to homepage in @BeforeMethod...");
		driver.get("https://forms.zohopublic.com/murodil/form/JobApplicationForm/formperma/kOqgtfkv1dMJ4Df6k4_mekBNfNLIconAHvfdIk3CJSQ");
		
		firstName = faker.name().firstName();
		lastName = faker.name().lastName();
		gender = faker.number().numberBetween(1, 3);
		dateOfBirth = faker.date().birthday().toString();
		email = "demian_viorica@mail.ru";
		phoneNumber = faker.phoneNumber().cellPhone().replace(".", "");
		city = faker.address().city();
		state = faker.address().state();
		country = faker.address().country();
		annualSalary = faker.number().numberBetween(60000, 150000);
		technologies = new ArrayList<>();
		technologies.add("Java-" + faker.number().numberBetween(1, 4));
		technologies.add("HTML-"+ faker.number().numberBetween(1, 4));
		technologies.add("Selenium WebDriver-"+ faker.number().numberBetween(1, 4));
		technologies.add("TestNG-"+ faker.number().numberBetween(1, 4));
		technologies.add("Git-"+ faker.number().numberBetween(1, 4));
		technologies.add("Maven-"+ faker.number().numberBetween(1, 4));
		technologies.add("JUnit-"+ faker.number().numberBetween(1, 4));
		technologies.add("Cucumber-"+ faker.number().numberBetween(1, 4));
		technologies.add("API Automation-"+ faker.number().numberBetween(1, 4));
		technologies.add("JDBC-"+ faker.number().numberBetween(1, 4));
		technologies.add("SQL-"+ faker.number().numberBetween(1, 4));
		yearsOfExperience = faker.number().numberBetween(0, 11);
		education = faker.number().numberBetween(1, 4)+"";
		github = "https://github.com/vioricanicolaev";
//		certifications = new ArrayList<>();
//		certifications.add("Java OCA");
//		certifications.add("AWS");
		additionalSkills = faker.job().keySkills();

		
	}
	
	@Test
	public void submitFullApplication() {
		driver.findElement(By.xpath("//input[@name='Name_First']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@name='Name_Last']")).sendKeys(lastName);
		setGender(gender);
		setDateOfBirth(dateOfBirth);
		driver.findElement(By.xpath("//input[@name='Email']")).clear();
		driver.findElement(By.xpath("//input[@name='Email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@name='countrycode']")).sendKeys(phoneNumber);
		driver.findElement(By.xpath("//input[@name='Address_City']")).sendKeys(city);
		driver.findElement(By.xpath("//input[@name='Address_Region']")).sendKeys(state);
		Select countryElem = new Select(driver.findElement(By.xpath("//select[@id='Address_Country']")));
		countryElem.selectByIndex(faker.number().numberBetween(1, countryElem.getOptions().size()));
		driver.findElement(By.xpath("//input[@name='Number']")).sendKeys(String.valueOf(annualSalary)+Keys.TAB);
		verifySalaryCalculation(annualSalary);
		driver.findElement(By.xpath("//button[@elname='next']")).click();
		setSkillset(technologies);
		
		if(yearsOfExperience > 0)
		//driver.findElement(By.xpath("//a[@rating_value='" + yearsOfExperience + "']")).click();
		  driver.findElement(By.xpath("//a[@rating_value='"+ yearsOfExperience + "']")).click();;

		Select educationList = new Select(driver.findElement(By.xpath("//select[@name='Dropdown']")));
		educationList.selectByIndex(faker.number().numberBetween(1, educationList.getOptions().size()));
		
		certifications = driver.findElements(By.xpath("//input[@type='checkbox']"));
		for (WebElement eachCertificat : certifications) {
			eachCertificat.click();
		}
		driver.findElement(By.name("MultiLine")).clear();
		driver.findElement(By.name("MultiLine")).sendKeys(additionalSkills);
		driver.findElement(By.xpath("//em[.='Apply']")).click();
		
	}
	
	public void setSkillset(List<String> tech) {
		
		for (String skill : tech) {
			String technology = skill.substring(0, skill.length() - 2);
			int rate = Integer.parseInt(skill.substring(skill.length() - 1));

			String level = "";
			switch (rate) {
			case 1:
				level = "Expert";
				break;
			case 2:
				level = "Proficient";
				break;
			case 3:
				level = "Beginner";
				break;
			default:
				fail(rate + " is not a valid level");

			}
			String xpath = "//input[@rowvalue='"+ technology + "' and @columnvalue='"+ level + "']";
			driver.findElement(By.xpath(xpath)).click();
		}
		
		
	}
	
	public void verifySalaryCalculation(int annual) {

		String monthly = driver.findElement(By.xpath("//input[@name='Formula']")).getAttribute("value");
		String weekly = driver.findElement(By.xpath("//input[@name='Formula1']")).getAttribute("value");
		String hourly = driver.findElement(By.xpath("//input[@name='Formula2']")).getAttribute("value");

		System.out.println(monthly);
		System.out.println(weekly);
		System.out.println(hourly);

		DecimalFormat formatter = new DecimalFormat("#.##");

		assertEquals(Double.parseDouble(monthly), Double.parseDouble(formatter.format((double) annual / 12.0)));
		assertEquals(Double.parseDouble(weekly), Double.parseDouble(formatter.format((double) annual / 52.0)));
		assertEquals(Double.parseDouble(hourly), Double.parseDouble(formatter.format((double) annual / 52 / 40.0)));
	}

	public void setGender(int n) {
		if (n == 1) {
			driver.findElement(By.xpath("//input[@value='Male']")).click();
		} else
			driver.findElement(By.xpath("//input[@value='Female']")).click();
	}

	public void setDateOfBirth(String str) {
		String[] pieces = str.split(" ");
		String birthDay = pieces[2] + "-" + pieces[1] + "-" + pieces[5];
		driver.findElement(By.xpath("//input[@id='Date-date']")).sendKeys(birthDay);
	}


	@Test
	public void fullNameEmptyTest() {

		assertEquals(driver.getTitle(), "SDET Job Application");
		driver.findElement(By.xpath("//input[@name='Name_First']")).clear();
		driver.findElement(By.xpath("//input[@name='Name_Last']"));
		driver.findElement(By.xpath("//em[.=' Next ']")).click();
		String nameEror = driver.findElement(By.xpath("//p[@id='error-Name']")).getText();
		assertEquals(nameEror, "Enter a value for this field.");

	}

}
