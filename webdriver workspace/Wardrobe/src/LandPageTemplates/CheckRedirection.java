package LandPageTemplates;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CheckRedirection {
	WebDriver driver;
	String project_path = System.getProperty("user.dir");
	static PrintWriter writer;

	@BeforeMethod
	@Parameters("url")
	public void beforeTest(String url) throws InterruptedException, IOException {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		String systemDate = dateFormat.format(date);
		File file = new File(project_path + "/XSLT_Reports/"+systemDate+"/errors/checktemplates.txt");
		writer = new PrintWriter(new FileWriter(file));
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
	
	@Test
	@Parameters("url")
	public void Checkredirection(String url) throws InterruptedException, IOException {
		List<WebElement> activeListElements = driver.findElements(By.className("gridImageHeight"));
		for(int i=1;i<=activeListElements.size();i++){
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,900)", ""); 
            Thread.sleep(2000);
            String path = "//*[@id='kmBody']/div[10]/div/ul/li["+i+"]/div/section[1]/a[1]/img";
			driver.findElement(By.xpath(path)).click();
			String title = driver.getCurrentUrl();
			if(!title.contains(url+"templates/?")){
				writer.println("breaking at " + i);
				
			}
			Thread.sleep(2000);
			driver.navigate().back();
			Thread.sleep(2000);
		}
	}

	@AfterMethod
	public void afterMethod() throws IOException {
		driver.quit();
		writer.close();
	}

}
