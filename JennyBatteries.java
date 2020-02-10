import java.io.*;
import java.util.*;

public class JennyBatteries {
	static int[][] batteries;
	static long budget;
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		budget = Integer.parseInt(temp[1]);
		batteries = new int[N][3];
		for (int i = 0; i < N; i++)
		{
			temp = br.readLine().split(" ");
			batteries[i][0] = Integer.parseInt(temp[0]);
			batteries[i][1] = Integer.parseInt(temp[1]);
			batteries[i][2] = Integer.parseInt(temp[2]);
		}
		Comparator<int[]> c = new Comparator<int[]>()
		{
			public int compare(int[] o1, int[] o2)
			{
				return o1[2] - o2[2]; //ascending order of c value
			}
		};
		Arrays.sort(batteries, c);
		long start = batteries[0][0];
		long end = start;
		for (int i = 0; i < N; i++)
		{
			if (batteries[i][0] > end)
				end = batteries[i][0];
			if (batteries[i][0] < start)
				start = batteries[i][0];		
		}
		long med;
		boolean pass;
		while (start != end)
		{
			med = (start + end)/2;
			pass = pass(med);
			//System.out.println(med + " " + pass);
			if (pass)
				end = med;
			else
				start = med + 1;
		}
		System.out.println(start);
		br.close();
	}
	public static boolean pass(long med)
	{
		long count = 0;
		long cost = 0;
		//System.out.println("TEST: " + med);
		for (int[] element: batteries)
		{
			if (element[0] > med)
			{
				count += element[0] - med;
				cost += (element[0]-med)*element[1];
			}
		}
		//System.out.println("Count: " + count);
		int i = 0;
		while (i < batteries.length && count > 0)
		{
			if (batteries[i][0] >= med)
			{
				i++;
				continue;
			}
			//System.out.println(batteries[i][0] + " " + batteries[i][2]);
			count -= Math.min(count, (med - batteries[i][0]));
			cost += Math.min(count, (med - batteries[i][0])) * batteries[i][2];
			i++;
		}
		//System.out.println(med + " Cost: " + cost);
		if (i > batteries.length && count > 0)
			return false;
		return cost <= budget;
	}
}
