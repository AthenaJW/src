import java.io.*;
import java.util.*;
class PhilosophersStone {
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		String[] temp;
		int N, M;}
		catch(Exception t)
		{ System.out.println("Error");}
		int[][] tiles = new int[100][100];
		int[][] dp_states = new int[100][100];
		int max_subproblem; int max_stones;
		
		/*for (int i = 0; i < num; i++)
		{
			temp = br.readLine().split(" ");
			N = Integer.parseInt(temp[0]);
			M = Integer.parseInt(temp[1]);
			
			for (int a = 0; a < N; a++)
			{
				temp = br.readLine().split(" ");
				for (int j = 0; j < M; j++)
					tiles[a][j] = Integer.parseInt(temp[j]);
			}
			
			//good
			for (int a = 0; a < N; a++)	
			{
				for (int b = 0; b < M; b++)
				{
					if (a == 0)
					{
						dp_states[a][b] = tiles[a][b];
						continue;
					}
					max_subproblem = dp_states[a-1][b];
					if (b > 0)
						max_subproblem = Math.max(max_subproblem, dp_states[a-1][b-1]);
					if (b < M-1)
						max_subproblem = Math.max(max_subproblem, dp_states[a-1][b+1]);
					dp_states[a][b] = max_subproblem + tiles[a][b];
				}
			}
			max_stones = 0;
			for (int b = 0; b < M; b++)
				max_stones = Math.max(max_stones, dp_states[N-1][b]);
			System.out.println(max_stones);
		}*/
	}
}
