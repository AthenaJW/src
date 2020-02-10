import java.io.*;

public class LeftOut {
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("leftout.in"));
		int N = Integer.parseInt(br.readLine());
		char[][] board = new char[N][N];
		for (int i = 0; i < N; i++)
			board[i] = br.readLine().toCharArray();
		//force top and sides to be R
		for (int i = 0; i < N; i++)
		{
			if (board[0][i] == 'L')
			{
				for (int j = 0; j < N; j++)
				{
					if (board[j][i] == 'R')
						board[j][i] ='L';
					else
						board[j][i] ='R';
				}
			}
		}
		for (int i = 0; i < N; i++)
		{
			if (board[i][0] == 'L')
			{
				for (int j = 0; j < N; j++)
				{
					if (board[i][j] == 'R')
						board[i][j] ='L';
					else
						board[i][j] ='R';
				}
			}
		}
		int count = 0; //count gives an indicator
		int x = -2;
		int y = -2;
		boolean changeX = false;
		boolean changeY = false;
		for (int i = 1; i < N; i++)
		{
			for (int j = 1; j < N; j++)
			{
				if (board[i][j] == 'L')
				{
					x = i;
					y = j;
					if (board[x][1] == board[x][N-1] && board[x][1] == 'L')
						changeY = true;
					if (board[1][y] == board[N-1][y] && board[1][y] == 'L')
						changeX = true;
					break;
				}
			}
		}
		if (changeX)
			x = 0;
		if (changeY)
			y = 0;
		x++;
		y++;
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("leftout.out")));
		if (x == -1 || y == -1)
			pw.println(-1);
		else
			pw.println(x + " " + y);
		pw.close();
		br.close();
	}
}
