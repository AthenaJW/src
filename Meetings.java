import java.io.*;
import java.util.*;
public class Meetings {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("meetings.in"));
		String[] temp = br.readLine().split(" ");
		int num = Integer.parseInt(temp[0]);
		int length = Integer.parseInt(temp[1]);
		double target = 0;
		int[][] cows = new int[num][3];
		for (int i = 0; i < num; i++)
		{
			temp = br.readLine().split(" ");
			cows[i][0] = Integer.parseInt(temp[0]);//position
			cows[i][1] = Integer.parseInt(temp[1]);//weight
			cows[i][2] = Integer.parseInt(temp[2]);//direction
			target += Integer.parseInt(temp[1]);
		}
		target = target / 2;
		int weight = 0;
		int min;;
		int T = 0;
		boolean popRight = false;
		int minIndex = 0;
		int p1 = 0;
		int p2 = num;
		Comparator<int[]> c = new Comparator<int[]>()
		{
			public int compare(int[] o1, int[] o2)
			{
				return o1[0] - o2[0]; //sort by position
			}
		};
		Arrays.sort(cows, c);
		while (weight <= target)
		{
			min = Integer.MAX_VALUE;
			for (int i = p1; i < p2; i++)
			{
				if (cows[i][2] < 0)
				{
					if (cows[i][0] < min)
					{	
						min = cows[i][0];
						minIndex = i;
						popRight = false;
					}
				}
				else
				{
					if (length - cows[i][0] < min);
					{
						min = length - cows[i][0];
						minIndex = i;
						popRight = true;
					}
				}
			}
			if (popRight)
			{
				T = length - cows[minIndex][0];
				weight += cows[p2-1][1];
				p2--;
			}
			else
			{
				T = cows[minIndex][0];
				weight += cows[p1][1];
				p1++;
			}
		}
		System.out.println(T);
		int total = 0;
		for (int i = 0; i < num; i++)
		{
			for (int j = i; j < num; j++)
			{
				if (cows[i][2] < 0 && cows[j][2] > 0 && cows[i][1] - cows[j][1] <= 2*T)
					total++;
				if (cows[i][2] > 0 && cows[j][2] < 0 && cows[j][1] - cows[i][1] <= 2*T)
					total++;
			}
		}
		System.out.println(total);
		//PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("meetings.out")));
		//pw.println(total);
		//pw.close();
		br.close();
	}
}
