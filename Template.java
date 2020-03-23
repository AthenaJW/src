import java.io.*;
import java.util.*;
 
public class Template {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("swap.in"));
		int N = Integer.parseInt(br.readLine());
		String[] temp; long n, k;
		for (int i = 0; i < N; i++)
		{
		    temp = br.readLine().split(" ");
		    n = Long.parseLong(temp[0]);
		    k = Long.parseLong(temp[1]);
		    if (n%2 - k%2 == 0)
		    {
		        n -= (k*k);
		        if (n >= 0)
		        	System.out.println("YES");
		        else
		        	System.out.println("NO");
		    }
		    else
		        System.out.println("NO");
		}
		br.close();
	}
}