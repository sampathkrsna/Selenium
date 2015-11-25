package mobiletesting;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.android.AndroidDriver;





import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;


public class flipkarttest {

	AndroidDriver driver;
	
	@Test
	public void testFlipkart() throws MalformedURLException, InterruptedException
	{
		
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setCapability("deviceName","Sampath Krishna");
		capability.setCapability("platformName", "Android");
		capability.setCapability("platformVersion", "5.0");
		
		File file = new File("C:\\Users\\Sampath\\workspace\\mobiletesting\\apk\\app-release (2).apk");
		capability.setCapability("app", file.getAbsolutePath());
		
		driver = new AndroidDriver(new URL("http://192.168.3.56:4723/wd/hub"),capability);
	//	driver.findElementByAndroidUIAutomator(using)
		Thread.sleep(9000);
	/*	driver.findElement(By.id("com.flipkart.android:id/user_id")).click();
		driver.findElement(By.id("com.flipkart.android:id/user_id")).sendKeys("sampathkrsna@gmail.com");
		System.out.println("entering email");
		driver.findElement(By.id("com.flipkart.android:id/user_password")).click();
		driver.findElement(By.id("com.flipkart.android:id/user_password")).sendKeys("Pass12345");
		System.out.println("entering Password");
		driver.findElement(By.id("com.flipkart.android:id/btn_login")).click();
		System.out.println("clicling loging");
		Thread.sleep(7000);
		System.out.println("clicling loging");
		driver.findElement(By.id("com.flipkart.android:id/expandable_cat_cell")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("android:id/up")).click();
		Thread.sleep(2000);
		//driver.findElement(By.className("android.widget.LinearLayout")).click();
		//Thread.sleep(2000);*/
		driver.findElement(By.className("android.widget.ImageButton")).click(); 
	//	driver.tap(arg0, arg1, arg2);
		Thread.sleep(2000);
		//driver.tap(126, 432, 217,501 );
	//	driver.close();
	//	driver.quit();
	}

}
