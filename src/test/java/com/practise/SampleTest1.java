//Assignment 1: Test to find cheapest flight on Mercury Tours website

package com.practise;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class SampleTest1 {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D://Browser Driver files//chromedriver.exe");
		//Initialise browser
		WebDriver driver = new ChromeDriver();
		
				//Maximize the browser window		
				driver.manage().window().maximize();
				driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);				
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				
				//Enter the URL
				driver.get("http://newtours.demoaut.com/");
				driver.findElement(By.linkText("REGISTER")).click();
				
				driver.findElement(By.name("firstName")).sendKeys("ABC");
		        driver.findElement(By.name("lastName")).sendKeys("Test");
		        String Fname = driver.findElement(By.name("firstName")).getAttribute("value");
		        String Lname = driver.findElement(By.name("lastName")).getAttribute("value");
		         
		        String FullName= Fname+ " " +Lname ;
		        	        
		        
		        driver.findElement(By.name("address1")).sendKeys("123 street");
		        driver.findElement(By.name("city")).sendKeys("FL");
		        
		        driver.findElement(By.id("email")).sendKeys("testuser1");
		       
		        driver.findElement(By.name("password")).sendKeys("test123");
		        driver.findElement(By.name("confirmPassword")).sendKeys("test123");
		        
		        driver.findElement(By.name("register")).click();
		        
		        Thread.sleep(2000);
		        
		        
		        String acttext=driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[3]/td/p[1]/font/b")).getText();
		        System.out.println("Text on Register Page is" + " " +acttext);
		        
		        int len = acttext.length() -1;
		        
		        String act_fullname = acttext.substring(5,len);
		        
		        System.out.println("Full Name of the user is " +FullName);
		        //Verify if login is successful
		        
		        if((act_fullname.equals(FullName)))
		        {
		        	System.out.println("Login Successful");
		        }
		        else
		        {
		        	System.out.println("Login Failed");
		        }
		        
	// Click on Flights link
		        driver.findElement(By.linkText("Flights")).click();
	//select From City
		        WebElement FromCity = driver.findElement(By.name("fromPort"));
		        Select s= new Select (FromCity);
		        s.selectByIndex(2);
		        
   //read the selected from city value
		        String SelectedCity = FromCity.getAttribute("value");
		        System.out.println("Selected From City is " +SelectedCity);
		        
   //Month
		        Select m = new Select(driver.findElement(By.name("fromMonth")));
		        m.selectByVisibleText("October");
		        
	//Day
		        Select d = new Select(driver.findElement(By.name("fromDay")));
		        d.selectByValue("6");
		        
	//Select To City
		        Select t=new Select(driver.findElement(By.name("toPort")));
		        t.selectByVisibleText("Zurich");
		        System.out.println("Selected To City is " +driver.findElement(By.name("toPort")).getAttribute("value"));
	//Month
		        WebElement toMonth = driver.findElement(By.name("toMonth"));
		        Select tm = new Select(toMonth);
		        tm.selectByVisibleText("April");
		        
    //Day
		        WebElement toDay = driver.findElement(By.name("toDay"));
		        Select td = new Select(toDay);
		        td.selectByValue("16");
		        

	//Click on Continue button
		        driver.findElement(By.name("findFlights")).click();
		        
//Find The cheapest Flight
		        

		        String pre_xpath = "/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table[1]/tbody/tr[";
		        String post_xpath = "]/td[";
		        
		        int price1 =0;
		        int k = 4;
		        
		        for (int i =3; i<=10; i++) //rows start from 3
		        {
		        	for (int j=1; j<=4;j++) //columns start from 1
		        	{
		        		String text = driver.findElement(By.xpath(pre_xpath+i+post_xpath+j+"]")).getText();
		        		
		        		if (i%2==0) //for even numbered rows , there are no columns. Price is available in even number rows.
		        		{
		        			//Tried regex, it dint work.
		        			//String price = text.replaceAll("$()[A-z]+", "");
		        			
		        			String price = text.substring(8,11); //hard-coded to get the price.couldnt find better option
		        			int min_price = Integer.parseInt(price);
		        			
		        			if(i==4)
		        			{
		        				price1 = min_price;
		        			}
		        			
		        			if (price1 >min_price)
		        			{
		        				price1 = min_price ;
		        				k=i-1;
		        		
		        			}
		        	break; //break the inner loop for even numbered rows
		        	}
		        }
		    }	
		   	System.out.println("Row number with lowest price is:" +k);
		   	int rownum = k-1; //Rownbr to get flight details of lowest flight
		   	
		   	System.out.println("Cheapest airlines is " +driver.findElement(By.xpath(pre_xpath+rownum+post_xpath+"2"+"]")).getText());
		   	System.out.println("And the price is $" +price1);
  }
}