import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class curtainonline {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://root.test.customfurnish.com/curtains-online/");
		//Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[2]/div/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[4]/div[2]/ul/li[1]/a/div/div[3]/button")).click();
		Thread.sleep(1000);
		//WebElement element =driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[5]/div[2]/div[2]/div/div[1]/div[4]/div/select"));
		//driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[5]/div[2]/div[2]/div/div[1]/div[4]/div/select")).click();
		Select dropdown=new Select(driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[5]/div[2]/div[2]/div/div[1]/div[4]/div/select")));
		dropdown.selectByVisibleText("PRICE: HIGH TO LOW");
		//driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[5]/div[2]/div[2]/div/div[1]/div[4]/div/select")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[5]/div[2]/div[2]/div/div[1]/div[5]/div[2]/div[2]/div/div")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[5]/div[2]/div[2]/div/div[1]/div[5]/div[2]/div[2]/div/div/ul/li[4]/input")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[5]/div[2]/div[2]/div/div[1]/div[5]/div[2]/div[2]/div/div")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[5]/div[2]/div[2]/div/div[2]/ul/li[1]/div[1]/a[1]/img")).click();
		
		Thread.sleep(1000);
         
	/*	String pricerate1 = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[5]/div[2]/div[2]/div/div[2]/ul/li[1]/div[2]/div/a[1]/div[2]/span/span")).getText();
        //System.out.print(pricerate1);
        System.out.println(pricerate1.substring(4));
        
        String pricerate2 =driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[5]/div[2]/div[2]/div/div[2]/ul/li[2]/div[2]/div/a[1]/div[2]/span/span")).getText();
        System.out.println(pricerate2.substring(4));
        String pricerate3 = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[5]/div[2]/div[2]/div/div[2]/ul/li[3]/div[2]/div/a[1]/div[2]/span/span")).getText();
        System.out.println(pricerate3.substring(4));
        String pricerate4 = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[5]/div[2]/div[2]/div/div[2]/ul/li[4]/div[2]/div/a[1]/div[2]/span/span")).getText();
        System.out.println(pricerate4.substring(4));
        String pricerate5= driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[5]/div[2]/div[2]/div/div[2]/ul/li[5]/div[2]/div/a[1]/div[2]/span/span")).getText();
        System.out.println(pricerate5.substring(4));
        String pricerate6 = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[5]/div[2]/div[2]/div/div[2]/ul/li[3]/div[2]/div/a[1]/div[2]/span/span")).getText();
        System.out.println(pricerate6.substring(4));
        String pricerate7 = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[5]/div[2]/div[2]/div/div[2]/ul/li[3]/div[2]/div/a[1]/div[2]/span/span")).getText();
        System.out.println(pricerate7.substring(4));
        String pricerate8 = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[5]/div[2]/div[2]/div/div[2]/ul/li[3]/div[2]/div/a[1]/div[2]/span/span")).getText();
        System.out.println(pricerate8.substring(4));
        */
        for(int i =1;i<=8;i++) 
        {
        	String pricerate= driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[5]/div[2]/div[2]/div/div[2]/ul/li["+i+"]/div[2]/div/a[1]/div[2]/span/span")).getText();
           
            System.out.println(pricerate.substring(4));
            
        }
        
        //String a = driver.findElement(By.xpath("html/body/div[1]/div[5]/div[5]/div[2]/div[2]/div/div[2]/ul/li[10]/div[2]/div/a[1]/div[2]/span/span")).getText();
        
		
		String curtainname = null;
		WebElement name = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[5]/div[2]/div[2]/div/div[2]/ul/li[1]/div[2]/div/a[1]/div[1]"));
		//WebElement name = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[5]/div[2]/div[2]/div/div[2]/ul/li[2]/div[2]/div/a[1]/div[1]"));
				
		if(name.isDisplayed())
		{
			curtainname = name.getText();
			System.out.println("Selected Fabric Name is"+ " "+curtainname);
		}
		
		String curtainname1 = null;
		WebElement name1 = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[5]/div[2]/div[1]/div[1]/div[4]/div[2]/div[2]/div[2]/span/span"));
		if(name1.isDisplayed())
		{
			curtainname1 = name1.getText();
			System.out.println("Curtain Name-1"+" "+ curtainname1);
		}
		//click next -summary page
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[5]/div[2]/div[1]/div[1]/div[5]/a[1]")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[6]/div[2]/div[1]/div[3]/a")).click();
		Thread.sleep(1000);
		//dimension page
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[7]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[3]/select[1]")).sendKeys("8");
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[7]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[3]/select[2]")).sendKeys("0");
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[7]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[4]/select[1]")).sendKeys("6");
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[7]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[4]/select[2]")).sendKeys("6");
		Thread.sleep(2000);
		String curprice= null;
		//WebElement price = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[7]/div[2]/div[2]/div[2]/div[2]/div[1]"));
		WebElement price = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[7]/div[2]/div[2]/div[2]/div[2]/div[1]/b"));
		if(price.isDisplayed())
		{
		     curprice=price.getText();
			 System.out.println("Curtain Price"+ " "  + curprice);
			
		}
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[7]/div[3]/a[1]")).click();
	    Thread.sleep(1000);
	    
	    String liningcost = null;
	    //WebElement pric = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[8]/div[2]/ul/li[2]/div/a/div[3]/p"));
	    WebElement pric = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[8]/div[2]/ul/li[2]/div/a/div[3]/p/span[1]"));
	    if(pric.isDisplayed())
	    {
	    	liningcost=pric.getText();
	    	System.out.println("Lining cost " + "  " + liningcost);
	    }
	    
	    driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[8]/div[2]/ul/li[2]/div/div/a[2]")).click();
	    Thread.sleep(1000);
	    
	     
	    
	   	    
	    driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[9]/div[2]/div/div/div/div/div[1]/ul/li[5]/ul/li[2]/ul/li[2]/button")).click();
	    Thread.sleep(1000);
	    //driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[9]/div[2]/div/div/div/div/div[1]/ul/li[5]/ul/li[2]/ul/li[2]/button")).click();
	    //Thread.sleep(1000);
	    
	    
	    driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[9]/div[4]/a[1]")).click();
	    Thread.sleep(1000);
	    String curtain = null;
	    WebElement num = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[10]/div[2]/div/div[1]/div[2]/div[2]/a[1]/div/span[2]"));
	    if(num.isDisplayed())
	    {
	    	curtain=num.getText();
	    	System.out.println("No of curtain " + " " + curtain);
	    }
	    
	   int totalliningcost = Integer.parseInt(liningcost) * Integer.parseInt(curtain);
	   System.out.println("Total Lining Cost" + " "+ totalliningcost);
	   int totalcurtaincost = Integer.parseInt(curprice) + totalliningcost;
	   System.out.println("Total Curtain Price"+ " "+ totalcurtaincost);
	   
	      String totalprice= null;
	      WebElement totprice = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[10]/div[2]/div[1]/div/div[1]/div/div[2]/span/span"));
	      if(totprice.isDisplayed())
	      {
	    	  totalprice = totprice.getText();
	    	  System.out.println("Totalprice " + "   " + totalprice);
	      }
	      
	      String curtainname2 = null;
	      WebElement name2=driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[10]/div[2]/div[1]/div/div[2]/div[1]/div/ul/li/div/div[2]/div"));
	      if(name2.isDisplayed())
	      {
	    	  curtainname2 = name2.getText();
	    	  System.out.println("Curtain Name-2"+" "+curtainname2);
	      }
	      
	      if(curtainname.equals(curtainname2) && curtainname.equals(curtainname1))
	      {
	    	  System.out.println("Fabric Name is Same");
	      }
	      else
	      {
	    	  System.out.println("Fabric Name is Not Same");
	      }
	    	  
	      
	      
	    	      
	    driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[10]/div[2]/div[1]/div/div[1]/div/div[4]/button")).click();
	    Thread.sleep(1000);  
	    
	    
		
		
				

	}

}
