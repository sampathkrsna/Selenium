package mailpackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class gmailtestcase {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.co.in/?gfe_rd=cr&ei=-3KjVafsNKrv8wes05noDA&gws_rd=ssl");
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='gbw']/div/div/div[1]/div[1]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='Email']")).sendKeys("testcustom1234");
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='next']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='Passwd']")).sendKeys("custom1234");
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='signIn']")).click();
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		//Thread.sleep(20000);
		System.out.println("Test-1");
		driver.findElement(By.xpath("/html/body/div[7]/div[3]/div/div[2]/div[1]/div[1]/div[1]/div[2]/div/div/div[2]/div/div[1]/div[1]/div/div[3]/div/div/div[1]/span/a")).click();
		Thread.sleep(8000);
		System.out.println("Test-2");
		driver.findElement(By.xpath("/html/body/div[7]/div[3]/div/div[2]/div[1]/div[1]/div[1]/div[2]/div/div/div[2]/div/div[1]/div[1]/div/div[1]/div/div/div[1]/span/a")).click();
		Thread.sleep(3000);
		System.out.println("Test-3");
		driver.findElement(By.xpath("/html/body/div[7]/div[3]/div/div[2]/div[1]/div[1]/div[1]/div[2]/div/div/div[1]/div/div")).click();
		Thread.sleep(3000);
		System.out.println("Test-4");
		driver.findElement(By.name("to")).sendKeys("amir.srini@gmail.com");
		Thread.sleep(3000);
		System.out.println("Test-5");
		driver.findElement(By.name("subjectbox")).sendKeys("Hi");
		Thread.sleep(3000);		
		System.out.println("Test-6");
		
		driver.findElement(By.xpath("/html/body/div[14]/div/div/div/div[1]/div[3]/div[1]/div[1]/div/div/div/div[3]/div/div/div[4]/table/tbody/tr/td[2]/table/tbody/tr[1]/td/div/div[1]/div[2]/div[1]/div/table/tbody/tr/td[2]/div[2]/div")).sendKeys("Haiiiii");
		Thread.sleep(4000);
	    System.out.println("Test-7");
		
		driver.findElement(By.xpath("/html/body/div[14]/div/div/div/div[1]/div[3]/div[1]/div[1]/div/div/div/div[3]/div/div/div[4]/table/tbody/tr/td[2]/table/tbody/tr[2]/td/div/div/div[4]/table/tbody/tr/td[1]/div/div[2]")).click();
		Thread.sleep(5000);	
		
				
		driver.findElement(By.xpath(".//*[@id='gb']/div[1]/div[1]/div[2]/div[4]/div[1]/a/span")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='gb_71']")).click();
		
		System.out.println("Tested");
	}

}
