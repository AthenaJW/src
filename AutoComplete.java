import java.io.*;
import java.io.PrintWriter;
import java.util.*;

public class AutoComplete {
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("auto.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("auto.out")));

		String[] temp = br.readLine().split(" ");
		int W = Integer.parseInt(temp[0]);
		int N = Integer.parseInt(temp[1]);
		String[] dict = new String[W];
		HashMap<String, Integer> lookup = new HashMap<String, Integer>();
		for (int i = 0; i < W; i++)
		{
		    dict[i] = br.readLine();
		    lookup.put(dict[i], i+1);
		}
		Arrays.sort(dict); int K;
		//how strings are sorted
		for (String el: dict)
			System.out.println(el);
		String pref; int index;
		for (int i = 0; i < N; i++)
		{
			temp = br.readLine().split(" ");
			K = Integer.parseInt(temp[0]);
			pref = temp[1];
			index = Arrays.binarySearch(dict, pref);//so that everything after has same prefix
			if (index < 0)
				index = ~index;
			index += (K-1);
			if (index < W && dict[index].length() >= pref.length() && dict[index].substring(0, pref.length()).compareTo(pref)==0)
				pw.println(lookup.get(dict[index]));
			else
				pw.println(-1);
		}
		pw.close();
		br.close();
	}
}
