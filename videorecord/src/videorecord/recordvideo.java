package videorecord;

import org.openqa.selenium.By;

import atu.testrecorder.ATUTestRecorder;

public class recordvideo {

	public recordvideo() {
		// TODO Auto-generated constructor stub
		ATUTestRecorder recorder = new ATUTestRecorder("C:\\Test\\","testrecord",false);
		recorder.start();
		driver.get("https://www.google.co.in/");
		driver.findElement(By.linkText(Prop.getProperty("AboutLinkText"))).click();
		recorder.stop();
	}

	
}
