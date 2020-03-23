import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class PrincesPrincesses {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] temp;
		ArrayList<ArrayList<Integer>> princesses; int n;
		boolean[] princes; boolean found; int candidate; boolean opt;
		int improve = 0;
		for (int i = 0; i < N; i++)
		{
		    n = Integer.parseInt(br.readLine());
		    princesses = new ArrayList<ArrayList<Integer>>();
		    for (int a = 0; a < n; a++)
		    {
			    temp = br.readLine().split(" ");
		    	princesses.add(new ArrayList<Integer>());
		    	for (int j = 1; j < temp.length; j++)
		    		princesses.get(a).add(Integer.parseInt(temp[j]));
		    }
		    princes = new boolean[n];
		    opt = true;
		    outer: for (int j = 0; j < princesses.size(); j++)
		    {
			    found = false;
		    	for (int k = 0; k < princesses.get(j).size(); k++)
		    	{
		    		if (!princes[princesses.get(j).get(k)-1])
		    		{
		    			found = true;
		    			princes[princesses.get(j).get(k)-1] = true;
		    			break;
		    		}
		    	}
		    	if (!found)
		    	{	
		    		opt = false;
		    		improve = j;
		    	}
		    }	
		    if (opt)
		    	System.out.println("OPTIMAL");
		    else
		    {
		    	for (int a = 0; a < princes.length; a++)
	    			if (!princes[a])
	    			{
			    		System.out.println("IMPROVE");
	    				System.out.println((improve+1) + " " + (a+1));
	    				break;
	    			}
		    }
		}
		br.close();
	}
}
