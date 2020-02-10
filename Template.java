import java.io.*;
import java.util.*;
public class Template {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("template.in"));
		String s = "HELLO";
		String s2 = s;
		s2 = s2.toLowerCase();
		System.out.println(s + " " + s2);
		//PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("template.out")));
		//pw.println();
		//pw.close();
		br.close();
	}
}
