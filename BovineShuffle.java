import java.io.*;
import java.util.*;
public class BovineShuffle {
	public static void main(String[] args) throws IOException, InterruptedException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("shuffle.in"));
		int num = Integer.parseInt(br.readLine());
		String[] temp = br.readLine().split(" ");
		int[] outGoing =  new int[num+1];
		for (int i = 1; i < num+1; i++)
			outGoing[i] = Integer.parseInt(temp[i-1]); 
		boolean[] checked = new boolean[num+1];
		boolean[] path = new boolean[num+1];
		int j;
		int count = 0;
		int pivot;
		boolean isSolution;
		for (int i = 1; i < num + 1; i++)
		{
			if (checked[i])
				continue;
			j = i;
			isSolution = true;
			while(!path[j])
			{
				if (checked[j] && !path[j])
				{
					isSolution = false;
					break;
				}
				path[j] = true;	
				checked[j] = true;
				j = outGoing[j];
			}
			Arrays.fill(path, false);
			if (!isSolution)
				continue;
			pivot = j;
			count++;
			while(outGoing[j]!=pivot)//infinite loop
			{
				count++;
				j = outGoing[j];
				//for (boolean el: path)
				//	System.out.print(el + " ");
				//System.out.println();
			}
		}
		//System.out.println(count);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("shuffle.out")));
		pw.println(count);
		pw.close();
		br.close();
	}
}
