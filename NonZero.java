import java.io.*;
import java.util.*;

public class NonZero {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] array;
		int length;
		String[] temp;
		int moves;
		int currentsum;
		for (int i = 0; i < N; i++)
		{
			length = Integer.parseInt(br.readLine());
			temp = br.readLine().split(" ");
			array = new int[length];
			moves = 0;
			currentsum = 0;
			for (int j = 0; j < length; j++)
				array[j] = Integer.parseInt(temp[j]);
			for (int el: array)
			{
				if (el == 0)
				{
					moves++;
					currentsum++;
				}
				currentsum += el;
			}
			if (currentsum == 0)
				moves++;
			System.out.println(moves);
		}
	}
}
