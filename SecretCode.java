import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;

public class SecretCode {
	static HashMap<String, Long> lookup;
	static final int mod = 2014;
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("2.in"));
		String code = br.readLine();
		lookup = new HashMap<String, Long>();
		long ans = recurse(code);
		ans %= mod;
		System.out.println(ans);
		//PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("auto.out")));
		//pw.close();
		br.close();
	}
	public static long recurse(String s)
	{
		if (lookup.containsKey(s))
			return lookup.get(s);
		long sum = 1; // doing no operations is also a possibility
		for (int i = 1; i <= s.length()/2 ; i++)
		{
			if (s.substring(0, i).compareTo(s.substring(i, 2*i)) == 0)
				sum += recurse(s.substring(i))%mod; // ending prefix
			if (s.substring(0, i).compareTo(s.substring(s.length()-i)) == 0)
				sum += recurse(s.substring(i))%mod; // ending suffix
			if (s.substring(s.length()-i).compareTo(s.substring(0, i)) == 0)
				sum += recurse(s.substring(0, s.length()-i))%mod; // beginning prefix
			if (s.substring(s.length()-i).compareTo(s.substring(s.length()-2*i, s.length()-i)) == 0)
				sum += recurse(s.substring(0, s.length()-i))%mod; // beginning suffix
		}
		sum %= mod;
		lookup.put(s, sum);
		return sum;
	}
}
