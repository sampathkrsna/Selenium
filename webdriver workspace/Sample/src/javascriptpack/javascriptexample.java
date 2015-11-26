package javascriptpack;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

public class javascriptexample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	  /* System.setProperty("webdriver.chrome.driver","/home/customfurnish/downloads");
	   ChromeOptions option = new ChromeOptions();
	   option.addArguments("window size=1028,768");
	   DesiredCapabilities capabilities = DesiredCapabilities.chrome();
	   capabilities.setCapability(ChromeOptions.CAPABILITY,option);
	   WebDriver driver=new ChromeDriver(capabilities);
	   driver.get("https://www.google.co.in/?gfe_rd=cr&ei=ghgvVpzyBaPv8we-vKrQAQ&gws_rd=ssl");
	   if(driver instanceof JavascriptExecutor)
	   {
		   ((JavascriptExecutor)driver).executeScript("Alert('Welcome');");
	   }*/
	   
		System.setProperty("webdriver.firefox.driver","/home/customfurnish/downloads");
		FirefoxProfile profile = new FirefoxProfile();
		//profile.setPreference("javascript.enabled",true);
		
	    
		
		WebDriver driver = new FirefoxDriver(profile);
		driver.manage().window().maximize();
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability(ChromeOptions.CAPABILITY,profile);
		driver.get("https://www.google.co.in/?gfe_rd=cr&ei=ghgvVpzyBaPv8we-vKrQAQ&gws_rd=ssl");
		if(driver instanceof JavascriptExecutor)
		{
			((JavascriptExecutor)driver).executeScript("Alert('Welcome');");
		}
		

	}

}
