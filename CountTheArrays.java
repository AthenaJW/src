import java.io.*;

public class CountTheArrays {
	static final int mod = 998244353; 
	static long[] factorials;
	static int M;
	static int N;
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		N = Integer.parseInt(temp[0]);
		M = Integer.parseInt(temp[1]);
		factorials = new long[M+1];
		fact();
		long tot = choose(M, N-1);
		tot *= (N-2);
		tot %= mod;
		tot *= binpow(2, N-3);
		tot %= mod;
		System.out.println(tot);
		br.close();
	}
	public static void fact() // one-indexed factorial table
	{
		factorials[0] = 1;
		for (int i = 1; i < factorials.length; i++)
			factorials[i] = (factorials[i-1] * i ) % mod;
	}
	public static long inv(long a) //fermat's little theorem
	{
		return binpow(a, mod-2);
	}
	public static long div(long a, long b) // modular division
	{
		return (a * inv(b))%mod; 
	}
	public static long mult(long a, long b) // modular multiplication
	{
		return (a * b)%mod; 
	}
	public static long binpow(long a, long b) // binary exponentiation
	{
		long res = 1;
		long multiply = a;
		while (b > 0)
		{
			if ((b & 1) == 1)
			{
				res *= multiply;
				res %= mod;
			}
			multiply = multiply*multiply;
			multiply %= mod;
			b >>= 1;
		}
		return res;
	}
	public static long choose(int n, int k) // combinations
	{
		return ((factorials[n]%mod)*inv((factorials[n-k]*factorials[k])%mod))%mod;
	}
}
