package wardrobe_products;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;



public class products_wardrobes {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//File phantomjs = Phanbedder.unpack(); //Phanbedder to the rescue!
	//	DesiredCapabilities dcaps = new DesiredCapabilities();
	//	dcaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "C://phantomjs.exe");
	//	WebDriver driver =new PhantomJSDriver(dcaps);
		//driver = webdriver.PhantomJS();
		WebDriver driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://root.test.kustommade.com/wardrobe-designs/");
	int b = driver.findElements(By.className("product-image" )).size();
     System.out.println(b);
		Thread.sleep(7000);
		System.out.println("sampath");
		for(int i=1;i<=51;i++)
		{
	String w=	driver.findElement(By.xpath(".//*[@id='productListItem']/div/div["+3*i+"]/div[1]/div/div[2]/section/img")).getAttribute("src");
		System.out.println(w);
		int color = driver.findElements(By.className("wardrobe-template-details" )).size();
		System.out.println(color);
		int number =  driver.findElements(By.className("change-external-finish-type" )).size();
		System.out.println(number);
		for(int j=5;j<=9;j++){
		
			driver.findElement(By.xpath(".//*[@id='scrollBar']/ul/li["+j+"]/img")).click();
			Thread.sleep(4000);
			String A = driver.findElement(By.xpath(".//*[@id='productListItem']/div/div["+3*i+"]/div[1]/div/div[2]/section/img")).getAttribute("src");
			System.out.println(A);
			if(w == A)
			{
				System.out.println("fail to change");
				
			}
			else
			{
				System.out.println("Changed succesfully");
			}
		}
		}
		driver.close();
		driver.quit();
	}

}
