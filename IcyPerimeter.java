import java.io.*;
public class IcyPerimeter {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader("perimeter.in"));
		int num = Integer.parseInt(br.readLine());
		char[][] board = new char[num][num];
		boolean[][] filled = new boolean[num][num];
		int AreaPer[];
		int maxArea = 0;
		int minPerim = 0;
		for (int i = 0; i < num; i++)
		{
			board[i] = br.readLine().toCharArray();
		}
		for (int i = 0; i < num; i++)
		{
			for (int j = 0; j< num; j++)
			{
				if (board[i][j] == '#' && !filled[i][j])
				{
					AreaPer = new int[2];
					floodFill(i, j, board, filled, AreaPer);
					if (AreaPer[0] > maxArea)
					{
						maxArea = AreaPer[0];
						minPerim = AreaPer[1];
					}
					else if (AreaPer[0] == maxArea && AreaPer[1] < minPerim)
					{
						maxArea = AreaPer[0];
						minPerim = AreaPer[1];
					}
				}
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("perimeter.out")));
		pw.println(maxArea + " " + minPerim);
		pw.close();
		br.close();
	}
	public static void floodFill(int x, int y, char[][] board, boolean[][] filled, int[] AreaPer)
	{
		if (board[x][y] == '.')
		{
			AreaPer[1]++;
			return;
		}
		
		if (x == board.length - 1 || x == 0)
			AreaPer[1]++;
		if (y == 0 || y == board[0].length - 1)
			AreaPer[1]++;
		AreaPer[0]++;
		filled[x][y] = true;
		
		if (x < board.length - 1 && !filled[x+1][y]) //down
			floodFill(x+1, y, board, filled, AreaPer);
		if (x > 0 && !filled[x-1][y]) //up
			floodFill(x-1, y, board, filled, AreaPer);
		if (y > 0 && !filled[x][y-1]) // left
			floodFill(x, y-1, board, filled, AreaPer);
		if (y < board[0].length - 1 && !filled[x][y+1])
			floodFill(x, y+1, board, filled, AreaPer);
		return; 		// if you reach the edge

	}
}
