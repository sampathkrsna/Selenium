package cspackage;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;


public class SofaClass {
	
	private WebDriver driver = null;
	
	@BeforeSuite
	public void OpenBrowser()
	{
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://root.staging.kustommade.com/full-house/");
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}
	
	@Test
	public void fullhouse() throws InterruptedException, AWTException
	{		
		driver.findElement(By.xpath(".//*[@id='userRequirementDetails']/div[2]/input")).sendKeys("Sampath Krishna"); //Name
		driver.findElement(By.xpath(".//*[@id='userRequirementDetails']/div[3]/input")).sendKeys("kustomtestuser@gmail.com"); //Email 
		driver.findElement(By.xpath(".//*[@id='userRequirementDetails']/div[4]/input")).sendKeys("9703122939"); //phone number
		driver.findElement(By.xpath(".//*[@id='userRequirementDetails']/div[5]/textarea")).sendKeys("HIG-67, Madhavadhara, Vuda Colony, vskp-18"); // Address
		driver.findElement(By.xpath(".//*[@id='floorPlanPadding']")).click(); //click to upload
		Thread.sleep(3000);
		StringSelection ss = new StringSelection("C:\\Users\\Sampath\\Desktop\\mudu.jpg");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		 Robot robot = new Robot();
		 robot.delay(1000);

		 robot.keyPress(KeyEvent.VK_CONTROL);
		 robot.keyPress(KeyEvent.VK_V);
		 robot.keyRelease(KeyEvent.VK_V);
		 robot.keyRelease(KeyEvent.VK_CONTROL);
		 robot.keyPress(KeyEvent.VK_ENTER);
		 robot.keyRelease(KeyEvent.VK_ENTER);
		 robot.delay(1000); 
		driver.findElement(By.xpath(".//*[@id='userRequirementDetails']/div[7]/textarea")).sendKeys("PRELAM MDF, Particle Board,Modular Kitchen whit Acryilic finish");	
		driver.findElement(By.xpath(".//*[@id='requirementSubmitButton']")).click();
		
		
	}
	

	 @Test(dependsOnMethods="fullhouse")
	public void urlchecker() throws InterruptedException
	{
		 Thread.sleep(8000);
		 String url = driver.getCurrentUrl();
		  
		 System.out.println(driver.getCurrentUrl());
		 Assert.assertEquals(url, "http://root.test.kustommade.com/user-requirement-submitted-successful/");
		 System.out.print("Correct URL opened. ");
		 Thread.sleep(3000);
	}
	 
	
	 @Test(dependsOnMethods="urlchecker")
	public void Checkingtherurls() throws InterruptedException
	{
		 Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='loginUser']/p[1]")).click(); //click on login button
		Thread.sleep(5000);
		if (driver.findElement(By.xpath(".//*[@id='login']")).isDisplayed()) 
		{
		System.out.println("Pass");//find login button email
		}
		else{
			System.out.println("Fail");
		}
		driver.findElement(By.xpath(".//*[@id='closeIcon']")).click();
        
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='kmBody']/div[2]/div/div[2]/ul/li[5]/a/div")).click(); //click on login button
		Thread.sleep(3000);
		String url1 = driver.getCurrentUrl();
		 System.out.println(driver.getCurrentUrl());
		 Assert.assertEquals(url1, "http://root.test.kustommade.com/cart");
		 System.out.print("Correct URL opened. ");
       
	}
}
