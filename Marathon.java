import java.io.*;
import java.util.*;

public class Marathon {
	static int[][] dpstates;
	static int[][] distances;
	static int[][] locations;
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("marathon.in"));
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int K = Integer.parseInt(temp[1]);
		int x, y;
		locations = new int[N][2];
		distances = new int[N][N];
		for (int i = 0; i < N; i++)
		{
			temp = br.readLine().split(" ");
			x = Integer.parseInt(temp[0]);
			y = Integer.parseInt(temp[1]);
			locations[i] = new int[]{x, y};
		}
		for (int i = 0; i < N-1; i++)
			for (int j = i+1; j < N; j++)
			{
				distances[i][j] = Math.abs(locations[i][0]-locations[j][0]) 
						+ Math.abs(locations[i][1]-locations[j][1]); 
				distances[j][i] = Math.abs(locations[i][0]-locations[j][0]) 
						+ Math.abs(locations[i][1]-locations[j][1]); 
			}
		dpstates = new int[N][K+1];
		for (int[] row: dpstates)
			Arrays.fill(row, 400001);
		dpstates[0][0] = 0;
		int n, k;
		for (int i = 0; i < dpstates.length; i++)
		{
			for (int j = 0; j < dpstates[0].length; j++)
			{
				n = i+1;
				k = j;
				while (n < dpstates.length && k < dpstates[0].length)
				{
					if (i == 0 && k > 0)
						break;
					dpstates[n][k] = Math.min(dpstates[n][k], dpstates[i][j] + distances[i][n]);
					n++; k++;
				}
			}
		}
		/*for (int[] row: dpstates)
		{
			for (int el: row)
				System.out.print(el + " ");
			System.out.println();
		}*/
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < K+1; i++)
			min = Math.min(min, dpstates[N-1][i]);
		//System.out.println(min);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("marathon.out")));
		pw.println(min);
		pw.close();
		br.close();
	}
}
