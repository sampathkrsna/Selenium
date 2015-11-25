package paperjsautomation;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Jspaper {

	public static void main(String[] args) throws AWTException, InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("file:///C:/Users/Sampath/Desktop/paperjs/examples/Games/Paperoids.html");
		System.out.println("Playing game");
		//Thread.sleep(5000);
		//driver.findElement(By.xpath(" .//*[@id='canvas'] ")).click();
		Robot r = new Robot();
		//r.keyPress(KeyEvent.VK_F);
		driver.findElement(By.xpath(".//*[@id='canvas']")).sendKeys(Keys.chord( "f"));
		

	}

}
