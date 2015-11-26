package HouseEstimator;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class furniture {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver= new FirefoxDriver();
		driver.manage().window().maximize();
		Thread.sleep(2000);
		driver.get("http://root.test.customfurnish.com:8025/full-house-estimator/");
		Thread.sleep(2000);
		WebElement select =driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[3]/div[1]/div[1]/select"));
		List<WebElement> options = select.findElements(By.tagName("option"));
		for(WebElement option:options)
		{
			if("BEDS".equals(option.getText()))
				option.click();
			
		}
		
		
		

	}

}
