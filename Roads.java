import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Roads {
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner fin = new Scanner(new File("roads.dat"));
		boolean go = true;
		char[][] map = new char[10][10];
		boolean[][] visited;
		while (go)
		{
			for (int i = 0; i < 10; i++)
			{
				map[i] = fin.nextLine().toCharArray(); 
			}
			visited = new boolean[10][10];
			int val = 0;
			int count = 0;
			for (char[] row: map)
			{
				for (char el: row)
				{
					if (el == '*')
						count++;
				}
			}
			for (int i = 0; i < 10; i++)
			{
				for (int j = 0; j < 10; j++)
				{
					if (visited[i][j])
						continue;
					if (map[i][j] == 'I')
						continue;
					val = floodFill(i, j, map, visited, 0);
				}
			}
			System.out.println(count +  " " + val);
			if (count == val)
				System.out.println("Accepted");
			else
				System.out.println("Rejected");
			if (!fin.hasNextLine())
				go = false;
			else
				fin.nextLine();
			
		}
		
			
		fin.close();
	}
	public static int floodFill(int x, int y, char[][] board, boolean[][] filled, int total)
	{
		
		if (board[x][y] == '*')
			++total;
		if (board[x][y] == 'I')
			return total;
		filled[x][y] = true;
		
		if (x < board.length - 1 && !filled[x+1][y]) //down
			floodFill(x+1, y, board, filled, total);
		if (x > 0 && !filled[x-1][y]) //up
			floodFill(x-1, y, board, filled, total);
		if (y > 0 && !filled[x][y-1]) // left
			floodFill(x, y-1, board, filled, total);
		if (y < board[0].length - 1 && !filled[x][y+1])
			floodFill(x, y+1, board, filled, total);
		
		
		return total; 		// if you reach the edge

	}

}
