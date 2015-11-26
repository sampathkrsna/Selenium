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

public class AddUnits {
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
		File file = new File(project_path + "/XSLT_Reports/"+systemDate+"/errors/add_units_kitchen.txt");
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
	public void addUnits(String url) throws InterruptedException,IOException {
		addElements();
	}
	
	public void addElements() throws InterruptedException, IOException{
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@class='kitchen-start-design-button']")).click();
		addElementsLShapedKitchen();
		addElementsStraightKitchen();
		addElementsUShapedKitchen();
		addElementsParallelKitchen();		
	}
	
	public void addElementsLShapedKitchen() throws InterruptedException, IOException{
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='layout-selection']/div[2]/ul/li[1]/div/div[1]")).click();
		String feet = Integer.toString(randInt(11, 30));
		String inches = Integer.toString(randInt(0, 11));
		new Select(driver.findElement(By.xpath("//*[@id='runningLengthFeet']"))).selectByValue(feet);
		new Select(driver.findElement(By.xpath("//*[@id='runningLengthInches']"))).selectByValue(inches);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath("//*[@id='whatIncluded']")));
		Thread.sleep(2000);
		removeBaseUnits();
		removeWallUnits();
		removeTallUnits();
		String kitchenType = "LShapedKitchen";
		addBaseUnits(kitchenType,feet,inches);
		addWallUnits(kitchenType,feet,inches);
		addTallUnits(kitchenType,feet,inches);
		addAccessoriesUnits(kitchenType,feet,inches);
	}
	
	public void addElementsStraightKitchen() throws InterruptedException, IOException{
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='layout-selection']/div[2]/ul/li[2]/div/div[1]")).click();
		String feet = Integer.toString(randInt(4, 30));
		String inches = Integer.toString(randInt(0, 11));
		new Select(driver.findElement(By.xpath("//*[@id='runningLengthFeet']"))).selectByValue(feet);
		new Select(driver.findElement(By.xpath("//*[@id='runningLengthInches']"))).selectByValue(inches);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath("//*[@id='whatIncluded']")));
		Thread.sleep(2000);
		removeBaseUnits();
		removeWallUnits();
		removeTallUnits();
		String kitchenType = "StraightKitchen";
		addBaseUnits(kitchenType,feet,inches);
		addWallUnits(kitchenType,feet,inches);
		addTallUnits(kitchenType,feet,inches);
		addAccessoriesUnits(kitchenType,feet,inches);
	}
	
	public void addElementsUShapedKitchen() throws InterruptedException, IOException{
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='layout-selection']/div[2]/ul/li[3]/div/div[1]")).click();
		String feet = Integer.toString(randInt(17, 30));
		String inches = Integer.toString(randInt(0, 11));
		new Select(driver.findElement(By.xpath("//*[@id='runningLengthFeet']"))).selectByValue(feet);
		new Select(driver.findElement(By.xpath("//*[@id='runningLengthInches']"))).selectByValue(inches);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath("//*[@id='whatIncluded']")));
		Thread.sleep(2000);
		removeBaseUnits();
		removeWallUnits();
		removeTallUnits();
		String kitchenType = "UShapedKitchen";
		addBaseUnits(kitchenType,feet,inches);
		addWallUnits(kitchenType,feet,inches);
		addTallUnits(kitchenType,feet,inches);
		addAccessoriesUnits(kitchenType,feet,inches);
	}
	
	public void addElementsParallelKitchen() throws InterruptedException, IOException{
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='layout-selection']/div[2]/ul/li[4]/div/div[1]")).click();
		String feet = Integer.toString(randInt(4, 30));
		String inches = Integer.toString(randInt(0, 11));
		new Select(driver.findElement(By.xpath("//*[@id='runningLengthFeet']"))).selectByValue(feet);
		new Select(driver.findElement(By.xpath("//*[@id='runningLengthInches']"))).selectByValue(inches);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath("//*[@id='whatIncluded']")));
		Thread.sleep(2000);
		removeBaseUnits();
		removeWallUnits();
		removeTallUnits();
		String kitchenType = "ParallelKitchen";
		addBaseUnits(kitchenType,feet,inches);
		addWallUnits(kitchenType,feet,inches);
		addTallUnits(kitchenType,feet,inches);
		addAccessoriesUnits(kitchenType,feet,inches);
	}
	
	public void addBaseUnits(String kitchenType,String feet,String inch) throws InterruptedException, IOException{
		driver.findElement(By.xpath("//*[@id='atab-baseUnits']")).click();
		Thread.sleep(3000);
		List<WebElement> allunits = driver.findElements(By.xpath("//*[@id='tab-baseUnits']/ol/li"));
		int noofUnits = allunits.size();
		int new_base_space = convetorToInt(driver.findElement(By.xpath("//*[@id='remaining-length-Base']")).getText()),base_space=0;
		int new_cost = convetorToInt(driver.findElement(By.xpath("//*[@id='kitchen-price']/div[1]/b[2]")).getText()),base_cost=0;
		int index,width,unitCost,bufferfactor=0;
		for(int i=1 ; i<=noofUnits ; i++){
			String itemName =  driver.findElement(By.xpath("//*[@id='tab-baseUnits']/ol/li["+i+"]/div/div[1]/div[2]/div/p")).getText();
			String dimensions = driver.findElement(By.xpath("//*[@id='tab-baseUnits']/ol/li["+i+"]/div/div[1]/div[2]/p")).getText();
			index = dimensions.indexOf(":");
			width = convetorToInt(dimensions.substring(index,index+3));
			if(width*2 > new_base_space){
				removeBaseUnits();
				base_space = convetorToInt(driver.findElement(By.xpath("//*[@id='remaining-length-Base']")).getText());
				base_cost = convetorToInt(driver.findElement(By.xpath("//*[@id='kitchen-price']/div[1]/b[2]")).getText());
			}
			else{
				base_space = new_base_space;
				base_cost = new_cost;
			}
			if(itemName.equals("Corner Unit L-Shaped")){
				bufferfactor = width;
			}else if(itemName.equals("Corner Unit Straight")){
				bufferfactor = 24;
			}else{
				bufferfactor = 0;
			}
			unitCost = convetorToInt(driver.findElement(By.xpath("//*[@id='tab-baseUnits']/ol/li["+i+"]/div/div[2]/div/div[1]/span[2]")).getText());
			driver.findElement(By.xpath("//*[@id='tab-baseUnits']/ol/li["+i+"]/div/div[2]/div/div[2]/a")).click();
			Thread.sleep(3000);
			new_base_space = convetorToInt(driver.findElement(By.xpath("//*[@id='remaining-length-Base']")).getText());
			new_cost = convetorToInt(driver.findElement(By.xpath("//*[@id='kitchen-price']/div[1]/b[2]")).getText());
			if(new_base_space != (base_space - bufferfactor - width)){
				writer.println("Width is not updating (decreasing) for base units at width : "+width+". oldwidth : "+base_space+" , newwidth : "+new_base_space+" for "+itemName+" of "+feet+"."+inch);
			}
			if(new_cost != (base_cost + unitCost)){
				writer.println("Cost is not updating (decreasing) for base units at price : "+unitCost+". oldcost : "+base_cost+" , newcost : "+new_cost+" for "+itemName+" of "+feet+"."+inch);
			}
		}
		removeBaseUnits();
	}
	
	public void addWallUnits(String kitchenType,String feet,String inch) throws InterruptedException, IOException{
		driver.findElement(By.xpath("//*[@id='atab-wallUnits']")).click();
		Thread.sleep(3000);
		List<WebElement> allunits = driver.findElements(By.xpath("//*[@id='tab-wallUnits']/ol/li"));
		int noofUnits = allunits.size();
		int new_wall_space = convetorToInt(driver.findElement(By.xpath("//*[@id='remaining-length-Wall']")).getText()),wall_space=0;
		int new_cost = convetorToInt(driver.findElement(By.xpath("//*[@id='kitchen-price']/div[1]/b[2]")).getText()),base_cost=0;
		int index,width,unitCost,bufferfactor=0;
		for(int i=1 ; i<=noofUnits ; i++){
			String itemName =  driver.findElement(By.xpath("//*[@id='tab-wallUnits']/ol/li["+i+"]/div/div[1]/div[2]/div/p")).getText();
			if(!itemName.equals("Chimney Space")){
				String dimensions = driver.findElement(By.xpath("//*[@id='tab-wallUnits']/ol/li["+i+"]/div/div[1]/div[2]/p")).getText();
				index = dimensions.indexOf(":");
				width = convetorToInt(dimensions.substring(index,index+3));
				if(width > new_wall_space){
					removeWallUnits();
					wall_space = convetorToInt(driver.findElement(By.xpath("//*[@id='remaining-length-Wall']")).getText());
					base_cost = convetorToInt(driver.findElement(By.xpath("//*[@id='kitchen-price']/div[1]/b[2]")).getText());
				}
				else{
					wall_space = new_wall_space;
					base_cost = new_cost;
				}
				if(itemName.equals("Corner Unit L-Shaped")){
					bufferfactor = width;
				}else if(itemName.equals("Corner Unit Straight")){
					bufferfactor = 24;
				}else{
					bufferfactor = 0;
				}
				unitCost = convetorToInt(driver.findElement(By.xpath("//*[@id='tab-wallUnits']/ol/li["+i+"]/div/div[2]/div/div[1]/span[2]")).getText());
				driver.findElement(By.xpath("//*[@id='tab-wallUnits']/ol/li["+i+"]/div/div[2]/div/div[2]/a")).click();
				Thread.sleep(3000);
				new_wall_space = convetorToInt(driver.findElement(By.xpath("//*[@id='remaining-length-Wall']")).getText());
				new_cost = convetorToInt(driver.findElement(By.xpath("//*[@id='kitchen-price']/div[1]/b[2]")).getText());
				if(new_wall_space != (wall_space - bufferfactor - width)){
					writer.println("Width is not updating (decreasing) for wall units at width : "+width+". oldwidth : "+wall_space+" , newwidth : "+new_wall_space+" for "+itemName+" of "+feet+"."+inch);
				}
				if(new_cost != (base_cost + unitCost)){
					writer.println("Cost is not updating (decreasing) for wall units at price : "+unitCost+". oldcost : "+base_cost+" , newcost : "+new_cost+" for "+itemName+" of "+feet+"."+inch);
				}
			}
		}
		removeWallUnits();
	}
	
	public void addTallUnits(String kitchenType,String feet,String inch) throws InterruptedException, IOException{
		driver.findElement(By.xpath("//*[@id='atab-tallUnits']")).click();
		Thread.sleep(3000);
		List<WebElement> allunits = driver.findElements(By.xpath("//*[@id='tab-tallUnits']/ol/li"));
		int noofUnits = allunits.size();
		int new_base_space = convetorToInt(driver.findElement(By.xpath("//*[@id='remaining-length-Base']")).getText()),base_space=0;
		int new_wall_space = convetorToInt(driver.findElement(By.xpath("//*[@id='remaining-length-Wall']")).getText()),wall_space=0;
		int new_cost = convetorToInt(driver.findElement(By.xpath("//*[@id='kitchen-price']/div[1]/b[2]")).getText()),base_cost=0;
		int index,width,unitCost,bufferfactor=0;
		for(int i=1 ; i<=noofUnits ; i++){
			String itemName =  driver.findElement(By.xpath("//*[@id='tab-tallUnits']/ol/li["+i+"]/div/div[1]/div[2]/div/p")).getText();
			String dimensions = driver.findElement(By.xpath("//*[@id='tab-tallUnits']/ol/li["+i+"]/div/div[1]/div[2]/p")).getText();
			index = dimensions.indexOf(":");
			width = convetorToInt(dimensions.substring(index,index+3));
			if((width > new_base_space) || (width > new_wall_space)){ 
				removeTallUnits();
				base_space = convetorToInt(driver.findElement(By.xpath("//*[@id='remaining-length-Base']")).getText());
				wall_space = convetorToInt(driver.findElement(By.xpath("//*[@id='remaining-length-Wall']")).getText());
				base_cost = convetorToInt(driver.findElement(By.xpath("//*[@id='kitchen-price']/div[1]/b[2]")).getText());
			}
			else{
				base_space = new_base_space;
				wall_space = new_wall_space;
				base_cost = new_cost;
			}
			if(itemName.equals("Corner Unit L-Shaped")){
				bufferfactor = width;
			}else if(itemName.equals("Corner Unit Straight")){
				bufferfactor = 24;
			}else{
				bufferfactor = 0;
			}
			unitCost = convetorToInt(driver.findElement(By.xpath("//*[@id='tab-tallUnits']/ol/li["+i+"]/div/div[2]/div/div[1]/span[2]")).getText());
			driver.findElement(By.xpath("//*[@id='tab-tallUnits']/ol/li["+i+"]/div/div[2]/div/div[2]/a")).click();
			Thread.sleep(3000);
			new_base_space = convetorToInt(driver.findElement(By.xpath("//*[@id='remaining-length-Base']")).getText());
			new_wall_space = convetorToInt(driver.findElement(By.xpath("//*[@id='remaining-length-Wall']")).getText());
			new_cost = convetorToInt(driver.findElement(By.xpath("//*[@id='kitchen-price']/div[1]/b[2]")).getText());
			if(new_base_space != (base_space - bufferfactor-width)){
				writer.println("Width is not updating (decreasing) for base units at width : "+width+". oldwidth : "+base_space+" , newwidth : "+new_base_space+" for "+itemName+" of "+feet+"."+inch);
				
			}
			if(new_wall_space != (wall_space - bufferfactor -width)){
				writer.println("Width is not updating (decreasing) for wall units at width : "+width+". oldwidth : "+wall_space+" , newwidth : "+new_wall_space+" for "+itemName+" of "+feet+"."+inch);
				
			}
			if(new_cost != (base_cost + unitCost)){
				writer.println("Cost is not updating (decreasing) for base units at price : "+unitCost+". oldcost : "+base_cost+" , newcost : "+new_cost+" for "+itemName+" of "+feet+"."+inch);
				
			}
		}
		removeWallUnits();
	}
	
	public void addAccessoriesUnits(String kitchenType,String feet,String inch) throws InterruptedException, IOException{
		driver.findElement(By.xpath("//*[@id='atab-accessories']")).click();
		Thread.sleep(3000);
		List<WebElement> allunits = driver.findElements(By.xpath("//*[@id='tab-accessories']/ol/li"));
		int noofUnits = allunits.size();
		int new_cost = convetorToInt(driver.findElement(By.xpath("//*[@id='kitchen-price']/div[1]/b[2]")).getText()),base_cost=0;
		int unitCost;
		for(int i=1 ; i<=noofUnits ; i++){
			String itemName =  driver.findElement(By.xpath("//*[@id='tab-accessories']/ol/li["+i+"]/div/div[1]/div[2]/div/p")).getText();
			base_cost = new_cost;
			unitCost = convetorToInt(driver.findElement(By.xpath("//*[@id='tab-accessories']/ol/li["+i+"]/div/div[2]/div/div[1]/span[2]")).getText());
			driver.findElement(By.xpath("//*[@id='tab-accessories']/ol/li["+i+"]/div/div[2]/div/div[2]/a")).click();
			Thread.sleep(3000);
			new_cost = convetorToInt(driver.findElement(By.xpath("//*[@id='kitchen-price']/div[1]/b[2]")).getText());
			if(new_cost != (base_cost + unitCost)){
				writer.println("Cost is not updating (decreasing) for base units at price : "+unitCost+". oldcost : "+base_cost+" , newcost : "+new_cost+" for "+itemName+" of "+feet+"."+inch);	
			}
		}
	}
	
	public void removeBaseUnits() throws InterruptedException, IOException{
		List<WebElement> baseunits = driver.findElements(By.xpath("//*[@id='added-cabinets-Base']/tr"));
		for (WebElement baseunit : baseunits) {
			String id = baseunit.getAttribute("id");
			driver.findElement(By.xpath("//*[@id='"+id+"']/td[4]/a/img")).click();
		}
	}
	
	public void removeWallUnits() throws InterruptedException, IOException{
		List<WebElement> baseunits = driver.findElements(By.xpath("//*[@id='added-cabinets-Wall']/tr"));
		for (WebElement baseunit : baseunits) {
			String id = baseunit.getAttribute("id");
			driver.findElement(By.xpath("//*[@id='"+id+"']/td[4]/a/img")).click();
		}
	}
	
	public void removeTallUnits() throws InterruptedException, IOException{
		List<WebElement> baseunits = driver.findElements(By.xpath("//*[@id='added-cabinets-Tall']/tr"));
		for (WebElement baseunit : baseunits) {
			String id = baseunit.getAttribute("id");
			driver.findElement(By.xpath("//*[@id='"+id+"']/td[4]/a/img")).click();
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



