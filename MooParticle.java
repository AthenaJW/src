import java.io.*;
import java.util.*;
public class MooParticle {
	static int[][] particles;
	static ArrayList<ArrayList<Integer>> AdjList;
	static int[] sets;
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("moop.in"));
		int N = Integer.parseInt(br.readLine());
		AdjList = new ArrayList<ArrayList<Integer>>();
		particles = new int[N][2];
		sets = new int[N];
		String[] temp; int x, y;
		for (int i = 0; i < N; i++)
		{
			AdjList.add(new ArrayList<Integer>());
		    temp = br.readLine().split(" ");
		    x = Integer.parseInt(temp[0]);
		    y = Integer.parseInt(temp[1]);
		    particles[i] = new int[]{x, y};
		}
		for (int i = 0; i < N; i++)
		{
			for (int j = i+1; j < N; j++)
			{
				if (particles[i][0] <= particles[j][0] && particles[i][1] <= particles[j][1])
				{
					AdjList.get(i).add(j);
					AdjList.get(j).add(i);
				}
				else if (particles[j][0] <= particles[i][0] && particles[j][1] <= particles[i][1])
				{
					AdjList.get(i).add(j);
					AdjList.get(j).add(i);
				}
			}
		}
		Arrays.fill(sets, -1);
		int setnum = 0;
		for (int i = 0; i < N; i++)
		{
			if (sets[i] != -1)
				continue;
			bfs(i, setnum++);
		}
		//System.out.println(setnum);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("moop.out")));
		pw.println(setnum);
		pw.close();
		br.close();
	}
	public static void bfs(int node, int setnum)
	{
		if (sets[node] == setnum)
			return;
		sets[node] = setnum;
		for (int neighbor: AdjList.get(node))
			bfs(neighbor,setnum);
	}
}
