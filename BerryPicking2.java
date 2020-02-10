import java.io.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class BerryPicking2 {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("berries.in"));
		String[] temp = br.readLine().split(" ");
		int numTrees = Integer.parseInt(temp[0]);
		int numBaskets = Integer.parseInt(temp[1]);
		temp = br.readLine().split(" ");
		int[] trees = new int[numTrees];
		for (int i = 0; i < numTrees; i++)
			trees[i] = Integer.parseInt(temp[i]);
		Arrays.sort(trees);
		int[] solution = new int[numBaskets];
		for (int i = 0; i < numBaskets; i++)
			solution[i] = trees[trees.length - i-1];
		Arrays.sort(solution);
		boolean done = false;
		while (!done)
		{
			done = true;
			//for (int i = solution.length-1; i>= numBaskets/2; i--)
			//{
				if (solution[solution.length-1]/2 > solution[0])
				{
					done = false;
					solution[0] = solution[solution.length-1]/2;
					solution[solution.length-1] /= 2;
					Arrays.sort(solution);
				}
			//}
		}
		for (int el: solution)
			System.out.println(el);
		int total = 0;
		for (int i = 0; i < numBaskets/2; i++)
			total += solution[i];
		System.out.println(total);
		//PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("berries.out")));
		//pw.println(total);
		//pw.close();
		br.close();
	}
}
