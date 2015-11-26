package locator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class loctortestcase {

	public static void main(String[] args) {
		WebDriver driver=new FirefoxDriver();
		String baseUrl="https://accounts.google.com/ServiceLogin?service=mail&continue=https://mail.google.com/mail/#identifier";
		String tagName="";
		driver.manage().window().maximize();
		driver.get(baseUrl);
		tagName=driver.findElement(By.id("Email")).getTagName();
		System.out.println(tagName);
		driver.close();		
	
	}

}
