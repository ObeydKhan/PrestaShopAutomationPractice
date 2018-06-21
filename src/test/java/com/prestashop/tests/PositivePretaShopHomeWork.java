package com.prestashop.tests;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PositivePretaShopHomeWork {

	WebDriver driver;
	Faker faker=new Faker();
	Select select;
	Random random;
	String firstName;
	String lastName;
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		faker=new Faker();
		random=new Random();
		driver.manage().window().maximize();
		
		firstName=faker.name().firstName();
		String email=faker.internet().emailAddress();
		lastName=faker.name().lastName();
		String password=faker.internet().password();
		
		driver.get(" http://automationpractice.com");
		driver.findElement(By.cssSelector("a[href='http://automationpractice.com/index.php?controller=my-account']")).click();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// set universal wait time in case web page is slow

		driver.findElement(By.xpath("(//input)[5]")).sendKeys(email+Keys.ENTER);
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// set universal wait time in case web page is slow
		
		
		driver.findElement(By.xpath("//input[@id='customer_firstname']")).sendKeys(firstName);
		driver.findElement(By.xpath("(//input)[8]")).sendKeys(lastName);
		driver.findElement(By.xpath("(//input)[9]")).clear();
		driver.findElement(By.xpath("(//input)[9]")).sendKeys(email);
		driver.findElement(By.xpath("(//input)[10]")).sendKeys(password);
		driver.findElement(By.xpath("(//input)[16]")).sendKeys(faker.address().streetAddress());
		driver.findElement(By.xpath("(//input)[18]")).sendKeys(faker.address().city());
		
		select=new Select(driver.findElement(By.xpath("(//select)[4]"))); //selects state
		select.selectByIndex(random.nextInt(50)+1);
		driver.findElement(By.xpath("(//input)[19]")).sendKeys(faker.number().digits(5));
		driver.findElement(By.xpath("(//input)[21]")).sendKeys(faker.phoneNumber().cellPhone());
		driver.findElement(By.xpath("(//span)[42]")).click();
		driver.findElement(By.xpath("//a[@href='http://automationpractice.com/index.php?mylogout=']")).click();
		
		
		driver.findElement(By.id("email")).sendKeys(email);
		//driver.findElement(By.id("email")).sendKeys("mrRobot@gmail.com");
		driver.findElement(By.id("passwd")).sendKeys(password+Keys.ENTER);
		
		
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}
	
	
	//test scenario: login test
	@Test
	public void loginTest() {
		String actual=driver.findElement(By.tagName("span")).getText();
		String expected=firstName+" "+lastName;
		Assert.assertEquals(actual, expected);
		driver.findElement(By.xpath("//a[@href='http://automationpractice.com/index.php?mylogout=']")).click();
		
	}
}
