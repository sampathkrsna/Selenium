package cspackage;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

public class SofaClass {
	
	private WebDriver driver = null;
	
	@BeforeSuite
	public void OpenBrowser()
	{
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("www.customfurnish.com");
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}
	
	@Test
	public void CheckOutSofa()
	{
		System.out.println("I am in parent window" + driver.getWindowHandle());
		
		//get the list of all opened windows.
		Set<String> windowHandle = driver.getWindowHandles();
		for(String handle : windowHandle)
		{
			System.out.println(handle);
		}
		
		driver.findElement(By.xpath("//*[@id='SubscriptionModal']/a")).click(); //Close the modal window
		driver.findElement(By.xpath("//*[@id='km-header']/div/nav/section[1]/ul/li[4]/a")).click(); //click on sofas link
		driver.findElement(By.xpath("//*[@id='variants-container']/li[2]/div/section/a/img")).click(); //click on yale sofa
		driver.findElement(By.xpath("//*[@id='sofa-sets']")).click(); //click on order your set
		driver.findElement(By.xpath("//*[@id='sofa-order-set']/div/div[3]/a")).click(); //click on buy now
		driver.findElement(By.xpath("//*[@id='kmBody']/div[5]/div[2]/div[3]/div/div/div[2]/div/a")).click(); //click on book now
		
		
	}

}
