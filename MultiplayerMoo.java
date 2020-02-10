import java.util.*;
import java.io.*;
public class MultiplayerMoo {
	public static void main(String[] args) throws NumberFormatException, IOException, InterruptedException
	{
		BufferedReader br = new BufferedReader(new FileReader("multimoo.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("multimoo.out")));
		int num = Integer.parseInt(br.readLine());
		int[][] board = new int[num][num];
		String[] temp;
		for (int i = 0; i < num; i++)
		{
			temp = br.readLine().split(" ");
			for (int j = 0; j < temp.length; j++)
				board[i][j] = Integer.parseInt(temp[j]);
				
		}
		boolean[][] filled = new boolean[num][num];
		int[][] blobs = new int[num][num];
		int blob_num = 0;
		int maxArea = 0;
		int area;
		int[] temp2;
		ArrayList<int[]> blob_info = new ArrayList<int[]>();
		for (int i = 0 ; i < num; i++)
		{
			for (int j = 0; j < num; j++)
			{
				if (filled[i][j])
					continue;
				area =  floodFill(i, j, board, filled, board[i][j], blobs, blob_num);
				temp2 = new int[2];
				temp2[0] = board[i][j];
				temp2[1] = area;
				blob_info.add(temp2);
				maxArea = Math.max(maxArea, area);
				blob_num++;
			}
		}
		pw.println(maxArea);
		ArrayList<HashSet<Integer>> graph = new ArrayList<HashSet<Integer>>(Collections.nCopies(blob_num, null));
		boolean[][] checked = new boolean[blob_num][blob_num];
		for (int i = 0 ; i < num; i++) {
			for (int j = 0; j < num; j++) {
				if (graph.get(blobs[i][j]) == null)
					graph.set(blobs[i][j], new HashSet<Integer>());
				if (i < board.length - 1 && blobs[i+1][j]!=blobs[i][j]) //down
				{
					graph.get(blobs[i][j]).add(blobs[i+1][j]);
				}
				if (i > 0 && blobs[i-1][j]!=blobs[i][j]) //up
				{
					graph.get(blobs[i][j]).add(blobs[i-1][j]);
				}
				if (j > 0 && blobs[i][j-1]!=blobs[i][j]) // left
				{
					graph.get(blobs[i][j]).add(blobs[i][j-1]);
				}
				if (j < board[0].length - 1 && blobs[i][j+1]!=blobs[i][j]) //right
				{
					graph.get(blobs[i][j]).add(blobs[i][j+1]);
				}
			}
		}
		int id1;
		int id2;
		int[] colors = new int[2];
		maxArea = 0;
		boolean[] onPath;
		for (int i = 0 ; i < blob_num; i++)
		{
			id1 = blob_info.get(i)[0];
			Set<Integer> edges = graph.get(i);
			for (int e: edges)
			{
				if (checked[i][e])
					continue;
				id2 = blob_info.get(e)[0];
				onPath = new boolean[blob_num];
				maxArea = Math.max(maxArea, traverse(i, graph, id1, id2, checked, blob_info, onPath));
			}
		}
		pw.println(maxArea);
		pw.close();
		br.close();
	}
	public static int traverse(int nodeid, ArrayList<HashSet<Integer>> graph, int id1, int id2, boolean[][] checked, ArrayList<int[]> blob_info, boolean[] onPath) throws InterruptedException
	{
		int area = blob_info.get(nodeid)[1];
		Set<Integer> edges = graph.get(nodeid);
		onPath[nodeid] = true;
		for (int e: edges)
		{
			if (!checked[nodeid][e] && !onPath[e])
			{
				if (blob_info.get(e)[0] == id1 || blob_info.get(e)[0] == id2)
				{
					area += traverse(e, graph, id1, id2, checked, blob_info, onPath);
					checked[nodeid][e] = true; 
					checked[e][nodeid] = true; //is checked if it's been part of a path
				}
			}
		}
		return area;
	}
	public static int floodFill(int x, int y, int[][] board, boolean[][] filled, int current, int[][] blobs, int blob_num)
	{
		int area = 0;
		if (board[x][y] != current)
		{
			return area;
		}
		area++;
		filled[x][y] = true;
		blobs[x][y] = blob_num;
		if (x < board.length - 1 && !filled[x+1][y]) //down
			area += floodFill(x+1, y, board, filled, current, blobs, blob_num);
		if (x > 0 && !filled[x-1][y]) //up
			area += floodFill(x-1, y, board, filled, current, blobs, blob_num);
		if (y > 0 && !filled[x][y-1]) // left
			area += floodFill(x, y-1, board, filled, current, blobs, blob_num);
		if (y < board[0].length - 1 && !filled[x][y+1])
			area += floodFill(x, y+1, board, filled, current, blobs, blob_num);
		return area; 		// if you reach the edge

	}
}
