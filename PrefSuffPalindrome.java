import java.io.*;
import java.util.Arrays;

public class PrefSuffPalindrome {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder forward; StringBuilder backward; String pref = ""; int a;
		String f;
		StringBuilder output;
		//string builder, unsynchronized, mutable string structure
		//good if you are handling large string manipulations
		//like arraylist but for strings
		String forpal = ""; String backpal = "";
		int num;
		for (int i = 0; i < N; i++)
		{
			f = br.readLine();
			forward = new StringBuilder(f);
			num = forward.length();
			if (forward.length() == 1)
			{
				System.out.println(forward); continue;
			}
			backward = new StringBuilder(f);
			backward.reverse();
			a = 0;
			while (a < (forward.length()/2) && forward.charAt(a) == backward.charAt(a))
				a++;
			pref = forward.substring(0, a);
			forward.delete(forward.length()-a, forward.length());
			forward.delete(0, a);
			backward.delete(backward.length()-a, backward.length());
			backward.delete(0, a);
			forpal = palindrome(forward+ "#" + backward);
			backpal = palindrome(backward + "#" + forward);
			output = new StringBuilder(num);
			output.append(pref);
			if (forpal.length() > backpal.length())
				output = output.append(forpal);
			else
				output = output.append(backpal);
			for (int j = pref.length()-1; j > -1; j--)
				output = output.append(pref.charAt(j));
			System.out.println(output);
		}
		
	}
	public static String palindrome(String s)
	{
		int[] preffunc = new int[s.length()]; int p2 = 0;
		for (int p1 = 1; p1 < s.length(); p1++)
		{
			p2 = preffunc[p1-1];
			while (p2 > 0 && s.charAt(p2)!=s.charAt(p1))
				p2 = preffunc[p2-1];
			if (s.charAt(p1) == s.charAt(p2))
				p2++;
			preffunc[p1] = p2;
		}
		return s.substring(0, p2); //last value of j should reveal our longest palindrome
	}
}
