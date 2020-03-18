import java.io.*;
import java.util.*;

public class ClosingFarm {
	static ArrayList<ArrayList<Integer>> AdjList;
	static boolean[] closed;
	static int[] inSet;
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("closing.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("closing.out")));
		
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int M = Integer.parseInt(temp[1]);
		inSet = new int[N];
		AdjList = new ArrayList<ArrayList<Integer>>();
		closed = new boolean[N];
		for (int i = 0; i < N; i++)
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
		int toDelete;
		String output;
		outer: for (int i = 0; i < N; i++)
		{
			output = "YES";
			Arrays.fill(inSet, 0);
			toDelete = Integer.parseInt(br.readLine())-1; // not deleted yet
			bfs(toDelete);
			for (int j = 0; j < inSet.length; j++)
			{
				if (closed[j])
					continue;
				if (inSet[j] == 0)
					output = "NO";
			}
			//System.out.println(output);
			pw.println(output);
			AdjList.get(toDelete).clear(); // delete
			closed[toDelete] = true;
		}
		pw.close();
		br.close();
	}
	public static void bfs(int node)
	{
		if (closed[node] || inSet[node] == 1) // if it's closed or already visited
			return;
		inSet[node] = 1;
		for (int neighbor: AdjList.get(node))
			bfs(neighbor);
	}
}
