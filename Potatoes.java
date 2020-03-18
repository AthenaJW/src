import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Potatoes {
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner scan = new Scanner(new File("potatoes.dat"));
		scan.nextLine();
		double standard = (70.0-32.0)*5.0/9.0;
		while(scan.hasNextDouble())
		{
			double n = scan.nextDouble();
			double c = (n-32.0)*5.0/9.0;
			double d = c-standard;
			if(d>0)
			{
				System.out.printf("%.2f",d);
				System.out.println(" degrees cooler");
			}
			else if (d<0)
			{
				System.out.printf("%.2f",-d);
				System.out.println(" degrees warmer");
			}
			else
				System.out.println("No change");
		}
		scan.close();
	}
}
