package SpecialCase;

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

public class SpecialCase {
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
		File file = new File(project_path + "/XSLT_Reports/"+systemDate+"/errors/specialcase.txt");
		writer = new PrintWriter(new FileWriter(file));
		System.setProperty("webdriver.chrome.driver", project_path
				+ "/libs/chromedriver");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("test-type");
		capabilities.setCapability("chrome.binary", project_path
				+ "/libs/chromedriver.exe");
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new ChromeDriver(capabilities);
		driver.manage().window().maximize();
		driver.get(url+"wardrobe/dimensions");
		Thread.sleep(5000);
	}

	@AfterMethod
	public void afterMethod() throws IOException {
		driver.quit();
		writer.close();
	}

	@Test
	@Parameters("url")
	public void specialCase(String url) throws InterruptedException,IOException {
//		checkSpecialCase1(url);
//		checkSpecialCase2(url);
		checkRandom(url);
	}
	
	public void checkSpecialCase1(String url) throws InterruptedException, IOException{
		checkDimensionsPage(url,"doorSelection", "10","0", "6","5","special");
		checkDimensionsPage(url,"doorSelection", "8","0", "6","5","special");
		checkDimensionsPage(url,"slideSelection", "10","0", "6","5","special");
		checkDimensionsPage(url,"slideSelection", "8","0", "6","5","special");
		checkDimensionsPage(url,"externalDoorSelection", "10","0", "6","5","special");
		checkDimensionsPage(url,"externalDoorSelection", "8","0", "6","5","special");
	}
	
	public void checkSpecialCase2(String url) throws InterruptedException, IOException{
		checkDimensionsPage(url,"doorSelection", "10","0", "6","5","special");
		checkDimensionsPage(url,"doorSelection", "2", "8","6","5","special");
		checkDimensionsPage(url,"slideSelection", "10","0", "6","5","special");
		checkDimensionsPage(url,"slideSelection", "3", "4","6","5","special");
		checkDimensionsPage(url,"externalDoorSelection", "10","0", "6","5","special");
		checkDimensionsPage(url,"externalDoorSelection", "2","8", "6","5","special");
	}
	
	public void checkRandom(String url) throws InterruptedException, IOException{
		int testcases = 10;
		int[][] parameters = new int [testcases][5];
		getParameters(parameters,testcases);
		String doortype;
		for (int row = 0; row < testcases; row++) {
			if(parameters[row][0] == 1){
				doortype = "doorSelection";
			}
			else if(parameters[row][0] == 2){
				doortype = "slideSelection";
			}
			else {
				doortype = "externalDoorSelection";
			}
			checkDimensionsPage(url, doortype, Integer.toString(parameters[row][1]), Integer.toString(parameters[row][2]), Integer.toString(parameters[row][3]), Integer.toString(parameters[row][4]),"random");
		}
		testingDoneOn(parameters,testcases);
	}
	
	public void checkDimensionsPage(String url,String doortype, String widthFeet,String widthInch, String heightFeet,String heightInch,String casetype) throws InterruptedException, IOException{
		try{
			Thread.sleep(5000);
			new Select(driver.findElement(By.xpath("//*[@class='width-feet dimension-dropdown']"))).selectByValue(widthFeet);
			new Select(driver.findElement(By.xpath("//*[@class='width-inch dimension-dropdown']"))).selectByValue(widthInch);
			Thread.sleep(2000);
			new Select(driver.findElement(By.xpath("//*[@class='height-feet dimension-dropdown']"))).selectByValue(heightFeet);
			new Select(driver.findElement(By.xpath("//*[@class='height-inch dimension-dropdown']"))).selectByValue(heightInch);
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("a.button.Proceed")).click();
			new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='"+doortype+"']/div/div[1]/button")));
			Thread.sleep(4000);
			driver.findElement(By.xpath("//*[@id='"+doortype+"']/div/div[1]/button")).click();
			Thread.sleep(20000);
			driver.get(url+"wardrobe/dimensions/");
			try{
				driver.switchTo().alert().accept();
			}catch (Exception exc) {}
		}catch (Exception e) {
			writer.println("Error in "+doortype.substring(13)+" doortype for width : "+widthFeet+"."+widthInch+", height : "+heightFeet+"."+heightInch+" in "+casetype+" case.");
			
			try{
				driver.switchTo().alert().accept(); 
				driver.get(url+"wardrobe/dimensions");
			}catch (Exception exc) {}
		}
	}
	
	public void getParameters(int [][] parameters,int rows){
		for(int row=0 ; row < rows ; row++){
			parameters[row][0] = randInt(1, 3);
			getWidthParameter(parameters,row);
			getHeightParameter(parameters,row);
		}
	}
	
	public void getWidthParameter(int [][] parameters,int row){
		if(parameters[row][0]!=2){
			parameters[row][1] = randInt(2,10);
			if(parameters[row][1]==2){
				parameters[row][2] = randInt(8,10);
			}
			else if(parameters[row][1]==10){
					parameters[row][2] = 0;
			}
			else {
				parameters[row][2] = randInt(0,10);
			}
		}
		else{
			parameters[row][1] = randInt(3,10);
			if(parameters[row][1]==3){
				parameters[row][2] = randInt(4,10);
			}
			else {
				parameters[row][2] = randInt(0,10);
			}
		}
	}
	
	public void getHeightParameter(int [][] parameters,int row){
		parameters[row][3] = randInt(6,7);
		if(parameters[row][3]==7){
			parameters[row][4] = 0;
		}
		else {
			parameters[row][4] = randInt(0,10);
		}
	}
	
	public void testingDoneOn(int [][] parameters, int rows) throws IOException{
		writer.println("Testing Done Over");
		
		for (int i = 0; i < rows; i++) {
			writer.println(parameters[i][0]+" - "+parameters[i][1]+"."+parameters[i][2]+" - "+parameters[i][3]+"."+parameters[i][4]);
			
		}
	}
	
	public static int randInt(int min, int max) {
	    Random rand = new Random();
	    return rand.nextInt((max - min) + 1) + min;
	}
}
