package ExtremeDimensions;

import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class ExtremeDimensions {
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
		File file = new File(project_path + "/XSLT_Reports/"+systemDate+"/errors/extremedimensions.txt");
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
	}

	@AfterMethod
	public void afterMethod() throws IOException {
		driver.quit();
		writer.close();
	}

	@Test
	@Parameters("url")
	public void extremeDimensions(String url) throws InterruptedException,IOException {
		dimensionsPage(url);
		designPage(url);
	}
	
	public void dimensionsPage(String url) throws InterruptedException,IOException {
		checkDimensionsPage(url,"doorSelection", "2","8", "6","0");
		checkDimensionsPage(url,"slideSelection", "3","4", "6","0");
		checkDimensionsPage(url,"externalDoorSelection", "2","8", "6","0");
		checkDimensionsPage(url,"doorSelection", "12","0", "7","0");
		checkDimensionsPage(url,"slideSelection", "12","0", "7","0");
		checkDimensionsPage(url,"externalDoorSelection", "12","0", "7","0");
	}
	
	public void checkDimensionsPage(String url,String doortype, String widthFeet,String widthInch, String heightFeet,String heightInch) throws InterruptedException, IOException{
		try{
			Thread.sleep(5000);
			driver.get(url+"wardrobe/dimensions");
			Thread.sleep(1000);
			new Select(driver.findElement(By.xpath("//*[@class='width-feet dimension-dropdown']"))).selectByValue(widthFeet);
			new Select(driver.findElement(By.xpath("//*[@class='width-inch dimension-dropdown']"))).selectByValue(widthInch);
			Thread.sleep(2000);
			new Select(driver.findElement(By.xpath("//*[@class='height-feet dimension-dropdown']"))).selectByValue(heightFeet);
			new Select(driver.findElement(By.xpath("//*[@class='height-inch dimension-dropdown']"))).selectByValue(heightInch);
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("a.button.Proceed")).click();
			new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id(doortype)));
			Thread.sleep(4000);
			driver.findElement(By.xpath("//*[@id='"+doortype+"']/div/div[1]/button")).click();
			Thread.sleep(60000);
			checkErrorsDimensionsPage(doortype, widthFeet, widthInch, heightFeet, heightInch);
		}catch (Exception e) {
			writer.println("Error in "+doortype.substring(13)+" doortype for width : "+widthFeet+"."+widthInch+", height : "+heightFeet+"."+heightInch);
			try{
				driver.switchTo().alert().accept();
			}catch (Exception exc) {}
		}
	}
	
	public void checkErrorsDimensionsPage(String doortype, String widthFeet,String widthInch, String heightFeet,String heightInch) throws InterruptedException, IOException{
		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id("internalDesignMode")));
		if(checkPrice()){
			driver.findElement(By.id("internalDesignMode")).click();
			Thread.sleep(5000);
			new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='nextStep wardrobe-studio-next-nav state-internal']")));
			if(checkPrice()){
				driver.findElement(By.xpath("//*[@class='nextStep wardrobe-studio-next-nav state-internal']")).click();
				Thread.sleep(10000);
			}
			else {
				writer.println("Price for "+doortype.substring(13)+" doortype interiors for width : "+widthFeet+"."+widthInch+", height : "+heightFeet+"."+heightInch+" is 0");
			}
		}
		else {
			writer.println("Price for "+doortype.substring(13)+" doortype interiors for width : "+widthFeet+"."+widthInch+", height : "+heightFeet+"."+heightInch+" is 0");
		}
	}
	
	public void designPage(String url) throws InterruptedException,IOException {
		checkDesignPage(url,"doorSelection", "4","0", "7","0");
		checkTopToolBarTraditionalDoors();
		checkDesignPage(url,"slideSelection", "4","0", "7","0");
		checkTopToolBarSlidingDoors();
		checkDesignPage(url,"externalDoorSelection", "4","0", "7","0");
		checkTopToolBarExternalDoors();
	}
	
	public void checkDesignPage(String url,String doortype, String widthFeet,String widthInch, String heightFeet,String heightInch) throws InterruptedException, IOException{
		try{
			Thread.sleep(5000);
			driver.get(url+"wardrobe/dimensions");
			try{
				driver.switchTo().alert().accept();
			}catch (Exception exc) {}
			Thread.sleep(2000);
			new Select(driver.findElement(By.xpath("//*[@class='width-feet dimension-dropdown']"))).selectByValue(widthFeet);
			new Select(driver.findElement(By.xpath("//*[@class='width-inch dimension-dropdown']"))).selectByValue(widthInch);
			Thread.sleep(2000);
			new Select(driver.findElement(By.xpath("//*[@class='height-feet dimension-dropdown']"))).selectByValue(heightFeet);
			new Select(driver.findElement(By.xpath("//*[@class='height-inch dimension-dropdown']"))).selectByValue(heightInch);
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("a.button.Proceed")).click();
			new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id(doortype)));
			Thread.sleep(4000);
			driver.findElement(By.xpath("//*[@id='"+doortype+"']/div/div[1]/button")).click();
			Thread.sleep(25000);
			new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id("internalDesignMode")));
			driver.findElement(By.id("internalDesignMode")).click();
			Thread.sleep(5000);
			new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='nextStep wardrobe-studio-next-nav state-internal']")));
		}catch (Exception e) {
			writer.println("Error in "+doortype.substring(13)+" doortype for width : "+widthFeet+"."+widthInch+", height : "+heightFeet+"."+heightInch);
			try{
				driver.switchTo().alert().accept();
			}catch (Exception exc) {}
		}
	}
	
	public void checkTopToolBarTraditionalDoors() throws InterruptedException, IOException{
		deleteSections();
		updateDoorWidth("traditional", "15.75",1);
		updateDoorWidth("traditional", "18.70",1);
		updateDoorWidth("traditional", "31.50",2);
		updateDoorWidth("traditional", "37.40",2);
	}
	
	public void checkTopToolBarSlidingDoors() throws InterruptedException, IOException{
		deleteSections();
		updateDoorWidth("sliding", "11.82",1);
		updateDoorWidth("sliding", "29.52",1);
	}
	
	public void checkTopToolBarExternalDoors() throws InterruptedException, IOException{
		deleteSections();
		updateDoorWidth("traditional", "15.75",1);
		updateDoorWidth("traditional", "18.70",1);
		updateDoorWidth("traditional", "31.50",2);
		updateDoorWidth("traditional", "37.40",2);
	}
	
	public void deleteSections() throws InterruptedException{
		List<WebElement> sections = driver.findElements(By.xpath("//*[@id='perSection']/div"));
		for (int i=sections.size();i>1;i--) {
			driver.findElement(By.xpath("//*[@id='perSection']/div["+i+"]")).click();
			driver.findElement(By.xpath("//*[@id='options-menu']/ul/li[4]/a/img")).click();
			Thread.sleep(7000);
		}
	}
	
	public void updateDoorWidth(String doortype,String width,int noofdoors) throws InterruptedException, IOException{
		driver.findElement(By.xpath("//*[@id='perSection']/div[1]/a")).click();
		Thread.sleep(2000);
		String door = driver.findElement(By.xpath("//*[@class='switch medium round wardrobe-door-type-radio wardrobe-interior-door-selection']")).getText();
		if(noofdoors == 2 && door.contains("Single Door")){
			driver.findElement(By.xpath("//*[@class='switch medium round wardrobe-door-type-radio wardrobe-interior-door-selection']")).click();
			Thread.sleep(2000);
		}
		driver.findElement(By.xpath("//*[@id='wardrobeTraditionalWidthUpdate']/div["+(noofdoors+1)+"]/input")).clear();
		driver.findElement(By.xpath("//*[@id='wardrobeTraditionalWidthUpdate']/div["+(noofdoors+1)+"]/input")).sendKeys(width);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='wardrobeTraditionalWidthUpdate']/div[4]/button")).click();
		Thread.sleep(5000);
		if(!checkPrice()){
			writer.println("Price at "+doortype+" doortype updating width of "+width+" is 0");
		}
		Thread.sleep(3000);
	}
	
	public boolean checkPrice(){
		if(convetorToInt(driver.findElement(By.id("price")).getText(), 0)!=0){
			return true;
		}
		return false;
	}
	
	public static int convetorToInt(String strValue, int intValue) {
		for (int i=0;i<strValue.length();i++){
			if(Character.isDigit(strValue.charAt(i))){
				intValue = intValue*10 + Character.getNumericValue(strValue.charAt(i));
			}
		}
		if (strValue.contains("-"))
			return -1*intValue;
		return intValue;
	}
}