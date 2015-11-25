package testcases;



import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.sun.jna.platform.FileUtils;



public class SampleTEst {
	  WebDriver driver;
	
	@Test
	public void testwebsite() throws InterruptedException {
		   driver=new FirefoxDriver();
		    driver.manage().window().maximize();
			driver.get("http://kustommade.tk:9980/ ");
			Thread.sleep(9000);
			driver.findElement(By.xpath(".//*[@id='emailIdInModal']")).sendKeys("sampathkrsna@gmail.com");
			driver.findElement(By.xpath(".//*[@id='SubscriptionModal']/div/form/div[2]/div/a")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath(".//*[@id='SubscribeModal']/a/img")).click();
		    System.out.println("Succesfully Subscribed");
		}
	
	
@Test(dependsOnMethods={"testwebsite"})
public void testSignup() throws InterruptedException  {
	
	
	driver.findElement(By.xpath(".//*[@id='loginUser']/span")).click();
	Thread.sleep(3000);
	driver.findElement(By.xpath(".//*[@id='login-email']")).sendKeys("sampath@bizkinetic.com");
	driver.findElement(By.xpath(".//*[@id='login-password']")).sendKeys("CloudIsGust");
	driver.findElement(By.xpath(".//*[@id='login']/span/img")).click();
	System.out.println("Succesfully Signed-in");
}
	
@Test(dependsOnMethods={"testwebsite","testSignup"}) 
public void testCart() throws InterruptedException {
	Thread.sleep(3000);	
	String kk= driver.findElement(By.xpath(".//*[@id='cartCount']")).getText();	
	 
	 Integer x = Integer.valueOf(kk);
	 System.out.println("is the initial cart value " +x);
	}
@Test(dependsOnMethods={"testwebsite","testSignup","testCart"})
public void testWardrobe() throws InterruptedException {
	Thread.sleep(2000);	
	driver.findElement(By.xpath(".//*[@id='nav-list']/li[2]/a/span")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath(".//*[@id='scroll-products']/ul/li/div/div/a/section/img")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath(".//*[@id='design']/div[2]/div[2]/div[2]/div[2]/div/div[2]/p/a")).click();	
	Thread.sleep(12000);
	
	String kl= driver.findElement(By.xpath(".//*[@id='price']/span[2]")).getText();	
	  Integer y = Integer.valueOf(kl);
	 System.out.println("Cost " +y);
	 driver.findElement(By.xpath(".//*[@id='kmBody']/div[11]/div[3]/div[2]/ul/li[2]/a")).click(); 
	 Thread.sleep(8000);
	 driver.findElement(By.xpath(".//*[@id='designName']")).sendKeys("Wardrobe-1");
	 driver.findElement(By.xpath(".//*[@id='saveInModal']/img")).click();
	 Thread.sleep(8000);
	 Actions actions = new Actions(driver);
	 WebElement mainMenu = driver.findElement(By.xpath(".//*[@id='kmBody']/div[2]/div[1]/div/div/div[2]/div/ul[1]/li[3]/div/a/span"));
	 actions.moveToElement(mainMenu);

	 WebElement subMenu = driver.findElement(By.xpath(".//*[@id='user-options-design-pages']/li[3]/a"));
	 actions.moveToElement(subMenu);
	 actions.click().build().perform();
	 
}



}



