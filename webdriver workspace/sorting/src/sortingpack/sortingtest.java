package sortingpack;

import java.util.Arrays;
import java.util.Scanner;

public class sortingtest {
	
	
		
		Scanner scan;
		int[] num;
		int n;
		void getVal()
		{
			
			scan = new Scanner(System.in);
			
			
			System.out.println("Sort Array");
			
			System.out.println("\n Enter n value");
			n = Integer.parseInt(scan.nextLine());
			
			System.out.println("Enter numbers");
			num= new int[n];
			
			for(int i=0; i<n; i++)
			{
				num[i] = Integer.parseInt(scan.nextLine());
			}
		}
		
		void sort()
		{
			System.out.println("\n Before Sorting");
			for(int i=0; i<n;i++)
			{
				System.out.println("" + num[i]);
				
			}
			Arrays.sort(num);
			System.out.println("\n After Sorting");
			System.out.println("\n Ascending Order");
			
			for(int i=0;i<n;i++)
			{
				System.out.println(" "+num[i]);
				
			}
			
			System.out.println("\n Descending Order");
			
			for(int i=n-1;i>=0;i--)
			{
				System.out.println(" "+num[i]);
			}
		}
}
			
				class MainClass
				{
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		sortingtest obj=new sortingtest();
		obj.getVal();
		obj.sort();

	}

}
