package LinksLandingPage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LinksLandingPage {
	WebDriver driver;
	static String message = "";
	String project_path = System.getProperty("user.dir");
	static PrintWriter writer;

	@BeforeMethod
	@Parameters("url")
	public void beforeMethod(String url) throws InterruptedException, IOException {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		String systemDate = dateFormat.format(date);
		File file = new File(project_path + "/XSLT_Reports/"+systemDate+"/errors/landingpage_links.txt");
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
	}

	

	@AfterMethod
	public void afterMethod()  throws IOException {
		driver.quit();
		writer.close();
	}

	@Test
	@Parameters("url")
	public void LandingPage(String url) throws InterruptedException,
			IOException {
		Thread.sleep(5000);
		List<WebElement> linksize = driver.findElements(By.tagName("a"));
		int linksCount = linksize.size();
		String[] links = new String[linksCount];
		int j=1;
		for (int i = 0; i < linksCount; i++) {
			try{
				if (linksize.get(i).getAttribute("href").contains(url)) {
					links[j] = linksize.get(i).getAttribute("href");
					j++;
				}
			}catch (Exception e) {}
		}
//		System.out.println("Total no of links Available: " + (j-1));
		for (int i = 1; i < j; i++) {
//			System.out.println(i + ") "+links[i]);
			driver.navigate().to(links[i]);
			Thread.sleep(3000);
			if(!driver.findElement(By.id("loginUser")).isDisplayed()){
				writer.println("Signin not displayed in " + links[i]);
			}
//			driver.navigate().back();
			Thread.sleep(3000);
		}
	}
}
