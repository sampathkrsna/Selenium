package SaveDesign;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SaveDesign {
	static WebDriver driver;
	String project_path = System.getProperty("user.dir");
	static PrintWriter writer;
	
	@BeforeTest
	public void beforeTest() throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		String systemDate = dateFormat.format(date);
		File file = new File(project_path + "/XSLT_Reports/"+systemDate+"/errors/savedesign.txt");
		writer = new PrintWriter(new FileWriter(file));
	}

	@BeforeMethod
	@Parameters("url")
	public void beforeMethod(String url) throws InterruptedException, IOException {
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
		driver.get(url+"wardrobe/dimensions");
		Thread.sleep(5000);
	}

	@AfterMethod
	public void afterMethod(){
		driver.quit();
	}
	
	@AfterClass
	public void afterTest() throws IOException{
		writer.close();
	}
	
	@Test
	@Parameters("url")
	public void Interiors(String url) throws InterruptedException, IOException {
		Navigate("Interiors");
		AfterNavigation(url,"Interiors");
	}
	
	@Test
	@Parameters("url")
	public void Exteriors(String url) throws InterruptedException, IOException {
		Navigate("Exteriors");
		AfterNavigation(url,"Exteriors");
	}
	
	public static void Navigate(String page)
			throws InterruptedException, IOException {
		int widthFeet = randInt(2, 12);
		new Select(driver.findElement(By.xpath("//*[@class='width-feet dimension-dropdown']"))).selectByValue(Integer.toString(widthFeet));
		if(widthFeet==2){
			new Select(driver.findElement(By.xpath("//*[@class='width-inch dimension-dropdown']"))).selectByValue(Integer.toString(randInt(8, 11)));
		}
		else if (widthFeet>=3 && widthFeet<=11){
			new Select(driver.findElement(By.xpath("//*[@class='width-inch dimension-dropdown']"))).selectByValue(Integer.toString(randInt(0, 11)));
		}
		Thread.sleep(2000);
		int heightFeet = randInt(6, 7);
		new Select(driver.findElement(By.xpath("//*[@class='height-feet dimension-dropdown']"))).selectByValue(Integer.toString(heightFeet));
		if(heightFeet!=7){
			new Select(driver.findElement(By.xpath("//*[@class='height-inch dimension-dropdown']"))).selectByValue(Integer.toString(randInt(0, 11)));
		}
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("a.button.Proceed")).click();		
		WebDriverWait wait1 = new WebDriverWait(driver, 30);
		wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='doorSelection']/div/div[1]/button")));
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[@id='doorSelection']/div/div[1]/button")).click();
	}
	
	public static void AfterNavigation(String url,String page) throws InterruptedException, IOException{
		Thread.sleep(60000);
		if(page.equals("Interiors")){
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("internalDesignMode")));
			driver.findElement(By.id("internalDesignMode")).click();
			Thread.sleep(5000);
		}
		int price = convetor(driver.findElement(By.xpath("//*[@id='price']")).getText(), 0);
		driver.findElement(By.className("flaticon-diskette")).click();
		Signin(page);
		Savedesign(page,price);
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL).sendKeys(Keys.HOME).perform();
		goToMyDesigns();
		try{
    		driver.switchTo().alert().accept();
		}catch (Exception e) {
			ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(tabs2.get(1));
		}
		CheckMyDesignPage(url,page,price);
	}
	
	public static void Signin(String page) throws InterruptedException, IOException{
		try{
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id='login-email-save']")).clear();
			driver.findElement(By.xpath("//*[@id='login-email-save']")).sendKeys("test_user@gmail.com");
			driver.findElement(By.xpath("//*[@id='login-password-save']")).clear();
			driver.findElement(By.xpath("//*[@id='login-password-save']")).sendKeys("test_user");
			driver.findElement(By.xpath("//*[@id='loginSave']")).click();
		}catch (Exception e) {
			writer.println("Exception in Signin " +page);
			
		}
	}
	
	public static void Savedesign(String page,int price) throws InterruptedException, IOException{
		Thread.sleep(10000);
		if(price != convetor(driver.findElement(By.xpath("//*[@id='designPrice']")).getText(), 0)){
			writer.println("Price in model window is different in " +page);
		}
		DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy, h:mm");
		Date date = new Date();
		String systemDate = dateFormat.format(date);
		String designDate = (driver.findElement(By.id("designDate")).getText()).substring(0, 16);
		if(!designDate.contains(systemDate)){
			writer.println("Time is displayed wrong in save designs model " +page);
			writer.println(designDate +" - "+systemDate);
		}
		driver.findElement(By.xpath("//*[@id='designName']")).clear();
		driver.findElement(By.xpath("//*[@id='designName']")).sendKeys("test");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='saveInModal']")).click();
	}
	
	public static void CheckMyDesignPage(String url,String page,int price) throws InterruptedException, IOException{
		Thread.sleep(5000);
		String text = driver.findElement(By.className("myDesignName")).getText();
		if(!text.equals("test")){
			writer.println("Design is not saving " +page);
			
		}
		else{
			String PageDate = driver.findElement(By.xpath("//*[@id='myDesignDate']")).getText();
			DateFormat dateFormat = new SimpleDateFormat("dd'th' MMMM yyyy");
			Date date = new Date();
			if(!(dateFormat.format(date)).equals(PageDate)){
				writer.println("Date is displayed wrong in Design page " +page);
						
			}
			EditbtnRedirection(url,price);
			AddtocartbtnRedirection();
			Thread.sleep(5000);
			driver.findElement(By.className("data_delete")).click();
			Thread.sleep(2000);
		}
	}
	public static void EditbtnRedirection(String url,int price) throws IOException, InterruptedException{
		driver.findElement(By.xpath("//*[@id='viewButton']/img")).click();
		Thread.sleep(5000);
		if(!driver.getCurrentUrl().contains(url + "wardrobe/design")){
			writer.println("Edit button not directing to interior page");
		}
		Thread.sleep(5000);
		if(price!=convetor(driver.findElement(By.id("price")).getText(),0)){
			writer.println("Price in customizing is not same");
		}
		Thread.sleep(3000);
		driver.get(url+"mydesigns/");
		Thread.sleep(3000);
		try{
			driver.switchTo().alert().accept();
			Thread.sleep(5000);
		}catch (Exception e) {}
	}

	public static void AddtocartbtnRedirection() throws IOException, InterruptedException{
	    Thread.sleep(5000);
		int cartCount = Integer.parseInt(driver.findElement(By.xpath("//*[@id='cartCount']")).getText());
		List<WebElement> DesignsList = driver.findElements(By.xpath("//*[@id='myDesignsTable']/tbody/tr"));
		String id = DesignsList.get(1).getAttribute("id");
		driver.findElement(By.xpath("//*[@id='"+id+"']/td[6]/ul/li[2]/a/img")).click();
		if(Integer.parseInt(driver.findElement(By.xpath("//*[@id='cartCount']")).getText()) != cartCount){
			writer.println("Saved design added to cart, cart value not changing");
		}
	}

	public static void goToMyDesigns() throws InterruptedException{
		Thread.sleep(10000);
		driver.findElement(By.xpath("//*[@id='login-user-info']")).click();
		driver.findElement(By.linkText("My Designs")).click();
	}

	public static int convetor(String strValue, int intValue) {
		for (int i=0;i<strValue.length();i++){
			if(Character.isDigit(strValue.charAt(i))){
				intValue = intValue*10 + Character.getNumericValue(strValue.charAt(i));
			}
		}
		if (strValue.contains("-"))
			return -1*intValue;
		return intValue;
	}
	
	public static int randInt(int min,int max) {
	    Random rand = new Random();
	    return rand.nextInt((max - min) + 1) + min;
	}
}