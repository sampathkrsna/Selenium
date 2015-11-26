import java.util.Arrays;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class sorttest {
	
	

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://root.test.customfurnish.com/curtains-online/");
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[1]/div/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[3]/div[2]/ul/li[1]/a/div/div[3]/button")).click();
		Thread.sleep(2000);
		
		
		/*for(int m=1; m<=4; m++)
		{
			String price=driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[5]/div[2]/div[2]/div/div[2]/ul/li["+ m +"]/div[2]/div/a[1]/div[2]/span/span")).getText();
			System.out.println(price.substring(4));	
			
		}*/
		
		String prices = null;
		String price = null;
		//Scanner price;
		int[] num;
		int n;
		for(n=1; n<=4; n++)
		{
	     	prices = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[5]/div[2]/div[2]/div/div[2]/ul/li["+ n +"]/div[2]/div/a[1]/div[2]/span/span")).getText();
			price=prices.substring(4);
			System.out.println(price);
						
		}
		
				
		num=new int[n];
		
			
		for(int i=0; i<n;i++)
		{
		  num[i]= Integer.parseInt(price);
		  
		}
		
		System.out.println("Before Sorting");
		for(int i=1;i<n;i++)
		{
			System.out.println(" "+ num[i]);
		}
		
		
		Arrays.sort(num);
		System.out.println(" Sorting - Ascending");
		System.out.println("\n");
		for(int i=1;i<n;i++)
		{
			System.out.println(" "+ num[i]);
		}
		
		System.out.println("Descending");
		for(int i=n-1;i>=1;i--)
		{
			System.out.println(" " + num[i]);
		}
		

	}

}