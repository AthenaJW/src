import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PermutationPartition {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int k = Integer.parseInt(temp[1]);
		long[] p = new long[(int) N];
		temp = br.readLine().split(" ");
		for (int i = 0; i < N; i++)
			p[i] = Integer.parseInt(temp[i]);
		int mod = 998244353;
		for (int i = 0; i < N; i++)
			p[i] = Long.parseLong(temp[i]);
		long max = (N*(N+1)/2)-((N-k)*(N-k+1)/2);
		int count = 0; long total = 1;
		long last = -1;
		for (int i = 0; i < N; i++)
		{
			if (p[i] > (N-k))
			{
				total *= ((count+1)%mod);
				total %= mod;
				count = 0;
				last = p[i];
			}
			else if (last != -1)
				count++;
		}
		System.out.println(max + " " + total);
	}
}
