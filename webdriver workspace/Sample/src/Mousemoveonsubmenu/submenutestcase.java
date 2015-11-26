package Mousemoveonsubmenu;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class submenutestcase {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://root.test.customfurnish.com/");
		driver.manage().timeouts().implicitlyWait(4,TimeUnit.SECONDS);
		driver.findElement(By.xpath("/html/body/div[5]/a")).click();
		Thread.sleep(3000);
		Actions actions = new Actions(driver);
		WebElement moveonmenu=driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/nav/section[1]/ul/li[2]/a"));
		Thread.sleep(2000);
		//actions.moveToElement(moveonmenu).moveToElement(driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/nav/section[1]/ul/li[2]/div/ul/li[1]/a")));
		//Thread.sleep(1000);
		actions.moveToElement(moveonmenu).moveToElement(driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/nav/section[1]/ul/li[2]/div/ul/li[2]/a"))).click().perform();
		Thread.sleep(5000);
		//WebDriverWait wait = new WebDriverWait(driver,15);
		//wait.until(ExpectedConditions.titleContains("Wardrobes & cupboards designs online in india"));
		System.out.println("Tested");
		
		

	}

}
