import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Runner {
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner fin = new Scanner(new File("runner.dat"));
		double totf=0;
		double totm=0;
		for(int i=0; i<5;i++)
		{int f = fin.nextInt();
		int m = fin.nextInt();
		 totf+=f;
		 totm+=m;
		}
		
		totf=(double) totf/5280;
		totm=(double) totm/60;
		totf/=totm;
		System.out.print("Your speed was ");
		System.out.printf("%.3f",totf);
		System.out.print(" miles per hour.");
		
		fin.close();
	}
}
