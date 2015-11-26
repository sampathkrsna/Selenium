package curtains;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class curtaintestcase {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://root.test.customfurnish.com/curtains-online/");
		//driver.manage().timeouts().implicitlyWait(6,TimeUnit.SECONDS);
		//driver.findElement(By.xpath("/html/body/div[5]/a")).click();
		
		driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/nav/section[1]/ul/li[5]/a/span")).click();
		System.out.println("1-Curtain Page");
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[2]/div/button")).click();
		Thread.sleep(2000);
        String totprice = null;
        WebElement price=driver.findElement(By.xpath(".//*[@id='curtain-price']/div[1]/b[2]"));
        if(price.isDisplayed())
        {
        	totprice=price.getText();
        	System.out.println("Default Price"+ " " + totprice);
        }        
        
        Thread.sleep(2000);
        System.out.println("2-Default Price");
        driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[4]/div[2]/ul/li[2]/div/div[3]/a")).click();
        Thread.sleep(3000);
        System.out.println("3-Panel Curtain ");
        driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[5]/div[3]/div/div[1]/div[5]/ul/li[9]/div[1]/a/img")).click();
        Thread.sleep(2000);
        String totlprice = null;
        WebElement pric=driver.findElement(By.xpath(".//*[@id='curtain-price']/div[1]/b[2]"));
        if(pric.isDisplayed())
        {
        	totlprice=pric.getText();
        	System.out.println("Flat Panel price"+ " " + totlprice);
        }
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[13]/div/a[2]")).click();
        System.out.println("Click Next Step");
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[6]/button")).click();
        Thread.sleep(2000);
        System.out.println("Select Dimension");
        driver.findElement(By.xpath("/html/body/div[1]/div[6]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[3]/select[1]")).sendKeys("6");
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[1]/div[6]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[4]/select[2]")).sendKeys("5");
        Thread.sleep(2000); 
        driver.findElement(By.xpath("/html/body/div[1]/div[6]/div[2]/div[3]/button")).click();
        Thread.sleep(2000);
        String selectedprice=null;
        WebElement pri=driver.findElement(By.xpath(".//*[@id='curtain-price']/div[1]/b[2]"));
        if(pri.isDisplayed())
        {
        	selectedprice=pri.getText();
        	System.out.println("Without Lining Price " +" "+ selectedprice);
        }
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[1]/div[7]/div[2]/ul/li[2]/div")).click();
        Thread.sleep(2000);
        String polysterliningprice=null;
        WebElement polyliningprice=driver.findElement(By.xpath(".//*[@id='curtain-price']/div[1]/b[2]"));
        if(polyliningprice.isDisplayed())
        {
        	polysterliningprice=polyliningprice.getText();
        	System.out.println("Price with Polyster Lining"+ " "+ polysterliningprice);
        }
        Thread.sleep(2000);
        //next-click
        driver.findElement(By.xpath("/html/body/div[1]/div[7]/div[3]/button")).click();
        Thread.sleep(2000);
        //add hardware
        driver.findElement(By.xpath("/html/body/div[1]/div[8]/div[2]/div/div/div/div/div[1]/ul/li[5]/ul/li[2]/ul/li[2]/button")).click();
        Thread.sleep(2000);
        String hardware=null;
        WebElement hardwareprice=driver.findElement(By.xpath(".//*[@id='curtain-price']/div[1]/b[2]"));
        if(hardwareprice.isDisplayed())
        {
        	hardware=hardwareprice.getText();
        	System.out.println("Price With Hardware"+" "+ hardware);
        	
        }
        Thread.sleep(2000);
        //hardware next
        driver.findElement(By.xpath("/html/body/div[1]/div[8]/div[4]/button")).click();
        Thread.sleep(2000);
        
        //String TotalPrice;
        //int value=Integer.parseInt(totprice)+Integer.parseInt(totlprice)+Integer.parseInt(selectedprice)+Integer.parseInt(polysterliningprice)+Integer.parseInt(hardware);
        //TotalPrice=" "+value;
        //System.out.println("TotalPrice is" +" "+TotalPrice);
        String Totalprice=null;
        WebElement finalprice=driver.findElement(By.xpath(".//*[@id='curtain-price']/div[1]/b[2]"));
        if(finalprice.isDisplayed())
        {
        	Totalprice=finalprice.getText();
        	System.out.println("TotalPrice is"+ " " + Totalprice);
        }
        Thread.sleep(2000);
        if(hardware.equals(Totalprice))
        {
        	driver.findElement(By.xpath("/html/body/div[1]/div[9]/div[2]/div/div[1]/div[2]/div/div[1]/button")).click();
        	System.out.println("Add to Cart Page");
        }
        else
        {
        	System.out.println("Check Price");
        }
        Thread.sleep(3000);
	}

}
