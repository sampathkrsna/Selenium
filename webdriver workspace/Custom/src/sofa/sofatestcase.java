package sofa;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class sofatestcase {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://root.test.customfurnish.com/");
		driver.manage().timeouts().implicitlyWait(4,TimeUnit.SECONDS);
		driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/nav/section[1]/ul/li[4]/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[2]/div/div/div[2]/ul/li[1]/div/div[2]/div[2]/a")).click();
	    //WebElement el1 =driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[5]/div/div[2]/ul/li[1]/div/select"));
	    //Select drpcount = new Select(el1);
	    //drpcount.selectByValue("2");
	    //Thread.sleep(3000);
	    String total;
	    WebElement price= driver.findElement(By.xpath(".//*[@id='sofa-order-set']/div/div[3]/table/tr[5]/td[2]"));
	    total = price.getText();
	    System.out.println("Price = " + total);
		WebElement list1 = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[5]/div/div[2]/ul/li[1]/div/select"));
		Select se = new Select(list1);
		se.selectByVisibleText("1");
		Thread.sleep(1000);
		se.selectByVisibleText("2");
		Thread.sleep(1000);
		se.selectByVisibleText("3");
		String tot;
		WebElement pricelist = driver.findElement(By.xpath(".//*[@id='sofa-order-set']/div/div[3]/table/tr[5]/td[2]"));
		tot = pricelist.getText();	
		
		System.out.println("The Total Price is " + tot);

	}

}
