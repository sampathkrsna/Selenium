package capturevideo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;

public class videocapture {

	
	public static void main(String[] args) throws ATUTestRecorderException, InterruptedException {
		// TODO Auto-generated method stub
		ATUTestRecorder recorder = new ATUTestRecorder("F:","testrecord",false);
		recorder.start();
		 WebDriver driver=new FirefoxDriver();
		 driver.manage().window().maximize();
		driver.get("http://www.customfurnish.com/");
	//	driver.findElement(By.xpath(".//*[@id='loginUser']")).click();
	//	Thread.sleep(3000);
		
	//	driver.findElement(By.xpath(".//*[@id='sblsbb']/button")).click();
	//	Thread.sleep(3000); 
		driver.close();
	
		recorder.stop();
	}

}
