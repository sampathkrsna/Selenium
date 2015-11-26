package curtainonlinepack;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;

public class curtainonlinetestcase {
	
	WebDriver driver;
	
	@BeforeMethod
	@Parameters("url")
	public void BeforeMethod(String url)throws InterruptedException, IOException
	{
		driver.manage().window().maximize();
		driver.get(url);
	}
	
	@AfterMethod
	public void AfterMethod() throws IOException
	{
		driver.quit();
		
	}	
	
  @Test
  @Parameters("url")
  public void selectCurtainstyle(String url)throws InterruptedException, IOException
  {
	selectcurtains();  
  }
  
  public void selectcurtains() throws IOException, InterruptedException
  {
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[2]/div/a")).click();
	  selectcurtainEyeletcurtain();
	  
  }
  
  public void selectcurtainEyeletcurtain()throws InterruptedException, IOException
  {
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[4]/div[2]/ul/li[1]/a/div/div[3]/button")).click();
	  
  }
  
  public void selectcurtainwidthsize() throws InterruptedException,IOException
  {
	  Thread.sleep(2000);
	  String feet = Integer.toString(randInt(2,20));
	  String inch = Integer.toString(randInt(0,11));
	  new Select(driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[7]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[3]/select[1]"))).selectByValue(feet);
	  new Select(driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[7]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[3]/select[2]"))).selectByValue(inch);
		  
  }
  
  public void selectcurtainheightsize() throws InterruptedException
  {
	  Thread.sleep(2000);
	    
	  String feetH = Integer.toString(randInt(4,20));
	  String inchh = Integer.toString(randInt(0,11));
	  new Select(driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[7]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[4]/select[1]"))).selectByValue(feetH);
	  new Select(driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[7]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[4]/select[2]"))).selectByValue(inchh);
		  
  }
  
  
private static int randInt(int min , int max)
{
	Random rand = new Random();
	return rand.nextInt((max - min)+1) + min;
	
}

public void selectlining() throws InterruptedException
  {
	  Thread.sleep(1000);
	  
	  driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[7]/div[3]/a[1]")).click();
	  
	  for(int m=1;m<=3;m++)
	  {
		  //String lining = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[8]/div[2]/ul/li["+m+"]/div/div/a[2] ")).isSelected();
		  driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[8]/div[2]/ul/li["+m+"]/div/div/a[2] ")).isSelected();
		  
	  }
	  
	  
  }
public void selecthardrobe() throws InterruptedException
{
	Thread.sleep(2000);
	driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[8]/div[3]/a[1]")).click();
	for(int k=0;k<=42;k++)
	{
		String hardware=driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[9]/div[2]/div/div/div/div/div[1]/ul/li["+k+"]/ul/li[2]/ul/li[2]/button")).click();
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[9]/div[2]/div/div/div/div/div[1]/ul/li["+k+"]/ul/li[2]/ul/li[2]/button")).click();
	}
	
}
	  
	  
 }
  
  
  

