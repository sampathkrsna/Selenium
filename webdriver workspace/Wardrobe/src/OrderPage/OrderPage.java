package OrderPage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class OrderPage {
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
		File file = new File(project_path + "/XSLT_Reports/"+systemDate+"/errors/orderpage.txt");
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
	public void Orderpage(String url) throws InterruptedException,IOException, MessagingException {
		selectDimensions();
		navigateAccessoriesPage();
		addAccessory();
		scheduleCallBack();
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
		int wardrobeprice = convetorToInt(driver.findElement(By.xpath("//*[@id='price']")).getText(),0);
		List<WebElement> AccessoriesList = driver.findElements(By.xpath("//div[@class='row item-hover product-shadow']"));
		int clickElement = randInt(1,AccessoriesList.size());
		String AccessoryId = AccessoriesList.get(clickElement-1).getAttribute("data-accessoryid");
		Select dropdown = new Select(driver.findElement(By.id("quantity-"+AccessoryId)));
		int accessoryprice = convetorToInt(driver.findElement(By.xpath("//*[@id='#body-wrapper']/div[7]/div[1]/div[2]/form/ul/li["+clickElement+"]/div/div/div/section/div[1]/span[2]")).getText(),0);
		String accessoryname = driver.findElement(By.xpath("//*[@id='#body-wrapper']/div[7]/div[1]/div[2]/form/ul/li["+clickElement+"]/div/div/div/section/div[1]/span[1]")).getText();
		int randomNumber = randInt(1,10);
		dropdown.selectByValue(Integer.toString(randomNumber));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='#body-wrapper']/div[7]/div[1]/div[2]/form/ul/li["+clickElement+"]/div/div/div/section/div[3]/div[2]/div/a")).click();
		Thread.sleep(5000);
		driver.findElement(By.partialLinkText("PROCEED TO ORDER")).click();
		Thread.sleep(10000);
		CheckText(driver,AccessoryId,accessoryname,accessoryprice,randomNumber);
		CheckBillAmount(driver,wardrobeprice,AccessoryId,accessoryname,accessoryprice);
	}

	public static void CheckText(WebDriver driver,String AccessoryId,String accessoryname, 
			int accessoryprice,int quantity) throws InterruptedException, IOException {
		if (!accessoryname.equals(driver.findElement(By.xpath("//*[@id='paydone-os']/tbody/tr[2]/td[3]")).getText())){
			writer.println("Name of accessory is displayed wrong for "+accessoryname);
		}
		if (accessoryprice != convetorToInt(driver.findElement(By.xpath("//*[@id='paydone-os']/tbody/tr[2]/td[5]")).getText(), 0)){
			writer.println("Price of accessory is displayed wrong for "+accessoryname);
		}
		if (quantity != convetorToInt(new Select(driver.findElement(By.id("quantity-"+AccessoryId))).getFirstSelectedOption().getText(),0)){
			writer.println("Quantity of accessory is displayed wrong for "+accessoryname);
		}
		if ((quantity*accessoryprice != convetorToInt(driver.findElement(By.xpath("//*[@id='paydone-os']/tbody/tr[2]/td[6]")).getText(),0))){
			writer.println("Total cost of accessory is displayed wrong for "+accessoryname);
		}
		if(convetorToInt(driver.findElement(By.xpath("//*[@id='wardrobe-total-cost-including-accessories']")).getText(), 0)!=convetorToInt(driver.findElement(By.id("finalTotal")).getText(), 0)){
			writer.println("Initially total price is displayed wrong for "+accessoryname);
		}
	}
	
	public static void CheckBillAmount(WebDriver driver,int wardrobeprice, String AccessoryId,
			String accessoryname, int accessoryprice) throws IOException, InterruptedException {
		Select dropdown = new Select(driver.findElement(By.id("quantity-"+AccessoryId)));
		int quantity = randInt(1,10);
		dropdown.selectByValue(Integer.toString(quantity));
		Thread.sleep(10000);
		if (quantity*accessoryprice != convetorToInt(driver.findElement(By.xpath("//*[@id='paydone-os']/tbody/tr[2]/td[6]")).getText(),0)){
			writer.println("Total cost of accessory is not updating for "+accessoryname + " - "+ quantity);
			writer.println(quantity*accessoryprice +" - "+ convetorToInt(driver.findElement(By.xpath("//*[@id='paydone-os']/tbody/tr[2]/td[6]")).getText(),0));
		}
		if(convetorToInt(driver.findElement(By.xpath("//*[@id='grandTotal']")).getText(), 0)!=(quantity * accessoryprice + wardrobeprice)){
			writer.println("Price is not updating for total price at bottom for "+accessoryname + " - "+ quantity);
			writer.println((quantity * accessoryprice + wardrobeprice) +" - "+ convetorToInt(driver.findElement(By.xpath("//*[@id='grandTotal']")).getText(),0));
		}
		if(convetorToInt(driver.findElement(By.xpath("//*[@id='wardrobe-total-cost-including-accessories']")).getText(), 0)!=(quantity * accessoryprice + wardrobeprice)){
			writer.println("Price is not updating for total price at top for "+accessoryname + " - "+ quantity);
			writer.println((quantity * accessoryprice + wardrobeprice) +" - "+ convetorToInt(driver.findElement(By.xpath("//*[@id='wardrobe-total-cost-including-accessories']")).getText(),0));
		}
	}
	
	public static void scheduleCallBack() throws InterruptedException, MessagingException, IOException{
		driver.findElement(By.xpath("//*[@id='callBackName']/input")).clear();
		driver.findElement(By.xpath("//*[@id='callBackName']/input")).sendKeys("testuser");
		driver.findElement(By.xpath("//*[@id='callBackEmailAddress ']/input")).clear();
		driver.findElement(By.xpath("//*[@id='callBackEmailAddress ']/input")).sendKeys("kustomtestuser@gmail.com");
		driver.findElement(By.xpath("//*[@id='callBackPhoneNumber']/input")).clear();
		driver.findElement(By.xpath("//*[@id='callBackPhoneNumber']/input")).sendKeys("8908908908");
		driver.findElement(By.xpath("//*[@id='callBackPincode']/input")).clear();
		driver.findElement(By.xpath("//*[@id='callBackPincode']/input")).sendKeys("500033");
		driver.findElement(By.xpath("//*[@id='design']/div[2]/div[2]/div[3]/div/form/div[6]/input")).click();
		Thread.sleep(10000);
		if(!driver.findElement(By.xpath("//*[@class='alert-box success']")).isDisplayed()){
			writer.println("1st schedule call back is not working");
		}
		Thread.sleep(5000);
		if(!checkEmail("New wardrobe request")){
			writer.println("mail not received from 1st schedule call back");
		}
	}

	public void CheckRedirection(String url) throws InterruptedException, IOException {
		Thread.sleep(1000);
		int oldcartcount = convetorToInt(driver.findElement(By.id("cartCount")).getText(), 0);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scroll(200, 0)");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='design']/div[2]/div[2]/a")).click();
		driver.findElement(By.xpath("//*[@id='pincodeAvailabilityDropDown']/div/input")).clear();
		driver.findElement(By.xpath("//*[@id='pincodeAvailabilityDropDown']/div/input")).sendKeys("500033");
		driver.findElement(By.xpath("//*[@id='pincodeAvailabilityDropDown']/input")).click();
		Thread.sleep(5000);
		if(oldcartcount+1!=convetorToInt(driver.findElement(By.id("cartCount")).getText(), 0)){
			writer.println("Total count in cart is not updating");
		}
		if(!driver.getCurrentUrl().contains("cart")){
			writer.println("Page is not redirecting to cart page for top button");
		}
		Thread.sleep(3000);
		driver.navigate().back();
		Thread.sleep(5000);
		if(!driver.getCurrentUrl().contains("order")){
			writer.println("Page is not redirecting to order page from cart page");
		}
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='#body-wrapper']/div[7]/div[6]/div[2]/a")).click();
		Thread.sleep(5000);
		if(!driver.getCurrentUrl().contains("accessories")){
			writer.println("Page is not redirecting to accessories page");
		}
		Thread.sleep(3000);
	}

	public static boolean checkEmail(String message) throws MessagingException, IOException, InterruptedException{
		Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");
        Session session = Session.getInstance(props, null);
        Store store = session.getStore();
        store.connect("imap.gmail.com", "kustomtestuser@gmail.com", "kustomtestuser_");
        String subject;
		Date date;
        int count=1;
        do{
        	Thread.sleep(10000);
        	Folder inbox = store.getFolder("INBOX");
        	inbox.open(Folder.READ_ONLY);
        	Message msg = inbox.getMessage(inbox.getMessageCount());
        	subject = msg.getSubject();
        	date = msg.getSentDate();
        	count = count + 1;
		}while(!message.equals(subject) && daysBetween(date) && count<15);
        if(count==15){
        	return false;
        }
        return true;
	}
	
	public static boolean daysBetween(Date date) {
		Date presentdate = new Date();
		long seconds = (presentdate.getTime() - date.getTime())/1000;
		if(seconds<120){
			return false;
		}
		return true;
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
	
	public static int randInt(int min, int max) {
	    Random rand = new Random();
	    return rand.nextInt((max - min) + 1) + min;
	}
}