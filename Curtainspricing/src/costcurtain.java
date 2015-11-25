import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


public class costcurtain {

	public static void main(String[] args) throws InterruptedException {
	
		WebDriver driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://www.customfurnish.com/curtains-online/");
		Thread.sleep(5000);
		driver.findElement(By.xpath(".//*[@id='curtainBannerImage']/div/a")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath(".//*[@id='curtainStyle']/div[2]/ul/li[1]/a/div/div[3]/button")).click();
		Thread.sleep(5000);
		int a = driver.findElements(By.xpath(".//*[@id='fabricCurtainOptionValuesWithPagination']/ul/li")).size();
		System.out.println(a);
		
		for (int i=1;i<=a;i++)
		{
			driver.findElement(By.xpath(".//*[@id='fabricCurtainOptionValuesWithPagination']/ul/li["+i+"]/div[1]/a[1]/img")).click();
			WebElement a1= driver.findElement(By.xpath(".//*[@id='fabricCurtainOptionValuesWithPagination']/ul/li["+i+"]/div[2]/div/a[1]/div[2]/span/span"));
			System.out.println("clicking on and value is"+i+a1);
			//price caliculation code 
		}
		
		
		

	}

}
