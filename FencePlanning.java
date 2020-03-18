import java.io.*;
import java.util.*;

public class FencePlanning {
	static ArrayList<ArrayList<Integer>> AdjList;
	static int[] sets;
	static Point[] locations;
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("fenceplan.in"));
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int M = Integer.parseInt(temp[1]);
		AdjList = new ArrayList<ArrayList<Integer>>();
		locations = new Point[N];
		sets = new int[N];
		Arrays.fill(sets, -1);
		for (int i = 0; i < N; i++)
		{
			temp = br.readLine().split(" ");
			locations[i] = new FencePlanning().new Point(Integer.parseInt(temp[0])
					, Integer.parseInt(temp[1]));
			AdjList.add(new ArrayList<Integer>());
		}
		int node1, node2;
		for (int i = 0; i < M; i++)
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
			bfs(i, setnum++);
		}
		ArrayList<ArrayList<Integer>> groups = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < setnum; i++)
			groups.add(new ArrayList());
		
		for (int i = 0; i < sets.length; i++)
			groups.get(sets[i]).add(i);
		
		//get min perimeter
		int minX, minY, maxX, maxY;
		int minP = Integer.MAX_VALUE;
		Point currloc;
		for (ArrayList<Integer> group: groups)
		{
			minX = locations[group.get(0)].x;
			maxX = minX;
			minY = locations[group.get(0)].y;
			maxY = minY;
			for (int cow: group)
			{
				currloc = locations[cow];
				minX = Math.min(currloc.x, minX);
				maxX = Math.max(currloc.x, maxX);
				minY = Math.min(currloc.y, minY);
				maxY = Math.max(currloc.y, maxY);
			}
			if (2*(maxX - minX + maxY - minY) < minP)
				minP = 2*(maxX - minX + maxY - minY);
		}
		//System.out.println(minP);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("fenceplan.out")));
		pw.println(minP);
		pw.close();
		br.close();
	}
	public static void bfs(int node, int setnum)
	{
		if (sets[node] == setnum) // if we've already been here return
			return;
		sets[node] = setnum; //or else claim it in the set
		for (int neighbor: AdjList.get(node))
			bfs(neighbor, setnum);
	}
	public class Point
	{
		public int x, y;
		public Point(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
		public Point() {}
	}
}
