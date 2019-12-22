import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Apprentice {
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int num = scan.nextInt();
		scan.nextLine();
		int[][] masters = new int[num][3];
		for (int i = 0; i<num;i++)
		{
			masters[i][0] = scan.nextInt();
			masters[i][1] = scan.nextInt();
			masters[i][2] = scan.nextInt();
			scan.nextLine();
		}
		//sort from min time to make sword to max
		int minIndex = 0;
		int[] temp;
		for (int i = 0; i<num;i++)
		{
			for (int j = i; j<num; j++)
			{
				if (masters[j][2]>masters[minIndex][2])
					minIndex = j;
			}
			temp = masters[i];
			masters[i] = masters[minIndex];
			masters[minIndex] = temp;
		}
		int maxtime = masters[0][1];
		int mintime = masters[0][0];
		for (int i = 0; i<num;i++)
		{
			if (masters[i][1]>maxtime)
				maxtime = masters[i][1];
			if (masters[i][0] < mintime);
				mintime = masters[i][0];
		}
		//placing swords with our third for loop
		int begin, end, length;
		double[] schedule = new double[maxtime-mintime];
		boolean place = true;
		for (int i = 0; i<num;i++)
		{
			begin = masters[i][0]-mintime;
			end = masters[i][1]-mintime;
			length = masters[i][2];
			for (int j = begin; j<begin+end; j=j+length)
			{
				for (int k = 0; k<length; k++)
				{
					if (schedule[j+k] == 0)
						schedule[j+k] = 1.0/length;
					else
					{
						
					}
				}
			}
		}	
	}
	public int canReplace(int index, int direction, int numtype)
	{
		if (direction == 0)
		{
			
		}
		return -1;
	}
}
