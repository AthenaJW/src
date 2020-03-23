import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ClockTree {
	static int[] times = new int[100000];
	static ArrayList<ArrayList<Integer>> AdjList = new ArrayList<ArrayList<Integer>>();
	static int s0 = 0, s1 = 0, n0 = 0, n1 = 0;
	static boolean[] visited;
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("clocktree.in"));
		int a, b;
		int N = Integer.parseInt(br.readLine());
		String[] temp = br.readLine().split(" ");
		for (int i = 0; i < N; i++)
		{
			times[i] = Integer.parseInt(temp[i]);
			AdjList.add(new ArrayList<Integer>());
		}
		visited = new boolean[N];
		for (int i = 0; i < N-1; i++)
		{
			temp = br.readLine().split(" ");
			a = Integer.parseInt(temp[0])-1;
			b = Integer.parseInt(temp[1])-1;
			AdjList.get(a).add(b);
			AdjList.get(b).add(a);
		}
		int total = 0;
		dfs0(0, 0);
		s0 %= 12; s0 += 12; s0 %= 12;
		if (s0 == 0 || s0 == 1)
			total += n0; //all the "even" nodes
		Arrays.fill(visited, false);
		dfs1(1, 0);
		s1 %= 12; s1 += 12; s1 %= 12;
		if (s1 == 0 || s1 == 1)
			total += n1; //all the "odd" nodes
		//System.out.println(total);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("clocktree.out")));
		pw.println(total);
		pw.close();
		br.close();
	}
	public static void dfs0(int node, int parity)
	{
		if (visited[node])
			return;
		visited[node] = true;
		if (parity == 0)
		{
			s0 += times[node];
			n0++;
			parity = 1;
		}
		else
		{
			s0 -= times[node];
			parity = 0;
		}
		for (int neighbor: AdjList.get(node))
			dfs0(neighbor, parity);
	}
	public static void dfs1(int node, int parity)
	{
		if (visited[node])
			return;
		visited[node] = true;
		if (parity == 0)
		{
			s1 += times[node];
			n1++;
			parity = 1;
		}
		else
		{
			s1 -= times[node];
			parity = 0;
		}
		for (int neighbor: AdjList.get(node))
			dfs1(neighbor, parity);
	}
}
