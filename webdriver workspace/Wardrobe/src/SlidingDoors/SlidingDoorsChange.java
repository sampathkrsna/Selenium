package SlidingDoors;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SlidingDoorsChange {
	static WebDriver driver;
	static String message = "";
	String project_path = System.getProperty("user.dir");
	static PrintWriter writer;
	
	@BeforeMethod
	@Parameters("url")
	public void beforeTest(String url) throws InterruptedException, IOException {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		String systemDate = dateFormat.format(date);
		File file = new File(project_path + "/XSLT_Reports/"+systemDate+"/errors/slidingdoorschange.txt");
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
		driver.get(url+"design/closets/step1/");
		Thread.sleep(5000);
	}

	@AfterMethod
	public void afterMethod()  throws IOException {
		driver.quit();
		writer.close();
	}

	@Test
	@Parameters("url")
	public void slidingdoorchange(String url) throws InterruptedException,IOException {
		selectDimensions();
		selectDoorType();
		verifyDoors();
	}

	public static void selectDoorType()	throws InterruptedException {
		driver.findElement(By.cssSelector("a.button.Proceed")).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='slideSelection']/div/div[1]/button")));
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[@id='slideSelection']/div/div[1]/button")).click();
		Thread.sleep(60000);
	}
	
	public static void selectDimensions() throws InterruptedException {
		int widthFeet = randInt(3, 10);
		new Select(driver.findElement(By.xpath("//*[@class='width-feet dimension-dropdown']"))).selectByValue(Integer.toString(widthFeet));
		if(widthFeet==3){
			new Select(driver.findElement(By.xpath("//*[@class='width-inch dimension-dropdown']"))).selectByValue(Integer.toString(randInt(4, 11)));
		}
		else if (widthFeet>=4 && widthFeet<=11){
			new Select(driver.findElement(By.xpath("//*[@class='width-inch dimension-dropdown']"))).selectByValue(Integer.toString(randInt(0, 11)));
		}
		Thread.sleep(2000);
		int heightFeet = randInt(6, 7);
		new Select(driver.findElement(By.xpath("//*[@class='height-feet dimension-dropdown']"))).selectByValue(Integer.toString(heightFeet));
		if(heightFeet!=7){
			new Select(driver.findElement(By.xpath("//*[@class='height-inch dimension-dropdown']"))).selectByValue(Integer.toString(randInt(0, 11)));
		}
		Thread.sleep(1000);
	}
	
	public static void verifyDoors() throws InterruptedException {
		new Select(driver.findElement(By.xpath("//*[@id='wardrobeSlideDoorSelector']"))).selectByVisibleText("Contemporary");
		Thread.sleep(5000);
		if(!driver.findElement(By.xpath("//*[@id='wardrobeSlidingNoOfDoorList']")).isDisplayed()){
			driver.findElement(By.xpath("//*[@id='no-doors']/a/div")).click();
		}
		Thread.sleep(5000);
		for(int i=1;i<4;i++){
			if(driver.findElement(By.xpath("//*[@id='wardrobeSlidingNoOfDoorList']/li["+i+"]")).isDisplayed()){
				driver.findElement(By.xpath("//*[@id='wardrobeSlidingNoOfDoorList']/li["+i+"]")).click();
				Thread.sleep(5000);
				if(convetorToInt(driver.findElement(By.xpath("//*[@id='price']")).getText())==0){
					writer.println("Price is zero when no of doors changed to "+(i+1)+" for wardrobe "+
						driver.findElement(By.xpath("//*[@id='#body-wrapper']/div[7]/div[1]/div[1]/div/div[2]/div/div/section[3]/div/div[2]/span[1]")).getText()+
						driver.findElement(By.xpath("//*[@id='#body-wrapper']/div[7]/div[1]/div[1]/div/div[2]/div/div/section[3]/div/div[2]/span[2]")).getText()+
						driver.findElement(By.xpath("//*[@id='#body-wrapper']/div[7]/div[1]/div[1]/div/div[2]/div/div/section[3]/div/div[2]/span[3]")).getText());
				}
			}
		}
	}
	
	public static int convetorToInt(String strValue) {
		int intValue=0;
		for (int i=0;i<strValue.length();i++){
			if(Character.isDigit(strValue.charAt(i))){
				intValue = intValue*10 + Character.getNumericValue(strValue.charAt(i));
			}
		}
		if (strValue.contains("-"))
			return -1*intValue;
		return intValue;
	}
	
	public static int randInt(int min, int max) {
	    Random rand = new Random();
	    return rand.nextInt((max - min) + 1) + min;
	}
}
