import java.io.*;
import java.util.Scanner;

public class CowGym {
	public static void main(String[] args) throws IOException
	{
		Scanner scan = new Scanner(new File("gymnastics.in"));
		int rounds = scan.nextInt();
		int num_cows = scan.nextInt();
		scan.nextLine();
		int[][] rankings = new int[num_cows][num_cows];
		int[][] data = new int[rounds][num_cows];
		for (int i = 0; i< rounds; i++)
		{
			for (int j = 0; j<num_cows; j++)
			{
				data[i][j] = scan.nextInt();
			}
			scan.nextLine();
		}
		int[] temp;
		int total = 0;
		for (int i = 0; i< rounds; i++)
		{
			temp = data[i];
			for (int j = 0; j<num_cows; j++)
			{
				for (int k = j + 1; k<num_cows; k++)
				{
					rankings[temp[j]-1][temp[k]-1]++; //minus 1 for indexing from 0
					if (rankings[temp[j]-1][temp[k]-1] == rounds)
						total++;
				}
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("gymnastics.out")));
		pw.println(total);
		pw.close();
		scan.close();
	}
}
