import java.io.*;
import java.util.*;
public class RecordingMoolympics {
	static public int[][] dp_states;
	static public int[][] intervals;
	static int[][] maxtop;
	static int[][] maxbot;
	static Comparator<int[]> c = new Comparator<int[]>()
	{
		public int compare(int[] o1, int[] o2)
		{
			if (o1[0] != o2[0])
				return o1[0] - o2[0];
			return o1[1] - o2[1];
		}
	};
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("recording.in"));
		int N = Integer.parseInt(br.readLine());
		dp_states = new int[N+1][N+1]; // one indexed
		intervals = new int[N][2];
		maxtop = new int[N+1][N+1];
		maxbot = new int[N+1][N+1];
		String[] temp; int start, end;
		for (int i = 0; i < N; i++)
		{
		    temp = br.readLine().split(" ");
		    start = Integer.parseInt(temp[0]);
		    end = Integer.parseInt(temp[1]);
		    intervals[i] = new int[] {start, end};
		}
		Arrays.sort(intervals, c);
		dp_states[0][1] = 1;
		maxtop[0][1] = intervals[0][1];
		dp_states[1][0] = 1;
		maxbot[1][0] = intervals[0][1];
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j <= i; j++)
			{
				for (int k = 0; k <= i; k++)
				{
					if (maxtop[j][k] <= intervals[i][0] && (dp_states[j][k]+1) > dp_states[j][i+1])
					{
						dp_states[j][i+1] = dp_states[j][k]+1;
						maxtop[j][i+1] = Math.max(intervals[i][1], maxtop[j][k]);
						maxbot[j][i+1] = maxbot[j][k];
					}
					if (maxbot[j][k] <= intervals[i][0] && (dp_states[j][k]+1) > dp_states[i+1][k])
					{
						dp_states[i+1][k] = dp_states[j][k]+1;
						maxtop[i+1][k] = maxtop[j][k];
						maxbot[i+1][k] = Math.max(intervals[i][1], maxbot[j][k]);
					}	
				}
			}
		}
		for (int i = 0; i < N+1; i++)
		{
			for (int j = 0; j < N+1; j++)
				System.out.print(dp_states[i][j] + " ");
			System.out.println();
		}
		int max = 0;
		for (int i = 0; i < N+1; i++)
			for (int j = 0; j < N+1; j++)
				max = Math.max(max, dp_states[i][j]);
		//System.out.println(max);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("recording.out")));
		pw.println(max);
		pw.close();
		br.close();
	}
}
