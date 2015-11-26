//author = 'Sampath'

package kitchen;

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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DimensionsCheck {
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
		File file = new File(project_path + "/XSLT_Reports/"+systemDate+"/errors/dimensions_check_kitchen.txt");
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
	public void CloseBrowser(){
		writer.close();
		driver.quit();
	}

	@Test
	public void CheckDimensions() throws InterruptedException {
		driver.findElement(By.xpath("//*[@class='kitchen-start-design-button']")).click();
		Thread.sleep(3000);
		int kitchenstypes = driver.findElements(By.xpath("//*[@id='layout-selection']/div[2]/ul/li")).size();
		Thread.sleep(3000);
		for (int run = 1; run <= kitchenstypes; run++){
			driver.findElement(By.xpath("//*[@id='layout-selection']/div[2]/ul/li["+ run + "]/div/div[1]")).click();
			Thread.sleep(5000);
			int feet = selectAndGetFeet(run);
			if ( feet*12 != getTotalWidth()) {
				writer.println("The running length and the selected length are not equal for "+run+" - "+feet);
			}
		}
	}
	
	public static int selectAndGetFeet(int kitchentype) {
		int minFeet=0,maxFeet=0;
		if(kitchentype==1){
			minFeet=11;maxFeet=30;
		}else if(kitchentype==2){
			minFeet=4;maxFeet=30;
		}else if(kitchentype==3){
			minFeet=17;maxFeet=30;
		}else{
			minFeet=4;maxFeet=30;
		}
		int feet = randInt(minFeet, maxFeet);
		WebElement dropdown = driver.findElement(By.xpath("//*[@id='runningLengthFeet']"));
		new Select(dropdown).selectByValue(Integer.toString(feet));
		return feet;
	}
	
	public static int getTotalWidth() throws InterruptedException {
		Thread.sleep(5000);
		int sumofwidth = getWidth("added-cabinets-Base");
		int remain = Integer.parseInt(driver.findElement(By.xpath("//*[@id='remaining-length-Base']")).getText());
		return remain + sumofwidth;
	}
	
	public static int getWidth(String unitid) {
		int noofelements = driver.findElements(By.xpath("//*[@id='"+unitid+"']/tr")).size();
		int sumofwidth = 0,additinalfactor;
		for (int element = 1; element <= noofelements; element++) {
			String name = driver.findElement(By.xpath("//*[@id='"+unitid+"']/tr["+element+"]/td[1]")).getText();
			String w = driver.findElement(By.xpath("//*[@id='"+unitid+"']/tr["+element+"]/td[2]")).getText();
			String inches = String.valueOf(w.substring(0, w.length() - 1));
			if(name.contains("Corner Unit Straight")){
				additinalfactor=24;
			}else if(name.contains("Corner Unit L-Shaped")){
				additinalfactor=39;
			}else{
				additinalfactor=0;
			}
			int measurement = Integer.parseInt(inches);
			sumofwidth += additinalfactor+measurement;
//			writer.println(name + " - " + inches + " - " + sumofwidth);
		}
		return sumofwidth;
	}

	public static int randInt(int min,int max) {
	    Random rand = new Random();
	    return rand.nextInt((max - min) + 1) + min;
	}
}
