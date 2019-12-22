import java.io.*;
import java.util.*;

public class Convention {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader("convention.in"));
		String[] temp;
		temp = br.readLine().split(" ");
		int num_cows = Integer.parseInt(temp[0]);
		int num_bus = Integer.parseInt(temp[1]);
		int capacity = Integer.parseInt(temp[2]);
		temp = br.readLine().split(" ");
		ArrayList<Integer> cows = new ArrayList<Integer>();
		for (int i = 0; i<num_cows; i++)
			cows.add(Integer.parseInt(temp[i]));
		int minIndex;
		int temp2;
		Collections.sort(cows);
		int output = binarySearch(0, 1000000000, cows, num_bus, capacity) + 1;
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("convention.out")));
		pw.println(output);
		pw.close();
		br.close();
	}
	public static int binarySearch(int start, int end, ArrayList<Integer> cows, int num_bus, int capacity)
	{
		int point = 0;
		int mid = (start+end)/2;
		int count = 1;
		if (start == mid)
			return start;
		for (int i = 0; i < cows.size(); i++)
		{
			if (cows.get(i) - cows.get(point) > mid || (i-point) == capacity)
			{
				count++;
				point = i;
			}
		}
		if (count > num_bus)
			return binarySearch(mid, end, cows, num_bus, capacity);
		else
			return binarySearch(start, mid, cows, num_bus, capacity);
	}
}
