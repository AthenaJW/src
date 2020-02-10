import java.io.*;
import java.util.*;

public class CensoringSilver {
	public static void main(String[] args) throws IOException, InterruptedException
	{
		//BufferedReader br = new BufferedReader(new FileReader("censor.in"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String output = br.readLine();
		char[] censor = br.readLine().toCharArray();
		//preprocessing
		int[] preprocessed = new int[censor.length];
		int j = 0;
		for (int i = 1; i < censor.length; i++)
		{
			if (censor[j] == censor[i])
				preprocessed[i] = j;
			else if (j!=0)
			{
				j=0;
				i--;
			}
		}
		int i = 0;
		j = 0;
		int[] previous = new int[output.length()];
		while (i < output.length())
		{
			if (output.charAt(i) == censor[j])
			{
				j++;
				if (i < output.length()-1 && j < censor.length)
					previous[++i] = j;
				else
					i++;
			}
			if (j==censor.length)
			{
				output = output.substring(0, i-censor.length) + output.substring(i);
				i -= censor.length;
				j = previous[i];
			}
			else if (i < output.length() && output.charAt(i) != censor[j])
			{
				if (j!=0)
				{
					previous[i] = j;
					j = preprocessed[j];
				}
				else
					i++;
			}
		}
		//PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("censor.out")));
		//pw.println(output);
		//pw.close();
		System.out.println(output);
		br.close();
	}
}