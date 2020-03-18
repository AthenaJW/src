import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Rocket {
	static char[][] board;
	static boolean[][] beenThere;
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner scan = new Scanner(new File("rocket.dat"));
		
		int n = scan.nextInt();
		scan.nextLine();
		String[] temp;
		int r, c;
		boolean launch;
		for (int i = 0; i < n; i++)
		{
			temp = scan.nextLine().split(" ");
			r = Integer.parseInt(temp[0]); c = Integer.parseInt(temp[1]);
			System.out.println(r + " " + c);
			board = new char[r][c]; beenThere = new boolean[r][c];
			for (int a = 0; a < r; a++)
				board[a] = scan.nextLine().toCharArray();
			for (int a = 0; a < r; a++)
			{
				if (board[a][0] == '.')
					search(a, 0);
			}
			launch = false;
			for (int a = 0; a < r; a++)
			{
				if (beenThere[a][c-1])
					 launch = true;
			}
			if (launch)
				System.out.println("Destination: Mars");
			else
				System.out.println("Destination: Asteriod");
		}
		
		//scan.close();
	}
	public static void search(int x, int y)
	{
		if (board[x][y] == '#')
			return;
		beenThere[x][y] = true;
		if (y == board[0].length-1)
			return;
		search(x, y+1);
		if (x > 0)
			search(x-1, y+1);
		if (x < board.length-1)
			search (x+1, y);
					
	}
}
