import java.io.*;
import java.util.Arrays;

public class Cereal {
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("3.in"));
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int M = Integer.parseInt(temp[1]);
		int[] sol = new int[N];
		int[] cowsusing = new int[M];
		int[][] preferences = new int[N][2];
		int first, second;
		for (int i = 0; i < N; i++)
		{
			temp = br.readLine().split(" ");
			first = Integer.parseInt(temp[0])-1;
			second = Integer.parseInt(temp[1])-1;
			preferences[i] = new int[] {first, second};
		}
		Arrays.fill(cowsusing, -1);
		int cereal, cownum, current;
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
				while (cowsusing[cereal]!=-1)
				{
					cownum = cowsusing[cereal];
					if (cownum <= current)
					{
						count--;
						break;
					}
					cowsusing[cereal] = current;
					if (cereal == preferences[cownum][1])
					{
						count--;
						break;
					}
					cereal = preferences[cownum][1];
					current = cownum;
				}
			}
			sol[i] = count;
		}
		for (int el: sol)
			System.out.println(el);
		//PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("homework.out")));
		//pw.close();
		br.close();
	}
}
