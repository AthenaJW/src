import java.io.*;
import java.util.*;
public class Template {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("swap.in"));
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("swap.out")));
		br.close();
		pw.close();
	}
}
