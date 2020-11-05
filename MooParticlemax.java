import java.io.*;

public class MooParticlemax {
	static int[][] particles;
	static int minx, miny, maxx, maxy;
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("moop.in"));
		int N = Integer.parseInt(br.readLine());
		particles = new int[N][2];
		String[] temp; int x, y;
		minx = 0; miny = 0;
		maxy = 0; maxx = 0;
		
		for (int i = 0; i < N; i++)
		{
		    temp = br.readLine().split(" ");
		    x = Integer.parseInt(temp[0]);
		    y = Integer.parseInt(temp[1]);
		    if (particles[minx][0] > x)
		    	minx = i;
		    if (particles[maxx][0] < x)
		    	maxx = i;
		    if (particles[miny][1] > y)
		    	miny = i;
		    if (particles[maxy][1] < y)
		    	maxy = i;
		    particles[i] = new int[]{x, y};
		}
		int sol = ans();
		//System.out.println(sol);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("moop.out")));
		pw.println(sol);
		pw.close();
		br.close();
	}
	public static int ans()
	{
		if (particles[minx][1] <= particles[maxx][1])
			return 1;
		if (particles[miny][0] <= particles[maxy][0])
			return 1;
		return 2;
	}
}
