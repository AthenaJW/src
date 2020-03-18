import java.io.*;
import java.util.*;

public class GreatRevegetation {
	static ArrayList<ArrayList<Integer>> AdjList;
	static int[] sets;
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("revegetate.in"));
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int M = Integer.parseInt(temp[1]);
		AdjList = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < N; i++)
			AdjList.add(new ArrayList<Integer>());
		sets = new int[N];
		Arrays.fill(sets, -1);
		int node1, node2;
		for (int i = 0; i < M; i++)
		{
			temp = br.readLine().split(" ");
			node1 = Integer.parseInt(temp[1])-1;//turn 0 based			
			node2 = Integer.parseInt(temp[2])-1;
			AdjList.get(node1).add(node2);			
			AdjList.get(node2).add(node1);
		}
		int setnum = 0;
		for (int i = 0; i < N; i++)
		{
			if (sets[i] != -1)// if it belongs to another set
				continue;
			bfs(i, setnum++);
		}
		//for each set there is two options. 2^setnum for all possibilities
		//System.out.print("1");
		//for (int i = 0; i < setnum; i++)
		//	System.out.print("0");
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("revegetate.out")));
		pw.write('1');
		for (int i = 0; i < setnum; i++)
			pw.write('0');
		pw.println();
		pw.close();
		br.close();
	}
	public static void bfs(int node, int setnum)
	{
		if (sets[node] == setnum) //if we've already visited here
			return;
		sets[node] = setnum; //or else mark it as in the set
		for (int edge: AdjList.get(node))
			bfs(edge, setnum);
	}
}
