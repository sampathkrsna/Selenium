package Signup;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DoorTypePage {
	static WebDriver driver;
	static String message = "";
	String project_path = System.getProperty("user.dir");

	@BeforeMethod
	@Parameters("url")
	public void beforeTest(String url) throws InterruptedException {
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
		Thread.sleep(3000);
	}

	@AfterMethod
	public void afterTest() {
		driver.quit();
	}

	@Test
	@Parameters("url")
	public void DoorTypepage(String url) throws InterruptedException,
			IOException {
		DateFormat dateFormat = new SimpleDateFormat("dMMyyyyHm");
		Date date = new Date();
		navigateDoorTypePage();
		manualSignup( "test_"+dateFormat.format(date), "DoorTypePage", url);
		navigateDoorTypePage();
		manualSignin( "test_"+dateFormat.format(date), "DoorTypePage", url);
		navigateDoorTypePage();
		signinFB("DoorTypePage", url);
		navigateDoorTypePage();
		signinGoogle("DoorTypePage", url);
	}

	public static void navigateDoorTypePage()
			throws InterruptedException, IOException {
		Thread.sleep(1000);
		driver.findElement(By.linkText("WARDROBES")).click();
		driver.findElement(By.linkText("Design From Scratch")).click();
		Thread.sleep(2000);
		int widthFeet = randInt(2, 10);
		new Select(driver.findElement(By.xpath("//*[@class='width-feet dimension-dropdown']"))).selectByValue(Integer.toString(widthFeet));
		if(widthFeet==2){
			new Select(driver.findElement(By.xpath("//*[@class='width-inch dimension-dropdown']"))).selectByValue(Integer.toString(randInt(8, 11)));
		}
		else if (widthFeet>=3 && widthFeet<=9){
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
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='loginUser']")).click();
	}

	public static void manualSignup(String name, String page,
			String url) throws InterruptedException{
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='signup-name']")).clear();
		driver.findElement(By.xpath("//*[@id='signup-name']")).sendKeys(name);
		driver.findElement(By.xpath("//*[@id='signup-email']")).clear();
		driver.findElement(By.xpath("//*[@id='signup-email']")).sendKeys(name + "@gmail.com");
		driver.findElement(By.xpath("//*[@id='signup-phoneNumber']")).clear();
		driver.findElement(By.xpath("//*[@id='signup-phoneNumber']")).sendKeys("9999999999");
		driver.findElement(By.xpath("//*[@id='signup-password']")).clear();
		driver.findElement(By.xpath("//*[@id='signup-password']")).sendKeys(name);
		driver.findElement(By.xpath("//*[@id='signup']")).click();
		Thread.sleep(15000);
		signOut(page, url);
	}

	public static void manualSignin( String name, String page,
			String url) throws InterruptedException{
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='login-email']")).clear();
		driver.findElement(By.xpath("//*[@id='login-email']")).sendKeys(name+"@gmail.com");
		driver.findElement(By.xpath("//*[@id='login-password']")).clear();
		driver.findElement(By.xpath("//*[@id='login-password']")).sendKeys(name);
		driver.findElement(By.xpath("//*[@id='login']")).click();
		Thread.sleep(5000);
		signOut(page, url);
	}

	public static void signinFB( String page, String url)
			throws InterruptedException{
		Thread.sleep(2000);
		String winHandleBefore = driver.getWindowHandle();
		driver.findElement(By.xpath("//*[@id='AccountModel']/div[3]/div[1]/div/ul[1]/li[1]/a")).click();
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='email']")).clear();
		driver.findElement(By.xpath("//*[@id='email']")).sendKeys("10pa1a0590@gmail.com");
		driver.findElement(By.xpath("//*[@id='pass']")).clear();
		driver.findElement(By.xpath("//*[@id='pass']")).sendKeys("10pa1a0590");
		driver.findElement(By.xpath("//*[@id='loginbutton']")).click();
//		try{
//			Thread.sleep(3000);
//			driver.findElement(By.xpath("//*[@id='platformDialogForm']/div[3]/div/table/tbody/tr/td[2]/button[2]")).click();
//		}catch(Exception e){}
		driver.switchTo().window(winHandleBefore);
		Thread.sleep(2000);
		signOut(page, url);
	}

	public static void signinGoogle( String page, String url)
			throws InterruptedException{
		Thread.sleep(2000);
		String winHandleBefore = driver.getWindowHandle();
		driver.findElement(By.xpath(".//*[@id='AccountModel']/div[3]/div[1]/div/ul[1]/li[2]/a")).click();
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='Email']")).clear();
		driver.findElement(By.xpath("//*[@id='Email']")).sendKeys("10pa1a0590@gmail.com");
		driver.findElement(By.xpath("//*[@id='Passwd']")).clear();
		driver.findElement(By.xpath("//*[@id='Passwd']")).sendKeys("10pa1a0590_");
		driver.findElement(By.xpath("//*[@id='signIn']")).click();
//		try{
//			Thread.sleep(3000);
//			driver.findElement(By.xpath("//*[@id='submit_approve_access']")).click();
//		}catch(Exception e){}
		driver.switchTo().window(winHandleBefore);
		Thread.sleep(5000);
		signOut(page, url);
	}

	public static void signOut( String page, String url)
			throws InterruptedException{
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='login-user-info']")).click();
		driver.findElement(By.linkText("Sign Out")).click();
		Thread.sleep(5000);
	}

	public void checkMail(String message) throws MessagingException, IOException{
		Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");
        Session session = Session.getInstance(props, null);
        Store store = session.getStore();
        store.connect("imap.gmail.com", "kustomtestuser@gmail.com", "kustomtestuser_");
        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);
        Message msg = inbox.getMessage(inbox.getMessageCount());
        if("Welcome to KustomMade".equals(msg.getSubject())){
//        	writer.println(message+" did not come within 1 min");
        }
	}
	
	public static int randInt(int min,int max) {
	    Random rand = new Random();
	    return rand.nextInt((max - min) + 1) + min;
	}
}