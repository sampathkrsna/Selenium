package furnishing;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class sample {

	private WebDriver driver = null;
	
	public void screenshots() throws InterruptedException, AWTException, IOException
	{
		driver.findElement(By.xpath("//body")).sendKeys(Keys.chord(Keys.CONTROL,Keys.SHIFT, "i"));
		Thread.sleep(4000);
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		Robot robot = new Robot();
		BufferedImage img =robot.createScreenCapture(new Rectangle(size));
		File save_path=new File("screen.jpg");
		ImageIO.write(img,"JPG",save_path);
		Thread.sleep(3000);
		   File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				// Now you can do whatever you need to do with it, for example copy somewhere
				FileUtils.copyFile(scrFile, new File("E:\\sampath\\screenshotimporant.png"));
	}
	
	@BeforeSuite
	public void OpenBrowser() {
		
		
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://root.test.kustommade.com/kitchen");
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

	@Test
	

	public void fullhouse() throws InterruptedException, AWTException, IOException {
		
	
		 screenshots();
	
		driver.findElement(By.xpath(".//*[@id='body-wrapper']/div[5]/div[1]/div[1]/div/button")).click();
		Thread.sleep(3000);
		int noOfKitchens = driver.findElements(
				By.xpath(".//*[@id='layout-selection']/div[2]/ul/li")).size();
		System.out.println("Total modal of kitchens are" + noOfKitchens);
		// int SelectedKitchen =randInt(1,noOfKitchens);
		// System.out.println(+SelectedKitchen);
		Thread.sleep(3000);
		for (int run = 1; run <= noOfKitchens; run++)

		{
			System.out.println("selecting the kitchen model" + run);
			driver.findElement(
					By.xpath(".//*[@id='layout-selection']/div[2]/ul/li[" + run
							+ "]/div/div[1]")).click();
			Thread.sleep(5000);
			WebElement drpDown = driver.findElement(By
					.xpath(".//*[@id='runningLengthFeet']"));
			 String a3 = drpDown.getAttribute("value");
			 System.out.println("attribute values are"+a3);
	

			Select objSel = new Select(drpDown);
			 WebElement tmp = objSel.getFirstSelectedOption();
			   String firstoption= tmp.getText(); 
			   
			   System.out.println("The initial value of the indivudial kitchen is "+firstoption);
		
			List<WebElement> weblist = objSel.getOptions();
			int count = weblist.size();

			Random num = new Random();
			int iSelect = num.nextInt(count);
			objSel.selectByIndex(iSelect);
			System.out.println(drpDown.getAttribute("value"));
			String a = drpDown.getAttribute("value");
			int foo = Integer.parseInt(a);
			int yoo = 12 * foo;
			System.out
					.println("the total number of elemts present are" + count);
			System.out.println(yoo);
			int noOfbase = driver
					.findElements(
							By.xpath("/html/body/div[5]/div[7]/div[2]/div[1]/div/table[1]/tr"))
					.size();
			System.out.println(+noOfbase);

			int a1[] = new int[100];
			int ans = 0;

			for (int i = 1; i <= noOfbase; i++) {
				driver.findElement(
						By.xpath("/html/body/div[5]/div[7]/div[2]/div[1]/div/table[1]/tr["
								+ i + "]/td[2]")).getText();
				String w = driver
						.findElement(
								By.xpath("/html/body/div[5]/div[7]/div[2]/div[1]/div/table[1]/tr["
										+ i + "]/td[2]")).getText();
				String mychar1 = String.valueOf(w.substring(0, w.length() - 1));
				int measurement = Integer.parseInt(mychar1);
				a1[i] = measurement;
				System.out.println(measurement);
				System.out.println(a1[i]);
				ans += a1[i];
			}
			System.out.println("the total length of commodities is " + ans);

			String remaining = driver.findElement(
					By.xpath(".//*[@id='remaining-length-Base']")).getText();
			int remain = Integer.parseInt(remaining);
			int total = remain + ans;

			if (yoo != total) {
				System.out
						.println("The running length and the selected length are not equal ");

			} else {
				System.out
						.println("fail : The running length is  equal to the selected length");
			}
		}

		/*
		 * int Selectedlength = randInt(8,28); driver.findElement(By.xpath(
		 * "/html/body/div[5]/div[5]/div[2]/div[1]/select[1]/option["
		 * +Selectedlength+"]")).click();
		 * System.out.println("clicking on "+Selectedlength);
		 */
		driver.findElement(By.xpath("//body")).sendKeys(Keys.chord(Keys.CONTROL,Keys.SHIFT, "i"));
		
		   File scrFile1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				// Now you can do whatever you need to do with it, for example copy somewhere
				FileUtils.copyFile(scrFile1, new File("E:\\sampath\\screenshot1.png"));
	        }			

	

	private int randInt(int min, int max) {
		// TODO Auto-generated method stub
		Random rand = new Random();
		return rand.nextInt((max - min) + 1) + min;
	}
}
