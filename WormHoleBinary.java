import java.io.*;
import java.util.*;

public class WormHoleBinary {
	//global graph variables
	static ArrayList<ArrayList<Integer>> AdjList = new ArrayList<ArrayList<Integer>>();
	static ArrayList<ArrayList<Integer>> weights = new ArrayList<ArrayList<Integer>>();
	static int[] beenThere;
	static int[] positions;
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("wormsort.in"));
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int M = Integer.parseInt(temp[1]);
		beenThere = new int[N];
		positions = new int[N];
		temp = br.readLine().split(" ");
		for (int i = 0; i < N; i++)
		{
			positions[i] = Integer.parseInt(temp[i])-1;
			AdjList.add(new ArrayList<Integer>());
			weights.add(new ArrayList<Integer>());
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
		//binary search
		int start = 1;
		int end = 1000000001;
		int mid;
		while (start != end)
		{
			mid = (start + end + 1) / 2;
			if (passes(mid))
				start = mid;
			else
				end = mid - 1;	
		}
		//System.out.println(start);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("wormsort.out")));
		if (start == 1000000001)
			pw.println(-1);
		else 
			pw.println(start);
		pw.close();
		br.close(); 
	}
	private static boolean passes(int test)
	{
		Arrays.fill(beenThere, -1);
		int numComp = 0;
		for (int i = 0; i < beenThere.length; i++)
		{
			if (beenThere[i] < 0) // if not visited
				dfs(i, numComp++, test);
		}
		for (int i = 0; i < beenThere.length; i++)
		{
			if (beenThere[i] != beenThere[positions[i]])
			    return false;
		}
		return true;
	}	
	private static void dfs(int i, int currentComp, int test)
	{
		if (beenThere[i] == currentComp) // if you're going into a cycle
			return; //then do nothing		
		//otherwise, add into current set and keep exploring
		beenThere[i] = currentComp;
		for (int k = 0; k < AdjList.get(i).size(); k++)
		{
			if (weights.get(i).get(k) >= test)
				dfs(AdjList.get(i).get(k), currentComp, test);
		}
	}	
}
