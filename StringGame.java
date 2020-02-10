import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class StringGame {
	static int[] permute;
	static char[] string;
	static char[] substring;
	public static void main(String[] args) throws IOException, InterruptedException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		string = br.readLine().toCharArray();
		substring = br.readLine().toCharArray();
		String[] temp = br.readLine().split(" ");
		permute = new int[temp.length];
		for (int i = 0; i < temp.length; i++)
			permute[i] = Integer.parseInt(temp[i]);
		int start = 0;
		int end = temp.length;
		int med;
		while (start != end)
		{
			med = (start + end + 1)/2;
			//System.out.println(start + " " + end);
			if (pass(med))
				start = med;
			else
				end = med - 1;
		}
		System.out.println(start);
		br.close();
	}
	public static boolean pass(int test)
	{
		char[] removed = Arrays.copyOf(string, string.length);
		for (int i = 0; i < test; i++)
			removed[permute[i]-1] = '-'; //permute is one based
		//for (char el: removed)
		//	System.out.print(el);
		//System.out.println();
		int point = 0;
		for (int i = 0; i < string.length; i++)
		{
			if (removed[i] == substring[point] && removed[i] != '-')
				point++;
			if (point == substring.length)
				return true;
		}
		return false;
	}
}
