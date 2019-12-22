import java.io.*;
import java.util.Scanner;
public class ShellGame {
	public static void main(String[] args) throws IOException
	{
		Scanner scan = new Scanner(new File("shell.in"));
		int num = Integer.parseInt(scan.nextLine());
		int[] A = new int[num];
		int[] B = new int[num];
		int[] G = new int[num];
		for (int i = 0; i < num; i++)
		{
			A[i] = scan.nextInt();
			B[i] = scan.nextInt();
			G[i] = scan.nextInt();
			scan.nextLine();
		}
		int maxCorrect = 0;
		int current;
		int correct = 0;
		for (int j = 1; j <= 3; j++)
		{
			current = j;
			for (int i = 0; i<num; i++)
			{
				if (current == A[i])
					current = B[i];
				else if (current == B[i])
					current = A[i];
				if (current == G[i])
					correct++;
			}
			if (correct > maxCorrect)
				maxCorrect = correct;
			correct = 0;
		}
		PrintWriter pw = new PrintWriter(new FileWriter("shell.out"));
		pw.println(maxCorrect);
		scan.close();
		pw.close();
	}
}
