package Signup;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Subscribe {
	static WebDriver driver;
	static String message = "";
	String project_path = System.getProperty("user.dir");

	@BeforeMethod
	@Parameters("url")
	public void beforeTest(String url) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", project_path
				+ "/libs/chromedriver");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("test-type");
		capabilities.setCapability("chrome.binary", project_path
				+ "/libs/chromedriver");
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new ChromeDriver(capabilities);
		driver.manage().window().maximize();
		driver.get(url);
		Thread.sleep(3000);
	}

	@AfterMethod
	public void afterTest() {
		driver.quit();
	}

	@Test
	@Parameters("url")
	public void subscribe(String url) throws InterruptedException {
		subscribeBottom( "test_user@gmail.com", url);
	}

	public static void subscribeBottom(String name, String url)
			throws InterruptedException {
		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView();",
				driver.findElement(By.xpath("//*[@id='emailId']")));
		driver.findElement(By.xpath("//*[@id='emailId']")).clear();
		driver.findElement(By.xpath("//*[@id='emailId']")).sendKeys(name);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='mailSubscribe']")).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions
				.elementToBeClickable(By
						.xpath("//*[@id='SubscribeModal']/a")));
		driver.findElement(By.xpath("//*[@id='SubscribeModal']/a")).click();
	}

}
