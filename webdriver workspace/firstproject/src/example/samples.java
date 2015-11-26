package example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class samples {

	public static void main(String[] args)  {
		WebDriver driver = new FirefoxDriver();
		String baseUrl ="http://www.sastra.edu";
		String actualTitle="";
		String exceptedTitle="SASTRA University";
		driver.manage().window().maximize();
		driver.get(baseUrl);
		actualTitle=driver.getTitle();
		if(actualTitle.contentEquals(exceptedTitle))
		{
			System.out.println("Tested");
		}
		else
		{
			System.out.println("Please Check");
		}
		driver.close();


	}

}
