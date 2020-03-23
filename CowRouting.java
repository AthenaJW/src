import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class CowRouting {
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("cowroute.in"));
		int[][][] AdjMatrix = new int[1001][1001][2];
		for (int[][] row: AdjMatrix)
			for (int[] col: row)
			Arrays.fill(col, Integer.MAX_VALUE);
		for (int i = 0; i < AdjMatrix.length; i++)
			AdjMatrix[i][i] = new int[] {0, 0};
		String[] temp = br.readLine().split(" ");
		int start = Integer.parseInt(temp[0]);
		int end = Integer.parseInt(temp[1]);
		int N = Integer.parseInt(temp[2]);
		int c1, c2, cost, num;
		for (int i = 0; i < N; i++)
		{
			temp = br.readLine().split(" ");
			cost = Integer.parseInt(temp[0]);
			num = Integer.parseInt(temp[1]);
			temp = br.readLine().split(" ");
			for (int a = 0; a < num-1; a++)
			{
				for (int b = a+1; b < num; b++)
				{
					c1 = Integer.parseInt(temp[a]); c2 = Integer.parseInt(temp[b]);
					if (AdjMatrix[c1][c2][0] > cost)
					{
						AdjMatrix[c1][c2][0]= cost;
						AdjMatrix[c1][c2][1] = b-a;
					}
					else if (AdjMatrix[c1][c2][0] == cost)
					{
						if (AdjMatrix[c1][c2][1] > b-a)
							AdjMatrix[c1][c2][1] = b-a;
					}
						
				}
			}
		}

		long[] distances = new long[1001];
		int[] numlines = new int[1001];
		Arrays.fill(distances, Long.MAX_VALUE);
		Arrays.fill(numlines, Integer.MAX_VALUE);
		numlines[start] = 0;
		distances[start] = 0;
		Comparator<long[]> c = new Comparator<long[]>()
		{
			public int compare(long[] o1, long[] o2)
			{
				if (o1[0] != o2[0])	
					return Long.signum(o1[0] - o2[0]); //ascending by distance
				return Long.signum(o1[1] - o2[1]);
			}
		};
		PriorityQueue<long[]> pq = new PriorityQueue<long[]>(c);
		pq.add(new long[] {0, 0, start});
		boolean found = false;
		long[] el;
		while (!pq.isEmpty())
		{
			el = pq.poll();
			if (el[0] > distances[(int) el[2]]) //if we already got to this point with a path that is more optimal, don't even relax this one's edges, because it couldn't be on the shortest path.
				continue;
			if (el[2] == end)
			{
				found = true;
				break;
			}
			for (int i = 0; i < 1001; i++)
			{
				if (AdjMatrix[(int) el[2]][i][0] == Integer.MAX_VALUE)
					continue;
				if (el[0] + AdjMatrix[(int) el[2]][i][0] < distances[i])
				{
					distances[i] = el[0] + AdjMatrix[(int) el[2]][i][0];
					numlines[i] = (int) (el[1] + AdjMatrix[ (int) el[2]][i][1]);
					pq.add(new long[] {distances[i], numlines[i], i});
				}
				else if (el[0] + AdjMatrix[(int) el[2]][i][0] == distances[i])
				{
					if ((int) (el[1] + AdjMatrix[ (int) el[2]][i][1]) < numlines[i])
					{
						distances[i] = el[0] + AdjMatrix[(int) el[2]][i][0];
						numlines[i] = (int) (el[1] + AdjMatrix[ (int) el[2]][i][1]);
						pq.add(new long[] {distances[i], numlines[i], i});
					}
				}
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowroute.out")));

		if (found)
		{
			//System.out.println(distances[end] + " " +  (numlines[end]));
			pw.println(distances[end] + " " +  (numlines[end]));
		}
		else
		{
			//System.out.println(distances[end] + " " +  (numlines[end]));
			pw.println("-1 -1");
		}
		pw.close();
		br.close();
	}
}
