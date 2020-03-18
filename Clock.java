import java.io.*;
import java.util.*;
public class Clock {
	static int[] d = new int[100000];
	static int[] A = new int[100000];
	static ArrayList<ArrayList<Integer>> AdjList = new ArrayList<ArrayList<Integer>>();
	static int s0 = 0, s1 = 0, n0 = 0, n1 = 0;
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("clocktree.in"));
		//PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("clocktree.out")));
		int a, b;
		int N = Integer.parseInt(br.readLine());
		String[] temp = br.readLine().split(" ");
		for (int i = 0; i < N; i++)
		{
			A[i] = Integer.parseInt(temp[i]);
			AdjList.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < N-1; i++)
		{
			temp = br.readLine().split(" ");
			a = Integer.parseInt(temp[0])-1;
			b = Integer.parseInt(temp[1])-1;
			AdjList.get(a).add(b);
			AdjList.get(b).add(a);
		}
		dfs(0, 0, -1); //par is not the node we just visited?
		for (int i = 0; i <N; i++)
		{
			if (d[i]%2 == 0)
				s1 += A[i];
			else 
			{
				s0 += A[i]; 
				n0++;
			}
		}
		if ((s0 %12) == (s1%12))
			System.out.println(N);
		else if ((s0+1)%12 == (s1%12))
			System.out.println(n1);
		else if((s0%12) == ((s1+1)%12))
			System.out.println(n0);
		else
			System.out.println(0);
		//pw.close();
		br.close();
	}
	public static void dfs(int i, int depth, int par)
	{
		d[i] = depth;
		for (int j = 0; j < AdjList.get(i).size(); j++)
			if (AdjList.get(i).get(j) != par)
				dfs(AdjList.get(i).get(j), depth + 1, i); //par = i as long as you go forward
	}
}
