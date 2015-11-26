package example2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class example2testcase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://www.toolsqa.com/automation-practice-table");
		driver.manage().timeouts().implicitlyWait(4,TimeUnit.SECONDS);
		String sRow="1";
		String sCol="2";
		String sCellValue=driver.findElement(By.xpath(".//*[@id='post-1715']/div/div/div/table/tbody/tr["+sRow+"]/td["+sCol+"]")).getText();
		System.out.println("sCellValue");
		

	}

}
