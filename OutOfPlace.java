import java.io.*;
public class OutOfPlace {
	public static void main(String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(new File("outofplace.in")));
		//BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(reader.readLine());
		int[] heights = new int[num];
		for (int i = 0; i < num; i++)
		{
			heights[i] = Integer.parseInt(reader.readLine());
		}
		int oop = 0;
		int wrongPlace = 0;
		int k = 1;
		for (int i = 0; i < num-1; i++)
		{
			if (heights[i] > heights[i+1])
			{
				if (i == 0)
				{
					oop = heights[i];
					wrongPlace = i;
					k=1;
				}
				else
				{
					if (heights[i-1] < heights[i+1])
					{
						oop = heights[i];
						wrongPlace = i;
						k=1;
					}
					else
					{
						oop = heights[i+1];
						wrongPlace = i+1;
						k=-1;
					}
				}
			}
		}
		int rightPlace = 0;
		for (int i = 0; i < num; i += k )
		{
			if (oop<heights[i])
			{
				rightPlace = i;
				break;
			}
			if (i == num-1)
			{
				rightPlace = num-1;
			}
		}
		int a = Math.min(rightPlace, wrongPlace);
		int b = Math.max(rightPlace, wrongPlace);
		int output = 0;
		for (int i = a; i<b; i++)
		{
			if (heights[i] != heights[i+1])
				output++;
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("outofplace.out")));
		//System.out.println(output);
		pw.println(output);
		pw.close();
		reader.close();
	}
}
