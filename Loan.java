import java.io.*;
import java.util.*;
public class Loan {
	static long N, M, K;
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("loan.in"));
		String[] temp = br.readLine().split(" ");
		N = Long.parseLong(temp[0]);
		K = Long.parseLong(temp[1]);
		M = Long.parseLong(temp[2]);
		long start = 0;
		long end = N/M;
		long med;
		while (start != end)
		{
			med = (start + end + 1)/2;
			if (binarySearch(med)) // if passed with days less than K, can move search space up
				start = med;
			else // if failed with days greater than K, must move search space down
				end = med - 1;
		}
		//System.out.println(start);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("loan.out")));
		pw.println(start);
		pw.close();
		br.close();
	}
	public static boolean binarySearch(long med)
	{
		long i = N;
		long candidate;
		long days = 0;
		long untilChange;
		long numPayments;
		long paid;
		if (N/med <= M)
		{
			days = N/M;
			if (N%M != 0)
				days++;
		}
		else {
			while (i > 0 && days <= K)
			{
				candidate = i/med;
				if (candidate <= M)
				{
					days += i / M;
					if (i%M != 0)
						days++;
					break;
				}
				untilChange = i - candidate*med;
				numPayments = (untilChange / candidate) + 1;
				paid = numPayments * candidate;
				days += numPayments;
				i -= paid;
			}
		}
		return days <= K;
		/*if (start == end)
			return end;
		if (days > K)
			return binarySearch(med, end);
		else if (days < K)
			return binarySearch(start, med - 1);
		return med;*/
	}
}
