import java.util.ArrayList;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;


public class Testingpayment {

	
	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver=new FirefoxDriver();
		
	
		driver.manage().window().maximize();
		driver.get("http://www.customfurnish.com/sofa");
		
		Thread.sleep(3000);
	
//		driver.findElement(By.xpath(" .//*[@id='km-header']/div/nav/section[1]/ul/li[4]/a/span ")).click();
//		Thread.sleep(3000);
//		driver.findElement(By.xpath(" .//*[@id='loginUser']/p[2] ")).click();
//		Thread.sleep(3000);
//		driver.findElement(By.xpath(" .//*[@id='login-email']")).sendKeys("sampathkrsna@gmail.com");
//		driver.findElement(By.xpath(".//*[@id='login-password']")).sendKeys("Pass123456"); 
//		driver.findElement(By.xpath(" .//*[@id='login']/span/span ")).click();
//		
//		Thread.sleep(14000);
		System.out.println("pass1");
	
		int noOfSofas = driver.findElements(By.xpath("//*[@id='variants-container']/li")).size();
		System.out.println(+noOfSofas);
		int selectedSofa = randInt(1, noOfSofas);
		System.out.println("pass2");
/*	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath("//*[@id='variants-container']/li["+selectedSofa+"]/div/div[1]/div/span")));
		String sofaName = driver.findElement(By.xpath("//*[@id='variants-container']/li["+selectedSofa+"]/div/div[1]/div/span")).getText();*/
		System.out.println("pass3");
		WebElement a = driver.findElement(By.xpath(" /html/body/div[5]/div[2]/div/div/div[2]/ul/li[1]/div/section/a/img "));
		a.click();
	    System.out.println("pass3");
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div[5]/div[2]/div[1]/div[1]/div/div/a")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(" /html/body/div[5]/div[5]/div/div[3]/a ")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath(" .//*[@id='login-email-save'] ")).sendKeys("sampathkrsna@gmail.com");
		driver.findElement(By.xpath(" .//*[@id='login-password-save'] ")).sendKeys("Pass12345");
		driver.findElement(By.xpath(" .//*[@id='loginSave'] ")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='cartModal']/a/span")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(" .//*[@id='kmBody']/div[5]/div[2]/div[3]/div/div/div[2]/div/a ")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='saveDetails']")).click();
		Thread.sleep(3000);
		//driver.findElement(By.xpath(".//*[@id='saveDetails']")).click();
		//Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='ebsPay']")).click();
		Thread.sleep(7000);
		driver.findElement(By.xpath(".//*[@id='login-btn']")).click();
		Thread.sleep(5000);
		System.out.println("Directing to payment");
		driver.switchTo().frame(driver.findElement(By.id("login-iframe")));
		driver.findElement(By.xpath(".//*[@id='username']")).sendKeys("8373900312");
		driver.findElement(By.xpath(".//*[@id='password']")).sendKeys("Paytm@197");
		driver.findElement(By.xpath(" .//*[@id='login_btn'] ")).click();
		Thread.sleep(5000);
		driver.switchTo().defaultContent();
		
		driver.findElement(By.xpath("html/body/div[10]/div[2]/div[4]/form/div/div[1]/input")).click();
/*	if(!sofaName.equals(driver.findElement(By.xpath("//*[@id='itemDetail']/div[1]/div/label")).getText())){
			System.out.println("Sofa name is displayed wrong for "+sofaName);
		}
		
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='sofa-sets']"));
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='sofa-order-set']/div/div[3]/a"));*/
		
		
		
		/*
		  
		driver.findElement(By.xpath(".//*[@id='kmBody']/div[9]/a/ul")).click();  
		driver.findElement(By.xpath(".//*[@id='measurement_form']/div/div[1]/div[3]/div/ul/li[1]/select")).click();
*/
		
	}
public static int randInt(int min,int max)
{
	Random rand= new Random();
	return rand.nextInt((max-min)+1)+min;
}
}

