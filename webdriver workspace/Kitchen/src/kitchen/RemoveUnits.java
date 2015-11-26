package kitchen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class RemoveUnits {
	WebDriver driver;
	static String message = "";
	String project_path = System.getProperty("user.dir");
	static PrintWriter writer;
	
	@BeforeMethod
	@Parameters("url")
	public void beforeMethod(String url) throws InterruptedException, IOException {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		String systemDate = dateFormat.format(date);
		File file = new File(project_path + "/XSLT_Reports/"+systemDate+"/errors/remove_units_kitchen.txt");
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
	public void removeUnits(String url) throws InterruptedException,IOException {
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@class='kitchen-start-design-button']")).click();
		removeElementsLShapedKitchen();
		removeElementsStraightKitchen();
		removeElementsUShapedKitchen();
		removeElementsParallelKitchen();	
	}
	
	public void removeElementsLShapedKitchen() throws InterruptedException, IOException{
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='layout-selection']/div[2]/ul/li[1]/div/div[1]")).click();
		String feet = Integer.toString(randInt(11, 30));
		String inches = Integer.toString(randInt(0, 11));
		new Select(driver.findElement(By.xpath("//*[@id='runningLengthFeet']"))).selectByValue(feet);
		new Select(driver.findElement(By.xpath("//*[@id='runningLengthInches']"))).selectByValue(inches);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath("//*[@id='whatIncluded']")));
		Thread.sleep(2000);
		String kitchenType = "LShapedKitchen";
		removeUnits("Base",kitchenType,feet,inches);
		removeUnits("Wall",kitchenType,feet,inches);
		removeTallUnits(kitchenType,feet,inches);
		compareSpaces(kitchenType,feet,inches,24);
		checkPrice(kitchenType,feet,inches);
	}
	
	public void removeElementsStraightKitchen() throws InterruptedException, IOException{
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='layout-selection']/div[2]/ul/li[2]/div/div[1]")).click();
		String feet = Integer.toString(randInt(4, 30));
		String inches = Integer.toString(randInt(0, 11));
		new Select(driver.findElement(By.xpath("//*[@id='runningLengthFeet']"))).selectByValue(feet);
		new Select(driver.findElement(By.xpath("//*[@id='runningLengthInches']"))).selectByValue(inches);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath("//*[@id='whatIncluded']")));
		Thread.sleep(2000);
		String kitchenType = "StraightKitchen";
		removeUnits("Base",kitchenType,feet,inches);
		removeUnits("Wall",kitchenType,feet,inches);
		removeTallUnits(kitchenType,feet,inches);
		compareSpaces(kitchenType,feet,inches,0);
		checkPrice(kitchenType,feet,inches);
	}
	
	public void removeElementsUShapedKitchen() throws InterruptedException, IOException{
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='layout-selection']/div[2]/ul/li[3]/div/div[1]")).click();
		String feet = Integer.toString(randInt(17, 30));
		String inches = Integer.toString(randInt(0, 11));
		new Select(driver.findElement(By.xpath("//*[@id='runningLengthFeet']"))).selectByValue(feet);
		new Select(driver.findElement(By.xpath("//*[@id='runningLengthInches']"))).selectByValue(inches);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath("//*[@id='whatIncluded']")));
		Thread.sleep(2000);
		String kitchenType = "UShapedKitchen";
		removeUnits("Base",kitchenType,feet,inches);
		removeUnits("Wall",kitchenType,feet,inches);
		removeTallUnits(kitchenType,feet,inches);
		compareSpaces(kitchenType,feet,inches,48);
		checkPrice(kitchenType,feet,inches);
	}
	
	public void removeElementsParallelKitchen() throws InterruptedException, IOException{
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='layout-selection']/div[2]/ul/li[4]/div/div[1]")).click();
		String feet = Integer.toString(randInt(4, 30));
		String inches = Integer.toString(randInt(0, 11));
		new Select(driver.findElement(By.xpath("//*[@id='runningLengthFeet']"))).selectByValue(feet);
		new Select(driver.findElement(By.xpath("//*[@id='runningLengthInches']"))).selectByValue(inches);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath("//*[@id='whatIncluded']")));
		Thread.sleep(2000);
		String kitchenType = "ParallelKitchen";
		removeUnits("Base",kitchenType,feet,inches);
		removeUnits("Wall",kitchenType,feet,inches);
		removeTallUnits(kitchenType,feet,inches);
		compareSpaces(kitchenType,feet,inches,0);
		checkPrice(kitchenType,feet,inches);
	}
	
	public void checkPrice(String kitchenType,String feet, String inch) throws IOException{
		if(convetorToInt(driver.findElement(By.xpath("//*[@id='kitchen-price']/div[1]/b[2]")).getText()) != 0){
			writer.println("Price is not becoming 0 after removing all items in "+kitchenType+" of "+feet+"."+inch);
		}
	}
	
	public void removeUnits(String type,String kitchenType,String feet, String inch) throws InterruptedException, IOException{
		List<WebElement> baseunits = driver.findElements(By.xpath("//*[@id='added-cabinets-"+type+"']/tr"));
		int base_space = convetorToInt(driver.findElement(By.xpath("//*[@id='remaining-length-"+type+"']")).getText()),new_base_space=0;
		int base_cost = convetorToInt(driver.findElement(By.xpath("//*[@id='kitchen-price']/div[1]/b[2]")).getText()),new_cost=0;
		int cost,width,bufferfactor=0,multiplicationfactor=1;
		String name;
		for (WebElement baseunit : baseunits) {
			String id = baseunit.getAttribute("id");
			name = driver.findElement(By.xpath("//*[@id='"+id+"']/td[1]/a")).getText();
			width = convetorToInt(driver.findElement(By.xpath("//*[@id='"+id+"']/td[2]")).getText());
			cost = convetorToInt(driver.findElement(By.xpath("//*[@id='"+id+"']/td[3]")).getText());
			driver.findElement(By.xpath("//*[@id='"+id+"']/td[4]/a/img")).click();
			Thread.sleep(3000);
			new_base_space = convetorToInt(driver.findElement(By.xpath("//*[@id='remaining-length-"+type+"']")).getText());
			new_cost = convetorToInt(driver.findElement(By.xpath("//*[@id='kitchen-price']/div[1]/b[2]")).getText());
			if(name.equals("Corner Unit L-Shaped")){
				bufferfactor = width;
				multiplicationfactor=2;
			}else if(name.equals("Corner Unit Straight")){
				bufferfactor = 24;
				multiplicationfactor=1;
			}else{
				bufferfactor = 0;
				multiplicationfactor=1;
			}
			if(new_base_space != (base_space + bufferfactor + multiplicationfactor*width)){
				writer.println("Width is not updating (increasing) for "+type+" unit "+name+" at width : "+width+". oldwidth : "+base_space+" , newwidth : "+new_base_space+" in "+kitchenType+" of "+feet+"."+inch);
			}
			if(new_cost != (base_cost - cost)){
				writer.println("Cost is not updating (increasing) for "+type+" unit "+name+" at price : "+cost+". oldcost : "+base_cost+" , newcost : "+new_cost+" in "+kitchenType);
			}
			base_space = new_base_space;
			base_cost = new_cost;
		}
	}
	
	public void removeTallUnits(String kitchenType,String feet, String inch) throws InterruptedException, IOException{
		List<WebElement> tallunits = driver.findElements(By.xpath("//*[@id='added-cabinets-Tall']/tr"));
		int base_space = convetorToInt(driver.findElement(By.xpath("//*[@id='remaining-length-Base']")).getText()),new_base_space=0;
		int wall_space = convetorToInt(driver.findElement(By.xpath("//*[@id='remaining-length-Wall']")).getText()),new_wall_space=0;
		int base_cost = convetorToInt(driver.findElement(By.xpath("//*[@id='kitchen-price']/div[1]/b[2]")).getText()),new_cost=0;
		int cost,width,bufferfactor=0,multiplicationfactor=1;
		String name;
		for (WebElement tallunit : tallunits) {
			String id = tallunit.getAttribute("id");
			name = driver.findElement(By.xpath("//*[@id='"+id+"']/td[1]/a")).getText();
			width = convetorToInt(driver.findElement(By.xpath("//*[@id='"+id+"']/td[2]")).getText());
			cost = convetorToInt(driver.findElement(By.xpath("//*[@id='"+id+"']/td[3]")).getText());
			driver.findElement(By.xpath("//*[@id='"+id+"']/td[4]/a/img")).click();
			Thread.sleep(3000);
			new_base_space = convetorToInt(driver.findElement(By.xpath("//*[@id='remaining-length-Base']")).getText());
			new_wall_space = convetorToInt(driver.findElement(By.xpath("//*[@id='remaining-length-Wall']")).getText());
			new_cost = convetorToInt(driver.findElement(By.xpath("//*[@id='kitchen-price']/div[1]/b[2]")).getText());
			if(name.equals("Corner Unit L-Shaped")){
				bufferfactor = width;
				multiplicationfactor=2;
			}else if(name.equals("Corner Unit Straight")){
				bufferfactor = 24;
				multiplicationfactor=1;
			}else{
				bufferfactor = 0;
				multiplicationfactor=1;
			}
			if(new_base_space != (base_space + bufferfactor+multiplicationfactor*width)){
				writer.println("Width is not updating (increasing) for tall unit "+name+" at width : "+width+". oldwidth : "+base_space+" , newwidth : "+new_base_space+" in "+kitchenType);
			}
			if(new_wall_space != (wall_space + bufferfactor+multiplicationfactor*width)){
				writer.println("Width is not updating (increasing) for tall unit "+name+" at width : "+width+". oldwidth : "+wall_space+" , newwidth : "+new_wall_space+" in "+kitchenType);
			}
			if(new_cost != (base_cost - cost)){
				writer.println("Cost is not updating (increasing) for tall units at price : "+cost+". oldcost : "+base_cost+" , newcost : "+new_cost+" in "+kitchenType);	
			}
			base_space = new_base_space;
			wall_space = new_wall_space;
			base_cost = new_cost;
		}
	}
	
	public void compareSpaces(String kitchenType,String feet, String inch, int buffer) throws IOException{
		int base_space = convetorToInt(driver.findElement(By.xpath("//*[@id='remaining-length-Base']")).getText());
		int wall_space = convetorToInt(driver.findElement(By.xpath("//*[@id='remaining-length-Wall']")).getText());
		if(base_space != wall_space + buffer){
			writer.println("After removing all items base and wall space are not equal in "+kitchenType+" for "+feet+"."+inch);
		}
		if(base_space != (Integer.parseInt(feet)*12+Integer.parseInt(inch))){
			writer.println("After removing all items base and given dimensions are not equal in "+kitchenType+" for "+feet+"."+inch);
		}
	}
	
	public static int convetorToInt(String strValue) {
		int intValue=0;
		for (int i=0;i<strValue.length();i++){
			if(Character.isDigit(strValue.charAt(i))){
				intValue = intValue*10 + Character.getNumericValue(strValue.charAt(i));
			}
		}
		if (strValue.contains("-"))
			return -1*intValue;
		return intValue;
	}
	
	
	public static int randInt(int min,int max) {
	    Random rand = new Random();
	    return rand.nextInt((max - min) + 1) + min;
	}
}

