import java.io.*;
import java.util.*;
public class SnowBoots {
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("snowboots.in"));
		String[] temp = br.readLine().split(" ");
		int numTiles = Integer.parseInt(temp[0]);
		int numBoots = Integer.parseInt(temp[1]);
		int[] snowTiles = new int[numTiles];
		int[][] boots = new int[numBoots][2];
		temp = br.readLine().split(" ");
		for (int i = 1; i < numTiles-1; i++)
			snowTiles[i] = Integer.parseInt(temp[i]);
		for (int i = 0; i < numBoots; i++)
		{
			temp = br.readLine().split(" ");
			boots[i][0] = Integer.parseInt(temp[0]);//durability, then distance
			boots[i][1] = Integer.parseInt(temp[1]);
		}
		// recursive backtracking
		Stack<int[]> checkpoints = new Stack<int[]>();//bootNum, location, discarded
		int[] candidate = new int[2]; //all already are 0
		checkpoints.push(candidate);
		int[] temp2;
		int output = Integer.MAX_VALUE;
		int bootNum, location, discarded;
		boolean[][] beenThere = new boolean[numTiles][numBoots];
		while (!checkpoints.isEmpty())
		{
			candidate = checkpoints.pop();
			bootNum = candidate[0];
			location = candidate[1];
			beenThere[location][bootNum] = true;

			for (int j = location + 1; j <= location + boots[bootNum][1] && j < numTiles; j++)
			{
				if (j == numTiles-1)
				{
					if (bootNum<output)
						output = bootNum;
				}
				if (!beenThere[j][bootNum] && snowTiles[j] <= boots[bootNum][0])
				{
					temp2 = new int[2];
					temp2[0] = bootNum; //bootnum
					temp2[1] = j;//location
					if (temp2[0] < output)
						checkpoints.push(temp2);
					//num discarded
				}
			}//stepping forward
			for (int j = candidate[0] + 1; j < numBoots; j++)
			{
				if (!beenThere[location][j] && boots[j][0]>=snowTiles[location])
				{
					temp2 = new int[2];
					temp2[0] = j; //bootnum
					temp2[1] = location;//location
					if (temp2[0] < output)
						checkpoints.push(temp2);
					//num discarded
				}
			}//changing boots
		}
		//dynamic programming
		/*boolean[][] possible = new boolean[numTiles][numBoots];
		for (int i = 0; i < numTiles; i++)
		{
			for (int j = 0; j < numBoots; j++)
			{
				if (i == 0 && j == 0)
					possible[i][0] = true;
				if (boots[j][0]<snowTiles[i])
				{
					possible[i][j] = false;
					continue;
				}
				for (int i2 = 0; i2 < i; i2++)
				{
					if (possible[i2][j] && i2 + boots[j][1]>= i)
					{
						possible[i][j] = true;
						continue;
					}
				}
				for (int j2 = 0; j2 < numBoots; j2++)
				{
					if (possible[i][j2] && boots[j2][0]>= snowTiles[i])
					{
						possible[i][j] = true;
						continue;
					}
				}
				if (possible[i][j] && i == numTiles-1)
				{
					output = j;
					break;
				}
			}
		}*/
		//System.out.println(output);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("snowboots.out")));
		pw.println(output);
		pw.close();
		br.close();
	}
}
