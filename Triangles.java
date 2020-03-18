import java.io.*;
import java.util.*;

public class Triangles {
	public static void main(String[] args) throws IOException
	{
		int mod = 1000000007;
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("triangles.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("triangles.out")));
		int N = Integer.parseInt(br.readLine());
		Point[] points = new Point[N];
		String[] temp; int x, y;
		for (int i = 0; i < N; i++)
		{
			temp = br.readLine().split(" ");
			x = Integer.parseInt(temp[0]);
			y = Integer.parseInt(temp[1]);
			points[i] = new Triangles().new Point(x, y);
		}
		Comparator<Point> byY = new Comparator<Point>()
		{
			public int compare(Point o1, Point o2)
			{
				if (o1.y !=  o2.y)
					return o1.y-o2.y;
				else
					return o1.x-o2.x;
			}
		};
		Arrays.sort(points, byY);
		int i = 0; int j; int total; int beg;
		while (i < N)
		{
			j = i;
			total = 0;
			while (++j < N && points[j].y == points[i].y)
			{
				total += (points[j].x-points[i].x);
				total %= mod;
			}
			j--;
			points[i].dx = total;
			beg = i;
			while ( i < j )
			{
				points[i+1].dx = points[i].dx + ((i+i-beg-j+1)*(points[i+1].x-points[i].x)%mod);
				points[i+1].dx %= mod;
				i++;
			}
			i++;
		}
		Comparator<Point> byX = new Comparator<Point>()
		{
			public int compare(Point o1, Point o2)
			{
				if (o1.x !=  o2.x)
					return o1.x-o2.x;
				else
					return o1.y-o2.y;
			}
		};
		Arrays.sort(points, byX);
		i = 0;
		while (i < N)
		{
			j = i;
			total = 0;
			while (++j < N && points[j].x == points[i].x)
			{
				total += (points[j].y-points[i].y);
				total %= mod;
			}
			j--;
			points[i].dy = total;
			beg = i;
			while ( i < j )
			{
				points[i+1].dy = points[i].dy + ((i+i-beg-j+1)*(points[i+1].y-points[i].y)% mod);
				i++;
			}
			i++;
		}
		long output = 0;
		for (Point point: points)
		{
			//System.out.println("Point: " + point.x + " " + point.y + " " + point.dx+" " + point.dy);
			output += ((point.dx%mod) * (point.dy%mod)%mod);
			output %= mod;
			
		}
		//System.out.println(output);
		pw.println(output);
		pw.close();
		br.close();
	}
	public class Point
	{
		int x; int y; long dx; long dy;
		public Point(int x, int y)
		{
			this.x = x;
			this.y = y;
			dy = 0;
			dx = 0;
		}
	}
}
