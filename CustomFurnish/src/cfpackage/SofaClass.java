package cfpackage;

import java.util.Set;
import java.util.concurrent.TimeUnit;

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
		Set<String> windowHandle = driver.getWindowHandles();
		for(String handle : windowHandle)
		{
			System.out.println(handle);
		}
	}

}
