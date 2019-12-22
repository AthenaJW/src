import java.io.*;
import java.util.Scanner;

public class WhereAmI {
	public static void main(String[] args) throws IOException
	{
		//testing: Scanner scan = new Scanner(System.in);
		Scanner scan = new Scanner(new File("whereami.in"));
		int num = scan.nextInt();
		scan.nextLine();
		char[] houses = null;
		houses = scan.nextLine().toCharArray();
		int point1 = 0;
		int point2 = 1;
		int temp = point1;
		int count = 0;
		int maxIter = 0;
		int maxCount = 0;
		// i is point 2
		while (point1<num-1)
		{
			for (int i = point2; i<num; i++)
			{
				//testing: System.out.println(point1);
				//System.out.println(i);
				//Thread.sleep(500);
				if (houses[point1] == houses[i])
				{
					point1++;
					count++;
					if (i == num-1)
					{
						if(count > maxIter)
							maxIter = count;
						point1 -= count;
						count = 0;
					}
				}
				else if (count != 0)
				{
					if(count > maxIter)
						maxIter = count;
					point1 -= count;
					count = 0;
				}
			}
			if (maxIter == 0)
				point1++;
			else
				point1 = point1+maxIter;
			point2 = point1 + 1;
			if (maxIter>maxCount)
				maxCount = maxIter;
			maxIter = 0;
			count = 0;
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("whereami.out")));
		pw.println(maxCount+1);
		pw.close();
		scan.close();
	}
}
