package Curtainpack;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class curtaintestcase {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://root.test.customfurnish.com/curtains-online/");
		//Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[1]/div/a")).click();
		Thread.sleep(2000);
		//selecting sample
		/*for(int n=2;n<=6;n++)
		{
			WebElement style =driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[3]/div[2]/ul/li["+n+"]/a/div/div[3]/button"));
			style.click();
			
		}*/
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[3]/div[2]/ul/li[1]/a/div/div[3]/button")).click();
		Thread.sleep(2000);
		
		//sample
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[4]/div[2]/div[1]/div[1]/button")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[4]/div[2]/div[1]/div[1]/div[2]/div[2]/div/a/img")).click();
		Thread.sleep(2000);
		//description add to sample 
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[4]/div[2]/div[1]/div[1]/div[2]/div[3]/div[1]/div/a/img[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[4]/div[2]/div[1]/div[1]/div[2]/div[3]/div[3]/div/div[2]/div[2]/div[7]/button[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[4]/div[2]/div[1]/div[1]/div[2]/div[2]/div/a/img")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[4]/div[2]/div[1]/div[1]/div[2]/div[3]/div[1]/div/a/img[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[4]/div[2]/div[1]/div[1]/div[2]/div[3]/div[3]/div/div[2]/div[2]/div[7]/button[2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[4]/div[2]/div[1]/div[1]/button")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[4]/div[2]/div[1]/div[1]/div[2]/div[2]/div/a/img")).click();
		Thread.sleep(2000);
		
		//search	
		
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[4]/div[2]/div[2]/div[1]/div/div[1]/div[1]/div/input")).sendKeys("cherry");
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[4]/div[2]/div[2]/div[1]/div/div[1]/div[2]/button")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[4]/div[2]/div[2]/div[1]/div/div[1]/div[1]/div/input")).clear();
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[4]/div[2]/div[2]/div[1]/div/div[1]/div[2]/button")).click();
		Thread.sleep(3000);
		
		//next - step-2
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[4]/div[2]/div[1]/div[1]/div[2]/div[4]/a[1]")).click();
		Thread.sleep(2000);
		// back to samples
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[5]/div[2]/div[2]/div[1]/div[2]/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[4]/div[2]/div[1]/div[1]/div[2]/div[2]/div/a/img")).click();
		Thread.sleep(2000);
		
		
		//filter
		Select dropdown = new Select(driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[4]/div[2]/div[2]/div[1]/div/div[1]/div[4]/div/select")));
		dropdown.selectByVisibleText("PRICE: HIGH TO LOW");
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[4]/div[2]/div[2]/div[1]/div/div[1]/div[5]/div[2]/div[1]/div/div")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[4]/div[2]/div[2]/div[1]/div/div[1]/div[5]/div[2]/div[1]/div/div/ul/li[4]/input")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[4]/div[2]/div[2]/div[1]/div/div[1]/div[5]/div[2]/div[2]/div/div")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[4]/div[2]/div[2]/div[1]/div/div[1]/div[5]/div[2]/div[2]/div/div/ul/li[10]")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[4]/div[2]/div[2]/div[1]/div/div[1]/div[5]/div[2]/div[3]/div/div")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[4]/div[2]/div[2]/div[1]/div/div[1]/div[5]/div[2]/div[3]/div/div/ul/li[17]")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("/html/body/div[1]/div[5]")).click();
	    
	    // add samples
	    
	    driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[4]/div[2]/div[2]/div[1]/div/div[2]/ul/li[1]/div[1]/div")).click();
	    Thread.sleep(1000);
	    driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[4]/div[2]/div[2]/div[1]/div/div[2]/ul/li[2]/div[1]/div")).click();
	    Thread.sleep(1000);
	    driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[4]/div[2]/div[2]/div[1]/div/div[2]/ul/li[3]/div[1]/div")).click();
	    Thread.sleep(1000);
	    driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[4]/div[2]/div[2]/div[1]/div/div[2]/ul/li[4]/div[1]/div")).click();
	    Thread.sleep(1000);
	    driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[4]/div[2]/div[2]/div[1]/div/div[2]/ul/li[5]/div[1]/div")).click();
	    Thread.sleep(1000);
	    
	  //next - step-2
	  	driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[4]/div[2]/div[1]/div[1]/div[2]/div[4]/a[1]")).click();
	  	Thread.sleep(2000);
	  	
	  	//back - step-1
	  	driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[5]/div[2]/div[1]/div[1]/a")).click();
	  	Thread.sleep(3000);
	  	
	  //click sample
	  	driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[4]/div[2]/div[1]/div[1]/button")).click();
	  	Thread.sleep(2000);
	  	
	  		
	    //remove sample
	    driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[4]/div[2]/div[1]/div[1]/div[2]/div[2]/div/div[3]/div[2]/ul/li[1]/a")).click();
	    Thread.sleep(3000);
	    driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[4]/div[2]/div[1]/div[1]/div[2]/div[2]/div/a/img")).click();
		Thread.sleep(2000);
		
		//clear filter
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[4]/div[2]/div[2]/div[1]/div/div[1]/div[5]/div[1]/div[2]/div/a")).click();
		Thread.sleep(3000);
		//next - step2
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[4]/div[2]/div[1]/div[1]/div[2]/div[4]/a[1]")).click();
		Thread.sleep(2000);
		// apply sample to curtain
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[5]/div[2]/div[2]/div[1]/div[1]/div/div[2]/ul/li[1]/div/a/img")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[5]/div[2]/div[2]/div[1]/div[1]/div/div[2]/ul/li[2]/div/a/img")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[5]/div[2]/div[2]/div[1]/div[1]/div/div[2]/ul/li[3]/div/a/img")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[5]/div[2]/div[2]/div[1]/div[1]/div/div[2]/ul/li[4]/div/a/img")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[5]/div[2]/div[2]/div[1]/div[1]/div/div[2]/ul/li[1]/div/a/img")).click();
		Thread.sleep(3000);
		
		//order - step-3
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[5]/div[2]/div[1]/div[3]/a")).click();
		Thread.sleep(2000);
		
		// measurement guide
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[8]/div[2]/div[1]/div/div/div[1]/div[1]/a/div/span")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[4]")).click();
		
		//next-step-4 -lining
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[8]/div[3]/a[1]")).click();
		Thread.sleep(2000);
		//next-step-5 - hardware
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[9]/div[6]/a[1]")).click();
		Thread.sleep(2000);
		//next-step-6 - cart
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[10]/div[7]/a[1]")).click();
		Thread.sleep(2000);
		//click for samples in cart page
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[11]/div[2]/div[1]/div/div[1]/div/div[5]/a/span")).click();
		Thread.sleep(2000);
		//next - step2
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[4]/div[2]/div[1]/div[1]/div[2]/div[4]/a[1]")).click();
		Thread.sleep(2000);
		//order - step-3
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[5]/div[2]/div[1]/div[3]/a")).click();
		Thread.sleep(2000);
		//choose dimension - width
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[8]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[4]/select[1]")).sendKeys("8");
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[8]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[4]/select[2]")).sendKeys("6");
		Thread.sleep(2000);
		//choose height
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[8]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[5]/select[1]")).sendKeys("9");
		Thread.sleep(3000);
		//next-step-4 -lining
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[8]/div[3]/a[1]")).click();
		Thread.sleep(2000);
		//lining - polyster
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[9]/div[3]/div/ul/li[2]/a/div/div[4]/button")).click();
		Thread.sleep(3000);
		//select - hardware
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[10]/div[5]/div/div/div/div/div[1]/ul/li[2]/ul/li[2]/ul/li[2]/button")).click();
		Thread.sleep(2000);
		//next-step-6 - cart
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[10]/div[7]/a[1]")).click();
		Thread.sleep(2000);
		//fabric page
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[11]/div[1]/div/ul/li[2]/a")).click();
		Thread.sleep(3000);
		//summary page
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[4]/div[1]/div/ul/li[3]/a")).click();
		Thread.sleep(3000);
		//dimension page
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[5]/div[1]/div/ul/li[4]/a")).click();
		Thread.sleep(3000);
		//lining page
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[8]/div[1]/div/ul/li[7]/a")).click();
		Thread.sleep(3000);
		//black out lining
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[9]/div[3]/div/ul/li[3]/a/div/div[4]/button")).click();
		Thread.sleep(3000);
		//remove hardware
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[10]/div[6]/div[2]/div/div[5]/a")).click();
		Thread.sleep(2000);
		//add hardware
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[10]/div[5]/div/div/div/div/div[2]/a[4]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[10]/div[5]/div/div/div/div/div[1]/ul/li[1]/ul/li[2]/ul/li[2]/button")).click();
		Thread.sleep(3000);
		// next - cart page
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[10]/div[7]/a[1]")).click();
		Thread.sleep(2000);
		///add to cart
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[11]/div[2]/div[2]/div[2]/button")).click();
		Thread.sleep(3000);	    
	    
		
		
		
		
		
		
		

	}

}