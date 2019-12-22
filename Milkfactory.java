import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Milkfactory {
	public static void main(String[] args) throws IOException
	{
		Scanner scan = new Scanner(System.in);
		int num = scan.nextInt();
		scan.nextLine();
		int temp = scan.nextInt();
		int[] num_out = new int[num];
		for (int i = 0; i<num; i++)
		{
			temp = scan.nextInt();
			num_out[temp-1]++;
			scan.nextLine();
		}
		int output = -1;
		for (int i = 0; i<num; i++)
		{
			if (num_out[i] == 0)
			{
				output = i+1;
				break;
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("factory.out")));
		pw.println(output);
		pw.close();
		scan.close();
		
	}
}
