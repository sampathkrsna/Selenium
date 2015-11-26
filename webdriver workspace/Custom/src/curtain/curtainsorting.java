package curtain;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.Arrays;
import java.util.Collection;

public class curtainsorting {
	

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriver driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://root.test.customfurnish.com/curtains-online/");
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[2]/div/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[4]/div[2]/ul/li[1]/a/div/div[3]/button")).click();
		Thread.sleep(1000);
		Select dropdown=new Select(driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[5]/div[2]/div[2]/div/div[1]/div[4]/div/select")));
		dropdown.selectByVisibleText("PRICE: LOW TO HIGH");
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[5]/div[2]/div[2]/div/div[1]/div[5]/div[2]/div[2]/div/div")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[5]/div[2]/div[2]/div/div[1]/div[5]/div[2]/div[2]/div/div/ul/li[3]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[5]/div[2]/div[2]/div/div[1]/div[5]/div[2]/div[2]/div/div")).click();
		Thread.sleep(1000);
		
		 
		
		String a = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[5]/div[2]/div[2]/div/div[2]/ul/li[8]/div[2]/div/a[1]/div[2]/span/span")).getText();
		System.out.println(a.substring(4));
		String a1 = a.substring(4);
		String b = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[5]/div[2]/div[2]/div/div[2]/ul/li[6]/div[2]/div/a[1]/div[2]/span/span")).getText();
		System.out.println(b.substring(4));
		String b1 = b.substring(4);
		String c = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[5]/div[2]/div[2]/div/div[2]/ul/li[7]/div[2]/div/a[1]/div[2]/span/span")).getText();
		System.out.println(c.substring(4));
		String c1 =c.substring(4);
		int a2=Integer.parseInt(a1);
		int b2=Integer.parseInt(b1);
		int c2=Integer.parseInt(c1);
		
		System.out.println("The Descending Order values are");
		
		if ((a2 < b2 && a2 < c2))
        {
            if(b2 < c2)
            {
                System.out.print(c2 + " " + b2 + " " + a2);
            }
            else
                System.out.print(b2 + " " + c2 + " " + a2);
        }
        else if ((b2 < a2 && b2 < c2))
        {
            if(a2 < c2)
            {
                System.out.print(c2 + " " + a2 + " " + a2);
            }
            else
                {
                System.out.print(a2 + " " + c2 + " " + b2);
                }
        }
        else if ((c2 < a2 && c2 < b2))
        {
            if(a2 < b2)
            {
                System.out.print(b2 + " " + a2 + " " + c2);
            }
            else
                System.out.print(a2 + " " + b2 + " " + c2);
        }
        else
        {
            System.out.println(" check ");
        }
		
		
		
		
		
		
		
		/*for( int m=1;m<=9;m++)
		{
			String price=driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[5]/div[2]/div[2]/div/div[2]/ul/li["+m+"]/div[2]/div/a[1]/div[2]/span/span")).getText();
			String prices = price.substring(4);
			System.out.println(prices);			
							
		}*/
	
			

	}


		
}
