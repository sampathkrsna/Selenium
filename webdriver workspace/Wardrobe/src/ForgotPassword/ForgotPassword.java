package ForgotPassword;

import java.io.IOException;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ForgotPassword {
	WebDriver driver;
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
	public void afterMethod() {
		driver.quit();
	}

	@Test
	@Parameters("url")
	public void forgotPassword(String url) throws InterruptedException,IOException, MessagingException {
		SubmitEmail();
		EnterPassword(url,"kustomtes");
		manualSignin(driver, "kustomtes");		
	}

	public void SubmitEmail() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='loginUser']")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText("Forgot Password?")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='email']")).clear();
		driver.findElement(By.xpath("//*[@id='email']")).sendKeys("kustomtestuser@gmail.com");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='emailSubmit']")).click();
		Thread.sleep(20000);
	}
	
	public String checkEmail(String oldurl) throws MessagingException, IOException{
		Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");
        Session session = Session.getInstance(props, null);
        Store store = session.getStore();
        store.connect("imap.gmail.com", "kustomtestuser@gmail.com", "kustomtestuser_");
        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);
        Message msg = inbox.getMessage(inbox.getMessageCount());
        Multipart mp = (Multipart) msg.getContent();
        BodyPart bp = mp.getBodyPart(0);
        String content = (String) bp.getContent();
        String word = oldurl + "account/resetpassword";
        int index1 = content.indexOf(word);	
        int index2 = content.indexOf(" ", index1);
		return content.substring(index1, index2-6);
	}

	public void EnterPassword(String url,String password) throws MessagingException, IOException, InterruptedException {
		driver.get(checkEmail(url));
		Thread.sleep(10000);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("passwordSubmit")).click();
		Thread.sleep(3000);
	}
	
	public static void manualSignin(WebDriver driver, String password) throws InterruptedException{
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='login-email']")).clear();
		driver.findElement(By.xpath("//*[@id='login-email']")).sendKeys("kustommadetester@gmail.com");
		driver.findElement(By.xpath("//*[@id='login-password']")).clear();
		driver.findElement(By.xpath("//*[@id='login-password']")).sendKeys(password);
		driver.findElement(By.xpath("//*[@id='login']/span/img")).click();
	}
}