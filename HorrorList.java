import java.io.*;
import java.util.*;

public class HorrorList {
	static ArrayList<ArrayList<Integer>> AdjList; //zero based
	static int[] HI;
	static int[] horror;
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("template.in"));
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int H = Integer.parseInt(temp[1]);
		int L = Integer.parseInt(temp[2]);
		AdjList = new ArrayList<ArrayList<Integer>>(N);
		HI = new int[N];
		Arrays.fill(HI,  Integer.MAX_VALUE);
		for (int i = 0; i < N; i++)
		{
			AdjList.add(new ArrayList<Integer>());
		}
		horror = new int[H];
		temp = br.readLine().split(" ");
		for (int i = 0; i < H; i++)
			horror[i] = Integer.parseInt(temp[i]);
		int node1, node2;
		for (int i = 0; i < L; i++)
		{
			temp = br.readLine().split(" ");
			node1 = Integer.parseInt(temp[0]);
			node2 = Integer.parseInt(temp[1]);
			AdjList.get(node1).add(node2);
			AdjList.get(node2).add(node1);
		}
		for (int h: horror)
			bfs(h, 0);
		int maxMovie = 0;
		for (int i = 0; i < HI.length; i++)
		{
			if (HI[i] > HI[maxMovie])
				maxMovie = i;
		}
		System.out.println(maxMovie);
		//PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("template.out")));
		//pw.println();
		//pw.close();
		br.close();
	}
	public static void bfs(int node, int layer)
	{
		if (layer < HI[node])
		{
			HI[node] = layer;
			layer++;
			for (int edge: AdjList.get(node))
				bfs(edge, layer);
		}
	}
}
