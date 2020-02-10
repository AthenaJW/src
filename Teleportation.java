import java.io.*;
import java.util.*;
public class Teleportation {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("template.in"));
		int num = Integer.parseInt(br.readLine());
		ArrayList<int[]> left = new ArrayList<int[]>();
		ArrayList<int[]> right = new ArrayList<int[]>();
		int[] temp;
		String[] temp2;
		for (int i = 0; i < num; i++)
		{
			temp2 = br.readLine().split(" ");
			temp = new int[2];
			temp[0] = Integer.parseInt(temp2[0]);
			temp[1] = Integer.parseInt(temp2[1]);
			if (temp[0] <= 0 || temp[1] <= 0)
				left.add(temp);
			if (temp[0]>=0 || temp[1]>=0)
				right.add(temp);
		}
		int med1 = left.size()/2;
		int med2 = right.size()/2;
		int posleft = left.get(med1)[0];
		int posright = right.get(med2)[1];
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("template.out")));
		pw.println();
		pw.close();
		br.close();
	}
}
