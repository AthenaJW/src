import java.io.*;
import java.util.*;
public class LoadBalancing {
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("balancing.in"));
		int N = Integer.parseInt(br.readLine());
		Point[] points = new Point[N];
		String[] temp;
		for (int i = 0; i < N; i++)
		{
			temp = br.readLine().split(" ");
			points[i] = new Point(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
		}
		Comparator<Point> byX = new Comparator<Point>()
		{
			public int compare(Point o1, Point o2)
			{
				return o1.x-o2.x;
			}
		};
		Comparator<Point> byY = new Comparator<Point>()
		{
			public int compare(Point o1, Point o2)
			{
				return o1.y-o2.y;
			}
		};	
		Arrays.sort(points, byX);
		Point[] sortbyX = Arrays.copyOf(points, N);
		Arrays.sort(points, byY);
		Point[] sortbyY = Arrays.copyOf(points, N);
		int rightBot = 0; int rightTop = N; int leftBot = 0; int leftTop = 0;
		int j;
		int i = 0; int a = 0; int b; int min = Integer.MAX_VALUE;
		while (i < N)
		{
			j = i;
			rightBot = N-i;
			leftBot = i;
			while (i<N && sortbyX[i].x == sortbyX[j].x)
			{
				rightBot--;
				leftBot++;
				i++;
			}
			a = 0;
			leftTop = 0;
			rightTop = 0;
			while (a < N)
			{
				b = a;
				
				while (a < N && sortbyY[b].y == sortbyY[a].y)
				{
					if (sortbyY[a].x <= sortbyX[j].x)
					{	leftTop++; leftBot--;	}
					else
					{	rightTop++; rightBot--;	}
					a++;
				}
				if (Math.max(rightBot,  Math.max(leftBot, Math.max(rightTop, leftTop)))<min)
					min = Math.max(rightBot,  Math.max(leftBot, Math.max(rightTop, leftTop)));
				//System.out.println(leftTop + " " + rightTop);
				//System.out.println(leftBot + " " + rightBot);
				//System.out.println("Split by: " + sortbyX[j].x + " " + sortbyY[b].y);
			}
		}
		//System.out.println(min);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("balancing.out")));
		pw.println(min);
		pw.close();
		br.close();
	}
	static class Point
	{
		int x; int y;
		public Point(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
	}
}
