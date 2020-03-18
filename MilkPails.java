import java.io.*;
import java.util.Arrays;

public class MilkPails {
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("pails.in"));
		String[] temp = br.readLine().split(" ");
		int X = Integer.parseInt(temp[0]);
		int Y = Integer.parseInt(temp[1]);
		int K = Integer.parseInt(temp[2]);
		int M = Integer.parseInt(temp[3]);
		boolean[][] toExplore = new boolean[X+1][Y+1];
		boolean[][] nextStates = new boolean[X+1][Y+1];
		toExplore[0][0] = true;
		
		System.out.println("-------");
		for (int i = 0; i < K; i++)
		{
			for (boolean[] row: nextStates)
				Arrays.fill(row,  false); //clean slate
			for (int a = 0; a < X+1; a++)
				for (int b = 0; b < Y+1; b++)
				{
					if (!toExplore[a][b])
						continue;
					nextStates[a][b] = true; //need another board so the next steps
					nextStates[a][0] = true; //don't get confused with the current ones
					nextStates[0][b] = true;
					nextStates[a][Y] = true;
					nextStates[X][b] = true;
					if (Y-b<a)
						nextStates[a-(Y-b)][Y] = true;
					else
						nextStates[0][b+a] = true;
					if (X-a < b)
						nextStates[X][b-(X-a)] = true;
					else
						nextStates[a+b][0] = true;
				}	
			for (int a = 0; a < X+1; a++)
				for (int b = 0; b < Y+1; b++)
					toExplore[a][b] = nextStates[a][b]; //update the board with the new set of states;
		}
		
		int min = Integer.MAX_VALUE;
		for (int a = 0; a < X+1; a++)
			for (int b = 0; b < Y+1; b++)
			{
				if (!toExplore[a][b])
					continue;
				if (Math.abs(M-(a+b)) < min)
					min = Math.abs(M-(a+b));
			}
		//System.out.println(min);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("pails.out")));
		pw.println(min);
		pw.close();
	}
}
