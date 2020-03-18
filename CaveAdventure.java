import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CaveAdventure {
	static boolean beenThere[][] = new boolean[8][8];
	static char[][] board = new char[8][8];
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner fin = new Scanner(new File("cave_adventure.dat"));
		int xstart = 0, ystart = 0, xend = 0, yend = 0;
		while (fin.hasNextLine())
		{
			for (boolean[] row: beenThere)
				Arrays.fill(row, false);
			for (int i = 0; i < 8; i++)
				board[i] = fin.nextLine().toCharArray();
			if (fin.hasNextLine())
				fin.nextLine();
			for (int i= 0; i < 8; i++)
			{
				for (int j = 0; j < 8; j++)
				{
					if (board[i][j] == 'S')
					{
						xstart = i; ystart = j;
					}
					if (board[i][j] == 'E')
					{
						xend = i; yend = j;
					}
				}
			}
			search(xstart, ystart);
			if (beenThere[xend][yend])
				System.out.println("Solvable");
			else
				System.out.println("No Solution");
		}
	}
	public static void search(int x, int y)
	{
		if (beenThere[x][y])
			return;
		if (board[x][y] == 'W')
			return;
		beenThere[x][y] = true;
		if (board[x][y] == 'E')
			return;
		if (board[x][y] == 'G')
		{
			if (x > 0 && board[x-1][y] != 'G')
				search(x-1, y);
			if (x < 7 && board[x+1][y] != 'G')
				search(x+1, y);
			if (y > 0 && board[x][y-1] != 'G')
				search(x, y-1);
			if (y < 7 && board[x][y+1] != 'G')
				search(x, y+1);
		}
		else
		{
			if (x > 0)
				search(x-1, y);
			if (x < 7)
				search(x+1, y);
			if (y > 0)
				search(x, y-1);
			if (y < 7)
				search(x, y+1);
		}
		return;
	}
}
