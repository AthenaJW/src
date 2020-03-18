import java.io.*;
import java.util.*;
public class HighCardWins {
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("highcard.in"));
		int N = Integer.parseInt(br.readLine());
		int[] elsie = new int[N];
		for (int i = 0; i < N; i++)
			elsie[i] = Integer.parseInt(br.readLine());
		int[] bessie = new int[2*N];
		for (int i = 0; i < 2 * N; i++)
			bessie[i] = i+1;
		for (int el: elsie)
			bessie[el-1] = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for (int el: bessie)
			if (el != 0)
				pq.add(el);
		Arrays.sort(elsie);
		int val; int i = 0; int total = 0;
		while (!pq.isEmpty())
		{
			val = pq.remove();
			if (val > elsie[i])
			{
				total++;
				i++;
			}
		}
		//System.out.println(total);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("highcard.out")));
		pw.println(total);
		pw.close();
	}
}
