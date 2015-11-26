package kitchen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

public class ScheduleCallBack {
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
		File file = new File(project_path + "/XSLT_Reports/"+systemDate+"/errors/ScheduleCallback_kitchen.txt");
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
	public void afterMethod() throws IOException {
		driver.quit();
		writer.close();
	}

	@Test
	@Parameters("url")
	public void  Kitchen(String url) throws InterruptedException,IOException, MessagingException {
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@class='kitchen-start-design-button']")).click();
		Thread.sleep(3000);
		selectLayout();
		scheduleCallBack1();
		scheduleCallBack2();
	}
	
	public static void selectLayout(){
		driver.findElement(By.xpath("//*[@id='layout-selection']/div[2]/ul/li[1]/div/div[1]")).click();
		String feet = Integer.toString(randInt(10, 30));
		String inches = Integer.toString(randInt(0, 11));
		new Select(driver.findElement(By.xpath("//*[@id='runningLengthFeet']"))).selectByValue(feet);
		new Select(driver.findElement(By.xpath("//*[@id='runningLengthInches']"))).selectByValue(inches);
	}
	
	public static void scheduleCallBack1() throws InterruptedException, MessagingException, IOException{
		driver.findElement(By.xpath("//*[@id='schedule-user-name']")).clear();
		driver.findElement(By.xpath("//*[@id='schedule-user-name']")).sendKeys("testuser");
		driver.findElement(By.xpath("//*[@id='schedule-user-email']")).clear();
		driver.findElement(By.xpath("//*[@id='schedule-user-email']")).sendKeys("kustomtestuser@gmail.com");
		driver.findElement(By.xpath("//*[@id='schedule-user-phone']")).clear();
		driver.findElement(By.xpath("//*[@id='schedule-user-phone']")).sendKeys("8908908908");
		driver.findElement(By.xpath("//*[@id='scheduleUserPincode']")).clear();
		driver.findElement(By.xpath("//*[@id='scheduleUserPincode']")).sendKeys("500033");
		driver.findElement(By.xpath("//*[contains(@class,'button customize-button c-red')]")).click();
		Thread.sleep(10000);
		if(!driver.findElement(By.xpath("//*[@class='alert-box alert radius']/p")).getText().equals("Thanks for contacting us.Our experts will get in touch with you shortly")){
			writer.println("1st schedule call back is not working");
		}
		driver.findElement(By.xpath("//*[@class='close']")).click();
		Thread.sleep(5000);
		if(!checkEmail("Kitchen new expert review request")){
			writer.println("mail not received from 1st schedule call back");
		}
	}
	
	public static void scheduleCallBack2() throws InterruptedException, MessagingException, IOException{
		driver.findElement(By.xpath("//*[@id='kitchen-user-name']")).clear();
		driver.findElement(By.xpath("//*[@id='kitchen-user-name']")).sendKeys("testuser");
		driver.findElement(By.xpath("//*[@id='kitchen-user-email']")).clear();
		driver.findElement(By.xpath("//*[@id='kitchen-user-email']")).sendKeys("kustomtestuser@gmail.com");
		driver.findElement(By.xpath("//*[@id='kitchen-user-phone']")).clear();
		driver.findElement(By.xpath("//*[@id='kitchen-user-phone']")).sendKeys("8908908908");
		driver.findElement(By.xpath("//*[@id='kitchenUserPincode']")).clear();
		driver.findElement(By.xpath("//*[@id='kitchenUserPincode']")).sendKeys("500033");
		driver.findElement(By.xpath("//*[contains(@class,'kitchen-submit')]")).click();
		Thread.sleep(10000);
		if(!driver.findElement(By.xpath("//*[@class='alert-box alert radius']/p")).getText().equals("Thanks for contacting us.Our experts will get in touch with you shortly")){
			writer.println("2st schedule call back is not working");
		}
		driver.findElement(By.xpath("//*[@class='close']")).click();
		Thread.sleep(5000);
		if(!checkEmail("Kitchen new expert review request")){
			writer.println("mail not received from 2st schedule call back");
		}
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
	
	public static int randInt(int min,int max) {
	    Random rand = new Random();
	    return rand.nextInt((max - min) + 1) + min;
	}
}