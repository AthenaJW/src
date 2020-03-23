import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddingPowers {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] array = new long[30]; String[] temp; int num; int k; 
		boolean gotone; boolean possible;
		for (int i = 0; i < N; i++)
		{
		    temp = br.readLine().split(" ");
		    num = Integer.parseInt(temp[0]); k = Integer.parseInt(temp[1]);
		    temp = br.readLine().split(" ");
		    for (int a = 0; a < num; a++)
		    	array[a] = Long.parseLong(temp[a]);
		    possible = true;
		    outer: for (int a = 0; a < 55; a++) //upper bound 55
		    {
			    gotone = false;
		    	for (int b = 0; b < num; b++)
		    	{
		    		
		    		if (array[b]%k != 0)
		    		{
		    			if (array[b]%k != 1)
		    			{
		    				possible = false;
		    				break outer;
		    			}	
		    			if (gotone == false)
		    				gotone = true;
		    			else
		    			{
		    				possible = false;
		    				break outer;
		    			}
		    		}
		    		array[b] = array[b]/k;
		    	}
		    }
		    if (possible)
		    	System.out.println("YES");
		    else
		    	System.out.println("NO");
		}
	}
}
