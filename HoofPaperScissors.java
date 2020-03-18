import java.io.*;
public class HoofPaperScissors {
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("hps.in"));
		int N = Integer.parseInt(br.readLine());
		char[] plays = new char[N];
		char play;
		int H = 0;
		int P = 0;
		int S = 0;
		for (int i = 0; i < N; i++)
		{
			play = br.readLine().charAt(0);
			plays[i] = play;
			if (play == 'H')
				H++;
			else if (play == 'P')
				P++;
			else
				S++;
		}
		int Sbef = 0; int Hbef = 0; int Pbef = 0;
		int max = Math.max(H, Math.max(P,  S));
		int getSum;
		for (int i = 0; i < N; i++)
		{
			if (plays[i] == 'H')
			{
				Hbef++;
				H--;
			}
			else if (plays[i] == 'P')
			{
				Pbef++;
				P--;
			}
			else
			{
				Sbef++;
				S--;
			}
			getSum = Math.max(H,  Math.max(P, S)) + Math.max(Hbef,  Math.max(Pbef, Sbef));
			max = Math.max(getSum, max);
		}
		//System.out.println(max);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));
		pw.println(max);
		pw.close();
		br.close();
	}
}
