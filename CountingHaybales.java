import java.io.*;
import java.util.*;
public class CountingHaybales {
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("haybales.in"));
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int Q = Integer.parseInt(temp[1]);
		int[] haybales = new int[N];
		temp = br.readLine().split(" ");
		for (int i = 0; i < N; i++)
			haybales[i] = Integer.parseInt(temp[i]);
		Arrays.sort(haybales);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("haybales.out")));
		int x, y;
		for (int i = 0; i < Q; i++)
		{
			temp = br.readLine().split(" ");
			x = Arrays.binarySearch(haybales, Integer.parseInt(temp[0]));
			if (x<0)
				x = ~x;
			y = Arrays.binarySearch(haybales, Integer.parseInt(temp[1]));
			if (y<0)
				y = ~y;
			else
				y++;
			//System.out.println(y-x);
			pw.println(y-x);
		}
		pw.close();
		br.close();
	}
}
