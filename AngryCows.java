import java.io.*;
import java.io.IOException;
import java.util.Arrays;

public class AngryCows {
	static int K;
	static int[] haybales;
	
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("angry.in"));
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		K = Integer.parseInt(temp[1]);
		haybales = new int[N];
		for (int i = 0; i < N; i++)
			haybales[i] = Integer.parseInt(br.readLine());
		Arrays.sort(haybales);
		int start = 0; int end = haybales[haybales.length-1] - haybales[0]; int mid; 
		while (start != end)
		{
			mid = (start+end)/2;
			if (pass(mid))
				end = mid;
			else
				start = mid + 1;
		}
		//System.out.println(start);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("angry.out")));
		pw.println(start);
		pw.close();
		br.close();
	}
	private static boolean pass(int test)
	{
		int count = 0;
		int pointer = 0;
		for (int i = 0; i < haybales.length; i++)
		{
			if (haybales[i] - haybales[pointer] > 2*test)
			{
				pointer = i;
				count++;
			}
		}
		count++;//cow has not been counted yet
		return count <= K;
	}
}
