import java.io.*;
import java.util.Arrays;

public class CrossCountrySkiing {
	static boolean[][] beenThere;
	static int[][] board;
	static int[][] waypoint;
	static int[] point;
	static int N, M;
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("ccski.in"));
		String[] temp = br.readLine().split(" ");
		N = Integer.parseInt(temp[0]);
		M = Integer.parseInt(temp[1]);
		beenThere = new boolean[N][M];
		board = new int[N][M];
		waypoint = new int[N][M];
		int max = 0;
		for (int i = 0; i < N; i++)
		{
			temp = br.readLine().split(" ");
			for (int j = 0; j < M; j++)
			{
				board[i][j] = Integer.parseInt(temp[j]);
				max = Math.max(Integer.parseInt(temp[j]), max);
			}
		}
		point = null;
		for (int i = 0; i < N; i++)
		{
			temp = br.readLine().split(" ");
			for (int j = 0; j < M; j++)
			{
				waypoint[i][j] = Integer.parseInt(temp[j]);
				if (point == null && waypoint[i][j] == 1)
					point = new int[] {i, j};
			}
		}
		
		int start = 0; int end = max; int mid = 0; 
		while (start != end)
		{
			mid = (start+end)/2;
			if (pass(mid))
				end = mid;
			else
				start = mid + 1;
		}
		//System.out.println(start);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("ccski.out")));
		pw.println(start);
		pw.close();
		br.close();
	}
	private static boolean pass(int test)
	{
		for (boolean[] row: beenThere)
			Arrays.fill(row, false);
		search(point[0], point[1], test);
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
			{
				if (waypoint[i][j] == 0)
					continue;
				if (!beenThere[i][j])
					return false;
			}
		return true;
				
	}
	public static void search(int x, int y, int d)
	{
		if (beenThere[x][y])
			return;	
		beenThere[x][y] = true;
		if (x > 0 && Math.abs(board[x-1][y] - board[x][y]) <= d)
			search(x-1, y, d);
		if (x < N-1 && Math.abs(board[x+1][y] - board[x][y]) <= d)
			search(x+1, y, d);
		if (y > 0 && Math.abs(board[x][y-1] - board[x][y]) <= d)
			search(x, y-1, d);
		if (y < M-1 && Math.abs(board[x][y+1] - board[x][y]) <= d)
			search(x, y+1, d);
		return;
	}
}
