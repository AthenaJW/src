import java.io.*;
import java.util.Arrays;

public class Present {
	static int[][] bits;
	static int[] ans = new int[25];
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("2.in"));
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		String[] temp = br.readLine().split(" ");
		for (int i = 0; i < N; i++)
			nums[i] = Integer.parseInt(temp[i]);
		bits = new int[25][N];
		for (int i = 0; i < 25; i++)
			for (int j = 0; j < N; j++)
				bits[i][j] = (int) (nums[j]%Math.pow(2, i+1));
		int x, y;
		int sum;
		for (int i = 0; i < 25; i++)
		{
			Arrays.sort(bits[i]);
			for (int el: bits[i])
				System.out.print(el + " ");
			System.out.println();
			sum = 0;
			for (int j = 0; j < N; j++)
			{
				x = lower_bound(bits[i], (int) (Math.pow(2, i) - nums[j]));
				y = upper_bound(bits[i], (int) (Math.pow(2, i+1) - nums[j]-1));
				System.out.println(x + " " + y + " " + i);
				sum += (y-x);
				x = lower_bound(bits[i], (int) (Math.pow(2, i+1) + Math.pow(2, i) - nums[j]));
				y = upper_bound(bits[i], (int) (Math.pow(2, i+2) - nums[j] - 2));
				sum += (y-x);
			}
			System.out.println(sum);
			if (sum % 2 == 1)
				ans[i] = 1;
		}
		int sol = 0;
		for (int i = 0; i < ans.length; i++)
		{
			System.out.print(ans[i]);
			sol += ans[i] * Math.pow(2, i);
		}
		System.out.println(sol);
		//PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("homework.out")));
		//pw.close();
		br.close();
	}
	/**
	 * If the value is present in the array, returns the first occurrence of this value
	 * Else, if not present, returns the index of the least element greater than it
	 * 
	 * @param a		array to search on
	 * @param key	value we are searching for
	 * 
	 * @return
	 */
	public static int lower_bound(int[] a, int key)
	{
		int x = Arrays.binarySearch(a, key);
		if (x < 0)
			return ~x;
		while (x > -1 && a[x] == key)
			x--;
		x++;
		return x;
	}
	/**
	 * If the value is present in the array, returns the index after the last occurrence.
	 * Else, if not present, returns the index of the least element greater than it.
	 * 
	 * @param a		array to search on
	 * @param key	value we are searching for
	 * 
	 * @return
	 */
	public static int upper_bound(int[] a, int key)
	{
		int x = Arrays.binarySearch(a, key);
		if (x < 0)
			return ~x;
		while (x < a.length && a[x] == key)
			x++;
		return x;
	}
}
