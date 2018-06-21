package com.prestashop.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NegativePretaShopHomeWork {
	
	
	
	WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(" http://automationpractice.com");
		driver.findElement(By.cssSelector("a[href='http://automationpractice.com/index.php?controller=my-account']")).click();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}
	
	//test scenario: wrong credentials test
	@Test
	public void wrongCredTest() {
		
		driver.findElement(By.id("email")).sendKeys("atlasIsBest@gmail.com");
		//driver.findElement(By.id("email")).sendKeys("mrRobot@gmail.com");
		driver.findElement(By.id("passwd")).sendKeys("GoodPassword"+Keys.ENTER);
		
		String actual= driver.findElement(By.xpath("(//li)[15]")).getText();
	    String expected="Authentication failed.";
		
	    Assert.assertEquals(actual, expected);
	
	}
	
	
	
	//test scenario: invalid email test
	@Test
	public void invalidEmailTest() {
		
		driver.findElement(By.id("email")).sendKeys("mrRobot-gmail.com");
		driver.findElement(By.id("passwd")).sendKeys("GoodPassword"+Keys.ENTER);
		
		String actual= driver.findElement(By.xpath("(//li)[15]")).getText();
	    String expected="Invalid email address.";
		
	    Assert.assertEquals(actual, expected);
	}
	
	
	//test scenario: blank email test
		@Test
		public void blankEmailTest() {
			
			driver.findElement(By.id("email")).clear();
			driver.findElement(By.id("passwd")).sendKeys("GoodPassword"+Keys.ENTER);
			
			String actual= driver.findElement(By.xpath("(//li)[15]")).getText();
		    String expected="An email address required.";
			
		    Assert.assertEquals(actual, expected);
		}
		
		
		//test scenario: blank password test
				@Test
				public void blankPasswordTest() {
					
					driver.findElement(By.id("email")).sendKeys("mrRobot@gmail.com");
					driver.findElement(By.id("passwd")).clear();
					driver.findElement(By.id("passwd")).sendKeys(Keys.ENTER);;
					
					String actual= driver.findElement(By.xpath("(//li)[15]")).getText();
				    String expected="Password is required.";
					
				    Assert.assertEquals(actual, expected);
				}	
		

}
