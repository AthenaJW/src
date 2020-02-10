import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Herons {
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner fin = new Scanner(new File("herons.dat"));
		
		int n = fin.nextInt();
		fin.nextLine();
		
		for (int i = 0; i < n; i++)
		{
			String[] vals;
			
			vals = fin.nextLine().split(",");
			
			double s1 = Double.parseDouble(vals[0]);
			double s2 = Double.parseDouble(vals[1]);
			double s3 = Double.parseDouble(vals[2]);
			
			double a;
			
			double s = (s1 + s2 + s3) / 2;
			a = s * (s-s1) * (s - s2) * (s - s3);
			
			a = Math.pow(a, 0.5);
			
			System.out.printf("%.3f\n", a);
		}
		
		fin.close();
	}
}
