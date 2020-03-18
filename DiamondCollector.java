import java.io.*;
import java.util.Arrays;

public class DiamondCollector {
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("diamond.in"));
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int K = Integer.parseInt(temp[1]);
		int[] diamonds = new int[N];
		for (int i = 0; i < N; i++)
			diamonds[i] = Integer.parseInt(br.readLine());
		int pointer = 0;
		int count;
		Arrays.sort(diamonds);
		int[] leftSums = new int[N];
		for (int i = 0; i < N; i++)
		{
			while(diamonds[i] - diamonds[pointer] > K)
				pointer++;
			count = i - pointer + 1;
			if (i > 0 && count < leftSums[i-1])
				leftSums[i] = leftSums[i-1];
			else
				leftSums[i] = count;
		}
		pointer = N-1;
		int[] rightSums = new int[N];
		for (int i = N-1; i > -1; i--)
		{
			while (diamonds[pointer] - diamonds[i]  > K)
				pointer--;
			count = pointer - i + 1;
			if (i < N-1 && count < rightSums[i+1])
				rightSums[i] = rightSums[i+1];
			else
				rightSums[i] = count;
		}
		int max = 0;
		for (int i = 0; i < N-1; i++)
			max = Math.max(max, leftSums[i] + rightSums[i+1]);
		//System.out.println(max);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("diamond.out")));
		pw.println(max);
		pw.close();
		br.close();
	}
}
