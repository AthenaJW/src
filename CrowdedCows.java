import java.io.*;
import java.util.*;

public class CrowdedCows {
	static Comparator<int[]> c = new Comparator<int[]>()
	{
		public int compare(int[] o1, int[]o2)
		{
			return o1[0] - o2[0];
		}
	};
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("crowded.in"));
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int D = Integer.parseInt(temp[1]);
		int[][] cows = new int[N][2];
		int x;
		int h;
		for (int i = 0; i < N; i++)
		{
			temp = br.readLine().split(" ");
			x = Integer.parseInt(temp[0]);
			h = Integer.parseInt(temp[1]);
			cows[i] = new int[] { x, h };
		}
		Arrays.sort(cows, c);
		TreeMap<Integer, Integer> before = new TreeMap<Integer, Integer>();
		TreeMap<Integer, Integer> after = new TreeMap<Integer, Integer>();
		int i = 0;
		int j = 0;
		int k = 1;
		int count;
		int ans = 0;
		while (i < N-1)
		{
			count = 0;
			while (cows[i][0] - cows[j][0] >= D)
			{
				before.put(cows[j][1], before.get(cows[j][1])-1);
				if (before.get(cows[j][1])==0)
					before.remove(cows[j][1]);
				j++;
			}
			while (k < N && cows[k][0] - cows[i][0] <= D)
			{
				if (after.containsKey(cows[k][1]))
					after.put(cows[k][1], after.get(cows[k][1])+1);
				else
					after.put(cows[k][1], 1);
				k++;
			}
			//System.out.println(i);
			//System.out.println(before);
			//System.out.println(after);
			if (!before.isEmpty() && before.lastKey() >= 2*cows[i][1])
				count++;
			if (!after.isEmpty() && after.lastKey() >= 2*cows[i][1])
				count++;
			if (count == 2)
				ans++;
			if (before.containsKey(cows[i][1]))
				before.put(cows[i][1], before.get(cows[i][1])+1);
			else
				before.put(cows[i][1], 1);
			i++;		

			after.put(cows[i][1], after.get(cows[i][1])-1);
			if (after.get(cows[i][1])==0)
				after.remove(cows[i][1]);
		}
		//System.out.println(ans);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("crowded.out")));
		pw.println(ans);
		pw.close();
		br.close();
	}
}
