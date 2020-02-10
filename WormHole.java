import java.io.*;
import java.util.*;

public class WormHole {
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("wormsort.in"));
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int M = Integer.parseInt(temp[1]);
		temp = br.readLine().split(" ");
		int[] positions = new int[N];
		int[] capacity = new int[N];
		boolean[] used = new boolean[N];
		ArrayList<Integer> outOfPlace = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> AdjList = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> weights = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < N; i++)
		{
			positions[i] = Integer.parseInt(temp[i]);
			AdjList.add(new ArrayList<Integer>());
			weights.add(new ArrayList<Integer>());
			if (positions[i] != i+1)
				outOfPlace.add(i);
		}
		int e1, e2, w;
		for (int i = 0; i < M; i++)
		{
			temp = br.readLine().split(" ");
			e1 = Integer.parseInt(temp[0]);
			e2 = Integer.parseInt(temp[1]);
			w = Integer.parseInt(temp[2]);
			AdjList.get(e1-1).add(e2-1);
			AdjList.get(e2-1).add(e1-1);
			weights.get(e1-1).add(w);
			weights.get(e2-1).add(w);
		}
		ArrayList<Integer> edges_current;
		ArrayList<Integer> weights_current;
		for (int i = 0; i < N; i++)
		{
			int j = -1;
			//finds the max suggested branch of the spanning tree
			for (int k = 0; k < N; k++)
			{
				if (used[k]) //if the edge is non viable(leads to a cycle), don't compare it at all
					continue;
				//short circuits
				if (j == -1 || capacity[k] > capacity[j]) //if it's the first one viable or it is greater than max
					j = k;
			}
			//mark as visited
			used[j] = true;
			//extend branches outward and suggest the distances to be the min of weight and distance there(the max min distance)
			edges_current = AdjList.get(j);
			weights_current = weights.get(j);
			for (int k = 0; k < edges_current.size(); k++)
			{
				if (capacity[j] == 0) //only first time
				{
					capacity[edges_current.get(k)] = weights_current.get(k); 
					continue;
				}
				System.out.println(k + " " + Math.min(capacity[j], weights_current.get(k)));
				capacity[edges_current.get(k)] = Math.max(Math.min(capacity[j], weights_current.get(k)), capacity[edges_current.get(k)]);
			}
		}
		int smallest = Integer.MAX_VALUE;
		for (int o: outOfPlace)
		{
			if (capacity[o] < smallest)
				smallest = capacity[o];
		}
		
		//System.out.println(smallest);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("wormsort.out")));
		pw.println(smallest);
		pw.close();
		br.close();
	}
}
