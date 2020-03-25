import java.io.*;

public class CowJogSimplified {
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("cowjog.in"));
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int T = Integer.parseInt(temp[1]);
		long[][] segments = new long[N][2];
		long x1, x2, v;
		for (int i = 0; i < N; i++) // segments already given in increasing order
		{
			temp = br.readLine().split(" "); // of starting point
			x1 = Long.parseLong(temp[0]);
			v = Long.parseLong(temp[1]);
			x2 = v*T+x1;
			segments[i] = new long[] {x1, x2};
		}
		long min = Long.MAX_VALUE;
		int groups = 0;
		for (int i = N-1; i>-1; i--)
		{
			if (segments[i][1] < min)
				groups++;
			min = Math.min(segments[i][1], min);
		}
		//System.out.println(groups);
		/*Explanation from USACO:
		 * Processing the segments backward from decreasing starting point
		 * keep track of the point closest to the start, out of the cows we've seen
		 * so far, this represents the slowest cow. Any time a cow segment ends after
		 * this value, this cow is absorbed with the slower cow.
		 * If a new min is established, this cow is independent and never overlaps 
		 * our previous min. Now we compare against this one.
		 */
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowjog.out")));
		pw.println(groups);
		pw.close();
		br.close();
	}
}
