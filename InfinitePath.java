import java.io.*;
import java.util.*;
public class InfinitePath {
	static int[] p = new int[20000];
	static int[] c = new int[20000];
	static int[] sets = new int[20000];
	static ArrayList<int[]> cycles;
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("6.in"));
		int N = Integer.parseInt(br.readLine()); int num;
		String[] temp; int setnum; int point; int length; int k; int start;
		ArrayList<Integer> factors; int cons; int stride; boolean works;
		for (int a = 0; a < N; a++)
		{
			cycles = new ArrayList<int[]>();
			num = Integer.parseInt(br.readLine());
			temp = br.readLine().split(" ");
			for (int j = 0; j < num; j++)
				p[j] = Integer.parseInt(temp[j])-1;
			temp = br.readLine().split(" ");
			for (int j = 0; j < num; j++)
				c[j] = Integer.parseInt(temp[j]);
			Arrays.fill(sets, 0, num, -1);
			setnum = 0;
			for (int i = 0; i < num; i++)
			{
				if (sets[i] != -1)
					continue;
				sets[i] = setnum++;
				point = p[i];
				length = 1;
				while (point != i)
				{
					sets[point] = setnum;
					point = p[point];
					length++;
				}
				cycles.add(new int[] {i, length});
			}
			k = Integer.MAX_VALUE;
			for (int[] cycle: cycles)
			{
				length = cycle[1];
				factors = primeFactors(length);
				for (int i = 0; i < Math.pow(2, factors.size()); i++)
				{
					start = cycle[0];
					stride = 1;
					for (int j = 0; j < factors.size(); j++)
					{
						if ((i & (1 << j)) != 0)
							stride *= factors.get(j);
					}
					for (int j = 0; j < stride; j++)
					{
						cons = c[start];
						works = true;
						point = getNext(start, stride);
						while (point != start)
						{
							if (c[point]!=cons)
							{
								works = false;
								break;
							}
							point = getNext(point, stride);
						}
						if (works)
						{
							k = Math.min(k, stride);
							break;
						}
						else
							start = p[start];
					}
				}
			}
			System.out.println(k);
		}
		br.close();
	}
	public static ArrayList<Integer> primeFactors(int N)
	{

		ArrayList<Integer> factors = new ArrayList<Integer>();
		while (N > 0 && N%2==0)
		{
			N >>=1;
			factors.add(2);
		}
		for (int i = 3; i < Math.floor(Math.sqrt(N)); i+=2)
		{
			while (N%i == 0)
			{
				N /= i;
				factors.add(i);
			}
		}
		if (N != 1)
			factors.add(N);
		return factors;
	}
	public static int getNext(int node, int steps)
	{
		while (steps > 0)
		{
			node = p[node];
			steps--;
		}
		return node;
	}
}
