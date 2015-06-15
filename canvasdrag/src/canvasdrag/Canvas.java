package canvasdrag;



import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.WebDriverWait;

class Vertex{
	int x;
	int y;
	
	public String toString(){
		return "("+this.x+","+this.y+")";
	}
}

public class Canvas {

	private static WebDriver driver = null;

	/*public static void screenshots() throws InterruptedException, AWTException, IOException
	{
		  int i = 0;
		driver.findElement(By.xpath("//body")).sendKeys(Keys.chord(Keys.CONTROL,Keys.SHIFT, "i"));
		Thread.sleep(4000);
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		Robot robot = new Robot();
		BufferedImage img =robot.createScreenCapture(new Rectangle(size));
		File save_path=new File("E:\\sampath\\screen["+i+"].jpg");
		ImageIO.write(img,"JPG",save_path);
		Thread.sleep(3000);
		   File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				// Now you can do whatever you need to do with it, for example copy somewhere
				FileUtils.copyFile(scrFile, new File("E:\\sampath\\screenshotimporant["+i+"].jpg"));
	}*/
	public static int initialprice()
	{
		WebElement initialprice = driver.findElement(By.xpath(".//*[@id='price']/span[2]"));
		String ip= initialprice.getText(); 
		return Integer.parseInt(ip);
	}
	
	/*public  void pricecompare()
	{	if(FP==(IP))
	{
		
		
		
		
		System.out.println("An error in dragging the element ");

}
else
{
System.out.println("Dragging succesfully ");
}
	}*/
	
	public static void main(String[] args) throws InterruptedException, AWTException, IOException {
		// TODO Auto-generated method stub

		  ArrayList<Vertex> vertices= new ArrayList<Vertex>();
		WebDriver driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://root.test.kustommade.com/wardrobe/dimensions");
		System.out.println("Pass-1");
		Thread.sleep(5000);
		WebElement Subemail = driver.findElement(By.xpath("/html/body/div[1]/div[7]/div[1]/div[3]/form/div/div[1]/div[3]/div/ul/li[1]/select/option[12]"));
		System.out.println("Pass-2");
		Subemail.click();
		System.out.println("Pass-3");
	
		WebElement Subemailbuton1 = driver.findElement(By.xpath("/html/body/div[1]/div[7]/div[1]/div[3]/form/div/div[2]/a"));
		Subemailbuton1.click();
		System.out.println("Pass-4");
		Thread.sleep(5000);
		WebElement Subemailbuton2 = driver.findElement(By.xpath(".//*[@id='doorSelection']/div/div[1]/button"));
		Subemailbuton2.click();
		Thread.sleep(45000);
		WebElement Subemailbuton4 = driver.findElement(By.xpath(".//*[@id='mirrorToolBarItem']/label"));
		Subemailbuton4.click();
		Thread.sleep(20000);
		Object val = ((JavascriptExecutor)driver).executeScript("return[ window.app.design.DOOR.length]");
		 String s = val.toString();
		 String a =s.substring(1,2);
		 System.out.println(a);	
		  int b = Integer.parseInt(a); 
		 System.out.println(b);
	
		 
		 //DRAGS AND DROPS DOOR ONTO THE WARDROBE 
		 for(int i= 0;i<b;i++)
		 {
			 WebElement initialprice = driver.findElement(By.xpath(".//*[@id='price']/span[2]"));
				String ip= initialprice.getText(); 
				int IP = Integer.parseInt(ip);
			 
			Object val1 = ((JavascriptExecutor)driver).executeScript("return[window.app.design.DOOR[" +i+ "].X]");
			 String s1 = val1.toString();
			 int a1 = Integer.parseInt(s1.substring(1, s1.length() - 1)); 
			 System.out.println(a1);
			// System.out.println( of + "   " + a1);
			 WebElement canvas = driver.findElement(By.xpath(".//*[@id='mirrorToolBarItem']/div/ul/li[2]/img"));
				WebElement canvavParent = driver.findElement(By.xpath(".//*[@id='appExternalCanvas']"));
				
				 
		Actions builder= new Actions(driver);
		builder.clickAndHold(canvas);
		builder.moveToElement(canvavParent, a1+1, 0);
		
		builder.release();
		builder.perform();
	
		  Thread.sleep(10000);
		  
		  WebElement finalprice = driver.findElement(By.xpath(".//*[@id='price']/span[2]"));
		String fp=	finalprice.getText();
	int FP=Integer.parseInt(fp);
			if(FP==(IP))
			{
				driver.findElement(By.xpath("//body")).sendKeys(Keys.chord(Keys.CONTROL,Keys.SHIFT, "i"));
				Thread.sleep(4000);
				Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
				Robot robot = new Robot();
				BufferedImage img =robot.createScreenCapture(new Rectangle(size));
				File save_path=new File("E:\\sampath\\screen["+i+"].jpg");
				ImageIO.write(img,"JPG",save_path);
				Thread.sleep(3000);
				   File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
						// Now you can do whatever you need to do with it, for example copy somewhere
						FileUtils.copyFile(scrFile, new File("E:\\sampath\\screenshotimporant["+i+"].jpg"));
			
				
						
						System.out.println("An error in dragging the element ");
				
			}
			else
			{
				System.out.println("Dragging succesfully ");
			}
		 }
		  System.out.println("Pass-6");
		  Thread.sleep(5000);
	 
		  
		  WebElement Subemailbuton3 = driver.findElement(By.xpath(".//*[@id='internalDesignMode']"));
		System.out.println("Pass-5");
		Subemailbuton3.click();
		//ENTERING INTERIOR PAGE
		Thread.sleep(11000);
		 WebElement initialprice = driver.findElement(By.xpath(".//*[@id='price']/span[2]"));
			String ip= initialprice.getText(); 
			int IP = Integer.parseInt(ip);
		Object interunits =	((JavascriptExecutor) driver).executeScript("return[ window.app.design.SECTIONS.length]"); 
		//System.out.println(interunits);
		 String sec = interunits.toString();
		 int sect = Integer.parseInt(sec.substring(1, sec.length() - 1)); 
		 System.out.println(sect);
		 for(int shelf=0;shelf<sect;shelf++)
		 {
		 Object interdraw =	((JavascriptExecutor) driver).executeScript("return[ window.app.design.SECTIONS["+shelf+"].SUBSECTIONS.length]"); 
			 String interdr = interdraw.toString();
			 int intcor = Integer.parseInt(interdr.substring(1, interdr.length() - 1)); 
			 System.out.println(intcor);
			 for(int racks=0;racks<intcor;racks++)
			 {
				 Object intershelves =	((JavascriptExecutor) driver).executeScript("return[ window.app.design.SECTIONS["+shelf+"].SUBSECTIONS["+ racks+"].X]"); 
				 String intersh = intershelves.toString();
				 int ints = Integer.parseInt(intersh.substring(1, intersh.length() - 1));
				 Vertex vertex = new Vertex();
				 vertex.x = ints;
				 Object intershelvesy =	((JavascriptExecutor) driver).executeScript("return[ window.app.design.SECTIONS["+shelf+"].SUBSECTIONS["+ racks+"].Y]"); 
				 String intershy = intershelvesy.toString();
				 int intsy = Integer.parseInt(intershy.substring(1, intershy.length() - 1)); 
				 vertex.y = intsy;
				 vertices.add(vertex);
				 System.out.println("window.app.design.SECTIONS["+shelf+"].SUBSECTIONS["+ racks+"].X="+ints+",window.app.design.SECTIONS["+shelf+"].SUBSECTIONS["+ racks+"].Y= "+intsy);
			 }
		 }
		 ((JavascriptExecutor)driver).executeScript( "window.app.design.internalLayer().find('.subgroup').fire('mouseover').fire('mouseout')");
		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript(" window.app.design.internalLayer().find('.delete').fire('click').draw()"); 
		Thread.sleep(10000);
		  WebElement finalprice = driver.findElement(By.xpath(".//*[@id='price']/span[2]"));
			String fp=	finalprice.getText();
		int FP=Integer.parseInt(fp);
				if(FP==(IP))
				{
					driver.findElement(By.xpath("//body")).sendKeys(Keys.chord(Keys.CONTROL,Keys.SHIFT, "i"));
					Thread.sleep(4000);
					Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
					Robot robot = new Robot();
					BufferedImage img =robot.createScreenCapture(new Rectangle(size));
					File save_path=new File("E:\\sampath\\screen.jpg");
					ImageIO.write(img,"JPG",save_path);
					Thread.sleep(3000);
					   File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
							// Now you can do whatever you need to do with it, for example copy somewhere
							FileUtils.copyFile(scrFile, new File("E:\\sampath\\screenshotimporant.jpg"));
				
					
							
							System.out.println("An error in deleting ");
					
				}
				else
				{
					System.out.println("Deleted interiors  succesfully ");
				}
					
	/*	Object int1 =  ((JavascriptExecutor)driver).executeScript("return[window.app.design.internalLayer().children.length]");
		 String sn = int1.toString();
		 int aL = Integer.parseInt(sn.substring(1, sn.length() - 1)); 
		 System.out.println(aL);*/
		 WebElement interior = driver.findElement(By.xpath(".//*[@id='wardrobeInternalComponents']/div[2]/ul/li[1]/div/div[1]"));
		 WebElement canvavParent = driver.findElement(By.xpath(".//*[@id='appInternalCanvas']"));
		 for(int inter= 0; inter<vertices.size(); inter++)
		 {
			 Vertex vertex = vertices.get(inter);
			 WebElement initialprice2 = driver.findElement(By.xpath(".//*[@id='price']/span[2]"));
				String ip2= initialprice2.getText(); 
				int IP2 = Integer.parseInt(ip2);
	//	Object interiorx =  ((JavascriptExecutor)driver).executeScript("return[window.app.design.internalLayer().children[" +inter+ "].getX()]");
       //     String secX = interiorx.toString();
		//	 int aX = Integer.parseInt(secX.substring(1, secX.length() - 1)); 
		//	 Object interiory =  ((JavascriptExecutor)driver).executeScript("return[window.app.design.internalLayer().children[" +inter+ "].getY()]");
	     //       String secY = interiory.toString();
			//	 int aY = Integer.parseInt(secY.substring(1, secY.length() - 1)); 
				 
				 
				 Actions builder= new Actions(driver);
					builder.clickAndHold(interior);
					builder.moveToElement(canvavParent, vertex.x, vertex.y);
					builder.release();
					builder.perform();
               System.out.println("dropping element on "+vertex);					
					 WebElement finalprice2 = driver.findElement(By.xpath(".//*[@id='price']/span[2]"));
						String fp2=	finalprice2.getText();
						int FP2=Integer.parseInt(fp);
							if(FP2==(IP2))
							{
								
								driver.findElement(By.xpath("//body")).sendKeys(Keys.chord(Keys.CONTROL,Keys.SHIFT, "i"));
								Thread.sleep(4000);
								Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
								Robot robott = new Robot();
								BufferedImage img =robott.createScreenCapture(new Rectangle(size));
								File save_path=new File("E:\\sampath\\screen["+inter+"].jpg");
								ImageIO.write(img,"JPG",save_path);
								Thread.sleep(3000);
								   File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
										// Now you can do whatever you need to do with it, for example copy somewhere
										FileUtils.copyFile(scrFile, new File("E:\\sampath\\screenshotimporant["+inter+"].jpg"));
								
										
										System.out.println("An error in dragging the element ");
								
							}
							else
							{
								System.out.println("Dragging succesfully ");
							}
						 }
			//	Actions builder1= new Actions(driver);
				//	builder1.moveToElement(canvavParent, aX, aY)
					//.click().perform();
			//	 driver.switchTo().new();
				//	Robot robot = new Robot();
				//	robot.mouseMove(aX, aY);
				//	driver.switchTo().defaultContent();
					
				// Point coordinates = driver.findElement(By.xpath(".//*[@id='appExternalCanvas']")).getLocation();
			//	 Robot robot = new Robot();
				// robot.mouseMove(coordinates.getX(),coordinates.getY()+120);
				 
		 
		 
		// ((JavascriptExecutor)driver).executeScript( "window.app.design.internalLayer().find('.subgroup').fire('mouseover').fire('mouseout')");
		// System.out.println("Sleeping");
		// Thread.sleep(10000);
		//((JavascriptExecutor) driver).executeScript(" window.app.design.internalLayer().find('.delete').fire('click').draw()"); 
	
		driver.close();
	}

	private static void click(int i, int j) {
		// TODO Auto-generated method stub
		
	}

}
