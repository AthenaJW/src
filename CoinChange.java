
import java.util.*;
import java.lang.*;
import java.io.*;

class CoinChange{
	public static void main (String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		String[] temp;
		int[] coins;
		int[] sol;
		int change = 0;
		int numCoins;
		for (int i = 0; i < num; i++)
		{
		    numCoins = Integer.parseInt(br.readLine());
		    coins = new int[numCoins];
		    temp = br.readLine().split(" ");
		    for (int j = 0; j < numCoins; j++)
		    {
		        coins[j] = Integer.parseInt(temp[j]);
		        System.out.println(coins[j]);
		    }
		    change = Integer.parseInt(br.readLine());
		    sol = new int[change+1];
		    sol[0] = 1;
		    int tot;
		    for (int j = 1; j <= change; j++)
		    {
		        if (j<coins[0])
		            sol[j] = 0;
		        else if (j == coins[0])
		            sol[j] = 1;
		        else
		        {
		           tot = 0;
		           for (int coin: coins)
		           {
		               if (j >= coin)
		                   tot += sol[j-coin];
		               //System.out.println("Total: " + tot);
		           }
		           sol[j] = tot;
		        }
		        for (int s: sol)
		        	System.out.print(s);
		        System.out.println();
		    }
		    //rip it repeats solutions that are the same but in different order
		    System.out.println(sol[sol.length-1]);
		}
	}
}