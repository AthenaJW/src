import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
public class OccurringDifference {
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner fin = new Scanner(new File("occurring_difference.dat"));
		String[] temp;
		int[] nums;
		ArrayList<int[]> occurances; 
		boolean found;
		Comparator<int[]> c = new Comparator<int[]>()
		{
			public int compare(int[] o1, int[] o2)
			{
				return o1[1]-o2[1];
			}
		};
		while(fin.hasNextLine())
		{
			temp = fin.nextLine().split(" ");
			nums = new int[temp.length];
			for (int i = 0; i < temp.length; i++)
			{
				nums[i] = Integer.parseInt(temp[i]);
			}
			occurances = new ArrayList<int[]>();
			for (int el: nums)
			{
				found = false;
				for (int i = 0; i < occurances.size(); i++)
				{
					if (el == occurances.get(i)[0])
					{
						occurances.get(i)[1]++;
						found = true;
						break;
					}
				}
				if (!found)
					occurances.add(new int[]{el, 1});
			}
			
			Collections.sort(occurances, c);
			System.out.println("The difference is " + Math.abs(occurances.get(occurances.size()-1)[0] - occurances.get(0)[0]));
		}
	}
}
