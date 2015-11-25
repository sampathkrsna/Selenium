package testng;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Firstprog {

@Test
public void openbrowserandurl() throws InterruptedException
//open browser and goto kustommade.tk site
{
WebDriver driver=new FirefoxDriver();
	driver.get("http://kustommade.tk:9980/");
	Thread.sleep(10000);
	
	}
@Test


 
 //subscribes to the news letters
 
	

 
private WebElement findElement(By xpath) {
	// TODO Auto-generated method stub
	return null;
}
}
