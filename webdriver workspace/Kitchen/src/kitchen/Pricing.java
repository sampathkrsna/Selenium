package kitchen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
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

public class Pricing {
	static WebDriver driver;
	static String message = "";
	String project_path = System.getProperty("user.dir");
	static PrintWriter writer;
	static File inputWorkbook;
	static Workbook workbook;
	static String bodymaterial[]={"Boiling Water Proof (BWP) Plywood with lamination","Pre-laminated MDF","Pre-laminated Particle board"};
	static String doormaterial[]={"MDF with Acrylic Finish","MDF Membrane","Boiling Water Proof (BWP) Plywood with lamination",
							"Pre-laminated MDF","Pre-laminated Particle board"};
	static String hardwarematerial[]={"Customfurnish","Hettich"};
	static String drawertype[]={"Regular","Softclose","Premium"};
	static double accessoriesmargin[]={1,1};
	static double bodymargin[]={1,1.05};
	
	@BeforeMethod
	@Parameters("url")
	public void beforeMethod(String url) throws InterruptedException, IOException, BiffException {
		inputWorkbook = new File(project_path+"/modules.xls");
		workbook = Workbook.getWorkbook(inputWorkbook);
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		String systemDate = dateFormat.format(date);
		File file = new File(project_path + "/XSLT_Reports/"+systemDate+"/errors/check_pricing.txt");
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
	
	public static void preprocess() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='layout-selection']/div[2]/ul/li[1]/div/div[1]")).click();
		String feet = Integer.toString(randInt(19, 30));
		String inches = Integer.toString(randInt(0, 11));
		new Select(driver.findElement(By.xpath("//*[@id='runningLengthFeet']"))).selectByValue(feet);
		new Select(driver.findElement(By.xpath("//*[@id='runningLengthInches']"))).selectByValue(inches);
	}

	@Test
	@Parameters("url")
	public void checkPricing(String url) throws InterruptedException,IOException, BiffException {
		preprocess();
		int bodynumber = 0, doornumber = 0,  hardwarenumber=1, drawernumber=0;
//		for(int bodynumber=0; bodynumber<bodymaterial.length ; bodynumber++){
//			for(int doornumber=0 ; doornumber<doormaterial.length ; doornumber++){
//				for(int hardwarenumber=0 ; hardwarenumber<hardwarematerial.length ; hardwarenumber++){
//					for(int drawernumber=0 ; drawernumber<drawertype.length ; drawernumber++){
						writer.println("Body Material : "+bodymaterial[bodynumber]);
						writer.println("Door Material : "+doormaterial[doornumber]);
						writer.println("Hardware Material : "+hardwarematerial[hardwarenumber]);
						writer.println("Drawer Type : "+drawertype[drawernumber]);
						writer.println("-------------------------------------------------------------------------");
						selectMaterials(bodynumber, doornumber, hardwarenumber,drawernumber);
						checkBaseUnits("baseUnits",bodynumber,doornumber,hardwarenumber,drawernumber);
						checkWallUnits("wallUnits",bodynumber,doornumber,hardwarenumber,drawernumber);
						checkTallUnits("tallUnits",bodynumber,doornumber,hardwarenumber,drawernumber);
						writer.println();
						writer.println();
//					}
//				}
//			}
//		}
	}
		
	public void checkBaseUnits(String unitname,int bodymaterial,int doormaterial,int hardware,int doortype) throws InterruptedException,IOException, BiffException {
		driver.findElement(By.xpath("//*[@id='atab-"+unitname+"']")).click();
		Thread.sleep(3000);
		List<WebElement> allunits = driver.findElements(By.xpath("//*[@id='tab-"+unitname+"']/ol/li"));
		int noofUnits = allunits.size(),unitCost;
		for(int i=1 ; i<=noofUnits ; i++){
			String itemName =  driver.findElement(By.xpath("//*[@id='tab-"+unitname+"']/ol/li["+i+"]/div/div[1]/div[2]/div/p")).getText();
			unitCost = convetorToInt(driver.findElement(By.xpath("//*[@id='tab-"+unitname+"']/ol/li["+i+"]/div/div[2]/div/div[1]/span[2]")).getText());
			int row = getRow("Modules",itemName);
			if(row != -1){
				int carcasscost = getInt("Modules", row, 9+bodymaterial);
				int shuttercost = getInt("Modules", row, 13+doormaterial);
				String components = getContent("Modules", row, 19);
				int componentscost = getCostComponents(components, bodymaterial,hardware,doortype);
				String shelves = getContent("Modules", row, 20);
				int shelvescost = getCostShelves(shelves, bodymaterial);
				String accessories = getContent("Modules", row, 21);
				int accessoriescost = getCostAccessories(accessories);
				int totalcost = (int)(2*(bodymargin[hardware]*(carcasscost+shuttercost)+(0.7*componentscost)+shelvescost)+accessoriescost*accessoriesmargin[hardware]);
				if((totalcost<(unitCost-3)) || (totalcost>(unitCost+3))){
					writer.println("2*("+bodymargin[hardware]+"*("+carcasscost+"+"+shuttercost+")+0.7*"+componentscost+"+"+shelvescost+")+"+accessoriescost+"*"+accessoriesmargin[hardware]);
					writer.println(itemName +" - "+totalcost+" - "+unitCost+" - "+unitname);
				}
			}
			else{
				writer.println(itemName + " element is not present in excel sheet - "+unitname);
			}
		}
	}
	
	public void checkWallUnits(String unitname,int bodymaterial,int doormaterial,int hardware,int doortype) throws InterruptedException,IOException, BiffException {
		driver.findElement(By.xpath("//*[@id='atab-"+unitname+"']")).click();
		Thread.sleep(3000);
		List<WebElement> allunits = driver.findElements(By.xpath("//*[@id='tab-"+unitname+"']/ol/li"));
		int noofUnits = allunits.size(),unitCost;
		for(int i=1 ; i<=noofUnits ; i++){
			String itemName =  driver.findElement(By.xpath("//*[@id='tab-"+unitname+"']/ol/li["+i+"]/div/div[1]/div[2]/div/p")).getText();
			if(!itemName.equals("Chimney Space")){
				unitCost = convetorToInt(driver.findElement(By.xpath("//*[@id='tab-"+unitname+"']/ol/li["+i+"]/div/div[2]/div/div[1]/span[2]")).getText());
				int row = getRow("Modules",itemName);
				if(row !=-1){
					int carcasscost = getInt("Modules", row, 9+bodymaterial);
					int shuttercost = getInt("Modules", row, 13+doormaterial);
					String components = getContent("Modules", row, 19);
					int componentscost = getCostComponents(components, bodymaterial,hardware,doortype);
					String shelves = getContent("Modules", row, 20);
					int shelvescost = getCostShelves(shelves, bodymaterial);
					String accessories = getContent("Modules", row, 21);
					int accessoriescost = getCostAccessories(accessories);
					int totalcost = (int)(2*(bodymargin[hardware]*(carcasscost+shuttercost)+(0.7*componentscost)+shelvescost)+accessoriescost*accessoriesmargin[hardware]);
					if((totalcost<(unitCost-3)) || (totalcost>(unitCost+3))){
						writer.println("2*("+bodymargin[hardware]+"*("+carcasscost+"+"+shuttercost+")+0.7*"+componentscost+"+"+shelvescost+")+"+accessoriescost+"*"+accessoriesmargin[hardware]);
						writer.println(itemName +" - "+totalcost+" - "+unitCost+" - "+unitname);
					}
				}
				else{
					writer.println(itemName + " element is not present in excel sheet - "+unitname);
				}
			}
		}
	}
	
	public void checkTallUnits(String unitname,int bodymaterial,int doormaterial,int hardware,int doortype) throws InterruptedException,IOException, BiffException {
		driver.findElement(By.xpath("//*[@id='atab-"+unitname+"']")).click();
		Thread.sleep(3000);
		List<WebElement> allunits = driver.findElements(By.xpath("//*[@id='tab-"+unitname+"']/ol/li"));
		int noofUnits = allunits.size(),unitCost;
		for(int i=1 ; i<=noofUnits ; i++){
			String itemName =  driver.findElement(By.xpath("//*[@id='tab-"+unitname+"']/ol/li["+i+"]/div/div[1]/div[2]/div/p")).getText();
			unitCost = convetorToInt(driver.findElement(By.xpath("//*[@id='tab-"+unitname+"']/ol/li["+i+"]/div/div[2]/div/div[1]/span[2]")).getText());
			int row = getRow("Modules",itemName);
			if(row != -1){
				float carcasscost = getFloat("Modules", row, 9+bodymaterial);
				float shuttercost = getFloat("Modules", row, 13+doormaterial);
				String components = getContent("Modules", row, 19);
				int componentscost = getCostComponents(components, bodymaterial,hardware,doortype);
				String shelves = getContent("Modules", row, 20);
				int shelvescost = getCostShelves(shelves, bodymaterial);
				String accessories = getContent("Modules", row, 21);
				int accessoriescost = getCostAccessories(accessories);
				int totalcost = (int)(2*(bodymargin[hardware]*(carcasscost+shuttercost)+(0.7*componentscost)+shelvescost)+accessoriescost*accessoriesmargin[hardware]);
				if((totalcost<(unitCost-3)) || (totalcost>(unitCost+3))){
					writer.println("2*("+bodymargin[hardware]+"*("+carcasscost+"+"+shuttercost+")+0.7*"+componentscost+"+"+shelvescost+")+"+accessoriescost+"*"+accessoriesmargin[hardware]);
					writer.println(itemName +" - "+totalcost+" - "+unitCost+" - "+unitname);
				}
			}
			else{
				writer.println(itemName + " element is not present in excel sheet - "+unitname);
			}
		}
	}
	
	public static void selectMaterials(int bodymaterialnumber, int doormaterialnumber, int hardwarematerialnumber, int doortypenumber) throws InterruptedException {
		Thread.sleep(3000);	
		new Select(driver.findElement(By.xpath("//*[@id='bodyMaterial-selection']"))).selectByIndex(bodymaterialnumber);
		Thread.sleep(3000);
		new Select(driver.findElement(By.xpath("//*[@id='doorMaterial-selection']"))).selectByIndex(doormaterialnumber);
		Thread.sleep(3000);
		new Select(driver.findElement(By.xpath("//*[@id='Hardware-selection']"))).selectByIndex(hardwarematerialnumber);
		Thread.sleep(3000);
		new Select(driver.findElement(By.xpath("//*[@id='drawerSelection']"))).selectByIndex((hardwarematerialnumber*2)+(hardwarematerialnumber+doortypenumber));
		Thread.sleep(10000);
	}

	public int getRow(String sheetname,String unitname) throws IOException, BiffException  {
		Sheet sheet = workbook.getSheet(sheetname);
		int rows = sheet.getRows();
		for (int i = 0; i < rows; i++) {
			Cell cell = sheet.getCell(1, i);
			if(cell.getContents().equals(unitname)){
				return i;
			}
		}
		return -1;
	}
	
	public int getInt(String sheetname,int row,int column) throws IOException, BiffException  {
		Sheet sheet = workbook.getSheet(sheetname);
		String content = sheet.getCell(column, row).getContents();
		return Math.round(Float.valueOf(content));
	}
	
	public float getFloat(String sheetname,int row,int column) throws IOException, BiffException  {
		Sheet sheet = workbook.getSheet(sheetname);
		String content = sheet.getCell(column, row).getContents();
		return Float.valueOf(content);
	}
	
	public String getContent(String sheetname,int row,int column) throws IOException, BiffException  {
		Sheet sheet = workbook.getSheet(sheetname);
		String content = sheet.getCell(column, row).getContents();
		return content;
	}

	public static int getCostComponents(String itemsarray, int material,int hardware,int drawertype) {
		if(!itemsarray.equals("")){
			List<String> items = Arrays.asList(itemsarray.split(","));
			int totalcost = 0,unitcost = 0;
			int coloumn = hardware+drawertype+1;
			Sheet sheet = workbook.getSheet("Components");
			int rows = sheet.getRows();
			int componentsnumber = items.size();
			for(int component=0;component <componentsnumber;component++){
				for (int row = 0; row < rows; row++) {
					Cell cell = sheet.getCell(0, row);
					if(cell.getContents().equals(items.get(component))){
						String content = sheet.getCell(coloumn, row).getContents();
						unitcost = Math.round(Float.valueOf(content));
						totalcost = totalcost + unitcost;
					}
				}
			}
			return totalcost;
		}
		return 0;
	}

	public static int getCostShelves(String itemsarray, int material) {
		if(!itemsarray.equals("")){
			List<String> items = Arrays.asList(itemsarray.split(","));
			int column = 0,totalcost = 0,unitcost = 0;
			if(material == 0){
				column = 6;
			}else if (material == 1) {
				column = 5;
			}else {
				column = 4;
			}
			Sheet sheet = workbook.getSheet("Shelves");
			int rows = sheet.getRows();
			int componentsnumber = items.size();
			for(int component=0;component <componentsnumber;component++){
				for (int row = 0; row < rows; row++) {
					Cell cell = sheet.getCell(0, row);
					if(cell.getContents().equals(items.get(component))){
						String content = sheet.getCell(column, row).getContents();
						unitcost = Math.round(Float.valueOf(content));
						totalcost = totalcost + unitcost;
					}
				}
			}
			return totalcost;
		}
		return 0;
	}

	public static int getCostAccessories(String itemsarray) {
		if(!itemsarray.equals("")){
			List<String> items = Arrays.asList(itemsarray.split(","));
			int totalcost = 0,unitcost = 0;
			Sheet sheet = workbook.getSheet("Accessories");
			int rows = sheet.getRows();
			int componentsnumber = items.size();
			for(int component=0;component < componentsnumber;component++){
				for (int row = 0; row < rows; row++) {
					Cell cell = sheet.getCell(0, row);
					if(cell.getContents().equals(items.get(component))){
						String content = sheet.getCell(2, row).getContents();
						unitcost = Math.round(Float.valueOf(content));
						totalcost = totalcost + unitcost;
					}
				}
			}
			return totalcost;
		}
		return 0;
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
	
	public static void checkLoader() {
		while(driver.findElement(By.xpath("//*[@id='resultLoading']")).isDisplayed());
	}
}
