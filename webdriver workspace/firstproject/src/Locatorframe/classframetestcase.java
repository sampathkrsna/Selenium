package Locatorframe;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class classframetestcase {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver = new FirefoxDriver();
		String baseUrl ="http://selenium.googlecode.com/svn/trunk/docs/api/java/index.html";
		driver.manage().window().maximize();
		
		driver.get(baseUrl);
	    driver.switchTo().frame("classFrame");
	    driver.findElement(By.linkText("Index")).click();
		System.out.println("Tested");

	}

}
