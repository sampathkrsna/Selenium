package Kitchen;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class KitchenTestcase {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		String baseUrl ="http://root.test.customfurnish.com/";
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
		driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/nav/section[1]/ul/li[3]/a/span")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[1]/div[1]/div/button")).click();
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[4]/div[2]/ul/li[2]/div/div[1]")).click();
		Thread.sleep(3000);
		WebElement el = driver.findElement(By.id("runningLengthFeet"));
		//el.click();
		Select drpfeet =new Select(el);
		drpfeet.selectByValue("7");
	    	    
	    Thread.sleep(4000);
		Select drpinch = new Select(driver.findElement(By.id("runningLengthInches")));
		drpinch.selectByValue("5");
		Thread.sleep(4000);
		
		WebElement ct = driver.findElement(By.id("counterTop-selection"));
		Select drpcountertop = new Select(ct);
		drpcountertop.selectByVisibleText("Granite - LITE");
		Thread.sleep(2000);
		
		WebElement hd = driver.findElement(By.id("Hardware-selection"));
		Select drphardware = new Select(hd);
		drphardware.selectByVisibleText("Hettich");
		Thread.sleep(4000);
		
		WebElement draw = driver.findElement(By.id("drawerSelection"));
		Select drpdrawer = new Select(draw);
		drpdrawer.selectByValue("hettichSoftclose");
		Thread.sleep(4000);
		
		//driver.findElement(By.xpath("//*[contains(text(),'Cooking Unit Regular')]"));
		
				
		driver.findElement(By.id("atab-accessories")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[6]/div[2]/div[2]/div[3]/div/div[2]/div/div[4]/ol/li[1]/div/div[2]/div/div[2]/a")).click();
		Thread.sleep(4000);
		
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[6]/div[2]/div[2]/div[3]/div/div[2]/div/div[4]/ol/li[5]/div/div[2]/div/div[2]/a")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[6]/div[2]/div[2]/div[3]/div/div[2]/div/div[4]/ol/li[3]/div/div[2]/div/div[2]/a")).click();
		Thread.sleep(3000);
		
		WebElement ass = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[6]/div[2]/div[2]/div[1]/div/table[4]/tr[2]/td[4]/a/img"));
		if(ass.isEnabled())
		{
			ass.click();
		}
		Thread.sleep(3000);
		WebElement Price=driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[10]/div[1]/b[2]"));
		//Price.isSelected();
		System.out.println("Total Price = " + Price.getText());
			
		 
		
		
	}

}
