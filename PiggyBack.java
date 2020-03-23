import java.io.*;
import java.util.*;

public class PiggyBack {
	static int[] bessie;
	static int[] elsie;
	static int[] distN;
	static boolean[] visited;
	static ArrayList<ArrayList<Integer>> AdjList;
	static LinkedList<Integer> queue = new LinkedList<Integer>();
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("piggyback.in"));
		String[] temp = br.readLine().split(" ");
		int B = Integer.parseInt(temp[0]);
		int E = Integer.parseInt(temp[1]);
		int P = Integer.parseInt(temp[2]);
		int N = Integer.parseInt(temp[3]);
		int M = Integer.parseInt(temp[4]);
		bessie = new int[N];
		elsie = new int[N];
		distN = new int[N];
		visited = new boolean[N];
		AdjList = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i <N; i++)
			AdjList.add(new ArrayList<Integer>());
		int node1, node2;
		for (int i = 0; i < M; i++)
		{
			temp = br.readLine().split(" ");
			node1 = Integer.parseInt(temp[0])-1;
			node2 = Integer.parseInt(temp[1])-1;
			AdjList.get(node1).add(node2);
			AdjList.get(node2).add(node1);
		}
		bfs(0, 0, bessie, B, N-1);
		Arrays.fill(visited, false);
		bfs(1, 0, elsie, E, N-1);
		Arrays.fill(visited, false);
		bfs(N-1, 0, distN, P, 0);
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++)
			if (bessie[i]+elsie[i]+distN[i] < min)
				min = bessie[i]+elsie[i]+distN[i];
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("piggyback.out")));
		pw.println(min);
		br.close();
		pw.close();
	}
	public static void bfs(int node, int distance, int[] array, int energy, int end)
	{
		queue.add(node);
		int current;
		visited[node] = true;
		while (!queue.isEmpty())
		{
			current = queue.removeFirst();
			for (int neighbor: AdjList.get(current))
			{
				if (visited[neighbor])
					continue;
				array[neighbor] = array[current] + energy;
				visited[neighbor] = true; // mark visited here just in case of cycles
				queue.add(neighbor);
			}
		}
	}
}
