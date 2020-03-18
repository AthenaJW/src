import java.io.*;
import java.util.*;

public class CowDanceShow {
	static int N, T;
	static long[] durations;
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("cowdance.in"));
		String[] temp = br.readLine().split(" ");
		N = Integer.parseInt(temp[0]);
		T = Integer.parseInt(temp[1]);
		durations = new long[N];
		for (int i = 0; i < N; i++)
		{
			durations[i] = Long.parseLong(br.readLine());
		}
		int start = 1;
		int end = N;
		int med;
		while(start != end)
		{
			med = (start + end) / 2;
			if (pass(med))
				end = med;
			else
				start = med + 1;
		}
		//System.out.println(start);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowdance.out")));
		pw.println(start);
		pw.close();
		br.close();
	}
	public static boolean pass(int K)
	{
		PriorityQueue<Long> onStage = new PriorityQueue<Long>();
		int i = 0;
		while (i < K)
		{
			onStage.add(durations[i]);
			i++;
		}
		long offStage;
		while (i < N)
		{
			offStage = onStage.remove();
			onStage.add(offStage + durations[i]);
			i++;
		}
		long time = 0;
		while (!onStage.isEmpty())
		{
			if (onStage.size() == 1)
			{
				time = onStage.remove();
				break;
			}
			onStage.remove();
		}
		//System.out.println(K + " " + endTimes[N-1] + " " + lastAct);
		return time <= T;
	}
}
