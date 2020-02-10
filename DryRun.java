import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class DryRun {
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner fin = new Scanner(new File("dryrun.dat"));
		
		int n = fin.nextInt();
		fin.nextLine();
		
		for (int i = 0; i < n; i++)
		{
			String s = fin.nextLine();
			System.out.println(s + " fly together.");
		}
		
		fin.close();
	}
}
