import java.io.*;
import java.util.*;

public class NoLargeBrownCow {
	static ArrayList<ArrayList<String>> adj;
	static ArrayList<Integer> indices;
	static String[][] missing;
	static int[] getind;
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("nocow.in"));
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int K = Integer.parseInt(temp[1]);
		temp = br.readLine().split(" ");
		int numadj = temp.length - 5;
		adj = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < numadj; i++)
			adj.add(new ArrayList<String>());
		indices = new ArrayList<Integer>();
		getind = new int[numadj];
		missing = new String[N][numadj];
		for (int j = 0; j < numadj; j++)
	    {
	    	missing[0][j] = temp[j+4];
	    	if (adj.get(j).contains(temp[j+4]))
	    		continue;
	    	else 
	    	{
	    		adj.get(j).add(temp[j+4]);
	    		continue;
	    	}
	    }
		for (int i = 1; i < N; i++)
		{
		    temp = br.readLine().split(" ");
		    for (int j = 0; j < numadj; j++)
		    {
		    	missing[i][j] = temp[j+4];
		    	if (adj.get(j).contains(temp[j+4]))
		    		continue;
		    	else 
		    	{
		    		adj.get(j).add(temp[j+4]);
		    		continue;
		    	}
		    }
		}
		for (ArrayList<String> row: adj)
			Collections.sort(row);
		int index; int multiply;
		for (String[] cow: missing)
		{
			index = 0;
			multiply = 1;
			for (int i = cow.length-1; i > -1; i--)
			{
				index += adj.get(i).indexOf(cow[i])*multiply;
				getind[i] = multiply;
				multiply *= adj.get(i).size();
			}
			indices.add(index);
		}
		Collections.sort(indices);
		int previous = 0;
		int next = Collections.binarySearch(indices, K);
		if (next < 0)
			next = ~next;
		else
			next++;
		while (next > previous)
		{
			K += (next-previous);
			previous = next;
			next = Collections.binarySearch(indices, K);
			if (next < 0)
				next = ~next;
			else
				next++;
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("nocow.out")));
		K--;
		for (int i = 0; i < numadj; i++)
		{
			index = K/getind[i];
			pw.print(adj.get(i).get(index));
			if (i != numadj-1)
				pw.print(" ");
			//System.out.print(adj.get(i).get(index) + " ");
			K = K%getind[i];
		}
		//System.out.println();	
		pw.close();
		br.close();
	}
}
