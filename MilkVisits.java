import java.io.*;
import java.util.*;

public class MilkVisits {
	static int[] sets;
	static ArrayList<ArrayList<Integer>> AdjList;
	static char[] types;
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("milkvisits.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milkvisits.out")));

		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int M = Integer.parseInt(temp[1]);
		types = br.readLine().toCharArray();
		sets = new int[N]; Arrays.fill(sets,  -1);
		AdjList = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < N; i++)
			AdjList.add(new ArrayList<Integer>());
		int node1, node2;
		for (int i = 0; i < N-1; i++)
		{
			temp = br.readLine().split(" ");
			node1 = Integer.parseInt(temp[0])-1;
			node2 = Integer.parseInt(temp[1])-1;
			AdjList.get(node1).add(node2);
			AdjList.get(node2).add(node1);
		}
		int setnum = 0;
		for (int i = 0; i < N; i++)
		{
			if (sets[i] != -1)
				continue;
			bfs(i, types[i], setnum++);
		}
		char type;
		for (int i = 0; i < M; i++)
		{
			temp = br.readLine().split(" ");
			node1 = Integer.parseInt(temp[0])-1;
			node2 = Integer.parseInt(temp[1])-1;
			type = temp[2].charAt(0);
			if (sets[node1] != sets[node2])
				pw.print("1");
			else if (types[node1] == type)
				pw.print("1");
			else
				pw.print("0");
		}
		//System.out.println();
		pw.println();
		pw.close();
		br.close();
	}
	public static void bfs(int node, char type, int setnum)
	{
		if (types[node] != type || sets[node] == setnum)
			return;
		sets[node] = setnum;
		for (int neighbor: AdjList.get(node))
			bfs(neighbor, type, setnum);
	}
}
