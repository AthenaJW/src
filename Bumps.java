import java.io.*;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Bumps {
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner scan = new Scanner(new File("bumps.dat"));
		HashMap<Character, ArrayList<Character>> AdjList = new HashMap<Character, ArrayList<Character>>();
		HashMap<Character, ArrayList<Integer>> weights = new HashMap<Character, ArrayList<Integer>>();
		HashMap<Character, Boolean> visited = new HashMap<Character, Boolean>();
		
		HashMap<Character, Integer> distances = new HashMap<Character, Integer>();
		HashMap<Character, Character> previous = new HashMap<Character, Character>();
		char end = scan.next().charAt(0); scan.nextLine();
		String temp; char n1, n2; int potholes;
		while (scan.hasNextLine())
		{
			temp = scan.nextLine();
			n1 = temp.charAt(0); n2 = temp.charAt(2);
			potholes = Integer.parseInt(temp.substring(6));
			if (!AdjList.containsKey(n1))
			{
				AdjList.put(n1, new ArrayList<Character>());
				weights.put(n1, new ArrayList<Integer>());
			}
			if (!AdjList.containsKey(n2))
			{
				AdjList.put(n2, new ArrayList<Character>());
				weights.put(n2, new ArrayList<Integer>());
			}
			AdjList.get(n1).add(n2);
			weights.get(n1).add(potholes);
			AdjList.get(n2).add(n1);
			weights.get(n2).add(potholes);
		}
		for (char key: AdjList.keySet())
		{
			visited.put(key, false);
			distances.put(key, Integer.MAX_VALUE);
			previous.put(key, key);
		}
		distances.put('A', 0);
		Comparator<Vertex> c = new Comparator<Vertex>()
		{
			public int compare(Vertex o1, Vertex o2)
			{
				return o1.distance - o2.distance; //ascending by distance
			}
		};
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>(c); 
		pq.add(new Bumps().new Vertex('A', 0));
		Vertex el;
		ArrayList<Character> edges;
		while (!pq.isEmpty())
		{
			el = pq.poll();
			if (el.id == end)
				break;
			edges = AdjList.get(el.id);
			for (int i = 0; i < edges.size(); i++)
			{
				if (el.distance + weights.get(el.id).get(i) < distances.get(edges.get(i)))
				{
					distances.put(edges.get(i), el.distance + weights.get(el.id).get(i));
					previous.put(edges.get(i), el.id);
					pq.add(new Bumps().new Vertex(edges.get(i), distances.get(edges.get(i))));
				}
			}
		}
		String path = "";
		char point = 'I';
		while (point != 'A')
		{
			path = point + path;
			point = previous.get(point);
		}
		System.out.println("A" + path);
	}
	public class Vertex
	{
		public char id;
		public int distance;
		public Vertex(char id, int distance)
		{
			this.id = id;
			this.distance = distance;
		}
	}
}
