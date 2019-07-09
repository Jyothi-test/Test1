//Assignment 2

package com.practise;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Captchatest {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D://Browser Driver files//chromedriver.exe");
		//Initialise browser
		WebDriver driver = new ChromeDriver();
		
				//Maximize the browser window		
				driver.manage().window().maximize();
				driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);				
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				
				//Enter the URL
				driver.get("https://phptravels.org/clientarea.php");
				Thread.sleep(1000);
				
				driver.findElement(By.xpath("//*[@id=\"header\"]/div/ul/li[3]/a")).click();
				Thread.sleep(500);
				System.out.println(driver.getTitle()); //Register - PHPTRAVELS
				
				
				driver.findElement(By.id("inputFirstName")).sendKeys("TestABC");
				driver.findElement(By.id("inputLastName")).sendKeys("tester");
				
				driver.findElement(By.id("inputEmail")).sendKeys("XYZ@gmail.com");
				
				driver.findElement(By.className("flag-container")).click();
				
				driver.findElement(By.xpath("//li[@data-dial-code='1']")).click();
				
				
				driver.findElement(By.id("inputPhone")).sendKeys("2125679876");
				driver.findElement(By.id("inputCompanyName")).sendKeys("XYZ Company");
				
				driver.findElement(By.id("inputAddress1")).sendKeys("123 Street");
				
				driver.findElement(By.id("inputCity")).sendKeys("Tampa");
				driver.findElement(By.id("stateinput")).sendKeys("FL");
				
				driver.findElement(By.id("inputPostcode")).sendKeys("33610");
				
				WebElement ctrydropdown = driver.findElement(By.id("inputCountry"));
				Select s = new Select(ctrydropdown);				
				s.selectByValue("IN");
				
				driver.findElement(By.id("customfield2")).sendKeys("2125679876");
				
				WebElement 	dropdown = driver.findElement(By.id("customfield1"));
				Select a = new Select(dropdown);				
				a.selectByValue("Google");
							
				driver.findElement(By.id("inputNewPassword1")).sendKeys("Test1234!");
				driver.findElement(By.id("inputNewPassword2")).sendKeys("Test1234!");
				
				driver.findElement(By.className("bootstrap-switch-label")).click();
				
				String ParentID = driver.getWindowHandle();
				
				System.out.println("Parent window ID is: " +ParentID);
				
				WebDriverWait wait = new WebDriverWait(driver, 20);
				WebElement iframe1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("iframe")));
				
				System.out.println("name of iframe captcha is -> " + iframe1.getAttribute("name"));
				driver.switchTo().frame(iframe1);
				
				//String ChkboxID = driver.getWindowHandle();
				//System.out.println("handleCheckBox -> " + ChkboxID);

				wait = new WebDriverWait(driver, 20);
				WebElement checkBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(("//span[@id='recaptcha-anchor']"))));
				Actions builder = new Actions(driver);

				builder.moveToElement(checkBox).perform();
				checkBox.click();
//System.out.println("checkobox clicked");

				
				
	}

}
