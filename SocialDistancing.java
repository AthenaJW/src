import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class SocialDistancing {
	static long[][] intervals;
	static int N, M;
	static long min;
	static Comparator<long[]> c = new Comparator<long[]>()
	{
		public int compare(long[] o1, long[] o2)
		{
			return Long.signum(o1[0] - o2[0]);
		}
	};
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("socdist.in"));
		String[] temp = br.readLine().split(" ");
		N = Integer.parseInt(temp[0]);
		M = Integer.parseInt(temp[1]);
		intervals = new long[M][2];
		long x1, x2; min = Long.MAX_VALUE; long max = 0;
		for (int i = 0; i < M; i++)
		{
			temp = br.readLine().split(" ");
			x1 = Long.parseLong(temp[0]);
			x2 = Long.parseLong(temp[1]);
			min = Math.min(min,  x1);
			max = Math.max(max,  x2);
			intervals[i] = new long[]{x1, x2};
		}
		System.out.println(min + " " + max);
		Arrays.sort(intervals, c);
		long start = 1;
		long end = max-min+1;
		long mid;
		while (start != end)
		{
			mid = (start+end+1)/2;
			if (pass(mid))
				start = mid;
			else
				end = mid-1;
		}
		//System.out.println(start);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("socdist.out")));
		pw.println(start);
		pw.close();
		br.close();
	}
	/*private static boolean pass(long test)
	{
		int i = 0; long xpos = min; int count = 1;
		System.out.println("TEST: " + test);
		while (i < M)
		{
			System.out.println(xpos);
			if (xpos + test < intervals[i][1])
			{
				xpos += test;
				count++;
			}
			else
			{
				while (i < M && intervals[i][1] < xpos + test)
				{
					System.out.println("i " + i + " " + intervals[i][1] +
							" " + (xpos+test));
					i++;
				}
				if (i == M)
					break;
				xpos = Math.max(intervals[i][0], xpos + test);
			}
			
			if (count >= N)
				return true;
		}
		return false;
	}*/
	private static boolean pass(long test)
	{
		long nextstart = 0;
		long count = 0;
		long numcows = 0;
		for (int i = 0; i < M; i++)
		{
			if (nextstart > intervals[i][1])
				continue;
			if (nextstart < intervals[i][0])
				nextstart = intervals[i][0];
			numcows = ((intervals[i][1] - nextstart)/test)+1;
			count += numcows;
			nextstart += numcows*test;
		}
		return count >= N;
	}
}
