/**
 * 
 */
package captureemail;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

/**
 * @author Sampath
 *
 */
public class Emailfirst {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
	WebDriver driver = new FirefoxDriver();
	driver.manage().window().maximize();
driver.get("https://www.google.co.in/intl/en/about/");
String color = driver.findElement(By.xpath("//a[@href='products/']")).getCssValue("color");
System.out.println("color saved");
String[] hexValue = color.replace("rgba(", "").replace(")", "").split(",");

int hexValue1=Integer.parseInt(hexValue[0]);
hexValue[1] = hexValue[1].trim();
int hexValue2=Integer.parseInt(hexValue[1]);
hexValue[2] = hexValue[2].trim();
int hexValue3=Integer.parseInt(hexValue[2]);
 
String actualColor = String.format("#%02x%02x%02x", hexValue1, hexValue2, hexValue3);


Assert.assertEquals("#245dc1", actualColor);


	
}

	

}

	


