import java.io.*;
import java.util.*;

public class CowHopscotch {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader("hopscotch.in"));
		String[] temp = br.readLine().split(" ");
		int rows = Integer.parseInt(temp[0]);
		int cols = Integer.parseInt(temp[1]);	
		int colors = Integer.parseInt(temp[2]);
		int[][] board = new int[rows][cols];
		int[][] hops = new int[rows][cols];
		for (int i = 0; i < rows; i++)
		{
			temp = br.readLine().split(" ");
			for (int j = 0; j < cols; j++)
			{
				board[i][j] = Integer.parseInt(temp[j]);
			}
		}
		hops[0][0] = 1;
		int current;
		int hopPaths;
		for (int i = 0; i < rows-1; i++)
		{
			for (int j = 0; j < cols-1; j++)
			{
				current = board[i][j];
				hopPaths = hops[i][j];
				for (int x = i+1; x < rows; x++)
				{
					for (int y = j+1; y < cols; y++)
					{
						if (board[x][y] != current)
						{
							hops[x][y] += hopPaths;
						}
					}
				}
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("hopscotch.out")));
		pw.println(hops[rows-1][cols-1]);
		pw.println(hops[rows-1][cols-1]%1000000007);
		pw.close();
		br.close();
		//OH MY GOD IT WAS INTEGER OVERFLOW
		//IF YOU ACCUMULATE TO THE END, YOU MIGHT GET INTEGER OVERFLOW SCREWING UP YOUR WHOLE LIFE
		//MAKE SURE IF YOU KNOW YOU'RE OUTPUT IS GOING TO BE SUPER BIG
		//TO TAKE CARE OF INTEGER OVERFLOW
	}
}
