import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class LifeGuard {
	public static void main(String[] args) throws IOException
	{
		int[][] hours = null;
		Scanner scan = new Scanner(new File("lifeguards.in"));
		int num = scan.nextInt();
		scan.nextLine();
		hours = new int[num][2];
		int beg, end;
		int[] schedule = null;
		int total = 0;
		int max = 0;
		for (int i = 0; i < num; i++)
		{
			beg = scan.nextInt();
			end = scan.nextInt();
			hours[i][0] = beg;
			hours[i][1] = end;
		}
		for (int i = 0; i<hours.length; i++)
		{
			schedule = new int[1000];
			total = 0;
			for (int j = 0; j<hours.length; j++)
			{
				
				if(j == i)
					continue;
				beg = hours[j][0];
				end = hours[j][1];
				for (int k = beg; k <end; k++) //counting hours don't count last hour
				{
					if (schedule[k] != 1)
					{
						schedule[k] = 1;
						total++;
					}
				}
			}
			if (total > max)
				max = total;
		}
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("lifeguards.out")));
		writer.println(max);
		writer.close();
		scan.close();
	}
}
