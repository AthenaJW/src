import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Octagon {
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner fin = new Scanner(new File("octagon.dat"));
		int n =fin.nextInt();
		for(int i=0;i<n;i++) {
			double area = fin.nextDouble();
			double side = Math.sqrt(area/(2*Math.sqrt(2.0)+2));
			System.out.printf("%.2f\n",side);
		}
	}
}
