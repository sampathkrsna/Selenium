import java.awt.List;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;


public class Templates {
	WebDriver driver;
	// function for ascending order
	public  void ascending() throws Exception  {
		try{
			driver.findElement(By.xpath(" /html/body/div[6]/div[3]/form/div[1]/select/option[2] ")).click();
			System.out.println("Sorting on Low to High");
			Thread.sleep(3000);
			int minPrice = Integer.MIN_VALUE;
			for(WebElement we : driver.findElements(By.className(" template-price"))) {
				int newPrice = Integer.parseInt((we.getText().substring(8).replaceAll("[,;\\s]", "")));
				if(minPrice <= newPrice) {
					minPrice = newPrice;
					continue;
				}
				else {
					// test failure
					throw new Exception("sort failed");	
				}
			}
			System.out.println("Low to High sort working fine");	
		}
		catch(Exception e){
			System.out.println(e);

		}
	}

	//function for descending order

	public  void decreasing() throws Exception  {
		try {

			driver.findElement(By.xpath(" /html/body/div[6]/div[3]/form/div[1]/select/option[3] ")).click();
			System.out.println("Sorting on High to low");
			Thread.sleep(3000);
			int maxPrice = Integer.MAX_VALUE;
			for(WebElement we : driver.findElements(By.className(" template-price"))){
				int newPrice = Integer.parseInt((we.getText().substring(8).replaceAll("[,;\\s]", "")));
				if(maxPrice >= newPrice) {
					maxPrice = newPrice;
					continue;
				}
				else {
					// test failure
					throw new Exception("sort failed");	
				}
			}

			System.out.println("High to Low sort working fine");
		}
		catch(Exception e){
			System.out.println(e);
		}
	}


	//function for width sort
	public void widthsort() throws Exception{	
		try {
			driver.findElement(By.xpath(" /html/body/div[6]/div[3]/form/div[1]/select/option[4] ")).click();
			System.out.println("Sorting on Width");
			Thread.sleep(5000);

			int minWidth = Integer.MAX_VALUE;
			for(WebElement we : driver.findElements(By.className(" template-info-small"))){
				int widthStartIndex = we.getText().indexOf("Width")+6;
				int widthEndIndex = we.getText().indexOf("\"");
				//	System.out.println("Start: "+widthStartIndex+" End: "+widthEndIndex);
				//System.out.println(we.getText().substring(widthStartIndex,widthEndIndex));

				int newWidth = Integer.parseInt((we.getText().substring(widthStartIndex,widthEndIndex).replaceAll("[.,;\\s]", "")));
				if(minWidth >= newWidth) {
					minWidth = newWidth;
					continue;
				}
				else{
					throw new Exception("Sort failed");
				}
			}
		}
		catch(Exception e){
			System.out.println(e);

		}

	}

		

	
	// test opens browser
	@Test
	public void testwebsite() throws InterruptedException {
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get(" http://www.kustommade.com/templates/ ");
		Thread.sleep(9000);
	}
	//test click on Low Price and checks the pricing are in the same order or not 
	@Test(dependsOnMethods={"testwebsite"})
	public void testLowprice() throws Exception   {
		{
			//function is called to do a low price sort
			ascending();
		}
	}	


	//test click on High Price and checks the pricing are in the same order or not 
	@Test(dependsOnMethods={"testwebsite"})

	public void testhighprice() throws Exception   {
		{

			//function is called to do a high price sort
			decreasing();

		}

	}			
	//test click on width and checks the pricing are in the same order or not
	@Test(dependsOnMethods={"testwebsite"})
	public void atestwardrobewidth() throws Exception   {


		{
			//function is called to do a width price sort (high to low)
			widthsort();
		}

	}

	
	//Pre Laminated Particle Board 

	@Test(dependsOnMethods={"testwebsite"})
	public void testprelam() throws InterruptedException   {
		//checking Pre Laminated Particle Board  
		driver.findElement(By.xpath(".//*[@id='woodTypePrelamPB']  ")).click();
		System.out.println("Sorting on Prelm only");
		Thread.sleep(5000);


		try{


			java.util.List<WebElement> A =	driver.findElements(By.className("template-info-small"));
			for(WebElement we:A){
				String content = we.getText();
				boolean retval = content.startsWith("Pre Laminated Particle Board");
				System.out.println("Method returns : " + retval);
			}

			{
				/*clicking on Low price filter
                     in Pre Laminated Particle Board */				

				ascending();
			}
			{

				decreasing();
			}
			{

				widthsort();
			}

		}
		catch(Exception e){
			System.out.println(e);


		}
		driver.findElement(By.xpath(".//*[@id='woodTypePrelamPB']  ")).click();
	}	

	/*Testing Pre Laminated Particle Board  
	selecting a door type  Full Panel Door */
	@Test(dependsOnMethods={"testwebsite","testprelam"})
	public void testprelamfulldoor() throws InterruptedException   {
//selecting wood type
		driver.findElement(By.xpath(".//*[@id='woodTypePrelamPB']  ")).click();
		// checking full panel door
		driver.findElement(By.xpath(".//*[@id='doorTypeTraditional']  ")).click();
		System.out.println("Sorting on Prelm fullpanel");
		Thread.sleep(5000);


		try{


			java.util.List<WebElement> A =	driver.findElements(By.className("template-info-small"));
			for(WebElement we:A){
				String content = we.getText();
				boolean retval = content.startsWith("Pre Laminated Particle Board");
				System.out.println("Method returns : " + retval);
			}

			{

				ascending();
			}
			{
				decreasing();
			}
			{
				widthsort();
			}

		}
		catch(Exception e){
			System.out.println(e);


		}
// Un-checking door type
		driver.findElement(By.xpath(".//*[@id='doorTypeTraditional']  ")).click();
		//un-checking wood type
		driver.findElement(By.xpath(".//*[@id='woodTypePrelamPB']  ")).click();
	}	


	/*Testing Pre Laminated Particle Board  
	selecting a door type  sliding Door */
	@Test(dependsOnMethods={"testwebsite","testprelam","testprelamfulldoor"})
	public void testprelamsliding() throws InterruptedException   {
		//selecting wood type
		driver.findElement(By.xpath(".//*[@id='woodTypePrelamPB']  ")).click();
		// checking on sliding door
		driver.findElement(By.xpath(" .//*[@id='doorTypeSlide'] ")).click();
		System.out.println("Sorting on Prelmsliding ");
		Thread.sleep(5000);


		try{


			java.util.List<WebElement> A =	driver.findElements(By.className("template-info-small"));
			for(WebElement we:A){
				String content = we.getText();
				boolean retval = content.startsWith("Pre Laminated Particle Board");
				System.out.println("Method returns : " + retval);
			}

			{

				ascending();
			}
			{
				decreasing();
			}
			{
				widthsort();
			}
		}
		catch(Exception e){
			System.out.println(e);


		}
		//un-checked sliding door
		driver.findElement(By.xpath(".//*[@id='woodTypePrelamPB']  ")).click();
		//un-checking woodtype
		driver.findElement(By.xpath(" .//*[@id='doorTypeSlide'] ")).click();
	}	



	/*Testing Pre Laminated Particle Board  
selecting a door type  Panel With external draws */


	@Test(dependsOnMethods={"testwebsite","testprelam","testprelamfulldoor"})
	public void testfullpanel() throws Exception   {
		//selecting wood type
		driver.findElement(By.xpath(".//*[@id='woodTypePrelamPB']  ")).click();
		//checking panel with external draws
		driver.findElement(By.xpath(" .//*[@id='doorTypeExternal'] ")).click();
		System.out.println("Sorting on Prelm fullpanel");
		Thread.sleep(5000);


		try{
			java.util.List<WebElement> A =	driver.findElements(By.className("template-info-small"));
			for(WebElement we:A){
				String content = we.getText();
				boolean retval = content.startsWith("Pre Laminated Particle Board");
				System.out.println("Method returns : " + retval);
			}
			{

				ascending();
			}
			{

				decreasing();
			}
			{

				widthsort();
			}


		}
		catch(Exception e){
			System.out.println(e);


		}
		//unchecking door type
		driver.findElement(By.xpath(" .//*[@id='doorTypeExternal'] ")).click();
		//unchecking wood type
		driver.findElement(By.xpath(".//*[@id='woodTypePrelamPB'] ")).click();
		Thread.sleep(5000);
	}	
	//Test for Prelam MDF

	@Test(dependsOnMethods={"testwebsite"})
	public void testprelamMDF() throws InterruptedException   {

		
	// selecting PrelamMDF 
		 
		driver.findElement(By.xpath(".//*[@id='woodTypePrelamMDF'] ")).click();
		System.out.println("Sorting on PrelmMDF only");
		Thread.sleep(5000);


		try{
			java.util.List<WebElement> A =	driver.findElements(By.className("template-info-small"));
			for(WebElement we:A){
				String content = we.getText();
				boolean retval = content.startsWith("Prelam MDF");
				System.out.println("Method returns : " + retval);
			}

			{

				ascending();
			}
			{

				decreasing();
			}
			{

				widthsort();
			}

		}
		catch(Exception e){
			System.out.println(e);


		}

	}	


	@Test(dependsOnMethods={"testwebsite","testprelamMDF"})
	public void testprelamMDFfulldoor() throws InterruptedException   {
		driver.findElement(By.xpath(".//*[@id='woodTypePrelamMDF'] ")).click();
		//selecting traditional door in Prelam MDF
		driver.findElement(By.xpath(" .//*[@id='doorTypeTraditional'] ")).click();
		System.out.println("Sorting on PrelmMDF fullpanel");
		Thread.sleep(5000);


		try{


			java.util.List<WebElement> A =	driver.findElements(By.className("template-info-small"));
			for(WebElement we:A){
				String content = we.getText();
				boolean retval = content.startsWith("Prelam MDF");
				System.out.println("Method returns : " + retval);
			}

			{

				ascending();
			}
			{

				decreasing();
			}
			{

				widthsort();
			}

		}
		catch(Exception e){
			System.out.println(e);


		}
		// un-checking the traditional door
		driver.findElement(By.xpath(" .//*[@id='doorTypeTraditional'] ")).click();
		//unchecking wood type
		driver.findElement(By.xpath(".//*[@id='woodTypePrelamMDF'] ")).click();
	}	

	@Test(dependsOnMethods={"testwebsite","testprelamMDF","testprelamMDFfulldoor"})
	public void testprelamMDFsliding() throws InterruptedException   {
		//selecting wood type
		driver.findElement(By.xpath(".//*[@id='woodTypePrelamMDF'] ")).click();
		//selecting sliding doors
		driver.findElement(By.xpath(" .//*[@id='doorTypeSlide'] ")).click();
		System.out.println("Sorting on PrelmMDFsliding ");
		Thread.sleep(5000);


		try{


			java.util.List<WebElement> A =	driver.findElements(By.className("template-info-small"));
			for(WebElement we:A){
				String content = we.getText();
				boolean retval = content.startsWith("Prelam MDF");
				System.out.println("Method returns : " + retval);
			}

			{

				ascending();
			}
			{

				decreasing();
			}
			{

				widthsort();
			}

		}
		catch(Exception e){
			System.out.println(e);


		}
		//un-checking sliding doors in Prelam MDF
		driver.findElement(By.xpath(" .//*[@id='doorTypeSlide'] ")).click();
		driver.findElement(By.xpath(".//*[@id='woodTypePrelamMDF'] ")).click();
	}	



	@Test(dependsOnMethods={"testwebsite","testprelamMDFfulldoor","testprelamMDFsliding","testprelamMDF"})
	public void testfullpanelMDF() throws Exception   {
		driver.findElement(By.xpath(".//*[@id='woodTypePrelamMDF'] ")).click();
		//selecting panels with external draws
		driver.findElement(By.xpath(" .//*[@id='doorTypeExternal'] ")).click();
		System.out.println("Sorting on PrelmMDF  fullpanel");
		Thread.sleep(5000);


		try{
			java.util.List<WebElement> A =	driver.findElements(By.className("template-info-small"));
			for(WebElement we:A){
				String content = we.getText();
				boolean retval = content.startsWith("Prelam MDF");
				System.out.println("Method returns : " + retval);
			}
			{

				ascending();
			}
			{

				decreasing();
			}
			{

				widthsort();
			}


		}
		catch(Exception e){
			System.out.println(e);


		}

		//un-checking panels with external draws
		driver.findElement(By.xpath(" .//*[@id='doorTypeExternal'] ")).click();
		//un-checking wood type
		driver.findElement(By.xpath(".//*[@id='woodTypePrelamMDF'] ")).click();
	}
	


	private String stripNonDigits(String kk) {
		// TODO Auto-generated method stub
		return null;
	}
	

}