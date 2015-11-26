package curtaintestngpack;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;


public class curtaintestng {
	
	WebDriver driver;
	
  @BeforeTest
  public void BeforeTest()
  {
	  driver = new FirefoxDriver();
	  System.out.println("before test");
  }
  
  @AfterTest
  public void AfterTest()
  {
	  driver.quit();
	  System.out.println("after test");
  }
  @BeforeMethod
  public void BeforeMethod() throws InterruptedException
  {
	  driver.get("http://root.test.customfurnish.com/curtains-online/");
	  driver.manage().window().maximize();
	  Thread.sleep(2000);
	  System.out.println("before method");
  }
  
  @AfterMethod
  public void AfterMethod()
  {
	  System.out.println("after method");
  }
  
  
  @Test
  public void f() throws InterruptedException 
  {
	
	  
	  String title = driver.getTitle();
	  System.out.println("Curtain Page title"+ " "+ title);
	  Thread.sleep(3000);
	  System.out.println("test");
  }
  
  
  
}
