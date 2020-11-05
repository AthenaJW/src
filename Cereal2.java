import java.io.*;
import java.util.Arrays;

public class Cereal2 {
	static int[][] preferences;
	static int[] cowsusing;
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("cereal.in"));
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int M = Integer.parseInt(temp[1]);
		int[] sol = new int[N];
		cowsusing = new int[M];
		preferences = new int[N][2];
		int first, second;
		for (int i = 0; i < N; i++)
		{
			temp = br.readLine().split(" ");
			first = Integer.parseInt(temp[0])-1;
			second = Integer.parseInt(temp[1])-1;
			preferences[i] = new int[] {first, second};
		}
		Arrays.fill(cowsusing, -1);
		int cereal, current;
		int count = 0;
		for (int i = N-1; i > -1; i--)
		{
			cereal = preferences[i][0];
			current = i;
			count++;
			if (cowsusing[cereal] == -1)
				cowsusing[cereal] = i;
			else
			{
				int evicted = cowsusing[cereal];
				cowsusing[cereal] = i;
				if (!resettle(evicted, cereal))
					count--;
			}
			sol[i] = count;
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cereal.out")));
		for (int el: sol)
			pw.println(el);
		pw.close();
		br.close();
	}
	public static boolean resettle(int evicted, int cereal)
	{
		if (cereal == preferences[evicted][0])
		{
			int second = preferences[evicted][1];
			if (cowsusing[second] == -1)
			{
				cowsusing[second] = evicted;
				return true;
			}
			if (cowsusing[second] > evicted)
			{
				int new_evict = cowsusing[second];
				cowsusing[second] = evicted;
				return resettle(new_evict, second);
			}
			else
				return false;
		}
		return false;
	}
}
