import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class BeautifulNumbers {
	public static void main(String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int num;
		num = Integer.parseInt(reader.readLine());
		int[] index;
		String[] input;
		int n, pleft, pright, cand;
		for (int i = 0; i<num; i++)
		{
			n = Integer.parseInt(reader.readLine());
			index = new int[n];
			input = reader.readLine().split(" ");
			for (int j = 0; j < n; j++)
			{
				index[Integer.parseInt(input[j])-1] = j;
			}
			System.out.print("1");
			pleft = pright = index[0];
			for (int j=1; j<n; j++)
			{
				cand = index[j];
				pleft = Math.min(cand, pleft);
				pright = Math.max(pright, cand); 
				if (pright - pleft == j)
					System.out.print("1");
				else
					System.out.print("0");
			}
			System.out.println();
		}
	}
}
