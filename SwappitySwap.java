import java.io.*;
import java.util.*;

public class SwappitySwap {
	static int[] outgoing;
	static int[] sets;
	static ArrayList<ArrayList<Integer>> cycles;
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("swap.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("swap.out")));

		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int M = Integer.parseInt(temp[1]);
		int K = Integer.parseInt(temp[2]);
		outgoing = new int[N];
		sets = new int[N];
		int[][] steps = new int[M][2];
		for (int i = 0; i < M; i++)
		{
			temp = br.readLine().split(" ");
			steps[i][0] = Integer.parseInt(temp[0])-1;
			steps[i][1] = Integer.parseInt(temp[1])-1;
		}
		int beg, end, step;
		for (int i = 0; i < N; i++)
		{
			step = i;
			for (int j = 0; j < M; j++)
			{
				beg = steps[j][0]; end = steps[j][1];
				if (step <= end && step >= beg)
					step = end - (step-beg);
			}
			outgoing[i] = step;
		}
		//correct
		Arrays.fill(sets, -1);
		int setnum = 0;
		cycles = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < N; i++)
		{
			if (sets[i] != -1)
				continue;
			cycles.add(new ArrayList<Integer>());
			cycleFind(i, setnum++);
		}
		//for (ArrayList<Integer> cycle: cycles)
		//	System.out.println(cycle);
		int iternum; int cyclenum; int node1; int node2;
		int[] output = new int[N];
		for (ArrayList<Integer> cycle: cycles)
		{
			iternum = K % cycle.size();
			for (int i = 0; i < cycle.size(); i++)
			{
				cyclenum = i + iternum;
				cyclenum %= cycle.size();
				node1 = cycle.get(i);
				node2 = cycle.get(cyclenum);
				output[node2] = node1+1;
			}
		}
		for (int el: output)
			pw.println(el);
		pw.close();
		br.close();
	}
	public static void cycleFind(int node, int setnum)
	{
		if (sets[node] == setnum)
			return;
		sets[node] = setnum;
		cycles.get(cycles.size()-1).add(node);
		cycleFind(outgoing[node], setnum);
	}
}
