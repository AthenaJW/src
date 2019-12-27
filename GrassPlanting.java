//Link to Problem: http://www.usaco.org/index.php?page=viewproblem2&cpid=894
import java.io.*;
public class GrassPlanting {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader("planting.in"));
		int num = Integer.parseInt(br.readLine());
		int[] num_edges = new int[num + 1];
		String[] temp;
		for (int i = 0; i < num - 1; i++)
		{
			temp = br.readLine().split(" ");
			num_edges[Integer.parseInt(temp[0])]++;
			num_edges[Integer.parseInt(temp[1])]++;
		}
		int max_edges = 0;
		for (int i = 1; i < num_edges.length; i++)
		{
			if (num_edges[i] > max_edges)
				max_edges = num_edges[i];
		}
		//System.out.println(max_edges + 1);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("planting.out")));
		pw.println(max_edges + 1);
		pw.close();
		br.close();
	}
}
