import java.io.*;
import java.util.Arrays;

public class CowCrossTheRoad3 {
	static Connection[][] connections;
	static int[][] sets;
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("countcross.in"));
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int K = Integer.parseInt(temp[1]);
		int R = Integer.parseInt(temp[2]);
		connections = new Connection[N][N];
		sets = new int[N][N];
		for (int[] row: sets)
			Arrays.fill(row, -1);
		Connection c;
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < N; j++)
			{
				c = new CowCrossTheRoad3().new Connection();
				if (i == 0)
					c.up = 1;
				if (i == N-1)
					c.down = 1;
				if (j == 0)
					c.left = 1;
				if (j == N-1)
					c.right = 1;
				connections[i][j] = c;
			}
		}
		int r1, r2, c1, c2;
		for (int i = 0; i < R; i++)
		{
			temp = br.readLine().split(" ");
			r1 = Integer.parseInt(temp[0])-1; c1 = Integer.parseInt(temp[1])-1;
			r2 = Integer.parseInt(temp[2])-1; c2 = Integer.parseInt(temp[3])-1;
			if (r1 < r2)
			{
				connections[r1][c1].down = 1;
				connections[r2][c2].up = 1;
			}
			else if (r2 < r1)
			{
				connections[r1][c1].up = 1;
				connections[r2][c2].down = 1;
			}
			else if (c1 < c2)
			{
				connections[r1][c1].right = 1;
				connections[r2][c2].left = 1;
			}
			else if (c2 < c1)
			{
				connections[r1][c1].left = 1;
				connections[r2][c2].right = 1;
			}
		}
		int setnum = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
			{
				if (sets[i][j] != -1)
					continue;
				bfs(i, j, setnum++);
			}
		int[] countsets = new int[setnum];
		
		for (int i = 0; i < K; i++)
		{
			temp = br.readLine().split(" ");
			r1 = Integer.parseInt(temp[0])-1;
			c1 = Integer.parseInt(temp[1])-1;
			countsets[sets[r1][c1]]++;
		}
		for (int el:countsets)
			System.out.println(el);
		int count = 0;
		for (int i = 0; i < setnum; i++)
			for (int j = i+1; j < setnum; j++)
				count += countsets[i] * countsets[j];
		//System.out.println(count);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("countcross.out")));
		pw.println(count);
		pw.close();
		br.close();
	}
	public class Connection
	{
		int left, right, up, down;
		public Connection()
		{
			left = 0; right = 0; up = 0; down = 0;
		}
	}
	public static void bfs(int x, int y, int setnum)
	{
		if (sets[x][y] == setnum)
			return;
		sets[x][y] = setnum;
		Connection c = connections[x][y];
		if (c.left == 0)
			bfs(x, y-1, setnum);
		if (c.right == 0)
			bfs(x, y+1, setnum);
		if (c.up == 0)
			bfs(x-1, y, setnum);
		if (c.down == 0)
			bfs(x+1, y, setnum);
	}
}
