import java.io.*;
import java.util.*;

public class LivestockLineup {
	public static void main(String[] args) throws IOException
	{
		//Scanner scan = new Scanner(System.in);
		Scanner scan = new Scanner(new File("lineup.in"));
		HashMap<String, Integer> groups = new HashMap<String, Integer>();
		ArrayList<ArrayList<String>> cowGroups = new ArrayList<ArrayList<String>>();
		ArrayList<String> names = new ArrayList<String>(
			      Arrays.asList("Beatrice", "Belinda", "Bella", "Bessie", "Betsy", "Blue", "Buttercup", "Sue"));		int num = scan.nextInt();
		scan.nextLine();
		String[] temp;
		String cow1, cow2;
		int index = 0;
		int replace;
		ArrayList<String> temp2, group1, group2;
		for (int i = 0; i < num; i++)
		{
			temp = scan.nextLine().split(" ");
			cow1 = temp[0];
			cow2 = temp[5];
			names.remove(cow1);
			names.remove(cow2);
			if (groups.containsKey(cow1) && groups.containsKey(cow2))
			{
				group1 = cowGroups.get(groups.get(cow1));
				group2 = cowGroups.get(groups.get(cow2));
				if (!cow1.equals(group1.get(group1.size()-1)))
					Collections.reverse(group1);
				if (!cow2.equals(group2.get(0)))
					Collections.reverse(group2);
				if (groups.get(cow2)<groups.get(cow1))
					replace = groups.get(cow1) - 1;
				else
					replace = groups.get(cow1);
				for (String cow: group2)
				{
					group1.add(cow);
				}
				for (String cow: group1)
				{
					groups.put(cow, replace);
				}
				cowGroups.remove(group2);
				if (group1.get(0).compareTo(group1.get(group1.size()-1))>0)
					Collections.reverse(group1);
			}	
			else if (groups.containsKey(cow1))
			{
				groups.put(cow2, groups.get(cow1)); //connecting to group
				temp2 = cowGroups.get(groups.get(cow1)); //alphabetically fixing group
				if (temp2.get(0).equals(cow1)) // if the first one is cow1
				{
					if (cow2.compareTo(temp2.get(temp2.size()-1))<0)
						temp2.add(0, cow2);
					else
					{
						Collections.reverse(temp2);
						temp2.add(temp2.size(), cow2);
					}	
				}
				else
				{
					if (cow2.compareTo(temp2.get(0))<0)
					{
						Collections.reverse(temp2);
						temp2.add(0, cow2);
					}	
					else
					{
						temp2.add(temp2.size(), cow2);
					}
				}
			}	
			else if (groups.containsKey(cow2))
			{
				groups.put(cow1, groups.get(cow2)); //connecting to group
				temp2 = cowGroups.get(groups.get(cow2)); //alphabetically fixing group
				if (temp2.get(0).equals(cow2)) // if the first one is cow1
				{
					if (cow1.compareTo(temp2.get(temp2.size()-1))<0)
						temp2.add(0, cow1);
					else
					{
						Collections.reverse(temp2);
						temp2.add(temp2.size(), cow1);
					}	
				}
				else
				{
					if (cow1.compareTo(temp2.get(0))<0)
					{
						Collections.reverse(temp2);
						temp2.add(0, cow1);
					}	
					else
					{
						temp2.add(temp2.size(), cow1);
					}
				}
			}
			else
			{
				groups.put(cow1, index);
				groups.put(cow2, index);
				index++;
				if (cow1.compareTo(cow2)<0)
					cowGroups.add(new ArrayList<String>(Arrays.asList(cow1, cow2)));
				else
					cowGroups.add(new ArrayList<String>(Arrays.asList(cow2, cow1)));
			}
			//System.out.println(cowGroups);
			//System.out.println(groups);
		}
		String temp3 = "";
		for (int i = 0; i < cowGroups.size(); i++) {
		    temp2 = cowGroups.get(i);
		    for (int j = 0; j < temp2.size(); j++)
		    {
		    	if (j==temp2.size()-1)
		    		temp3 += temp2.get(j);
		    	else
		    		temp3 += temp2.get(j) + "\n";
		    }
		    names.add(temp3);
		    temp3 = "";
		}
		Collections.sort(names);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lineup.out")));
		for (String name: names)
		{
			//System.out.println(name);
			pw.println(name);
		}
		pw.close();
		scan.close();
	}
}
