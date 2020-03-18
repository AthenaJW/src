import java.io.*;
import java.util.Arrays;

public class FieldReduction {
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("reduce.in"));
		int N = Integer.parseInt(br.readLine());
		int[][] points = new int[N][2];
		int[] xvals = new int[N];
		int[] yvals = new int[N];
		String[] temp;
		int x, y;
		for (int i = 0; i < N; i++)
		{
			temp = br.readLine().split(" ");
			x = Integer.parseInt(temp[0]);
			y = Integer.parseInt(temp[1]);
			points[i][0] = x;
			points[i][1] = y;
			xvals[i] = x;
			yvals[i] = y;
		}
		Arrays.sort(xvals); Arrays.sort(yvals);
		int[] lowX = new int[4]; int[] highX = new int[4];
		int[] lowY = new int[4]; int[] highY = new int[4];
		for (int i = 0; i < 4; i ++)
		{
			lowX[i] = xvals[i]; highX[i] = xvals[N-i-1];
			lowY[i] = yvals[i]; highY[i] = yvals[N-i-1];
		}
		int count;
		int xmin, xmax, ymin, ymax;
		int min = Integer.MAX_VALUE;
		for (int a = 0; a < 4; a++)
			for (int b = 0; b < 4; b++)
				for (int c = 0; c < 4; c++)
				{
					for (int d = 0; d< 4; d++)
					{
						count = 0;
						xmin = Math.min(lowX[a], highX[b]); // may overlap
						ymin = Math.min(lowY[c], highY[d]);
						xmax = Math.max(lowX[a], highX[b]);
						ymax = Math.max(lowY[c], highY[d]);
						for (int i = 0; i < N; i++)
						{
							x = points[i][0]; y = points[i][1];
							if (x < xmin || x > xmax || y < ymin || y > ymax)
								count++;
						}
						if (count > 3)
							continue;
						if ((xmax-xmin)*(ymax-ymin) < min)
							min = (xmax-xmin)*(ymax-ymin);
					}
				}
		//System.out.println(min);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("reduce.out")));
		pw.println(min);
		pw.close();
		br.close();
	}
}
