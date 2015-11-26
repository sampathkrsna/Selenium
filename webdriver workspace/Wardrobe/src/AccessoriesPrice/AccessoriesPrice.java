package AccessoriesPrice;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

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


public class AccessoriesPrice {
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
		File file = new File(project_path + "/XSLT_Reports/"+systemDate+"/errors/accessoriespricing.txt");
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
		driver.get(url+"wardrobe/dimensions");
		Thread.sleep(5000);
	}

	

	@AfterMethod
	public void afterMethod()  throws IOException {
		driver.quit();
		writer.close();
	}

	@Test
	@Parameters("url")
	public void CheckAccessoriesPrice(String url) throws InterruptedException,
			IOException {
		selectDimensions();
		navigateAccessoriesPage();
		addRemoveProcess();
	}
	
	public static void selectDimensions() throws InterruptedException {
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
	}
	
	public static void navigateAccessoriesPage() throws InterruptedException {
		Thread.sleep(3000);
		new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='doorSelection']/div/div[1]/button")));
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='doorSelection']/div/div[1]/button")).click();
		Thread.sleep(15000);
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("internalDesignMode")));
		driver.findElement(By.id("internalDesignMode")).click();
		Thread.sleep(5000);
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='nextStep wardrobe-studio-next-nav state-internal']")));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@class='nextStep wardrobe-studio-next-nav state-internal']")).click();
		Thread.sleep(7000);		
	}
	
	public void addRemoveProcess() throws InterruptedException, IOException{
		List<WebElement> AccessoriesList = driver.findElements(By.xpath("//div[@class='row item-hover product-shadow']"));
		int totalAccessories = AccessoriesList.size();
		for(int i=1;i<=totalAccessories;i++){
			int oldprice = convetorToInt(driver.findElement(By.xpath("//*[@id='price']/span[2]")).getText(), 0);
			Thread.sleep(2000);
			int accessoryprice = convetorToInt(driver.findElement(By.xpath("//*[@id='#body-wrapper']/div[7]/div[1]/div[2]/form/ul/li["+i+"]/div/div/div/section/div[1]/span[2]")).getText(),0);
			driver.findElement(By.xpath("//*[@id='#body-wrapper']/div[7]/div[1]/div[2]/form/ul/li["+i+"]/div/div/div/section/div[3]/div[2]/div/a")).click();
			Thread.sleep(7000);
			int newprice = convetorToInt(driver.findElement(By.xpath("//*[@id='price']/span[2]")).getText(), 0);
			addRemoveProduct(newprice, oldprice, accessoryprice,1, i);			
			
			String AccessoryId = AccessoriesList.get(i-1).getAttribute("data-accessoryid");
			int randomNumber = randInt(1,10);
			new Select(driver.findElement(By.id("quantity-"+AccessoryId))).selectByValue(Integer.toString(randomNumber));
			driver.findElement(By.xpath("//*[@id='#body-wrapper']/div[7]/div[1]/div[2]/form/ul/li["+i+"]/div/div/div/section/div[3]/div[2]/div/a")).click();
			Thread.sleep(5000);
			newprice = convetorToInt(driver.findElement(By.xpath("//*[@id='price']/span[2]")).getText(), 0);
			accessoryprice = accessoryprice * randomNumber;
			addRemoveProduct(newprice, oldprice, accessoryprice,randomNumber, i);
		}
	}
	
	public void addRemoveProduct(int newprice,int oldprice,int accessoryprice,int quantity,int i) throws IOException, InterruptedException{
		String productname = driver.findElement(By.xpath("//*[@id='#body-wrapper']/div[7]/div[1]/div[2]/form/ul/li["+i+"]/div/div/div/section/div[1]/span[1]")).getText();
		if(newprice != (oldprice + accessoryprice)){
			writer.println("price is varing when we add "+productname+" for " + quantity);
			writer.println("newprice : "+newprice);
			writer.println("oldprice + accessoryprice : "+(oldprice + accessoryprice));
		}
		driver.findElement(By.linkText("Remove")).click();
		Thread.sleep(5000);
		newprice = convetorToInt(driver.findElement(By.xpath("//*[@id='price']/span[2]")).getText(), 0);
		if(newprice != oldprice){
			writer.println("price is varing when we remove  "+productname);
			writer.println("newprice : "+newprice);
			writer.println("oldprice : "+oldprice);
		}
	}
	
	public int convetorToInt(String strValue, int intValue) {
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