import java.io.*;

public class RollingHashCensor {
	static String substring;
	static String string;
	static long[] hashes;
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("3.in"));
		int base = 26;
		long subHash = 0;
		string = br.readLine();
		substring = br.readLine();
		System.out.println(substring.length());
		for (int i = 0; i < substring.length(); i++)
		{
			string = "`" + string;
			subHash += Math.pow(base, substring.length()-1-i)*(substring.charAt(i)-96);
		}
		hashes = new long[string.length()];
		//rolling hash
		long prevHash = 0;
		long hash;
		long subtract;
		for (int i = 0; i < string.length()-substring.length(); i++)
		{
			subtract = (long) ((string.charAt(i)-96)*Math.pow(base,  substring.length()-1));
			hash = (long) (26*(prevHash - subtract) + (string.charAt(i + substring.length())-96));
			hashes[i+1] = hash;
			if (hash == subHash)
			{
				string = string.substring(0, i+1) + string.substring(i+1+substring.length());
				i -= substring.length();
				if (i < 0) i = -1;
			}
			prevHash = hashes[i+1];
			System.out.println(hash);
		}
		
		/*for (long el: hashes)
			System.out.println(el);
		System.out.println(subHash);*/
		System.out.println(string.substring(substring.length()));
		
		
		//PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("censor.out")));
		//pw.println(string.substring(substring.length()));
		//pw.close();
	}
}
