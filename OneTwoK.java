import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class OneTwoK {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("template.in"));
		int N = Integer.parseInt(br.readLine());
		BitSet strip;
		String[] temp;
		int len, K, lastSquare;
		for (int i = 0; i < N; i++)
		{
			temp = br.readLine().split(" ");
			len = Integer.parseInt(temp[0]) + 1; // n+1 squares
			K = Integer.parseInt(temp[1]);
			/*strip = new BitSet(len);
			for (int j = 0; j < len; j++)
			{
				if (strip.get(j))
					continue;
				if (j+1 < len)
					strip.set(j+1);;
				if (j+2 < len)
					strip.set(j+2);
				if (j+K < len)
					strip.set(j+K);	
			}
			for (int j = 0; j < len; j++)
				System.out.print(strip.get(j));
			System.out.println();
			if (strip.get(len-1))
				System.out.println("Alice");
			else 
				System.out.println("Bob");*/
			if (K % 3 != 0)
			{
				if ((len-1)%3 == 0)
					System.out.println("Bob");
				else
					System.out.println("Alice");
			}
			else
			{
				if ((len - 1) % (K + 1) == 0)
					System.out.println("Bob");
				else if (((len-1)%(K+1)) % 3 == 0 && ((len-1)%(K+1)) != K)
					System.out.println("Bob");
				else
					System.out.println("Alice");
			}
		}
		//PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("template.out")));
		//pw.println();
		//pw.close();
		br.close();
	}
}
