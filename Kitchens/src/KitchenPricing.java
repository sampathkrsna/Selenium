import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


public class KitchenPricing {
	 WebDriver driver;
	
	public void width(){
		{
			int noOfbase = driver.findElements(By.xpath(".//*[@id='tab-baseUnits']/ol/li")).size();
			System.out.println(+noOfbase);
			
			
			for(int i=1;i<=noOfbase;i++)
			{
				System.out.println(driver.findElement(By.xpath("/html/body/div[5]/div[7]/div[2]/div[2]/div/div[2]/div/div[1]/ol/li["+i+"]/div/div[2]/div/div[1]/span[2]")).getText());
				
				String w = driver.findElement(By.xpath("/html/body/div[5]/div[7]/div[2]/div[2]/div/div[2]/div/div[1]/ol/li["+i+"]/div/div[1]/div[2]/p")).getText();
				String mychar1 = String.valueOf(w.substring(2,4));
				System.out.println(mychar1);
			}
			
			}
		
		
	}

	public static void main(String[] args) throws IOException, RowsExceededException, WriteException, BiffException, InterruptedException {
		// TODO Auto-generated method stub

	
		
		
	/*	File fExcel = new File("E:\\sampath\\createexcel.xls");
		WritableWorkbook writableBook = Workbook.createWorkbook(fExcel);
			writableBook.createSheet("Data", 0);
			WritableSheet writableSheet = writableBook.getSheet(0);
			Label data1 = new Label(0,0,"sampath");
			 writableSheet.addCell(data1);
			Label data2 = new Label(0,1,"12345");
			 writableSheet.addCell(data2);
			 
			 writableBook.write();
			 writableBook.close();  */
			 
		/*	 String FilePath = "E:\\sampath\\Modules1.xls";
			 FileInputStream fs = new FileInputStream(FilePath);
			 System.out.println("heloo");
			 Workbook wb = Workbook.getWorkbook(fs);
			 Sheet sh = wb.getSheet("Modules");
			 //int rows = sh.getRows();
			 //int cols = sh.getColumns();
			 String a = sh.getCell(10,17).getContents();
				//	System.out.print(sh.getCell(10,17).getContents());
			float foo = Float.parseFloat(a);		
		System.out.println(+foo);
		String b = sh.getCell(14,17).getContents();
		float bar = Float.parseFloat(b);		
		System.out.println(+bar);
		float x;
		x=foo+bar;
		int y = ((int) Math.ceil(x))*2;
		System.out.println(+y);*/
		
		
		
		/*String FilePath = "E:\\sampath\\Modules.xls";
		 FileInputStream fs = new FileInputStream(FilePath);
		 System.out.println("heloo");
		 Workbook wb = Workbook.getWorkbook(fs);
		 Sheet sh = wb.getSheet("Modules");
		 int rows = sh.getRows();
		 int cols = sh.getColumns();
		 String CellGetContent = sh.getCell(0,0).getContents();
		 System.out.println(CellGetContent);*/
		 
		 WebDriver driver=new FirefoxDriver();
			driver.manage().window().maximize();
			driver.get("http://52.74.121.162/kitchen");
			
			
				driver.findElement(By.xpath("/html/body/div[5]/div[1]/div[1]/div/button")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath(".//*[@id='layout-selection']/div[2]/ul/li[1]/div/div[1]/img")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("/html/body/div[5]/div[6]/div[1]/div[2]/div[2]/div[2]/div[2]/select/option[1]")).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("/html/body/div[5]/div[6]/div[1]/div[2]/div[2]/div[3]/div[2]/select/option[1]")).click();
			Thread.sleep(3000);
			int noOfTall = driver.findElements(By.xpath(".//*[@id='added-cabinets-Base']")).size();
			System.out.println(+noOfTall);
			
	/*	String a=	driver.findElement(By.xpath("/html/body/div[5]/div[7]/div[2]/div[2]/div/div[2]/div/div[1]/ol/li[1]/div/div[1]/div[2]/p")).getText();
			System.out.println(a);
			String mychar = String.valueOf(a.substring(2,5));
			System.out.println(mychar);
			Thread.sleep(3000); */
			
			
			
			
		/*	String price = driver.findElement(By.xpath("/html/body/div[5]/div[7]/div[2]/div[2]/div/div[2]/div/div[1]/ol/li[1]/div/div[2]/div/div[1]/span[2]")).getText();
			int cost = Integer.parseInt(price);
			
			if(cost==y)
			{
				System.out.println("Pass");
			
			}else{
				System.out.println("Mismatch");
			}*/
			
			
		/*	driver.findElement(By.xpath(".//*[@id='atab-wallUnits']")).click();
			int noOfTall = driver.findElements(By.xpath(".//*[@id='tab-wallUnits']/ol/li")).size();
			System.out.println(+noOfTall);
			
			
			for(int j=1;j<=noOfTall;j++)
			{
				if(j!=3)
				try{
			
				System.out.println(driver.findElement(By.xpath("/html/body/div[5]/div[7]/div[2]/div[2]/div/div[2]/div/div[2]/ol/li["+j+"]/div/div[2]/div/div[1]/span[2]")).getText());
			}
			catch(Exception e){
				System.out.println(e);


			}
			}*/
				
				
	}
	
	
	private static void Width() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	}
	

			 
			 
	


	


