import java.io.*;
import java.util.*;

public class DuelingGPS {
	static ArrayList<ArrayList<Integer>> AdjList = new ArrayList<ArrayList<Integer>>();
	static ArrayList<ArrayList<Integer>> P = new ArrayList<ArrayList<Integer>>();
	static ArrayList<ArrayList<Integer>> Q = new ArrayList<ArrayList<Integer>>();
	static ArrayList<ArrayList<Integer>> dispute = new ArrayList<ArrayList<Integer>>();
	static ArrayList<ArrayList<Integer>> roads = new ArrayList<ArrayList<Integer>>();
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("gpsduel.in"));
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int M = Integer.parseInt(temp[1]);
		Comparator<int[]> c = new Comparator<int[]>()
		{
			public int compare(int[] o1, int[] o2)
			{
				return o1[0] - o2[0]; //ascending by distance
			}
		};
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(c); 
		for (int i = 0; i < N; i++)
		{
			AdjList.add(new ArrayList<Integer>());
			P.add(new ArrayList<Integer>());
			Q.add(new ArrayList<Integer>());
			dispute.add(new ArrayList<Integer>());
			roads.add(new ArrayList<Integer>());
		}
		int node1, node2, wp, wq;
		for (int i = 0; i < M; i++)
		{
			temp = br.readLine().split(" ");
			node1 = Integer.parseInt(temp[0])-1;
			node2 = Integer.parseInt(temp[1])-1;
			wp = Integer.parseInt(temp[2]);
			wq = Integer.parseInt(temp[3]);
			AdjList.get(node2).add(node1);
			P.get(node2).add(wp);
			Q.get(node2).add(wq); // reverse edges to compute shortest paths from 5(the sink)
		}
		int[] distp = new int[N];
		int[] distq = new int[N];
		Arrays.fill(distp, Integer.MAX_VALUE);
		int source = N-1;
		distp[source] = 0;
		pq.add(new int[] {0, source});
		int[] el;
		ArrayList<Integer> edges;
		while (!pq.isEmpty())
		{
			el = pq.poll();
			if (el[0] > distp[el[1]]) //if we already got to this point with a path that is more optimal, don't even relax this one's edges, because it couldn't be on the shortest path.
				continue;
			edges = AdjList.get(el[1]);
			for (int i = 0; i < edges.size(); i++)
			{
				if (el[0] + P.get(el[1]).get(i) < distp[edges.get(i)])
				{
					distp[edges.get(i)] = el[0] + P.get(el[1]).get(i);
					pq.add(new int[] {distp[edges.get(i)], edges.get(i)});
				}
			}
		}
		Arrays.fill(distq, Integer.MAX_VALUE);
		distq[source] = 0;
		pq.add(new int[] {0, source});
		while (!pq.isEmpty())
		{
			el = pq.poll();
			if (el[0] > distq[el[1]]) //if we already got to this point with a path that is more optimal, don't even relax this one's edges, because it couldn't be on the shortest path.
				continue;
			edges = AdjList.get(el[1]);
			for (int i = 0; i < edges.size(); i++)
			{
				if (el[0] + Q.get(el[1]).get(i) < distq[edges.get(i)])
				{
					distq[edges.get(i)] = el[0] + Q.get(el[1]).get(i);
					pq.add(new int[] {distq[edges.get(i)], edges.get(i)});
				}
			}
		}
		int weight;
		for (int i = 0; i < AdjList.size(); i++)
		{
			for (int j = 0; j < AdjList.get(i).size(); j++)
			{
				weight = 0;
				if (!(distp[AdjList.get(i).get(j)]-distp[i] == P.get(i).get(j)))
					weight++;
				if (!(distq[AdjList.get(i).get(j)]-distq[i] == Q.get(i).get(j)))
					weight++;
				dispute.get(AdjList.get(i).get(j)).add(weight); // un reversing edges once more
				roads.get(AdjList.get(i).get(j)).add(i); 
			}
		}
		source = 0;
		int[] disp = new int[N];
		int end = N-1;
		Arrays.fill(disp, Integer.MAX_VALUE);
		disp[source] = 0;
		pq.add(new int[] {0, source});
		while (!pq.isEmpty())
		{
			el = pq.poll();
			if (el[0] > disp[el[1]])
				continue;
			if (el[1] == end)
				break;
			edges = roads.get(el[1]);
			for (int i = 0; i < edges.size(); i++)
			{
				if (el[0] + dispute.get(el[1]).get(i) < disp[edges.get(i)])
				{
					disp[edges.get(i)] = el[0] + dispute.get(el[1]).get(i);
					pq.add(new int[] {disp[edges.get(i)], edges.get(i)});
				}
			}
		}
		//System.out.println(disp[end]);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("gpsduel.out")));
		pw.println(disp[end]);
		pw.close();
		br.close();
	}
}
