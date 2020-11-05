import java.io.*;
import java.util.Arrays;

public class CerealSim {
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("cereal.in"));
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int M = Integer.parseInt(temp[1]);
		int[] sol = new int[N];
		boolean[] cowsusing = new boolean[M];
		int[][] preferences = new int[N][2];
		int first, second;
		for (int i = 0; i < N; i++)
		{
			temp = br.readLine().split(" ");
			first = Integer.parseInt(temp[0])-1;
			second = Integer.parseInt(temp[1])-1;
			preferences[i] = new int[] {first, second};
		}
		int count = 0;
		for (int i = 0; i < N; i++)
		{
			count = 0;
			Arrays.fill(cowsusing, false);
			for (int j = i; j < N; j++)
			{
				if (!cowsusing[preferences[j][0]])
				{
					cowsusing[preferences[j][0]] = true;
					count++;
					continue;
				}
				if (!cowsusing[preferences[j][1]])
				{
					cowsusing[preferences[j][1]] = true;
					count++;
					continue;
				}
			}
			sol[i] = count;
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cereal.out")));
		for (int el: sol)
			pw.println(el);
		pw.close();
	}	
}
