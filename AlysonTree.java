import java.io.*;
import java.util.*;
public class AlysonTree {
	static int[] distance;
	static int[] values;
	static ArrayList<ArrayList<Integer>> AdjList = new ArrayList<ArrayList<Integer>>();
	static ArrayList<ArrayList<Integer>> weights = new ArrayList<ArrayList<Integer>>();
	static boolean[] visited;
	static boolean[] connected;
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		values = new int[N]; distance = new int[N]; visited = new boolean[N];
		connected = new boolean[N];
		String[] temp = br.readLine().split(" ");
		for (int i = 0; i < N; i++)
			values[i] = Integer.parseInt(temp[i]);
		int node, weight;
		for (int i = 0; i < N; i++)
		{
			AdjList.add(new ArrayList<Integer>());
			weights.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < N-1; i++)
		{
			temp = br.readLine().split(" ");
			node = Integer.parseInt(temp[0])-1;
			weight = Integer.parseInt(temp[1]);
			AdjList.get(i+1).add(node);
			AdjList.get(node).add(i+1);
			weights.get(i+1).add(weight);
			weights.get(node).add(weight);
		}
		treeTraversal(0);
		delete(0);
		int count = 0;
		for (boolean in: connected)
			if (!in)
				count++;
		System.out.println(count);
	}
	public static void treeTraversal(int node)
	{
		visited[node] = true;
		for (int i = 0; i < AdjList.get(node).size(); i++)
		{
			if (visited[AdjList.get(node).get(i)])
				continue;
			distance[AdjList.get(node).get(i)] = Math.max(distance[node] + weights.get(node).get(i), 0);
			treeTraversal(AdjList.get(node).get(i));
		}
	}
	public static void delete(int node)
	{
		connected[node] = true;
		for (int i = 0; i < AdjList.get(node).size(); i++)
		{
			if (connected[AdjList.get(node).get(i)])
				continue;
			if (distance[AdjList.get(node).get(i)] > values[AdjList.get(node).get(i)])
				continue;
			delete(AdjList.get(node).get(i));
		}
	}
}
