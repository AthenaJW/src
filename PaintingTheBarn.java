import java.io.BufferedReader;
import java.io.IOException;
import java.io.*;

public class PaintingTheBarn {
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("paintbarn.in"));
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int K = Integer.parseInt(temp[1]);
		int[][] barn = new int[1001][1001];
		int x1, x2, y1, y2;
		for (int i = 0; i < N; i++)
		{
			temp = br.readLine().split(" ");
			x1 = Integer.parseInt(temp[0]);
			y1 = Integer.parseInt(temp[1]);
			x2 = Integer.parseInt(temp[2]);
			y2 = Integer.parseInt(temp[3]);
			barn[x1][y1] = 1;
			barn[x2][y2] = 1;
			barn[x1][y2] = -1;
			barn[x2][y1] = -1;
		}
		for (int i = 1; i < 1001; i++)
			for (int j = 1; j < 1001; j++)
				barn[i][j] = barn[i][j] + barn[i-1][j] + barn[i][j-1] - barn[i-1][j-1];
		int count = 0;
		for (int i = 0; i < 1001; i++)
			for (int j = 0; j < 1001; j++)
				if (barn[i][j] == K) count++;
		//System.out.println(count);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("paintbarn.out")));
		pw.println(count);
		pw.close();
		br.close();
	}
}
