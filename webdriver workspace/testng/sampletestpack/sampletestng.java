package /sampletestpack;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations;
import org.testng.Assert;

public class sampletestng {
	
	public String baseurl = "http://root.test.customfurnish.com/modular-kitchens";
    public WebDriver driver= new FirefoxDriver();
	
  @Test
  public void f() {
	driver.get(baseurl);
    String expectedtitle ="Design Modular kitchens online";
    String actultitle = driver.getTitle();
    Assert.assertEquals(actultitle, expectedtitle);
    driver.quit();
        		
	  
  }
}
