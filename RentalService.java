import java.io.*;
import java.util.*;
public class RentalService {
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("rental.in"));
		String[] temp = br.readLine().split(" ");
		int ncows = Integer.parseInt(temp[0]);
		int nbuyers = Integer.parseInt(temp[1]);
		int nrenters = Integer.parseInt(temp[2]);
		int[] cows = new int[ncows];
		for (int i = 0; i < ncows; i++)
			cows[i] = Integer.parseInt(br.readLine());
		Arrays.sort(cows);
		int[][] priceQty = new int[nbuyers][2];
		for (int i = 0; i < nbuyers; i++)
		{
			temp = br.readLine().split(" ");
			priceQty[i][0] = Integer.parseInt(temp[0]);
			priceQty[i][1] = Integer.parseInt(temp[1]);
		}
		Comparator<int[]> c = new Comparator<int[]>()
		{
			public int compare(int[] o1, int[] o2)
			{
				return o2[1] - o1[1];//descending
			}
		};
		Arrays.sort(priceQty, c);
		int[] rentals = new int[nrenters];
		for (int i = 0; i < nrenters; i++)
		{
			rentals[i] = -Integer.parseInt(br.readLine());//descending
		}
		Arrays.sort(rentals);
		long profit = 0;
		int i = 0;
		while (i < nrenters && i < ncows)
		{
			profit += - rentals[i];
			i++;
		}
		int j = 0;
		int num = i-1;
		i = ncows-1;
		int produced;
		while (i > num && j < nbuyers)
		{
			produced = cows[i];
			while (produced > 0)
			{
				if (produced > priceQty[j][0])
				{
					profit += priceQty[j][0] * priceQty[j][1];
					produced -= priceQty[j][0];
					priceQty[j][0] = 0;
					j++;
				}
				else
				{
					profit += produced * priceQty[j][1];
					priceQty[j][0] -= produced;
					produced = 0;
				}
				if (j >= priceQty.length)
				{
					break;
				}
			}
			i--;
		}
		int k = 0;
		int total;
		while (i > -1 && j < nbuyers)
		{
			total = 0;
			produced = cows[i];
			while (produced > 0)
			{
				if (produced > priceQty[j][0])
				{
					total += priceQty[j][0] * priceQty[j][1];
					produced -= priceQty[j][0];
					priceQty[j][0] = 0;
					j++;
				}
				else
				{
					total += produced * priceQty[j][1];
					priceQty[j][0] -= produced;
					produced = 0;
				}
				if (j >= priceQty.length)
				{
					break;
				}
			}
			//System.out.println("comparing:");
			//System.out.println(total);
			//System.out.println(-rentals[rentals.length-1-k]);
			if (total > -rentals[rentals.length-1-k])
			{
				profit -= -rentals[rentals.length-1-k];
				profit += total;
				k++;
			}
			else
				break;
			i--;
			//System.out.println(profit);
		}
		//System.out.println(profit);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("rental.out")));
		pw.println(profit);
		pw.close();
		br.close();
	}
}
