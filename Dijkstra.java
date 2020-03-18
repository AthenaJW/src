import java.util.*;

public class Dijkstra {
	public static void main(String[] args)
	{
		int num = 10; // change to number of nodes
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(); 
		Comparator<int[]> c = new Comparator<int[]>()
		{
			public int compare(int[] o1, int[] o2)
			{
				return o1[0] - o2[0]; //ascending by distance
			}
		};
		ArrayList<ArrayList<Integer>> AdjList = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> weights = new ArrayList<ArrayList<Integer>>();
		//initialize adjacency list
		int[] distances = new int[num];
		int[] path = new int[num];
		Arrays.fill(distances, Integer.MAX_VALUE);
		int source = 0;
		int endpoint = 1; //beginning and ending of path
		distances[source] = 0;
		path[source] = source;
		pq.add(new int[] {0, source});
		int[] el;
		ArrayList<Integer> edges;
		while (!pq.isEmpty())
		{
			el = pq.poll();
			if (el[0] >= distances[el[1]]) //if we already got to this point with a path that is more optimal, don't even relax this one's edges, because it couldn't be on the shortest path.
				continue;
			if (el[1] == endpoint)
				break;
			edges = AdjList.get(el[1]);
			for (int i = 0; i < edges.size(); i++)
			{
				if (el[0] + weights.get(el[1]).get(i) < distances[edges.get(i)])
				{
					distances[edges.get(i)] = el[0] + weights.get(el[1]).get(i);
					path[edges.get(i)] = el[1];
					pq.add(new int[] {distances[edges.get(i)], i});
				}
			}
		}
	}
}
