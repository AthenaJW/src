import java.io.*;
import java.util.*;

public class BovineGenomics {
	static HashMap<String, int[]> diff = new HashMap<String, int[]>();
	static String[] genes = new String[] {"A", "T", "G", "C"};
	static char[][] genomes;
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("cownomics.in"));
		String key;
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				for (int k = 0; k < 4; k++)
				{
					key = genes[i] + genes[j] + genes[k];
					diff.put(key, new int[] {0, 0});
				}
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int num_genes = Integer.parseInt(temp[1]);
		genomes = new char[2*N][num_genes];
		for (int i =  0; i < 2*N; i++)
			genomes[i] = br.readLine().toCharArray();
		//check all things
		int count = 0;
		int[] c;
		boolean isSolution;
		for (int i = 0; i < num_genes; i++)
			for (int k = i + 1; k < num_genes; k++)
				for (int j = k + 1; j < num_genes; j++)
				{
					isSolution = true;
					for (int a = 0; a < 2*N; a++)
					{
						key = new String(new char[] {genomes[a][i], genomes[a][j], genomes[a][k]});
						diff.get(key)[1]++;
						if (a < N)
							diff.get(key)[0]++;
					}
					for (String s: diff.keySet())
					{
						c = diff.get(s);
						if (c[0]!= 0 && c[1] > c[0])
							isSolution = false;
						c[0] = 0; c[1] = 0;
					}
					if (isSolution)
					{
						count++;
					}
				}
		//System.out.println(count);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cownomics.out")));
		pw.println(count);
		pw.close();
		br.close();
	}
}
