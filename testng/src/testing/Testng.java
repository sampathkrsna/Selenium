package testing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Testng {

	

	@Test
	public void webopen(){
		WebDriver driver=new FirefoxDriver();
		
		driver.get("http://kustommade.tk:9980");
	}

}
