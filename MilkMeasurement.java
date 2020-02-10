import java.io.*;
import java.util.*;

public class MilkMeasurement {
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("measurement.in"));
		String[] temp;
		temp = br.readLine().split(" ");
		int numDays = Integer.parseInt(temp[0]);
		int initial = Integer.parseInt(temp[1]);
		TreeMap<Integer, Integer> cowMilk = new TreeMap<Integer, Integer>();
		HashMap<Integer, Integer> idToMilk = new HashMap<Integer, Integer>();
		idToMilk.put(0, initial);
		cowMilk.put(initial, numDays+2);
		int[][] measurements = new int[numDays][3];
		for (int i = 0; i < numDays; i++)
		{
			temp = br.readLine().split(" ");
			measurements[i][0] = Integer.parseInt(temp[0]);
			measurements[i][1] = Integer.parseInt(temp[1]);
			measurements[i][2] = Integer.parseInt(temp[2]);
		}
		Comparator<int[]> c = new Comparator<int[]>()
		{
			public int compare(int[] o1, int[] o2)
			{
				return o1[0] - o2[0];
			}
		};
		Arrays.sort(measurements, c);
		int id, change, before, after;
		int maxBefore, maxAfter;
		int num;
		int q;
		maxBefore = initial;
		int count = 0;
		for (int i = 0; i < measurements.length; i++)
		{
			id = measurements[i][1];
			change = measurements[i][2];
			if (!idToMilk.containsKey(id))
			{
				idToMilk.put(id, initial);
			}
			before = idToMilk.get(id);
			after = before + change;
			maxBefore = cowMilk.lastKey();
			num = cowMilk.get(maxBefore);
			idToMilk.put(id, after); //update milk of cow #
			q = cowMilk.get(before);
			if (q==1)
				cowMilk.remove(before);
			else
				cowMilk.put(before, --q);
			if (cowMilk.containsKey(after))
				cowMilk.put(after, cowMilk.get(after)+1);
			else
				cowMilk.put(after, 1);
			if (num != cowMilk.get(cowMilk.lastKey()))
			{
				count++;
				continue;
			} // if max changed the display obviously changed
			else
			{
				maxAfter = cowMilk.lastKey();
				if (maxBefore>maxAfter && after < maxAfter)
				{
					//if there is same max before and after, only possible if 
					//max before and after are one cow
					//check that they are not the same cow
					//if max decreases, if the current cow is less than new mad
					//it's not the same cow on display
					count++;
					continue;
				}
				if (maxAfter > maxBefore && before < maxBefore)
					count++;
				//if max rose, and the cow before was less than max before
				//the past max cow is not the same as the current max cow
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("measurement.out")));
		pw.println(count);
		pw.close();
		br.close();
	}
}
