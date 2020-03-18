import java.io.*;
import java.util.Arrays;
public class DoubleElim {
	static int N, K;
	static int[][] f = new int[1<<17][4];
	static int[] tag = new int[1<<17];
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int K = Integer.parseInt(temp[1]);
		temp = br.readLine().split(" ");
		int fav;
		for (int i = 0; i < K; i++)
		{
			fav = Integer.parseInt(temp[i])-1;
			tag[fav] = 1;
		}
		for (int[] row: f)
			Arrays.fill(row, -0x3f);
		int count = 0;
		for (int i = 0; i < (1<<(N-1)); i++)
			count += tag[i] + tag[i|1];
		int num;
		for (int i = 0; i < (1<<(N-1)); i++)
		{
			num = tag[i<<1] + tag[i<<1 | 1];
			f[(1<<(N-1)) + i][0] = 0;
			if (num >= 1) 
				f[(1 << (N - 1)) + i][1] = f[(1 << (N - 1)) + i][2] = 0;
		    if (num == 2) 
		    	f[(1 << (N - 1)) + i][3] = 0;
		}
		for (int i = (1 << (N - 1)) - 1; i > 0; i--) {
		    for (int j = 0; j < 4; j++)
		        for (int k = 0; k < 4; k++)
		            f[i][j | k] = Math.max(f[i][j | k], f[i << 1][j] + f[i << 1 | 1][k] + (j | k));
		}
		int j = 0;
		outer: for (int[] row: f)
		{
			for (int cell:row)
			{
				if (cell != -63)
					System.out.print(cell);
				else
					System.out.println(" ");
				j++;
				if (j == 100)
					break outer;
			}
			System.out.println();
		}
		int mx = 0;
		for (int i = 0; i < 4; i++) 
			mx = Math.max(mx, f[1][i]);
		System.out.println(mx + count + 1);
	}
}
