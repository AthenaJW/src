import java.io.*;
import java.util.*;
public class MooBuzz {
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("moobuzz.in"));
		int N = Integer.parseInt(br.readLine());
		int add = 1;
		int num_moos=0;
		int previous = 0;
		while(add != 0)
		{
			num_moos = (N/3) + (N/5) - (N/15);
			add = num_moos - previous;
			N += add;
			if (add == 0)
				break;
			previous = num_moos;
		}
		//System.out.println(N);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("moobuzz.out")));
		pw.println(N);
		pw.close();
		br.close();
	}
}
