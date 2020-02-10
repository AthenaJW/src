import java.io.*;
import java.util.*;

public class MaxMed {
	static int[] array;
	static int N, K;
	public static void main(String[] args) throws IOException, InterruptedException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		N = Integer.parseInt(temp[0]);
		K = Integer.parseInt(temp[1]);
		array = new int[N];
		temp = br.readLine().split(" ");
		for (int i = 0; i < temp.length; i++)
			array[i] = Integer.parseInt(temp[i]);
		Arrays.sort(array);
		long start = 0;
		long end = 1500000000;
		long mid;
		boolean check;
		while (start != end)
		{
			mid = (start + end + 1)/2;
			if (pass(mid))
				start = mid;
			else
				end = mid - 1;
			System.out.println(start + " " + end);
		}
		if  (N == 1)
			start = K + array[0];
		System.out.println(start);
	}
	private static boolean pass(long median)
	{
		int i = array.length - 1;
		while (i > -1 && array[i] >= median)
			i--;
		int temp = K;
		//if (median - array[i] > K) // if the first one less cannot be filled by K
		//	return false;
		while (i > -1 && temp >= 0)
		{
			System.out.println(":) " + temp);
			temp -= median - array[i];
			i--;
		}
		i++; //first one cannot fill
		if (i < N/2) //surpasses the median
			return true;
		return false;
		
	}
}
