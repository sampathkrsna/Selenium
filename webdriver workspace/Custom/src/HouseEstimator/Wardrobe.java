package HouseEstimator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Wardrobe {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver= new FirefoxDriver();
		driver.manage().window().maximize();
		Thread.sleep(2000);
		driver.get("http://root.test.customfurnish.com:8025/full-house-estimator/");
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[1]/div/a")).click();
		Select furniture = new Select(driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[3]/div[1]/div[1]/select")));
		furniture.selectByVisibleText("WARDROBE");
		Thread.sleep(2000);
		Select BaseMaterial = new Select(driver.findElement(By.id("baseMaterial")));
		BaseMaterial.selectByValue("PlaywoodLaminate");
		Thread.sleep(2000);
		
		Select DoorMaterial = new Select(driver.findElement(By.id("doorMaterial")));
		
				
		DoorMaterial.selectByValue("PlaywoodLaminate");
			
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[3]/div[3]/div[2]/a")).click();
		Thread.sleep(12000);
		driver.close();
		
		

	}

}
