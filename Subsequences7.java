import java.io.*;
import java.util.*;
public class Subsequences7 {
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("div7.in"));
		int N = Integer.parseInt(br.readLine());
		long[] cows = new long[N];
		for (int i = 0; i < N; i++)
			cows[i] = Integer.parseInt(br.readLine());
		long[] prefix = new long[N];
		prefix[0] = cows[0];
		for (int i = 1; i < N; i++)
			prefix[i] = prefix[i-1] + cows[i];
		//mod by 7
		for (int i = 0; i < N; i++)
			prefix[i] = prefix[i] % 7;
		ArrayList<ArrayList<Integer>> common = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < 7; i++)
			common.add(new ArrayList<Integer>());
		for (int i = 0; i < N; i++)
			common.get((int)prefix[i]).add(i);
		common.get(0).add(-1);
		for (ArrayList<Integer> list: common)
			Collections.sort(list);
		int max = 0;
		for (ArrayList<Integer> list: common)
		{
			System.out.println(list);
			if (list.isEmpty())
				continue;
			if (list.get(list.size()-1) - list.get(0) > max)
				max = list.get(list.size()-1) - list.get(0);
		}
		//System.out.println(max);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("div7.out")));
		pw.println(max);
		pw.close();
		br.close();
	}
}
