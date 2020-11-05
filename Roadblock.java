import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Roadblock {
	static ArrayList<ArrayList<Integer>> AdjList = new ArrayList<ArrayList<Integer>>();
	static ArrayList<ArrayList<Integer>> weights = new ArrayList<ArrayList<Integer>>();
	static PriorityQueue<int[]> pq; 
	static int N, M;
	static int[] dist;
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("rblock.in"));
		String[] temp = br.readLine().split(" ");
		N = Integer.parseInt(temp[0]);
		M = Integer.parseInt(temp[1]);
		Comparator<int[]> c = new Comparator<int[]>()
		{
			public int compare(int[] o1, int[] o2)
			{
				return o1[0] - o2[0]; //ascending by distance
			}
		};
		for (int i = 0; i < N; i++)
		{
			AdjList.add(new ArrayList<Integer>());
			weights.add(new ArrayList<Integer>());
		}
		int node1, node2, w;
		for (int i = 0; i < M; i++)
		{
			temp = br.readLine().split(" ");
			node1 = Integer.parseInt(temp[0])-1;
			node2 = Integer.parseInt(temp[1])-1;
			w = Integer.parseInt(temp[2]);
			AdjList.get(node1).add(node2);
			weights.get(node1).add(w);
			AdjList.get(node2).add(node1);
			weights.get(node2).add(w);
		}
		pq = new PriorityQueue<int[]>(c);
		dist = new int[N];
		int[] el;
		ArrayList<Integer> edges;
		int source = 0;
		int[] distf = new int[N];
		int[] path = new int[N];
		Arrays.fill(path, -1);
		path[source] = source;
		Arrays.fill(distf, Integer.MAX_VALUE);
		distf[source] = 0;
		pq.add(new int[] {0, source});
		while (!pq.isEmpty())
		{
			el = pq.poll();
			if (el[0] > distf[el[1]]) //if we already got to this point with a path that is more optimal, don't even relax this one's edges, because it couldn't be on the shortest path.
				continue;
			edges = AdjList.get(el[1]);
			for (int i = 0; i < edges.size(); i++)
			{
				if (el[0] + weights.get(el[1]).get(i) < distf[edges.get(i)])
				{
					distf[edges.get(i)] = el[0] + weights.get(el[1]).get(i);
					path[edges.get(i)] = el[1];
					pq.add(new int[] {distf[edges.get(i)], edges.get(i)});
				}
			}
		}
		int minbefore = distf[N-1];
		int point1 = -1; int point2 = N-1; int index; int min;
		int d;
		int max = 0;
		while (point2!=point1)
		{
			point1 = point2;
			point2 = path[point2];
			if (point1 == point2)
				break;
			index = AdjList.get(point2).indexOf(point1);
			weights.get(point2).set(index, weights.get(point2).get(index)*2);
			index = AdjList.get(point1).indexOf(point2);
			weights.get(point1).set(index, weights.get(point1).get(index)*2);
			d = shortestPath(0, N-1);
			max = Math.max(max, d);
			index = AdjList.get(point2).indexOf(point1);
			weights.get(point2).set(index, weights.get(point2).get(index)/2);
			index = AdjList.get(point1).indexOf(point2);
			weights.get(point1).set(index, weights.get(point1).get(index)/2);
		}
		//System.out.println(max-minbefore);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("rblock.out")));
		pw.println(max-minbefore);
		pw.close();
		br.close();
	}
	public static int shortestPath(int source, int end)
	{
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[source] = 0;
		pq.add(new int[] {0, source});
		int[] el;
		ArrayList<Integer> edges;
		while (!pq.isEmpty())
		{
			el = pq.poll();
			if (el[0] > dist[el[1]]) //if we already got to this point with a path that is more optimal, don't even relax this one's edges, because it couldn't be on the shortest path.
				continue;
			edges = AdjList.get(el[1]);
			for (int i = 0; i < edges.size(); i++)
			{
				if (el[0] + weights.get(el[1]).get(i) < dist[edges.get(i)])
				{
					dist[edges.get(i)] = el[0] + weights.get(el[1]).get(i);
					pq.add(new int[] {dist[edges.get(i)], edges.get(i)});
				}
			}
		}
		return dist[end];
	}
}
