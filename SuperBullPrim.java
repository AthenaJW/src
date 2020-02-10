import java.io.*;
import java.util.*;

public class SuperBullPrim {
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader br = new BufferedReader(new FileReader("superbull.in"));
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		long[] teamIDs = new long[num];
		boolean[] used = new boolean[num];
		long[] currentBest = new long[num];
		for (int i = 0; i < num; i++)
			teamIDs[i] = Long.parseLong(br.readLine());
		int current;
		long maxPath = 0;
		for (int i = 0; i < teamIDs.length; i++)
		{
			current = -1;
			//this cannot be 0. You are finding the max out of the unused edges. 
			//however, 0 is used right after the bat. 
			//but if this is always set to one, you end up comparing the rest 
			//of the edges to 0, when it's not even viable
			//so say if the element at 0 bitwise with something else is greater than
			//something else. your best keeps being current = 0;
			//and you keep adding the same edge on
			for (int j = 0; j < teamIDs.length; j++)//search for the largest edge
			// out of currently connected edges
			{
				if (used[j])
					continue;
				if (current == -1 || currentBest[j] > currentBest[current])
					current = j;
			}
			maxPath += currentBest[current];
			used[current] = true; //commit current to the path
			for (int j = 0; j < teamIDs.length; j++)
			{
				currentBest[j] = Math.max(currentBest[j], teamIDs[j]^teamIDs[current]);
			}
		}
		//System.out.println(maxPath);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("superbull.out")));
		pw.println(maxPath);
		pw.close();
		br.close();
	}
}
