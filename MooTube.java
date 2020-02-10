import java.io.*;
import java.util.*;
public class MooTube {
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("mootube.in"));
		String[] temp = br.readLine().split(" ");
		int num = Integer.parseInt(temp[0]);
		int querys = Integer.parseInt(temp[1]);
		boolean[] visited;
		ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>(num+1);
		ArrayList<ArrayList<Integer>> weights = new ArrayList<ArrayList<Integer>>(num+1);

		for (int i = 0; i < num+1; i++)
		{
			edges.add(new ArrayList<Integer>());
			weights.add(new ArrayList<Integer>());
		}
		int node1, node2, weight;
		for (int i = 0; i < num-1; i++)
		{
			temp = br.readLine().split(" ");
			node1 = Integer.parseInt(temp[0]);
			node2 = Integer.parseInt(temp[1]);
			weight = Integer.parseInt(temp[2]);
			edges.get(node1).add(node2);
			weights.get(node1).add(weight);
			edges.get(node2).add(node1);
			weights.get(node2).add(weight);
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("mootube.out")));
		int video, K;
		int count;
		for (int i = 0; i < querys; i++)
		{
			temp = br.readLine().split(" ");
			K = Integer.parseInt(temp[0]);
			video = Integer.parseInt(temp[1]);
			visited = new boolean[num+1];
			count = explore(video, edges, weights, visited, K);
			//System.out.println(count);
			pw.println(count);
		}	
		pw.close();
		br.close();
	}
	public static int explore(int node, ArrayList<ArrayList<Integer>> edges, ArrayList<ArrayList<Integer>> weights, boolean[] visited, int K)
	{
		int count = 0;
		visited[node] = true;
		ArrayList<Integer> e = edges.get(node);
		ArrayList<Integer> w = weights.get(node);
		for (int i = 0; i < e.size(); i++)
		{
			if (visited[e.get(i)])
				continue;
			if (w.get(i) >= K)
			{
				count++;
				count += explore(e.get(i), edges, weights, visited, K);
			}
		}
		return count;
	}
}
