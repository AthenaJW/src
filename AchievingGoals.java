import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class AchievingGoals {
	public static void main(String[] args) throws Exception
	{
		Scanner scan = new Scanner(new File("pr00.dat"));
		int N = scan.nextInt(); scan.nextLine();
		int num;
		for (int i = 0; i < N; i++)
		{
			num = scan.nextInt(); scan.nextLine();
			System.out.println(num + 1);
		}
	}
}
