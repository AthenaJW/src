import java.io.*;

public class Buckets {
	public static void main(String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader("buckets.in"));
		int Brow = 0;
		int Bcol = 0;
		int Rrow = 0;
		int Rcol = 0;
		int Lrow = 0;
		int Lcol = 0;
		int temp;
		for (int i = 0; i < 10; i++)
		{
			for (int j = 0; j <10; j++)
			{
				temp = reader.read();
				if (temp == 'B')
				{
					Brow = i;
					Bcol = j;
				}
				if (temp == 'R')
				{
					Rrow = i;
					Rcol = j;
				}
				if (temp == 'L')
				{
					Lrow = i;
					Lcol = j;
				}
			}
			reader.readLine();
		}
		int x = Math.abs(Bcol-Lcol);
		int y = Math.abs(Brow-Lrow);
		int output = x+y-1;//the corner bucket is counted twice
		int a = Math.min(Bcol,  Lcol);
		int b = Math.max(Bcol, Lcol);
		int c = Math.min(Brow, Lrow);
		int d = Math.max(Brow,  Lrow);
		if (x == 0 && Rcol == Bcol && Rrow > c && Rrow < d)
			output += 2;
		if (y == 0 && Rrow == Brow && Rcol > a && Rcol < b)
			output += 2;
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("buckets.out")));
		pw.println(output);
		pw.close();
		reader.close();
	}
}
