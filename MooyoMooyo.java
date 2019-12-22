import java.io.*;
import java.util.*;

public class MooyoMooyo {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader("mooyomooyo.in"));
		String[] temp;
		temp = br.readLine().split(" ");
		int rows = Integer.parseInt(temp[0]);
		int minPop = Integer.parseInt(temp[1]);
		char[][] board = new char[rows][10];
		boolean[][] explored = new boolean[rows][10];
		char[] temp2;
		for (int i = 0; i < board.length; i++)
			board[i] = br.readLine().toCharArray();
		ArrayList<Integer[]> toFill;
		boolean beingFilled = true;
		while (beingFilled)
		{
			beingFilled = false;
			for (boolean[] row: explored)
				Arrays.fill(row, false);
			for (int i = 0; i < board.length; i++)
			{
				for (int j = 0; j < 10; j++)
				{
					if (board[i][j] != '0' && !explored[i][j])
					{
						toFill = new ArrayList<Integer[]>();
						floodFill(i, j, board, board[i][j], explored, toFill);
						if (toFill.size() >= minPop)
						{
							beingFilled = true;
							for (Integer[] location: toFill)
								board[location[0]][location[1]] = '0';
						}
					}
				}
			}
			if (!beingFilled)
				break;
			for (int i = 0; i < board.length; i++)
			{
				for (int j = 0; j < board[i].length; j++)
					System.out.print(board[i][j]);
				System.out.println();
			}
			int empty;
			for (int j = 0; j < 10; j++)
			{
				empty = board.length - 1;
				while(board[empty][j] != '0')
					empty--;
				for (int i = empty; i > -1; i--)
				{
					if (board[i][j] != '0')
					{
						board[empty][j] = board[i][j];
						board[i][j] = '0';
						empty--;
					}
				}
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("mooyomooyo.out")));
		for (int i = 0; i < board.length; i++)
		{
			for (int j = 0; j < board[i].length; j++)
				pw.print(board[i][j]);
			pw.println();
		}
		br.close();
		pw.close();
	}
	public static void floodFill(int r, int c, char[][] board, char current, boolean[][] explored, ArrayList<Integer[]> block)
	{
		if (board[r][c] != current)
			return;
		Integer[] temp = new Integer[2];
		temp[0] = r;
		temp[1] = c;
		block.add(temp);
		explored[r][c] = true;
		if (r<board.length - 1 && !explored[r+1][c]) // down
			floodFill(r+1, c, board, current, explored, block);
		if (r>0 && !explored[r-1][c]) // up
			floodFill(r-1, c, board, current, explored, block);
		if (c<9 && !explored[r][c+1]) //right
			floodFill(r, c+1, board, current, explored, block);
		if (c>0 && !explored[r][c-1]) //left
			floodFill(r, c-1, board, current, explored, block);
		return;
	}
}
