package besttemplates;

import java.util.ArrayList;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;



public class FIlter {

	private static void startDesignFromATemplate() throws InterruptedException{
		// TODO Auto-generated method stub
		WebDriver driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://root.test.kustommade.com/templates/");
		driver.findElement(By.xpath(" .//*[@id='loginUser']/span ")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(" .//*[@id='login-email']")).sendKeys("admin@admin.com");
		driver.findElement(By.xpath(".//*[@id='login-password']")).sendKeys("admin");
		driver.findElement(By.xpath(".//*[@id='login']/span/img")).click();  
		driver.findElement(By.xpath(".//*[@id='kmBody']/div[9]/a/ul")).click();  
		driver.findElement(By.xpath(".//*[@id='measurement_form']/div/div[1]/div[3]/div/ul/li[1]/select")).click();

		ArrayList<WebElement> listOfWebElements = new ArrayList<WebElement>();
		for(int i=3;i<12;i++){
			WebElement a =  driver.findElement(By.xpath("/html/body/div[5]/div[3]/form/div/div[1]/div[3]/div/ul/li[1]/select/option["+i+"]"));
			listOfWebElements.add(a);
		}

		WebElement randomElement = selectRandomElementInList(listOfWebElements);
		randomElement.click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='measurement_form']/div/div[1]/div[3]/div/ul/li[2]/select")).click(); 
		WebElement b= driver.findElement(By.xpath("/html/body/div[5]/div[3]/form/div/div[1]/div[3]/div/ul/li[2]/select/option[9]")); 
		b.click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='measurement_form']/div/div[2]/a")).click();  

	}

	public static void main(String[] args) throws InterruptedException {
		for(int i=0;i<20;i++)
			startDesignFromATemplate();
	}

	public static WebElement selectRandomElementInList(ArrayList<WebElement> elements){
		return elements.get(randInt(0, elements.size()-1));
	}

	public static int randInt(int min, int max) {

		// NOTE: Usually this should be a field rather than a method
		// variable so that it is not re-seeded every call.
		Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

}
