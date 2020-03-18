import java.io.*;
import java.util.*;

public class Recommendations {
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] categories = new int[N][2];
		String[] temp = br.readLine().split(" ");
		for (int i = 0; i < N; i++)
			categories[i][0] = Integer.parseInt(temp[i]); // 0 is number of articles
		temp = br.readLine().split(" ");
		for (int i = 0; i < N; i++)
			categories[i][1] = Integer.parseInt(temp[i]); // 1 is cost
		Comparator<int[]> c = new Comparator<int[]>()
		{
			public int compare(int[] o1, int[] o2)
			{
				return o1[0]-o2[0];
			}
		};
		Arrays.sort(categories, c);
		Comparator<Integer> c2 = new Comparator<Integer>()
		{
			public int compare(Integer o1, Integer o2)
			{
				return o2-o1;
			}
		};		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(c2);
		int current = categories[0][0]; long sum = 0; long ans = 0; int i = 0;
		while (i < N)
		{
			while (i < N && categories[i][0] == current)
			{
				pq.add(categories[i][1]); //add into the pq all the numbers are the same
				sum += categories[i][1]; i++; //sum represents sum of all # in pq
			}
			if (!pq.isEmpty())
			{
				sum -= pq.remove(); //we will remove the most expensive category, and raise the number of all the others
				current++; // now the number of articles of all articles in pq is 1 higher
				ans += sum; //the cost of raising all these articles by 1 is represented by sum. this will be added to the answer
			}
			else
				current = categories[i][0];
		}
		while (!pq.isEmpty())
		{
			sum -= pq.remove(); //in case the pq is not exhausted when we're over
			ans += sum;		//we perform the same "raising" operation
		}
		System.out.println(ans);
	}
}
