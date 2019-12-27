import java.io.*;
import java.util.*;

public class MountainView {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader("mountains.in"));
		int num = Integer.parseInt(br.readLine());
		String[] temp;
		int[][] lowerUpper = new int[num][2];
		int x, y, upper, lower;
		for (int i = 0; i < num; i++)
		{
			temp = br.readLine().split(" ");
			x = Integer.parseInt(temp[0]);
			y = Integer.parseInt(temp[1]);
			lower = x-y;
			upper = x+y;
			lowerUpper[i][0] = lower;
			lowerUpper[i][1] = upper;
		}
		Comparator<int[]> c = new Comparator<int[]>()
		{
			public int compare(int[] mt1, int[] mt2)
			{
				if (mt1[0] == mt2[0])
					return mt2[1]-mt1[1]; //want higher upper bounds first(descending up bound
				return 
					mt1[0] - mt2[0]; //ascending lower bounds
			}
		};
		Arrays.sort(lowerUpper, c);
		int count = 0;
		int max = 0;
		for (int i = 0; i < num; i++)
		{
			upper = lowerUpper[i][1];
			if (upper > max)
			{
				count++;
				max = upper;
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("mountains.out")));
		pw.println(count);
		pw.close();
		br.close();
	}
}
