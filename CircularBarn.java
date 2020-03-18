import java.io.*;
import java.util.*;
public class CircularBarn {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("cbarn.in"));
		int N = Integer.parseInt(br.readLine());
		int[] barn = new int[N];
		for (int i = 0; i < N; i++)
			barn[i] = Integer.parseInt(br.readLine());
		int[] counts = new int[N];
		int cows = 0;
		int start = -1;
		for (int i = 0; i < N; i++)
		{
			if (barn[i] == 0)
				start = i;
		}
		if (start == -1)
		{
			System.out.println(0); return;
		}
		for (int i = start; i < N + start; i++)
		{
			if (barn[i%N] == 0 && cows != 0)
				cows--;
			else if (barn[i%N] > 1)
				cows += barn[i%N] - 1;
			counts[i%N] = cows;
		}
		for (int i = 0; i < N; i++)
		{
			if (counts[i] > counts[start])
				start = i;
		}
		
		start = (start - barn[start] + N)%N;
		System.out.println(start);
		Comparator<Integer> c= new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) { return o2-o1; }
		};
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(c);
		ArrayList<Integer> temp = new ArrayList<Integer>();
		int total = 0;
		for (int i = start; i < N+start; i++)
		{
			while (!pq.isEmpty())
				temp.add(pq.remove()+1);
			while (!temp.isEmpty())
				pq.add(temp.remove(temp.size()-1));
			if (pq.isEmpty())
			{
				if (barn[i%N] > 1)
					for (int j = 1; j < barn[i%N]; j++)
						pq.add(0);
			}
			else
			{
				total += Math.pow(pq.remove(), 2);
				if (barn[i%N] > 0)
					for (int j = 0; j < barn[i%N]; j++)
						pq.add(0);
			}
			System.out.println(i);
			System.out.println(pq);
			System.out.println(total);
		}
		System.out.println(total);
		//PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cbarn.out")));
		//pw.println(total);
		//pw.close();
		br.close();
	}
}
