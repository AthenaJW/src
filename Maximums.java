import java.io.*;

public class Maximums {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] temp = br.readLine().split(" ");
		long[] b = new long[N];
		for (int i = 0; i < N; i++)
			b[i] = Long.parseLong(temp[i]);
		long[] a = new long[N];
		a[0] = b[0]; long max = a[0];
		for (int i = 1; i < N; i++)
		{
			a[i] = b[i] + max;
			max = Math.max(a[i], max);
		}
		for (long el: a)
			System.out.print(el + " ");
	}
}
