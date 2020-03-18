import java.io.*;
import java.util.*;
public class BessieGoesMoo {
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("bgm.in"));
		long[] B = new long[7];
		long[] E = new long[7];
		long[] S = new long[7];
		long[] I = new long[7];
		long[] G = new long[7];
		long[] O = new long[7];
		long[] M = new long[7];

		int N = Integer.parseInt(br.readLine());
		char l; int num;
		String[] temp; int index;
		for (int i = 0; i < N; i++)
		{
			temp = br.readLine().split(" ");
			l = temp[0].charAt(0);
			num = Integer.parseInt(temp[1]);
			index = num%7;
			index += 7; index %= 7;
			if (l == 'B') {B[index]++;}
			if (l == 'E') {E[index]++;}
			if (l == 'S') {S[index]++;}
			if (l == 'I') {I[index]++;}
			if (l == 'G') {G[index]++;}
			if (l == 'O') {O[index]++;}
			if (l == 'M') {M[index]++;} 
		}
		long count = 0;
		for (int b = 0; b < 7; b++)
		{
			if (B[b] == 0) continue;
			for (int e = 0; e < 7; e++)
			{
				if (E[e] == 0) continue;
				for (int s = 0; s < 7; s++)
				{
					if (S[s] == 0) continue;
					for (int i = 0; i < 7; i++)
					{
						if (I[i] == 0) continue;
						for (int g = 0; g < 7; g++)
						{
							if (G[g] == 0) continue;
							for (int o = 0; o < 7; o++)
							{
								if (O[o] == 0) continue;
								for (int m = 0; m < 7; m++)
								{
									if (M[m] == 0) continue;
									if ((b + 2*e + 2*s + i)%7==0)
										count+= B[b]*E[e]*S[s]*I[i]*G[g]*O[o]*M[m];
									else if ((g + o + e + s)%7==0)
										count+= B[b]*E[e]*S[s]*I[i]*G[g]*O[o]*M[m];
									else if ((m + 2*o)%7==0)
										count+= B[b]*E[e]*S[s]*I[i]*G[g]*O[o]*M[m];
								}
							}
						}
					}
				}
			}
		}
		//System.out.println(count);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("bgm.out")));
		pw.println(count);
		pw.close();
		br.close();
	}
}
