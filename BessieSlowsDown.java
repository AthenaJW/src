import java.io.*;
import java.util.*;
public class BessieSlowsDown {
	static PriorityQueue<Integer> times = new PriorityQueue<Integer>();
	static PriorityQueue<Integer> distances = new PriorityQueue<Integer>();

	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("slowdown.in"));
		int N = Integer.parseInt(br.readLine());
		String[] temp; long n, k; int x;
		for (int i = 0; i < N; i++)
		{
			temp = br.readLine().split(" ");
			if (temp[0].charAt(0) == 'T')
				times.add(Integer.parseInt(temp[1]));
			else
				distances.add(Integer.parseInt(temp[1]));
		}
		double d;
		double vi = 1;
		double t;
		double dist = 0; double time = 0; double dnew, tnew;
		while (!times.isEmpty() || !distances.isEmpty())
		{
			if (distances.isEmpty())
			{
				t = times.peek();
				dist = dist + ((t-time)/vi);
				time = t;
				times.remove();
				vi++;
				continue;
			}
			else if (times.isEmpty())
			{
				d = distances.peek(); 
				time = time + (vi*(d-dist));
				dist = d;
				distances.remove();
				vi++;
				continue;
			}
			d = distances.peek(); 
			t = times.peek();
			if ((t-time) < (d-dist)*vi)
			{
				dist = dist + ((t-time)/vi);
				time = t;
				times.remove();
			}
			else
			{
				dnew = d;
				time = time + (vi*(d-dist));
				dist = d;
				distances.remove();
			}
			vi++;
		}
		time += (1000-dist)*vi;
		//System.out.println(Math.round(time));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("slowdown.out")));
		pw.println(Math.round(time));
		pw.close();
		br.close();
	}
	
}
