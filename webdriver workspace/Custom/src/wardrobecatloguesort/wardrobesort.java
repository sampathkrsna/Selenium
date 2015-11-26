package wardrobecatloguesort;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class wardrobesort {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://root.test.customfurnish.com/wardrobe-designs/");
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[3]/div/form/ul/li[6]/a")).click();
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[3]/div/form/ul/li[6]/div/div[2]/label[3]/input")).click();
		
		
		
		

	}

}
