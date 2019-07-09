package com.practise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Testfordelete {
	
	private static final String URL = "http://www.primefaces.org/showcase/ui/misc/captcha.xhtml";

	private static WebDriver driver;
	static String parentWindow;
	static String handleCheckBox;
	static WebDriverWait wait;
	static WebElement iframeCheck;
	static WebElement checkBox;
	
	
	
	

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "D://Browser Driver files//chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.get(URL);
		
		Thread.sleep(500);
		//Print the parent window
		
			parentWindow = driver.getWindowHandle();
			System.out.println("parentWindow -> " + parentWindow);
			

			wait = new WebDriverWait(driver, 20);
			iframeCheck = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("iframe")));

			System.out.println("name of iframe captcha is  -> " + iframeCheck.getAttribute("name"));

			driver.switchTo().frame(iframeCheck.getAttribute("name"));
			
			//check box
			
			//handleCheckBox = driver.getWindowHandle();
			//System.out.println("handleCheckBox -> " + handleCheckBox);

			wait = new WebDriverWait(driver, 20);
			checkBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("recaptcha-anchor")));

			Actions builder = new Actions(driver);

			builder.moveToElement(checkBox).perform();
			checkBox.click();
			
			wait = new WebDriverWait(driver, 20);
			driver.switchTo().window(parentWindow);
			
			for (WebElement frame : driver.findElements(By.tagName("iframe"))) {
				System.out.println("FRAME -> " + frame.getAttribute("name"));
			}

			
			
			
		}
	
		
		
		
		
		
		
		
		
	}

