package kustommadefriday;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import junit.framework.Assert;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.javascript.host.Document;
import com.gargoylesoftware.htmlunit.javascript.host.Element;
import com.gargoylesoftware.htmlunit.javascript.host.html.HTMLCollection;




public class firsttest {
	
	public static int randInt(int min, int max) {

	    // NOTE: Usually this should be a field rather than a method
	    // variable so that it is not re-seeded every call.
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	 
	 
	public static void main(String[] args) throws InterruptedException, IOException, RowsExceededException, WriteException {
		// TODO Auto-generated method stub

		
		
		WebDriver driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://ec2-175-41-189-22.ap-southeast-1.compute.amazonaws.com:8001/?secret=bangalore");
		System.out.println("Pass-1");
		Thread.sleep(10000);
		WebElement Subemail = driver.findElement(By.xpath(".//*[@id='emailIdInModal']"));
		System.out.println("Pass-2");
		Subemail.sendKeys("sampathkrsn@gmail.com");
		System.out.println("Pass-3");
		WebElement Subemailbuton = driver.findElement(By.xpath(".//*[@id='SubscriptionModal']/div/form/div[2]/div/a"));
		System.out.println("Pass-4");
		Subemailbuton.click();
		System.out.println("Pass-5");
		WebElement Subemailalert = driver.findElement(By.xpath(".//*[@id='fail']"));

		System.out.println("Pass-6");
		Subemailalert.click();
		System.out.println("Pass-7");
		Thread.sleep(4000);
		WebElement Subemailbuton1 = driver.findElement(By.xpath(".//*[@id='caption1']/div/div[2]/div[1]/a"));
		System.out.println("Pass-8");
		Subemailbuton1.click();
		
	//	if(Submailalert.isElementPresent(By.xpath(".//*[@id='fail']")))
		System.out.println("Subscribtion Succesfull ");
		
	
		WebElement Width = driver.findElement(By.xpath(".//*[@id='myText']"));
		Width.clear();
		Width.sendKeys("89 ");
		System.out.println("Width Entering:Pass");
		WebElement Height = driver.findElement(By.xpath(".//*[@id='heightOfCloset'] "));
		Height.clear();
		Height.sendKeys("84");
		System.out.println("Height Entering:Pass");
		System.out.println(driver.getCurrentUrl());
		WebElement page = driver.findElement(By.xpath(".//*[@id='navigateHere']/div[3]/div/div/div/div/a"));
		page.click();
		System.out.println("Height & Width entered and moved to next page");
		WebElement paneldoor = driver.findElement(By.xpath(".//*[@id='kmBody']/div[5]/div[2]/div[2]/a"));
		paneldoor.click();
		System.out.println("Full panel door clicked");
		
		Thread.sleep(15000);
		WebElement y = driver.findElement(By.xpath("/html/body/div[5]/div[3]/div[1]/label"));
		System.out.println(y.getText());
		WebElement pageshiftz = driver.findElement(By.xpath("/html/body/div[11]/div[1]/div[2]/dl/dd[1]/select/option[1]"));
		pageshiftz.click();
		Thread.sleep(5000);
		WebElement z = driver.findElement(By.xpath(" "));
		System.out.println(z.getText());
		
		WebElement pageshiftx = driver.findElement(By.xpath("/html/body/div[9]/div[1]/div[2]/dl/dd[2]/select/option[2]"));
		pageshiftz.click();
		Thread.sleep(5000);
		WebElement zx = driver.findElement(By.xpath(" /html/body/div[5]/div[3]/div[1]/label "));
		System.out.println(zx.getText());
		
		WebElement pageshifty = driver.findElement(By.xpath("/html/body/div[9]/div[1]/div[2]/dl/dd[2]/select/option[3]"));
		pageshifty.click();
		Thread.sleep(5000);
		WebElement zxy = driver.findElement(By.xpath(" /html/body/div[5]/div[3]/div[1]/label"));
		System.out.println(zxy.getText());
		WebElement pageshifta = driver.findElement(By.xpath("/html/body/div[9]/div[1]/div[2]/dl/dd[2]/select/option[4]"));
		pageshifta.click();
		Thread.sleep(5000);
		WebElement zxya = driver.findElement(By.xpath(" /html/body/div[5]/div[3]/div[1]/label "));
		System.out.println(zxya.getText());
		
		WebElement pageshiftb = driver.findElement(By.xpath("/html/body/div[9]/div[1]/div[2]/dl/dd[2]/select/option[5]"));
		pageshiftb.click();
		Thread.sleep(5000);
		WebElement zxyb = driver.findElement(By.xpath(" /html/body/div[5]/div[3]/div[1]/label"));
		System.out.println(zxyb.getText());
		
		WebElement pageshiftc = driver.findElement(By.xpath("/html/body/div[9]/div[1]/div[2]/dl/dd[2]/select/option[6]"));
		pageshifta.click();
		Thread.sleep(5000);
		WebElement zxyc = driver.findElement(By.xpath(" /html/body/div[5]/div[3]/div[1]/label "));
		System.out.println(zxya.getText());
		
		
		
		WebElement pageshift2 = driver.findElement(By.xpath(".//*[@id='kmBody']/div[11]/div[2]/div[7]/ul/li/div/a "));
		pageshift2.click();
		System.out.println(" entered Exteriors page");

		Thread.sleep(15000);
		
		WebElement doorcolor = driver.findElement(By.xpath(" /html/body/div[3]/div[1]/div[2]/dl/dd[4]/a"));
		doorcolor.click();
		System.out.println(" Door Color Selector : Pass");
		
		 //for(int x= 1; x<27; x=x++);
		WebElement elements = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[2]/dl/dd[4]/div/ul/li[1]/img "));
		
    //  List<WebElement> a = driver.findElements(By.className("itemdrag.patternList.MDFMembrane.ui-draggable.selected"));
		/* System.out.println("Clicking on "+index);
		WebElement element = a.get(index);  */
				
		elements.click(); 
		
	/*	WebElement doorcolorSelection = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[2]/dl/dd[4]/div/ul/li[x]/img "));
		doorcolorSelection.click();*/
		System.out.println(" Door Color changed : Pass"); 
		
		WebElement x = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/div[2]/div/ul[1]/li[1]/a"));
		System.out.println(x.getText());
		
		
		Thread.sleep(15000);
		WebElement element1 = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/div[4]/ul/li[2]/a"));
	element1.click();
	System.out.println("Accesories page entered ");
	Thread.sleep(15000);
	
	WebElement element2 = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/div[4]/ul/li[2]/a"));
	element2.click();
	System.out.println("Order page entered ");
	Thread.sleep(15000);
		
		/*WebElement sele = driver.findElement(By.className("cusionCount"));
		Select dropDown=new Select(sele);
		dropDown.selectByVisibleText("2")
		
/* 1		WebElement bingo3 = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div[3]/dl/dd[1]/div/ul/li[1]/img "));
		bingo3.click();
*/
		
		WebElement bingo = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div[3]/dl/dd[2]/a "));
		bingo.click(); 
		
		WebElement bingo1 = driver.findElement(By.xpath("/html/body/div[5]/div[3]/div[1]/label"));
		System.out.println(bingo1.getText());
		WebElement bingo2 = driver.findElement(By.xpath(".//*[@id='design']/td[6]/span[2]"));
		System.out.println(bingo2.getText());
		WebElement bingo4 = driver.findElement(By.xpath(".//*[@id='grandTotal']"));
		System.out.println(bingo4.getText());
		
		WebElement bingo3 = driver.findElement(By.xpath(" /html/body/div[4]/div[2]/div/div[2]/ul[2]/li[2]/a "));
		bingo3.click();
		
		Thread.sleep(15000);	
		
		WebElement Cart = driver.findElement(By.xpath(" /html/body/div[1]/div[1]/div/div/div[2]/ul/li[2]/a "));
		System.out.println(Cart.getText());
		
		WebElement Cartval = driver.findElement(By.xpath(" .//*[@id='drop-material'] "));
		System.out.println(Cartval.getText());
		
		
		
	/*	File fExcel = new File("E:\\sampath\\createexcel.xls");
		WritableWorkbook writableBook = Workbook.createWorkbook(fExcel);
			writableBook.createSheet("Data", 0);
			WritableSheet writableSheet = writableBook.getSheet(0);
			Label data1 = new Label(0,0,Start.getText());
			 writableSheet.addCell(data1);
			Label data2 = new Label(0,1,Cartval.getText());
			 writableSheet.addCell(data2);
			 
			 writableBook.write();
			 writableBook.close(); */
			
			 
		
		
		System.out.println(driver.getTitle());
	    System.out.println(driver.getCurrentUrl());
	}


	private static void findElement(By xpath) {
		// TODO Auto-generated method stub
		
	}
	

}
