package testngpack;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class testngclass {
	
	WebDriver driver = new FirefoxDriver();
	
	@BeforeMethod
	public void openbrowser() 
	{
		driver.manage().window().maximize();
		driver.get("http://root.test.customfurnish.com/curtains-online/");
		
	}
	
	@AfterMethod
	public void closebrowser()
	{
		System.out.println("Curtain Closed");
		driver.quit();
	}	
	
	
  @Test
  public void f() 
  {
	String title = driver.getTitle();
	System.out.println("Curtain Page Title" +" "+ title);
	
  }
}
