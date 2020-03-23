import java.io.*;

public class Equalize {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] a = new int[N]; int[] b = new int[N];
		for (int i = 0; i < N; i++)
			a[i] = br.read()-48;
		br.readLine();
		for (int i = 0; i < N; i++)
			b[i] = br.read()-48;
		br.readLine();
		int[] flip = new int[N];
		for (int i = 0; i < N; i++)
		{
			if (a[i] == b[i])
				flip[i] = -1;
			else
				flip[i] = a[i];
		}
		int i = -1;
		int cost = 0;
		while (++i < N)
		{
			if (flip[i] == -1)
				continue;
			if (i < N-1 && flip[i]+flip[i+1] == 1)
				i++;
			cost++;
		}
		System.out.println(cost);
	}
}
