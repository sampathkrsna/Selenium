package CartPage;

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

public class CartPage {
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
		File file = new File(project_path + "/XSLT_Reports/"+systemDate+"/errors/cartpage.txt");
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
	public void Cartpage(String url) throws InterruptedException,IOException {
		selectDimensions();
		navigateAccessoriesPage();
		addAccessory();
		CheckCartPage();
		CheckRedirection(url);
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
		Thread.sleep(60000);
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("internalDesignMode")));
		driver.findElement(By.id("internalDesignMode")).click();
		Thread.sleep(5000);
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='nextStep wardrobe-studio-next-nav state-internal']")));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@class='nextStep wardrobe-studio-next-nav state-internal']")).click();
		Thread.sleep(7000);		
	}
	
	public static void addAccessory() throws InterruptedException, IOException{
		List<WebElement> AccessoriesList = driver.findElements(By.xpath("//div[@class='row item-hover product-shadow']"));
		int clickElement = randInt(1,AccessoriesList.size());
		String AccessoryId = AccessoriesList.get(clickElement-1).getAttribute("data-accessoryid");
		Select dropdown = new Select(driver.findElement(By.id("quantity-"+AccessoryId)));
		int randomNumber = randInt(1,10);
		dropdown.selectByValue(Integer.toString(randomNumber));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='#body-wrapper']/div[7]/div[1]/div[2]/form/ul/li["+clickElement+"]/div/div/div/section/div[3]/div[2]/div/a")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@class='button Proceed']")).click();
		Thread.sleep(5000);
	}
	
	public void CheckCartPage() throws InterruptedException, IOException{
		String accessoryName = driver.findElement(By.xpath("//*[@id='paydone-os']/tbody/tr[2]/td[3]")).getText();
		int price = convetorToInt(driver.findElement(By.xpath("//*[@id='grandTotal']")).getText(), 0);
		driver.findElement(By.xpath("//*[@id='design']/div[2]/div[2]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='pincodeAvailabilityDropDown']/div/input")).clear();
		driver.findElement(By.xpath("//*[@id='pincodeAvailabilityDropDown']/div/input")).sendKeys("500033");
		driver.findElement(By.xpath("//*[@id='pincodeAvailabilityDropDown']/input")).click();
		Thread.sleep(5000);
		List<WebElement> rows = driver.findElements(By.xpath("//tr"));
		String rowId = rows.get(1).getAttribute("id");
		String text = driver.findElement(By.xpath("//*[@id='"+rowId+"']/td[2]")).getText();
		int accessIndex = text.indexOf("+") + 2;
		String accessName = text.substring(accessIndex);
		if(!accessName.contains(accessoryName)){
			writer.println("Accessory name is wrong for " + accessoryName);
		}
		if(!"1".equals(new Select(driver.findElement(By.xpath("//*[@id='"+rowId+"']/td[3]/select"))).getFirstSelectedOption().getText())){
			writer.println("In cart page primarly 1 is not displayed");
		}
		if(price!=convetorToInt(driver.findElement(By.xpath("//*[@id='"+rowId+"']/td[4]")).getText(), 0)){
			writer.println("Base price is displayed wrong in order page and cart page");
		}
		if(price!=convetorToInt(driver.findElement(By.xpath("//*[@id='"+rowId+"']/td[5]")).getText(), 0)){
			writer.println("Total cost is displayed wrong in order page and cart page");
		}
		if(1000!=convetorToInt(driver.findElement(By.xpath("//*[@id='"+rowId+"']/td[6]")).getText(), 0)){
			writer.println("1000 is not displayed as advance");
		}
		ChangeQuantity(price,rowId);
	}
	
	public void ChangeQuantity(int baseprice,String accessId) throws IOException, InterruptedException{
		Select dropdown = new Select(driver.findElement(By.xpath("//*[@id='"+accessId+"']/td[3]/select")));
		int quantity = randInt(1,10);
		dropdown.selectByValue(Integer.toString(quantity));
		Thread.sleep(5000);
		if((quantity*baseprice)!=convetorToInt(driver.findElement(By.xpath("//*[@id='"+accessId+"']/td[5]")).getText(), 0)){
			writer.println("Total cost is displayed wrong for " +quantity);
		}
		if((quantity*baseprice)!=convetorToInt(driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[2]/div[3]/div/div/div[2]/ul[1]/li[2]/span/span[2]")).getText(),0)){
			writer.println("Total cost is displayed wrong at bottom for " +quantity);
		}
		if((quantity*1000)!=convetorToInt(driver.findElement(By.xpath("//*[@id='"+accessId+"']/td[6]")).getText(), 0)){
			writer.println("Advance is not updating for " +quantity);
		}
		if((quantity*1000)!=convetorToInt(driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[2]/div[3]/div/div/div[2]/ul[2]/li[2]/span/span[2]")).getText(), 0)){
			writer.println("Advance is not updating at bottom for " +quantity);
		}
	}

	public void CheckRedirection(String url) throws IOException, InterruptedException {
		driver.findElement(By.linkText("CONTINUE SHOPPING")).click();
		Thread.sleep(5000);
		if(!driver.getCurrentUrl().equals(url)){
			writer.println("Continue shopping is not working");
		}
		driver.navigate().back();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[contains(@class,'checkoutStep')]")).click();
		Thread.sleep(5000);
		if(driver.findElement(By.xpath("//*[@id='loginAtCheckOutModel']")).isDisplayed()){
			manualSignin();
			Thread.sleep(4000);
			if (driver.findElement(By.id("cartModal")).isDisplayed()) {
				driver.findElement(By.xpath("//*[@id='cartModal']/div[2]/button")).click();
				DeleteRow(url);
			}
		}
		else {
			writer.println("Book now button at cart page is working");
		}
		Thread.sleep(5000);
	}

	public static void manualSignin() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id='login-email-save']")).clear();
		driver.findElement(By.xpath("//*[@id='login-email-save']")).sendKeys("kustomtestuser@gmail.com");
		driver.findElement(By.xpath("//*[@id='login-password-save']")).clear();
		driver.findElement(By.xpath("//*[@id='login-password-save']")).sendKeys("kustomtestuser_");
		driver.findElement(By.xpath("//*[@id='loginSave']")).click();
	}
	
	public void DeleteRow(String url) throws InterruptedException, IOException{
		List<WebElement> rows = driver.findElements(By.xpath("//tr"));
		String rowId = rows.get(1).getAttribute("id");
		driver.findElement(By.xpath("//*[@id='"+rowId+"']/td[7]/a/img")).click();
		Thread.sleep(3000);
		driver.switchTo().alert().accept();
		Thread.sleep(3000);
		if(driver.findElement(By.xpath("//a[@class='button customize-button c-medium c-red']")).isDisplayed()){
			driver.findElement(By.xpath("//a[@class='button customize-button c-medium c-red']")).click();
			Thread.sleep(5000);
			if(!driver.getCurrentUrl().equals(url)){
				writer.println("Continue shopping after deleting is not working");
			}
		}
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
	
	public static int randInt(int min,int max) {
	    Random rand = new Random();
	    return rand.nextInt((max - min) + 1) + min;
	}
}