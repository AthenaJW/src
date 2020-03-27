import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class PogoCow {
	static int[][] dp_states;
	static int[][] dp_states2;
	static Comparator<int[]> c = new Comparator<int[]>()
	{
		public int compare(int[] o1, int[]o2)
		{
			return o1[0] - o2[0];
		}
	};
	static Comparator<int[]> r = new Comparator<int[]>()
	{
		public int compare(int[] o1, int[] o2)
		{
			return o2[0] - o1[0];
		}
	};
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("pogocow.in"));
		int N = Integer.parseInt(br.readLine());
		dp_states = new int[N][N]; // going from i to j
		String[] temp;
		int[][] targets = new int[N][2];
		for (int i = 0; i < N; i++)
		{
		    temp = br.readLine().split(" ");
		    targets[i] = new int[] { Integer.parseInt(temp[0]), Integer.parseInt(temp[1]) };
		}
		Arrays.sort(targets, c);
		int max;
		for (int i = 0; i < N; i++)
			for (int j = i; j < N; j++)
			{
				max = 0;
				for (int k = 0; k <= i; k++)
				{
					if (targets[i][0] - targets[k][0] > targets[j][0] - targets[i][0])
						continue;
					max = Math.max(max, dp_states[k][i]);
				}
				dp_states[i][j] = Math.max(dp_states[i][j], max+targets[j][1]);
			}
		Arrays.sort(targets, r);
		int maxdistance = targets[0][0];
		for (int i = 0; i < N; i++)
			 targets[i][0] = maxdistance - targets[i][0];
		dp_states2 = new int[N][N];
		int max2;
		for (int i = 0; i < N; i++)
			for (int j = i; j < N; j++)
			{
				max2 = 0;
				for (int k = 0; k <= i; k++)
				{
					if (targets[i][0] - targets[k][0] > targets[j][0] - targets[i][0])
						continue;
					max2 = Math.max(max2, dp_states2[k][i]);
				}
				dp_states2[i][j] = Math.max(dp_states2[i][j], max2+targets[j][1]);
			}
		int ans = 0;
		for (int i = 0 ; i < N; i++)
			for (int j = 0; j < N; j++)
			{
				ans = Math.max(ans,  dp_states[i][j]);
				ans = Math.max(ans, dp_states2[i][j]);
			}
			
		//System.out.println(ans);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("pogocow.out")));
		pw.println(ans);
		pw.close();
		br.close();
	}
}
