import java.io.*;
import java.util.*;

public class WhatsUpWGravity {
	static Connection[][] connections;
	static char[][] board; 
	static int N, M;
	static boolean[][] processed;
	static int[][] distances;
	static Comparator<int[]> comp = new Comparator<int[]>()
	{
		public int compare(int[] o1, int[] o2)
		{
			return o1[2] - o2[2];
		}
	};
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("gravity.in"));
		String[] temp = br.readLine().split(" ");
		N = Integer.parseInt(temp[0]);
		M = Integer.parseInt(temp[1]);
		connections = new Connection[N][M];
		processed = new boolean[N][M];
		board = new char[N][M];
		for (int i = 0; i < N; i++)
			board[i] = br.readLine().toCharArray();
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				connections[i][j] = new Connection();
		int rstart = 0;
		int cstart = 0;
		int rend = 0;
		int cend = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
			{
				if (board[i][j] == 'C')
				{
					rstart = i; cstart = j;
					board[i][j] = '.';
				}
				else if (board[i][j] == 'D')
				{
					rend = i; cend = j;
					board[i][j] = '.';
				}
			}
		int r, c, rflip;
		for (int i = 0; i < N; i++)// downward floor
		{
			for (int j = 0; j < M; j++)
			{
				if (board[i][j] == '#') continue;
				r = i; c = j;
				while(r < N-1 && board[r+1][c] == '.')
					r++;
				if (r==N-1) continue;
				if (processed[r][c]) continue;
				processed[r][c] = true;
				connections[r][c].right = getRightDown(r, c);
				connections[r][c].left = getLeftDown(r, c);
				rflip = r;
				while(rflip > 0 && board[rflip-1][c] == '.')
					rflip--;
				if (rflip == 0)
					connections[r][c].flip = null;
				else
					connections[r][c].flip = new int[]{rflip, c, 1};
			}
		}
		for (int i = 0; i < N; i++)// downward floor
		{
			for (int j = 0; j < M; j++)
			{
				if (board[i][j] == '#') continue;
				r = i; c = j;
				while(r > 0 && board[r-1][c] == '.')
					r--;
				if (r==0) continue;
				if (processed[r][c]) continue;
				processed[r][c] = true;
				connections[r][c].right = getRightUp(r, c);
				connections[r][c].left = getLeftUp(r, c);
				rflip = r;
				while(rflip < N-1 && board[rflip+1][c] == '.')
					rflip++;
				if (rflip == N-1)
					connections[r][c].flip = null;
				else
					connections[r][c].flip = new int[]{rflip, c, 1};
			}
		}
		for (boolean[] row: processed)
			Arrays.fill(row, false);
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(comp);
		distances = new int[N][M];
		for (int[] row: distances)
			Arrays.fill(row, Integer.MAX_VALUE);
		pq.add(new int[] {rstart, cstart, 0});
		int[] el;
		int dist;
		while (!pq.isEmpty())
		{
			el = pq.poll();
			if (board[el[0]][el[1]] =='D')
				break;
			if (distances[el[0]][el[1]] < el[2])
				continue;
			r = el[0]; c = el[1]; dist = el[2];
			if (connections[r][c].right != null)
			{
				if (dist + connections[r][c].right[2] < distances[connections[r][c].right[0]][connections[r][c].right[1]])
				{
					distances[connections[r][c].right[0]][connections[r][c].right[1]] = 
							dist + connections[r][c].right[2];
					pq.add(new int[] {connections[r][c].right[0], 
						connections[r][c].right[1],
						dist + connections[r][c].right[2]});
				}
			}
			if (connections[r][c].left != null)
			{
				if (dist + connections[r][c].left[2] < distances[connections[r][c].left[0]][connections[r][c].left[1]])
				{
					distances[connections[r][c].left[0]][connections[r][c].left[1]] = 
							dist + connections[r][c].left[2];
					pq.add(new int[] {connections[r][c].left[0], 
						connections[r][c].left[1],
						dist + connections[r][c].left[2]});
				}
			}
			if (connections[r][c].flip != null)
			{
				if (dist + connections[r][c].flip[2] < distances[connections[r][c].flip[0]][connections[r][c].flip[1]])
				{
					distances[connections[r][c].flip[0]][connections[r][c].flip[1]] = 
							dist + connections[r][c].flip[2];
					pq.add(new int[] {connections[r][c].flip[0], 
						connections[r][c].flip[1],
						dist + connections[r][c].flip[2]});
				}
			}
		}
		//System.out.println(distances[rend][cend]);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("gravity.out")));
		if (distances[rend][cend] == Integer.MAX_VALUE)
			pw.println("-1");
		else
			pw.println(distances[rend][cend]);
		pw.close();
		br.close();
	}
	static class Connection
	{
		int[] left, right, flip;
		public Connection()
		{
			left = null; right = null; flip = null;
		}
		public String toString()
		{
			String s = "";
			s += "Left: "+left + "\n";
			s += "Right: "+right + "\n";
			s += "Flip: "+flip + "\n";
			return s;
		}
	}
	public static int[] getRightDown(int r, int c)
	{
		int candc = c+1;
		int candr = r;
		if (candc > M-1 || board[candr][candc] == '#')
			return null;
		while (candr <  N-1 && board[candr+1][candc] == '.') candr++;
		if (candr == N-1)
			return null;
		return new int[] {candr, candc, 0};
	}
	public static int[] getLeftDown(int r, int c)
	{
		int candc = c-1;
		int candr = r;
		if (candc < 0 || board[candr][candc] == '#')
			return null;
		while (candr <  N-1 && board[candr+1][candc] == '.') candr++;
		if (candr == N-1)
			return null;
		return new int[] {candr, candc, 0};
	}
	public static int[] getRightUp(int r, int c)
	{
		int candc = c+1;
		int candr = r;
		if (candc > M-1 || board[candr][candc] == '#')
			return null;
		while (candr > 0 && board[candr-1][candc] == '.') candr--;
		if (candr == 0)
			return null;
		return new int[] {candr, candc, 0};
	}
	public static int[] getLeftUp(int r, int c)
	{
		int candc = c-1;
		int candr = r;
		if (candc < 0 || board[candr][candc] == '#')
			return null;
		while (candr > 0 && board[candr-1][candc] == '.') candr--;
		if (candr == 0)
			return null;
		return new int[] {candr, candc, 0};
	}
}
