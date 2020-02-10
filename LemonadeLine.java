import java.util.*;
import java.io.*;
public class LemonadeLine {
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("lemonade.in"));
		int num = Integer.parseInt(br.readLine());
		String[] temp = br.readLine().split(" ");
		int[] tolerance = new int[temp.length];
		for (int i = 0; i < temp.length; i++)
		{
			tolerance[i] = Integer.parseInt(temp[i]);
		}
		Arrays.sort(tolerance);
		int min = tolerance.length;
		for (int i = 0; i < tolerance.length; i++)
		{
			if (tolerance[tolerance.length-1-i] < i)
			{
				min = i;
				break;
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lemonade.out")));
		pw.println(min);
		pw.close();
		br.close();
		//System.out.println(min);
	}
}
