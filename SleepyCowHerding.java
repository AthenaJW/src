import java.io.*;
import java.util.Arrays;

public class SleepyCowHerding {
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("herding.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("herding.out")));
		int N = Integer.parseInt(br.readLine());
		int[] loc = new int[N];
		for (int i = 0; i < N; i++)
			loc[i] = Integer.parseInt(br.readLine());
		int beg = 0;
		int end = N-1;
		Arrays.sort(loc);
		int moves = 0;
		while (loc[end] - loc[beg] + 1 > N)
		{
			//devolved case, ex. 5 6 7 8 100
			if (loc[end-1] - loc[beg] + 1 == N-1)
			{
				if (loc[end] - loc[end-1]-1 == 1)
					moves++;
				else
					moves += 2;
				break;
			}
			else if (loc[end] - loc[beg + 1] + 1 == N-1)
			{
				if (loc[beg+1] - loc[beg] - 1 == 1)
					moves++;
				else
					moves += 2;
				break;
			}
			if (loc[end-1] - loc[beg] < loc[end] - loc[beg+1])
				end--;
			else
				beg++;
			moves++;
		}
		//System.out.println(moves);
		pw.println(moves);
		end = N-1;
		beg = 0;
		int max = Math.max(loc[end] - loc[beg+1], loc[end-1] - loc[beg]) - N + 2;
		//System.out.println(max);
		pw.println(max);
		pw.close();
		br.close();
	}

}
