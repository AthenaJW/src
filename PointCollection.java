import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class PointCollection {
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner fin = new Scanner(new File("dryrun.dat"));
		
		int n = fin.nextInt();
		fin.nextLine();
		
		for (int i = 0; i < n; i++)
		{
			String s = fin.nextLine();
			System.out.println(s + " fly together.");
		}
		
		fin.close();
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
