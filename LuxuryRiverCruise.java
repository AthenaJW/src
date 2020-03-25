import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class LuxuryRiverCruise {
	static int[][] cyclestates;
	static int N, M; 
	static long K;
	static int[][] sets;
	static ArrayList<int[]> cycle;
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("cruise.in"));
		String[] temp = br.readLine().split(" ");
		N = Integer.parseInt(temp[0]);
		M = Integer.parseInt(temp[1]);
		K = Long.parseLong(temp[2]);
		K *= M;
		cyclestates = new int[N][M];
		cycle = new ArrayList<int[]>();
		sets = new int[N][M];
		for (int[] row: sets)
			Arrays.fill(row, -1);
		int[] left = new int[N];
		int[] right = new int[N];
		int l, r;
		for (int i = 0; i < N; i++)
		{
		    temp = br.readLine().split(" ");
		    l = Integer.parseInt(temp[0])-1;
		    r = Integer.parseInt(temp[1])-1;
		    left[i] = l;
		    right[i] = r;
		}
		char[] steps = new char[M];
		temp = br.readLine().split(" ");
		for (int i = 0; i < M; i++)
			steps[i] = temp[i].charAt(0);
		for (int i = 0; i < M; i++)
		{
			for (int j = 0; j < N; j++)
			{
				if (steps[i] == 'L')
					cyclestates[j][i] = left[j]; // this node at this step maps to node at left[j
				else
					cyclestates[j][i] = right[j];
			}
		}
		int ans = cycleDetection(new int[] {0, 0});
		//System.out.println(ans+1);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cruise.out")));
		pw.println(ans+1);
		pw.close();
		br.close();
	} // Floyd Tortoise and Hare Cycle Detection
	public static int cycleDetection(int[] node)
	{
		int[] tortoise = node;
		int[] hare = node;
		while (K > 0)
		{
			tortoise = getNext(tortoise);
			hare = getNext(getNext(hare));
			K--;
			if (tortoise[0] == hare[0] && tortoise[1] == hare[1])
				break;
		}//find the place where tortoise and hare meet, it will be within the cycle

		if (K == 0)
			return tortoise[0];
		int[] point = tortoise;
		int cyclesize = 0;
		while (true)
		{
			if (sets[point[0]][point[1]]==0) // count how many elements in the cycle
				break;
			sets[point[0]][point[1]] = 0;
			cyclesize++;
			point = getNext(point);
		}
		K %= cyclesize; // find which step of the cycle k will end up on
		while (K>0)
		{
			tortoise = getNext(tortoise);
			K--;
		} // go to that step of the cycle
		return tortoise[0];
	}
	public static int[] getNext(int[] node)
	{
		return new int[] { cyclestates[node[0]][node[1]], (node[1]+1)%M };
	}
}
