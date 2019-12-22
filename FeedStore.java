import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class FeedStore {
	public static void main(String[] args)
	{
		Scanner fin = null;
		try 
		{
			fin = new Scanner(new File("FeedStore.dat"));
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		int load = fin.nextInt();
		fin.nextLine();
		HashMap<Character, Integer> storeToFeed = new HashMap<Character, Integer>();
		HashMap<Character, ArrayList<Character>> graph = new HashMap<Character, ArrayList<Character>>();
		HashMap<Character, ArrayList<Integer>> weights = new HashMap<Character, ArrayList<Integer>>();
		HashMap<Character, Character> previous = new HashMap<Character, Character>();
		HashMap<Character, Integer> distance = new HashMap<Character, Integer>();
		HashMap<Character, Integer> feedLeft = new HashMap<Character, Integer>();
		ArrayList<Character> visited = new ArrayList<Character>();
		ArrayList<Character> tovisit = new ArrayList<Character>();
		graph.put('A', new ArrayList<Character>());
		weights.put('A', new ArrayList<Integer>());
		distance.put('A', 0);
		feedLeft.put('A',  load);
		tovisit.add('A');
		storeToFeed.put('A', 0);
		String[] feedNeed = fin.nextLine().split(",");
		for (String store: feedNeed)
		{
			storeToFeed.put(store.charAt(0), Integer.parseInt(store.substring(1)));
			graph.put(store.charAt(0), new ArrayList<Character>());
			weights.put(store.charAt(0), new ArrayList<Integer>());
			previous.put(store.charAt(0), 'A');
			distance.put(store.charAt(0), 100);
			feedLeft.put(store.charAt(0), 0);
			tovisit.add(store.charAt(0));
		}
		String temp;
		char key, valueStore;
		int weight;
		while (fin.hasNextLine())
		{
			temp = fin.nextLine();
			key = temp.charAt(0);
			valueStore = temp.charAt(2);
			weight = Integer.parseInt(temp.substring(4));
			graph.get(key).add(valueStore);
			weights.get(key).add(weight);
			if (key == 'A')
			{
				graph.get(valueStore).add('X');
				weights.get(valueStore).add(weight);
			}
			graph.get(valueStore).add(key);
			weights.get(valueStore).add(weight);
		}
		
		//adding fake store X
		graph.put('X', graph.get('A'));
		weights.put('X', weights.get('A'));
		distance.put('X',  100);
		feedLeft.put('X',  load);
		tovisit.add('X');
		storeToFeed.put('X', 0);
		System.out.println(weights);
		System.out.println(graph);
		Character current = 'A';
		ArrayList<Character> visits = null;
		int candidate;
		int min;
		boolean end = true;
		while (true)
		{
			visits = graph.get(current);
			System.out.println(visits);
			System.out.println(feedLeft);
			if (visits.indexOf('X') != -1)
			{
				end = true;
				System.out.println(":)");
				for (char store: visits)
				{
					if(feedLeft.get(current) - storeToFeed.get(store) >= 0 && store != 'X' && store != previous.get(current) && store != 'A')
						end = false;
				}
				if (end)
				{
					distance.put('X', distance.get(current) + weights.get(current).get(visits.indexOf('A')));
					previous.put('X', current);
					break;
				}
			}
			for (int i = 0; i < visits.size(); i++)
			{
				candidate = distance.get(current) + weights.get(current).get(i);
				if (candidate <= distance.get(visits.get(i)) && previous.get(current) != visits.get(i))
				{
					distance.put(visits.get(i), candidate);
					feedLeft.put(visits.get(i), feedLeft.get(current) - storeToFeed.get(visits.get(i)));
					if (feedLeft.get(visits.get(i)) >= 0)
					{
						previous.put(visits.get(i), current);
					}
				}	
			}
			visited.add(current);
			tovisit.remove(current);
			min = 100;
			for (char c: tovisit)
			{
				if (distance.get(c) < min)
				{
					min = distance.get(c);
					current = c;
				}
			}
		}
		Character point = 'X';
		String path = "A";
		while (true)
		{
			point = previous.get(point);
			path = point + path;
			if (point == 'A')
			{
				break;
			}
		}
		
		System.out.println(path + " " + distance.get('A'));
	}
	
}
