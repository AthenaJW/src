import java.io.*;
import java.util.*;
public class BreedCounting {
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("bcount.in"));
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int Q = Integer.parseInt(temp[1]);
		int[] ids = new int[N]; //0 indexed
		for (int i = 0; i < N; i++)
			ids[i] = Integer.parseInt(br.readLine());
		int[] pHol = new int[N+1]; // 1 indexed
		int[] pGuer = new int[N+1];
		int[] pJers = new int[N+1];
		for (int i = 1; i < N+1; i++)
		{
			pHol[i] = pHol[i-1]; pGuer[i] = pGuer[i-1]; pJers[i] = pJers[i-1];
			if (ids[i-1] == 1)
				pHol[i]++;
			else if (ids[i-1] == 2)
				pGuer[i]++;
			else
				pJers[i]++;
		}
		int beg, end;
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("bcount.out")));
		for (int i = 0; i < Q; i++)
		{
			temp = br.readLine().split(" ");
			beg = Integer.parseInt(temp[0])-1;
			end = Integer.parseInt(temp[1]); // 1-indexed
			pw.println((pHol[end]-pHol[beg]) + " "
					+ (pGuer[end] - pGuer[beg]) + " " + (pJers[end]-pJers[beg]));
		}
		pw.close();
		br.close();
	}
}
