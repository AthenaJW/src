import java.io.*;
import java.util.*;
public class SwitchingOnLights {
	static Room[][] rooms;
	static boolean[][] lit;
	static boolean[][] connected;
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("lightson.in"));
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int M = Integer.parseInt(temp[1]);
		rooms = new Room[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				rooms[i][j] = new Room();
		int x, y, a, b;
		for (int i = 0; i < M; i++)
		{
			temp = br.readLine().split(" ");
			x = Integer.parseInt(temp[0])-1;
			y = Integer.parseInt(temp[1])-1;
			a = Integer.parseInt(temp[2])-1;
			b = Integer.parseInt(temp[3])-1;
			rooms[x][y].switches.add(new int[] {a, b});
		}
		lit = new boolean[N][N]; lit[0][0] = true;
		connected = new boolean[N][N]; connected[0][0] = true;
		for (int[] s: rooms[0][0].switches)
			lit[s[0]][s[1]] = true;
		boolean found = true;
		while (found)
		{
			found = false;
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++)
				{
					if (connected[i][j])
						continue;
					if (!lit[i][j])
						continue;
					if (i > 0 && connected[i-1][j] || i < N-1 && connected[i+1][j])
					{
						floodFill(i, j);
						found = true;
					}
					else if (j > 0 && connected[i][j-1] || j < N-1 && connected[i][j+1])
					{
						floodFill(i, j);
						found = true;
					}
				}
		}
		int count = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (lit[i][j])
					count++;
		//System.out.println(count);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lightson.out")));
		pw.println(count);
		pw.close();
		br.close();
	}
	static class Room
	{
		ArrayList<int[]> switches;
		public Room()
		{
			switches = new ArrayList<int[]>();
		}
	}
	public static void floodFill(int x, int y)
	{
		if (connected[x][y])
			return;
		connected[x][y] = true;
		for (int[] s: rooms[x][y].switches)
			lit[s[0]][s[1]] = true;
		if (x < rooms.length - 1 && !connected[x+1][y] && lit[x+1][y]) //down
			floodFill(x+1, y);
		if (x > 0 && !connected[x-1][y] && lit[x-1][y]) //up
			floodFill(x-1, y);
		if (y > 0 && !connected[x][y-1] && lit[x][y-1]) // left
			floodFill(x, y-1);
		if (y < rooms[0].length - 1 && !connected[x][y+1] && lit[x][y+1])
			floodFill(x, y+1);
		return; 		// if you reach the edge
	}
	
}
