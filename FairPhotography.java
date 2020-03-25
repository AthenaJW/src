import java.io.*;
import java.util.*;
public class FairPhotography {
	static int[] pW;
	static int[] pS;
	static Cow[] cows;
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("swap.in"));
		int N = Integer.parseInt(br.readLine());
		pS = new int[N];	pW = new int[N];	cows = new Cow[N];
		String[] temp;
		for (int i = 0; i < N; i++)
		{
			temp = br.readLine().split(" ");
			cows[i] = new Cow(Integer.parseInt(temp[0]), temp[1].charAt(0));
		}
		Comparator<Cow> c = new Comparator<Cow>()
		{
			public int compare(Cow o1, Cow o2)
			{
				return o1.position-o2.position; //ascending by distance
			}
		};
		Arrays.sort(cows, c);
		if (cows[0].type == 'S')
			pS[0]++;
		else
			pW[0]++;
		for (int i = 1; i < cows.length; i++)
		{
			if (cows[i].type == 'S')
				pS[i] = pS[i-1] + 1;
			else
				pW[i] = pW[i-1] + 1;
		}
		int start = 0;
		int end = cows[N-1].position-cows[0].position;
		int med = 0;
		while (start != end)
		{
			med = (start + end+1) / 2;
			if (pass(med))
				start = med;
			else
				end = med-1;		
		}		
		
		br.close();
		//PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("homework.out")));
		//pw.close();
	}
	static class Cow
	{
		public int position; 
		public char type;
		public Cow(int position, char type)
		{
			this.position = position;
			this.type = type;
		}
		public String toString() {
			return position + " " + type;
		}
	}
	public static boolean pass(int med)
	{
		int j = 0;
		for (int i = 0; i < cows.length; i++)
		{
			while (j < cows.length && cows[j].position - cows[i].position <= med)
				j++;
			if (j == cows.length && cows[j].position - cows[i].position < med)
				return false;
			j--;
			if ((j-i)%2 == 0)
				continue;
			if (pW[j] - pW[i] >= pS[j] - pS[i])
				return true;
		}
		return false;
	}
}

