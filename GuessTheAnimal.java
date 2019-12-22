import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class GuessTheAnimal {
	public static void main(String[] args) throws IOException
	{
		Scanner scan = new Scanner(new File("guess.in"));
		int num = scan.nextInt();
		scan.nextLine();
		int[][] traitTable = new int[num][200];
		HashMap<String, Integer> traitToInd = new HashMap<String, Integer>();
		String temp;
		int n = 0;
		int x = 0;
		for (int i = 0; i < num; i++)
		{
			scan.next();
			n = scan.nextInt();
			for (int j = 0; j < n; j++)
			{
				temp = scan.next();
				if (traitToInd.containsKey(temp))
				{
					traitTable[i][traitToInd.get(temp)] = 1;
				}
				else
				{
					traitToInd.put(temp, x);
					traitTable[i][x] = 1;
					x++;
				}
			}
			scan.nextLine();
		}
		int[] traits1;
		int[] traits2;
		int common = 0;
		int maxCommon = 0;
		for (int i = 0; i < num-1; i++)
		{
			traits1 = traitTable[i];
			for (int j = i+1; j < num; j++)
			{
				traits2 = traitTable[j];
				for (int k = 0; k < x; k++)
				{
					if(traits2[k] == 1 && traits1[k] == 1)
						common++;
				}
				if (common > maxCommon)
				{
					maxCommon = common;
				}
				common = 0;
			}
		}
		PrintWriter bw = new PrintWriter(new BufferedWriter(new FileWriter("guess.out")));
		bw.println(maxCommon+1);
		scan.close();
		bw.close();
	}
}
