import java.io.*;
import java.util.*;
public class Tractor {
	static boolean[][] blocked = new boolean[1001][1001];
	static int[][] layers = new int[1001][1001];
	static LinkedList<int[]> node = new LinkedList<int[]>();
	static LinkedList<int[]> neighbors = new LinkedList<int[]>();
	static int xtract, ytract, maxx, maxy;
	public static void main(String[] args) throws IOException, InterruptedException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("3.in"));
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		xtract = Integer.parseInt(temp[1]);
		ytract = Integer.parseInt(temp[2]);
		int x, y;
		maxx = 0;
		maxy = 0;
		for (int[] row: layers)
			Arrays.fill(row, -1);
		for (int i = 0; i < N; i++)
		{
			temp = br.readLine().split(" ");
			x = Integer.parseInt(temp[0]);
			y = Integer.parseInt(temp[1]);
			maxx = Math.max(maxx, x);
			maxy = Math.max(maxy, x);

			blocked[x][y] = true;
		}
		maxx = Math.min(1000, maxx+1);
		maxy = Math.min(1000, maxy+1);
		node.add(new int[] {0, 0});
		int[] curr; int layernum = 0; int[] remove;
		while (!node.isEmpty() || !neighbors.isEmpty())
		{
			if (node.isEmpty())
			{
				while (!neighbors.isEmpty()) //need to move on to next layer
				{
					remove = neighbors.remove();
					blocked[remove[0]][remove[1]] = false; // open new pathways
					node.addLast(remove);
				}
				layernum++; // indicate that this is the next layer
			}
			curr = node.remove(); 
			search(curr[0], curr[1], layernum); // search current layer
			if (layers[xtract][ytract] != -1) // if we've reached exit, break;
				break;
		}
		System.out.println(layers[xtract][ytract]);

		br.close();
	}
	public static void search(int x, int y, int layernum) throws InterruptedException
	{
		if (layers[x][y]!=-1) // if we've already been here, no point in coming again
			return;
		if (blocked[x][y])
		{
			neighbors.add(new int[] {x, y}); // a barrier indicates this will go in the next layer
			return;
		}
		layers[x][y] = layernum; // keep track of how many bales we've broken
		if (x == xtract && y == ytract) 
			return;
		if (x > 0)
			node.add(new int[] {x-1, y}); // add these to process in current layer
		if (x < maxx-1)
			node.add(new int[] {x+1, y});
		if (y > 0)
			node.add(new int[] {x, y-1});
		if (y < maxy)
			node.add(new int[] {x, y+1});
		return;
	}
}
