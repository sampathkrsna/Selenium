package curtainshadepack;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class romanshade {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
	WebDriver driver = new FirefoxDriver();
	driver.manage().window().maximize();
	driver.get("http://root.test.customfurnish.com/curtains-online/");
	driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[1]/div/a")).click();
	Thread.sleep(2000);
	//driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[3]/div[2]/ul/li[7]/a/div/div[3]/button")).click();
	//Thread.sleep(2000);
	
	driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[3]/div[2]/ul/li[1]/a/div/div[3]/button")).click();
	Thread.sleep(2000);
	
	String curprice=driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[4]/div[2]/div[1]/div[1]/div[1]/span[1]/span")).getText();
	System.out.println("Rs " + curprice);
	double heightmeter = 2.4892;
	int price = 250;
	int fixedrate = 63;
	double totpric= (price*heightmeter)+(fixedrate*heightmeter);
	System.out.println("price " + totpric);
	double finalprice= totpric *1.125;
	System.out.println("Final Price " + finalprice);
	int a =25;
	double b = Integer.parseInt(curprice)+a;
	double c= Integer.parseInt(curprice)-a;
	boolean newprice;
	newprice = ((finalprice<b)&&(finalprice>c))? true:false;
	System.out.println("NewPrice = "+ newprice);
	if(newprice==true)
	{
		System.out.println("Price is Matching");
	}
	else
		System.out.println("Price is not Matching");
	String width =driver.findElement(By.xpath("/html/body/div[1]/div[5]/div[4]/div[2]/div[1]/div[1]/div[1]/span[5]")).getText();
	System.out.println("Width = "+ width);
	String widthfeet = width.substring(0,1);
	String widthinch = width.substring(2,3);
	System.out.println(widthfeet);
	System.out.println(widthinch);
	//double width1 =123.45;
	//String width2 = Double.toString(width1).substring(5,6); 
	//System.out.println(width2);
	
	
	
	
	

	
	
		
	

	}

}