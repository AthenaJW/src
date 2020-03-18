import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;

public class SafestPath {
	static char[][] board = new char[8][8];
	static int[][] distances = new int[8][8];
	static boolean[][] visited = new boolean[8][8];
	static Comparator<int[]> c = new Comparator<int[]>()
	{
		public int compare(int[] o1, int[] o2)
		{
			return o1[2] - o2[2];
		}
	};
	static PriorityQueue<int[]> pq = new PriorityQueue<int[]>(c);

	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner scan = new Scanner(new File("safest_path.dat"));
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		map.put('S', 0);map.put('E', 0);map.put('R', 1);map.put('B', 2);
		map.put('W', 4);map.put('G', 5);map.put('T', 9);
		int xstart=0, ystart=0, xend=0, yend=0;
		int[] temp;
		int x, y, dist;
		
		while (scan.hasNextLine())
		{
			pq.clear();
			for (int[] row: distances)
				Arrays.fill(row, Integer.MAX_VALUE);
			for (int i = 0; i < 8; i++)
				board[i] = scan.nextLine().toCharArray();
			for (int i = 0; i < 8; i++)
				for (int j = 0; j < 8; j++)
				{
					if (board[i][j] == 'S')
					{
						xstart = i; ystart = j;
					}
					else if (board[i][j] == 'E')
					{
						xend = i; yend = j;
					}
				}
			pq.add(new int[] {xstart, ystart, 0});
			while (!pq.isEmpty())
			{
				temp = pq.poll();
				if (board[temp[0]][temp[1]] =='E')
					break;
				x = temp[0]; y = temp[1]; dist = temp[2];
				if (x > 0 && !visited[x-1][y])
					if (temp[2] + map.get(board[x-1][y]) < distances[x-1][y])
					{
						distances[x-1][y] = temp[2] + map.get(board[x-1][y]);
						pq.add(new int[] {x-1, y, distances[x-1][y]});
					}
				if (x < 7 && !visited[x+1][y])
					if (temp[2] + map.get(board[x+1][y]) < distances[x+1][y])
					{
						distances[x+1][y] = temp[2] + map.get(board[x+1][y]);
						pq.add(new int[] {x+1, y, distances[x+1][y]});
					}
				if (y > 0 && !visited[x][y-1])
					if (temp[2] + map.get(board[x][y-1]) < distances[x][y-1])
					{
						distances[x][y-1] = temp[2] + map.get(board[x][y-1]);
						pq.add(new int[] {x, y-1, distances[x][y-1]});
					}
				if (y < 7 && !visited[x][y+1])
					if (temp[2] + map.get(board[x][y+1]) < distances[x][y+1])
					{
						distances[x][y+1] = temp[2] + map.get(board[x][y+1]);
						pq.add(new int[] {x, y+1, distances[x][y+1]});
					}
			}		
			System.out.println(distances[xend][yend]);
			if (scan.hasNextLine())
				scan.nextLine();
		}
	}
}
