package Furniture;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


public class dropdownexample {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://root.test.customfurnish.com/office-furniture-online/");
		driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[1]/div/a")).click();
		Thread.sleep(2000);
		
		 WebElement btn = driver.findElement(By.xpath("/html/body/div[1]/div[5]/form/div[2]/div/input[2]"));
		 if(btn.isEnabled())
		{
			driver.findElement(By.xpath("/html/body/div[1]/div[5]/form/div[2]/div/input[2]")).click();
			System.out.println("Quote is Enabled");
		}
		else
		{
		System.out.println("Both Layout & Quote is Enabled");
		}
		 
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("/html/body/div[1]/div[5]/form/div[2]/div/input[1]")).click();
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("/html/body/div[1]/div[5]/form/div[3]/div[1]/div[2]/input")).sendKeys("500");
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("/html/body/div[1]/div[5]/form/div[3]/div[2]/div[2]/input")).sendKeys("10");
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("/html/body/div[1]/div[5]/form/div[3]/div[2]/div[3]/input")).sendKeys("5");
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("/html/body/div[1]/div[5]/form/div[3]/div[3]/div[2]/input")).sendKeys("1");
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("/html/body/div[1]/div[5]/form/div[3]/div[3]/div[3]/input")).sendKeys("1");
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("/html/body/div[1]/div[5]/form/div[3]/div[3]/div[4]/input")).sendKeys("4");
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("/html/body/div[1]/div[5]/form/div[3]/div[3]/div[5]/input")).sendKeys("0");
		 Thread.sleep(2000);
		 
		 //WebElement List1; 
		 //List1 = driver.findElement(By.xpath("/html/body/div[1]/div[5]/form/div[3]/div[4]/div[1]/div[2]"));
		 //List<WebElement> lstOptions= List1.findElements(By.tagName("option"));
		 //List1.sendKeys(Keys.CONTROL);
		 //lstOptions.get(0).click();
		 //Thread.sleep(2000);
				 
		 driver.findElement(By.id("selectAllOthers")).isSelected();
		 if(!driver.findElement(By.id("selectAllOthers")).isSelected())
		 {
			 driver.findElement(By.id("selectAllOthers")).click(); 
		 }
		 Thread.sleep(2000);
		 
		 driver.findElement(By.id("networking")).click();
		 Thread.sleep(2000);
		 driver.findElement(By.id("electrical")).click();
		 Thread.sleep(2000);
		 driver.findElement(By.id("windowFurnishing")).click();
		 Thread.sleep(3000);
		 if(driver.findElement(By.id("windowFurnishing")).isSelected())
		 {
			 driver.findElement(By.id("windowFurnishing")).click();
		 }
		 
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/form/div[4]/div[2]/div[1]/input")).sendKeys("Test");
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/form/div[4]/div[2]/div[2]/input")).sendKeys("testcustom1234@gmail.com");
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/form/div[4]/div[2]/div[3]/input")).sendKeys("123456789");
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/form/div[4]/div[2]/div[4]/div[1]/input")).sendKeys("Test");
		Thread.sleep(2000);
		
		//driver.findElement(By.xpath("/html/body/div[1]/div[5]/form/div[4]/div[3]/div[1]/a")).click();
		
		
		
		
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/form/div[4]/div[2]/div[6]/input")).click();
		Thread.sleep(3000);
		

	}

}
