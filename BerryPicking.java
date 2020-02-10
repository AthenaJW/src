import java.io.*;
import java.util.*;
public class BerryPicking {
	static int mod;
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("berries.in"));
		String[] temp = br.readLine().split(" ");
		int numTrees = Integer.parseInt(temp[0]);
		int numBaskets = Integer.parseInt(temp[1]);
		temp = br.readLine().split(" ");
		ArrayList<Integer> trees = new ArrayList<Integer>(numTrees);
		for (int i = 0; i < numTrees; i++)
			trees.add(Integer.parseInt(temp[i]));
		int count;
		int maximum = 0;
		int total;
		Comparator<Integer> c = new Comparator<Integer>()
		{
			public int compare(Integer o1, Integer o2)
			{
				return (o2%mod) - (o1%mod);
			}
		};
		for (int i = 1; i <= 1000; i++)
		{
			count = 0;
			for (int j = 0; j < numTrees; j++)
			{
				count += trees.get(j) / i;
			}
			if (count < numBaskets/2)
				break;
			mod = i;
			Collections.sort(trees, c);
			System.out.println(count);
			//Bessie takes the max berries that would be left over
			//first give her extra baskets of b;
			total = 0;
			total += i * Math.min(count - numBaskets/2, numBaskets/2);
			//then everything less than b
			for (int j = 0; j < numBaskets - count && j < numTrees; j++)
			{
				System.out.println("J: " + j);
				total += trees.get(j) % mod;
			}
			//maximum may not be the last one
			if (total > maximum)
				maximum = total;		
		}
		//System.out.println(maximum);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("berries.out")));
		pw.println(maximum);
		pw.close();
		br.close();
	}
}
