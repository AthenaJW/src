import java.io.*;
import java.util.*;

public class DeadPixel {
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		/*int N = Integer.parseInt(br.readLine());
		int a, b, x, y;
		String[] temp;
		int area;
		for (int i = 0; i < N; i++)
		{
			temp = br.readLine().split(" ");
			a = Integer.parseInt(temp[0]);
			b = Integer.parseInt(temp[1]);
			x = Integer.parseInt(temp[2]);
			y = Integer.parseInt(temp[3]);
			area = Math.max(Math.max(x*b, (a-x-1)*b), Math.max(y*a, (b-y-1)*a));
			System.out.println(area);
		}*/
		int N = Integer.parseInt(br.readLine());
		String[] temp;
		int a, b, money, i;
		char[] seq;
		char current;
		int currInd, total;
		for (int j = 0; j < N; j++)
		{
			temp = br.readLine().split(" ");
			a = Integer.parseInt(temp[0]);
			b = Integer.parseInt(temp[1]);
			money = Integer.parseInt(temp[2]);
			seq = br.readLine().toCharArray();
			i = seq.length-2;
			current = seq[seq.length-2];
			currInd = seq.length-1;
			total = 0;
			while (i > -1)
			{
				if (seq[i] != current)
				{
					if (current == 'A')
						total += a;
					else
						total += b;
					if (total > money)
					{
						System.out.println(currInd+1);
						break;
					}
					current = seq[i];
					currInd = i + 1;
				}
				if (i == 0)
				{
					if (current == 'A')
						total += a;
					else
						total += b;
					if (total > money)
					{
						System.out.println(currInd+1);
						break;
					}
					else
						System.out.println("1");
				}
				i--;
			}
		}
	}
}