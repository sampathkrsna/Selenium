package CheckTemplates;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CheckTemplates {
	static WebDriver driver;
	static String message = "";
	String project_path = System.getProperty("user.dir");
	static PrintWriter writer;

	@BeforeMethod
	@Parameters("url")
	public void beforeMethod(String url) throws InterruptedException, IOException {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		String systemDate = dateFormat.format(date);
		File file = new File(project_path + "/XSLT_Reports/"+systemDate+"/errors/templates.txt");
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
		driver.get(url+"wardrobe/templates");
		Thread.sleep(7000);
	}

	@AfterMethod
	public void afterMethod()  throws IOException {
		driver.quit();
		writer.close();
	}

	@Test
	@Parameters("url")	
	public static void checkInformation() throws InterruptedException {
		travelToEnd();
		getInfo();
	}
	
	public static void travelToEnd() throws InterruptedException {
		while(!driver.findElement(By.linkText("Design from Scratch & Buy")).isDisplayed()){
			Actions actions = new Actions(driver);
			actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
			Thread.sleep(2000);
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("scroll(50, 0)");
		}
	}
	
	public static void getInfo() {
		int nooftemplates = driver.findElements(By.xpath("//*[@id='scroll-products']/ul/li")).size();
		for(int i=1;i<=nooftemplates;i++){
			String info = driver.findElement(By.xpath("//*[@id='scroll-products']/ul/li["+i+"]/div/div/section[2]/div[2]/div[2]/p")).getText();
			int widthindex = info.indexOf('"');
			if(convetorToInt(info.substring(0, widthindex))==0){
				writer.println("width is 0 for wardrobe of index "+ i);
			}
			int heightindex = info.indexOf('"', widthindex+1);
			if(convetorToInt(info.substring(widthindex, heightindex))==0){
				writer.println("height is 0 for wardrobe of index "+ i);
			}
			int depthindex = info.indexOf('"', heightindex+1);
			if(convetorToInt(info.substring(heightindex, depthindex))==0){
				writer.println("depth is 0 for wardrobe of index "+ i);
			}
		}
	}
	
	public static int convetorToInt(String strValue) {
		int intValue=0;
		for (int i=0;i<strValue.length();i++){
			if((strValue.charAt(i))=='.'){
				break;
			}
			else if(Character.isDigit(strValue.charAt(i))){
				intValue = intValue*10 + Character.getNumericValue(strValue.charAt(i));
			}
		}
		if (strValue.contains("-"))
			return -1*intValue;
		return intValue;
	}

	@Test
	@Parameters("url")
	public static void checkRedirection(String url) throws InterruptedException, IOException {
		try{
			if(driver.findElement(By.xpath("//*[@class='wardrobe-image-section']")).isDisplayed()){
				int noofTemplates = driver.findElements(By.xpath("//*[@class='wardrobe-image-section']")).size();
				String templateId = "";
				for (int templateNum=1 ; templateNum <= noofTemplates ; templateNum++) {
					templateId = driver.findElement(By.xpath("//*[@id='scroll-products']/ul/li["+templateNum+"]/div/div/section[1]/div/ul/li[1]/a[2]")).getAttribute("data-caption");
					hoverClickCustomize(url,templateNum,templateId);
					hoverClickDetails(url,templateNum,templateId);
				}
				int checks = randInt(noofTemplates),itemToCheck = 0;
				for (int i=1 ; i <= checks ; i++) {
					itemToCheck = randInt(noofTemplates);
					templateId = driver.findElement(By.xpath("//*[@id='scroll-products']/ul/li["+itemToCheck+"]/div/div/section[1]/div/ul/li[1]/a[2]")).getAttribute("data-caption");
					hoverClickCustomize(url,itemToCheck,templateId);
					hoverClickDetails(url,itemToCheck,templateId);
				}
			}
		}catch(Exception e){
			writer.println("No templates in templates page.");
		}
	}

	public static void hoverClickCustomize(String url,int templateNum,String templateId) throws InterruptedException, IOException{
		Actions builder = new Actions(driver); 
		Actions hoverOverRegistrar = builder.moveToElement(driver.findElement(By.xpath("//*[@id='scroll-products']/ul/li["+templateNum+"]/div/div/section[1]/a/img")));
		hoverOverRegistrar.perform();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='scroll-products']/ul/li["+templateNum+"]/div/div/section[1]/div/ul/li[1]/a[2]")).click();
		Thread.sleep(15000);
		if(!(url+"wardrobe/design/").equals((driver.getCurrentUrl()))){
			writer.println("Page is not redirecting to design page for "+templateId);
			
		}
		driver.navigate().back();
		try{
			Thread.sleep(2000);
			driver.switchTo().alert().accept();
		}catch (Exception e) {}
		Thread.sleep(3000);
	}

	public static void hoverClickDetails(String url,int templateNum,String templateId) throws InterruptedException, IOException{
		Actions builder = new Actions(driver); 
		Actions hoverOverRegistrar = builder.moveToElement(driver.findElement(By.xpath("//*[@id='scroll-products']/ul/li["+templateNum+"]/div/div/section[1]/a/img")));
		hoverOverRegistrar.perform();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='scroll-products']/ul/li["+templateNum+"]/div/div/section[1]/div/ul/li[3]/a")).click();
		Thread.sleep(5000);
		if(!(url+"wardrobe/order/").equals((driver.getCurrentUrl()))){
			writer.println("Page is not redirecting to order page for "+templateId);
			
		}
		driver.navigate().back();
		Thread.sleep(5000);
	}
	
	public static int randInt(int max) {
		int min=1;
	    Random rand = new Random();
	    return rand.nextInt((max - min) + 1) + min;
	}
}