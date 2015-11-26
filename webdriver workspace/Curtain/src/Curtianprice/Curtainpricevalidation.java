package Curtianprice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Curtainpricevalidation {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://root.test.customfurnish.com/curtains-online/");
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[1]/div/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[3]/div[2]/ul/li[1]/a/div/div[3]/button")).click();
		Thread.sleep(2000);
		//Fabric Page
		String fabricprice=driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[4]/div[2]/div[1]/div[1]/div[1]/span[1]/span")).getText();
		System.out.println("Fabric Price "+"Rs " + fabricprice);
		
		//Fabric Calculation
		double heightmeter = 2.4892;
		int fabprice=250;
		int fixedprice =63;
		int noofcurtain =1;
		double margin=1.125;
		double calculatedprice=(((heightmeter*fabprice)+(heightmeter*fixedprice))*noofcurtain)*margin;
		System.out.println("Calculated Price "+ calculatedprice);
		int a=25;
		double b=Integer.parseInt(fabricprice)+a;
		double c=Integer.parseInt(fabricprice)-a;
		boolean matchprice;
		matchprice=((calculatedprice<b)&&(calculatedprice>c))?true:false;
		System.out.println("Price is Matching : "+ matchprice);
		if(matchprice==true)
		{
			System.out.println("Price Matching");
		}
		else
			System.out.println("Price MisMatching");
		
		//next to summary page
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[4]/div[2]/div[1]/div[1]/div[2]/div[4]/a[1]")).click();
		Thread.sleep(2000);
		//next to dimension page
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[5]/div[2]/div[1]/div[3]/a")).click();
		Thread.sleep(2000);
		//select dimension
		
		WebElement widthfeet=driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[8]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[4]/select[1]"));
		Select widthfeetvalue=new Select(widthfeet);
		widthfeetvalue.selectByValue("9");
		Thread.sleep(2000);		
		WebElement widthinch=driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[8]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[4]/select[2]"));
		Select widthinchvalue=new Select(widthinch);
		widthinchvalue.selectByValue("1");
		Thread.sleep(2000);		
		WebElement heightfeet=driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[8]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[5]/select[1]"));
		Select heightfeetvalue=new Select(heightfeet);
		heightfeetvalue.selectByValue("9");
		Thread.sleep(2000);
		WebElement heightinch=driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[8]/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[5]/select[2]"));
		Select heightinchvalue=new Select(heightinch);
		heightinchvalue.selectByValue("6");
		Thread.sleep(2000);
		
		//Retrieve dimension value	
		
		WebElement option=widthfeetvalue.getFirstSelectedOption();
		String widthfeetvalue1=option.getText();
		String widthfeetvalue2=widthfeetvalue1.substring(0,2).trim();
		System.out.println("Width Feet Value is "+widthfeetvalue2);
		
		WebElement option1=widthinchvalue.getFirstSelectedOption();
		String widthinchvalue1=option1.getText();
		String widthinchvalue2=widthinchvalue1.substring(0,2).trim();
		System.out.println("Width Inch Value is "+widthinchvalue2);
				
		WebElement option2=heightfeetvalue.getFirstSelectedOption();
		String feetvalue1=option2.getText();
		String feetvalue2=feetvalue1.substring(0,2).trim();
		System.out.println("Height Feet Value is "+feetvalue2);
		
		WebElement option3=heightinchvalue.getFirstSelectedOption();
		String inchvalue1=option3.getText();
		String inchvalue2=inchvalue1.substring(0,2).trim();
		System.out.println("Height Inch Value is "+inchvalue2);
		
		//retrieve no of curtain
		String curtainquantity=driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[8]/div[2]/div[2]/div[2]/div[2]/div[1]/span[4]")).getText();
		System.out.println("No Of Curtain "+curtainquantity);
		
		//height meter value
		int mtoinch=12;
		int wastage=14;
		double mvalue=.0254;
		
		double heightmetervalue=((((mtoinch*(Integer.parseInt(feetvalue2)))+(Integer.parseInt(inchvalue2)))+wastage)*mvalue);
		System.out.println("Height Meter Value = "+heightmetervalue);
		// curtain price calculation
		int stichprice=63;
		double selectfabricprice=((((heightmetervalue*fabprice)+(heightmetervalue*stichprice))*(Integer.parseInt(curtainquantity)))*margin);
		System.out.println("Selected Fabric Price Rs "+selectfabricprice);
		int selectedfabprice=(int)Math.round(selectfabricprice);
		System.out.println("Curtain Rounded Price ="+selectedfabprice);
		//lining calculation
		int polylining=110;
		int blacklining=240;
		double eyelettypevariation=1.5;
		//double nooflining=(((Integer.parseInt(widthfeetvalue2)*12)*eyelettypevariation)/54);
		double nooflining=((((Double.parseDouble(widthfeetvalue2)*12)+Double.parseDouble(widthinchvalue2))*eyelettypevariation)/54);
		System.out.println(nooflining);
		int roundednooflining=(int)Math.round(nooflining);
		System.out.println(roundednooflining);
		
		
		

	}

}
