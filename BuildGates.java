import java.io.*;
import java.util.Arrays;

public class BuildGates {
	static Connection[][] board;
	static int[][] sets;
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("gates.in"));
		int N = Integer.parseInt(br.readLine());
		char[] directions = br.readLine().toCharArray();
		int minx = 0; int miny = 0; int maxx = 0; int maxy = 0;
		int x = 0; int y = 0;
		char a;
		for (int i = 0; i < directions.length; i++)
		{
			a = directions[i];
			if (a == 'E')
				y++;
			if (a == 'W')
				y--;
			if (a == 'N')
				x--;
			if (a == 'S')
				x++;
			minx = Math.min(minx,  x); miny = Math.min(miny,  y);
			maxx = Math.max(maxx,  x); maxy = Math.max(maxy,  y);
		}
		board = new Connection[maxx-minx+2][maxy-miny+2];
		for (int i = 0; i < board.length; i++)
		{
			for (int j = 0; j < board[0].length; j++)
			{
				board[i][j] = new Connection();
				if (i == 0)
					board[i][j].u = 0;
				if (i == board.length-1)
					board[i][j].d = 0;
				if (j == 0)
					board[i][j].l = 0;
				if (j == board[0].length-1)
					board[i][j].r = 0;
			}
		}
		x = -minx;
		y = -miny;
		System.out.println(x + " " + y);
		for (char step: directions)
		{
			if (step == 'N')
			{
				board[x][y].r = 0; board[x][y+1].l = 0;
				x--;
			}
			if (step == 'S')
			{
				x++;
				board[x][y].r = 0; board[x][y+1].l = 0;
			}
			if (step == 'E')
			{
				y++;
				board[x][y].d = 0; board[x+1][y].u = 0;
			}
			if (step == 'W')
			{
				board[x][y].d = 0; board[x+1][y].u = 0;
				y--;
			}
		}
		for (Connection[] row: board)
		{
			for (Connection c: row)
				System.out.print(c + "|");
			System.out.println();
		}
		sets = new int[board.length][board[0].length];
		for (int[] row: sets)
			Arrays.fill(row, -1);
		int setnum = 0;
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[0].length; j++)
			{
				if (sets[i][j] != -1)
					continue;
				bfs(i, j, setnum++);
			}		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("gates.out")));
		pw.println(setnum-1);
		pw.close();
		br.close();
		//System.out.println(setnum-1);
	}
	static class Connection
	{
		int l, r, u, d;
		public Connection()
		{
			l = 1; r = 1; u = 1; d = 1;
		}
		public String toString()
		{
			return l + " " + r + " " + u + " " + d;
		}
	}
	public static void bfs(int x, int y, int setnum)
	{
		if (sets[x][y] == setnum)
			return;
		sets[x][y] = setnum;
		Connection c = board[x][y];
		if (c.l == 1)
			bfs(x, y-1, setnum);
		if (c.r == 1)
			bfs(x, y+1, setnum);
		if (c.u == 1)
			bfs(x-1, y, setnum);
		if (c.d == 1)
			bfs(x+1, y, setnum);
	}
}
