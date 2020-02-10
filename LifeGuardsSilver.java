import java.io.*;
import java.util.*;

public class LifeGuardsSilver {
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("lifeguards.in"));
		int num = Integer.parseInt(br.readLine());
		int[][] shifts = new int[num][2];
		String[] temp;
		for (int i = 0; i < num; i++)
		{
			temp = br.readLine().split(" ");
			shifts[i][0] = Integer.parseInt(temp[0]);
			shifts[i][1] = Integer.parseInt(temp[1]);
		}
		Comparator<int[]> c = new Comparator<int[]>()
		{
			public int compare(int[] o1, int[] o2)
			{
				return o1[0]-o2[0];
			}
		};
		Arrays.sort(shifts, c);
		int total = 0; 
		int lonelyHours = shifts[0][0];//when the previous guards shift ends
		int lonelyStart = shifts[0][0];
		int lonelyEnd = shifts[0][1];
		int min = Integer.MAX_VALUE;
		for (int i = 1; i < shifts.length; i++)
		{			
			if (shifts[i][0] < lonelyEnd)
			{
				if (shifts[i][0] - lonelyHours > 0)
					total += shifts[i][0]-lonelyHours;
				lonelyHours = shifts[i][1];
			}
			else // if next shift is disconnected
			{
				total += lonelyEnd - lonelyHours;
				if (total < min)
					min = total; //compare to min
				lonelyStart = shifts[i][0]; //reset the shift
				lonelyHours = lonelyStart;
				lonelyEnd = shifts[i][1];
				continue;
			}
			if (lonelyHours >= lonelyEnd)
			{
				if (total < min)
					min = total;
				lonelyHours = lonelyEnd;
				lonelyStart = shifts[i][0];
				lonelyEnd = shifts[i][1];
				total = 0;
			}
			else
			{
				min = 0;
			}
		}
		int hours = 0;
		int maxEnd = shifts[0][1];
		int start = shifts[0][0];
		for (int i = 1; i < shifts.length; i++)
		{
			if (shifts[i][1] > maxEnd && shifts[i][0] <=maxEnd)
				maxEnd = shifts[i][1];
			if (shifts[i][0] > maxEnd)
			{
				hours += maxEnd - start;
				start = shifts[i][0];
				maxEnd = shifts[i][1];
			}
		}
		hours += maxEnd - start;
		//System.out.println(hours);
		//System.out.println(min);
		//System.out.println(hours - min);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lifeguards.out")));
		pw.println(hours-min);
		pw.close();
		br.close();
	}
}
