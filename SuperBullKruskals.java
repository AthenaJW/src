//put on hiatus because of the need for the union disjoint set
import java.io.*;
import java.util.*;
public class SuperBullKruskals {
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		//BufferedReader br = new BufferedReader(new FileReader("superbull.in"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		int[] teamIDs = new int[num];
		boolean[] used = new boolean[num];
		for (int i = 0; i < num; i++)
			teamIDs[i] = Integer.parseInt(br.readLine());
		ArrayList<int[]> kruskals = new ArrayList<int[]>(num*(num-1)/2);
		//you need to keep track of the vertices that have already been used.
		//because there's more than one way to connect two vertices, or to eliminate two teams in a match
		//example 1-2, 2-4 vs 1-2, 1-3, 1-4
		//here we eliminated 2 and 4 in different ways
		//since there's possibility of cycle, you need to keep track of what was already used
		int[] temp;
		for (int i = 0; i < teamIDs.length-1; i++)
		{
			for (int j = i+1; j < teamIDs.length; j++)
			{
				temp = new int[3];
				temp[0] = i;
				temp[1] = j;
				temp[2] = teamIDs[i]^teamIDs[j];
				kruskals.add(temp);
			}
		}
		Comparator<int[]> c = new Comparator<int[]>()
		{
			public int compare(int[] edge1, int[] edge2)
			{
				return edge2[2]-edge1[2];//reverse for descending order
			}
		};
		Collections.sort(kruskals, c);
		long maxPath = 0;
		int i = 0;
		int point = 0;
		for (int[] e: kruskals)
		{
			System.out.println("Node 1: " + e[0] + " Node 2: " + e[1]);
		}
		while (i < num-2)//v-1, indexed from 0
		{
			if (!used[kruskals.get(point)[0]] || !used[kruskals.get(point)[1]])
			{
				maxPath += kruskals.get(point)[2];
				System.out.println(kruskals.get(point)[0] + " " + kruskals.get(point)[1]);
				used[kruskals.get(point)[0]] = true;
				used[kruskals.get(point)[1]] = true;
				i++;
			}
			point++;
		}
		System.out.println(maxPath);
		//PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("superbull.out")));
		//pw.println(maxPath);
		//pw.close();
		//br.close();
	}
}
