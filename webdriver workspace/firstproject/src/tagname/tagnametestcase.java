package tagname;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class tagnametestcase {

	public static void main(String[] args) throws InterruptedException {
	 WebDriver driver = new FirefoxDriver();
	 driver.manage().window().maximize();
	 String baseUrl = "https://www.facebook.com/?_rdr=p";
	 String tagName =" ";
	 driver.get(baseUrl);
	 tagName=driver.findElement(By.id("email")).getTagName();
	 System.out.println("The TagName =" + tagName);
	 Thread.sleep(4000);
	 driver.close();
	 

	}

}
