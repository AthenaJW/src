import java.io.*;
import java.util.Arrays;

public class CowCrossTheRoad2 {
	static int[] broken;
	static int K;
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("maxcross.in"));
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		K = Integer.parseInt(temp[1]);
		int B = Integer.parseInt(temp[2]);
		broken = new int[B+2];
		broken[0] = 0;
		broken[broken.length-1] = N+1;
		for (int i = 1; i < broken.length-1; i++)
			broken[i] = Integer.parseInt(br.readLine());
		Arrays.sort(broken);
		int start = 0;
		int end = B;
		int med;
		while (start != end)
		{
			med = (start + end)/2;
			if (pass(med))
				end = med;
			else
				start = med + 1;
		}
		//System.out.println(start);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("maxcross.out")));
		pw.println(start);
		pw.close();
		br.close();
	}
	public static boolean pass(int med)
	{
		int interval = med+1;
		for (int i = 0; i < broken.length-interval; i++)
			if (broken[i + interval] - broken[i] - 1 >= K)
				return true;
		return false;
	}
}
