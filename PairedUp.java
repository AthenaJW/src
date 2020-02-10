import java.io.*;
import java.util.*;
public class PairedUp {
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("pairup.in"));
		int N = Integer.parseInt(br.readLine());
		int[][] cows = new int[N][2];
		String[] temp;
		for (int i = 0; i < N; i++)
		{
			temp = br.readLine().split(" ");
			cows[i][0] = Integer.parseInt(temp[0]);
			cows[i][1] = Integer.parseInt(temp[1]);
		}
		Comparator<int[]> c = new Comparator<int[]>()
		{
			public int compare(int[] o1, int[] o2)
			{
				return o1[1] - o2[1];
			}
		};
		Arrays.sort(cows, c);
		int point1 = 0;
		int point2 = cows.length - 1;
		int time = 0;
		int maxTime = 0;
		while(point1 < point2)
		{
			time = cows[point1][1] + cows[point2][1];
			//System.out.println(cows[point1][1] + " " + cows[point2][1] + " " + time);

			if (cows[point1][0] < cows[point2][0])
			{
				cows[point2][0] -= cows[point1][0];
				cows[point1][0] = 0;
				point1++;
			}
			else if (cows[point1][0] > cows[point2][0])
			{
				cows[point1][0] -= cows[point2][0];
				cows[point2][0] = 0;
				point2--;
			}
			else
			{
				cows[point1][0] = 0;
				cows[point2][0] = 0;
				point1++;
				point2--;
			}
			//for (int[] el: cows)
			//	System.out.println(el[0] + " " + el[1]);
			if (time > maxTime)
				maxTime = time;
		}
		if (point1 == point2 && cows[point1][0]!=0)
			maxTime = Math.max(maxTime, 2*cows[point1][1]);
		//System.out.println(maxTime);
			
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("pairup.out")));
		pw.println(maxTime);
		pw.close();
		br.close();
	}
}
