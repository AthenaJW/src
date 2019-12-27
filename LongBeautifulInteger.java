import java.io.*;
import java.util.*;
public class LongBeautifulInteger {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		int num_digits = Integer.parseInt(temp[0]);
		int k = Integer.parseInt(temp[1]);
		char[] temp2 = br.readLine().toCharArray();
		int[] x = new int[temp2.length];
		int[] output = new int[k];
		for (int i = 0; i < temp2.length; i++)
		{
			x[i] = temp2[i] - 48;
		}
		for (int i = 0; i <  k; i ++)
		{
			output[i] = x[i];
		}
		boolean increment = false;
		for (int i = k; i < x.length ; i++)
		{
			if (x[i] > output[i%k])
			{
				increment = true;
				break;
			}
		}
		if (increment)
		{
			for (int i = k-1; i>-1; i--)
			{
				if (output[i] == 9)
					output[i] = 0;
				else
				{
					output[i]++;
					break;
				}
			}
		}
		System.out.println(x.length);
		for (int i = 0; i < x.length; i++)
			System.out.print(output[i%k]);
	}
}
