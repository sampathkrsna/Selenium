package dimensions;

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

public class SlidingDoors {
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
		File file = new File(project_path + "/XSLT_Reports/"+systemDate+"/errors/sliding.txt");
		writer = new PrintWriter(new FileWriter(file));
		System.setProperty("webdriver.chrome.driver", project_path+ "/libs/chromedriver");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("test-type");
		capabilities.setCapability("chrome.binary", project_path+ "/libs/chromedriver");
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
	public void fullpanel(String url) throws InterruptedException, IOException {
		int min = 41, max = 144;
		int [] array = new int[max-min+1];
		createArray(array, min, max);
		shuffleArray(array);
		checkSlidingDoors(array, url,(max-min+1));
		checkedFlow(array);
	}
	
	public void createArray(int[] array,int min,int max){
		for(int i = min,j=0 ; i <= max ; i++,j++){
			array[j] = i;
		}
	}
	
	public static void shuffleArray(int[] array)
	{
	    Random rnd = new Random();
	    for (int i = array.length - 1; i > 0; i--)
	    {
	      int index = rnd.nextInt(i + 1);
	      int a = array[index];
	      array[index] = array[i];
	      array[i] = a;
	    }
	}
	
	public void checkSlidingDoors(int[] array, String url,int iterations) throws InterruptedException, IOException{
		for(int i=0 ; i<iterations ; i++){
		    double width = Math.round(((float)array[i] / 12) * 10) / 10.0;
		    int width_feet = (int)(width);
		    int width_inch = (int)(Math.round((width - width_feet)*10));
		    checkDimensionsPage(url, "slideSelection", Integer.toString(width_feet),  Integer.toString(width_inch), "6", "5");
		}
	}
	
	public void checkDimensionsPage(String url,String doortype, String widthFeet,String widthInch, String heightFeet,String heightInch) throws InterruptedException, IOException{
		try{
			Thread.sleep(5000);
			new Select(driver.findElement(By.xpath("//*[@class='width-feet dimension-dropdown']"))).selectByValue(widthFeet);
			new Select(driver.findElement(By.xpath("//*[@class='width-inch dimension-dropdown']"))).selectByValue(widthInch);
			Thread.sleep(2000);
			new Select(driver.findElement(By.xpath("//*[@class='height-feet dimension-dropdown']"))).selectByValue(heightFeet);
			new Select(driver.findElement(By.xpath("//*[@class='height-inch dimension-dropdown']"))).selectByValue(heightInch);
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("a.button.Proceed")).click();
			new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='"+doortype+"']/div/div[1]/button")));
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id='"+doortype+"']/div/div[1]/button")).click();
			Thread.sleep(15000);
			driver.get(url+"wardrobe/dimensions");
			try{
				driver.switchTo().alert().accept();
			}catch (Exception exc) {}
		}catch (Exception e) {
			writer.println("Error in "+doortype.substring(13)+" doortype for width : "+widthFeet+"."+widthInch+", height : "+heightFeet+"."+heightInch);
			try{
				driver.get(url+"design/closets/step1/");
				driver.switchTo().alert().accept(); 
			}catch (Exception exc) {}
		}
	}
	
	public void checkedFlow(int[] array) throws IOException{
		writer.println("******************************************************");
		writer.println("Flow to test");
		writer.println("******************************************************");
		writer.write(Double.toString(Math.round(((float)array[0] / 12) * 10) / 10.0));
		for(int i = 1 ; i<array.length ; i++){
			writer.write(" - ");
			writer.write(Double.toString(Math.round(((float)array[i] / 12) * 10) / 10.0));
		}
	}
}
