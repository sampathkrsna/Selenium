package curtainimagemap;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class imagecolortestcase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedImage img1=null;
		BufferedImage img2=null;
		
		try
		{
			File sourceimage1= new File("/home/customfurnish/Desktop/curtain11.jpeg");
			File sourceimage2=new File("/home/customfurnish/Desktop/curtain12.jpeg");
			
			img1=ImageIO.read(sourceimage1);
			img2=ImageIO.read(sourceimage2);
						
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		int width1=img1.getWidth();
		int width2=img2.getWidth();
		int height1=img1.getHeight();
		int height2=img2.getHeight();
		
		if((width1 != width2)|| (height1 != height2))
		{
			System.out.println("Error - Dimension Mismatch");
		}
		else
		{
			System.out.println("Dimension Matched");
		}
		
		long diff=0;
		for(int i=0;i< height1; i++)
		{
			for(int j=0;j<width1;j++)
			{
				int rgb1=img1.getRGB(j,i);
				int rgb2=img2.getRGB(j,i);
				int r1=(rgb1>>16) & 0xff;
				int g1=(rgb1>>8) &  0xff;
				int b1=(rgb1) & 0xff;
				int r2=(rgb2>>16) & 0xff;
				int g2=(rgb2>>8) & 0xff;
				int b2=(rgb2) & 0xff;
				
				diff += Math.abs(r1 - r2);
				diff += Math.abs(g1-g2);
				diff +=Math.abs(b1-b2);			
				
		  }						
		}
		double n=width1*height1*3;
		double p = diff/n/255.0;
		System.out.println("RGB Differ Percent"+" " + (p*100.0));
		//another method http://stackoverflow.com/questions/9018016/how-to-compare-two-colors
		//int diffRed= Math.abs(img1.getred() - img2.getred());

	}

}
