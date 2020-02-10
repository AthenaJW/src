import java.io.*;
import java.util.*;

public class NauuoCards {
	static int[] stack;
	static int[] hand;
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("template.in"));
		int N = Integer.parseInt(br.readLine());
		stack = new int[N];
		hand = new int[N];
		String[] temp = br.readLine().split(" ");
		for (int i = 0; i < temp.length; i++)
			hand[i] = Integer.parseInt(temp[i]);
		temp = br.readLine().split(" ");
		for (int i = 0; i < temp.length; i++)
			stack[i] = Integer.parseInt(temp[i]);
		int start = 0;
		int end = N;
		int mid;
		while (start != end)
		{
			mid = (start+end)/2;
			if (pass(mid))
				end = mid;
			else
				start = mid + 1;
		}
		if (start == 0 && stack[0] == 1)
			System.out.println(0);
		else if (stack[start-1] == 1)
			System.out.println(start-1);
		else
			System.out.println(N + start);
		br.close();
	}
	private static boolean pass(int test)
	{
		for (int i = test; i < stack.length; i++)
		{
			if (stack[i] != 0 && i-test + 1 >= stack[i])
				return false;
		}
		return true;
	}
}
