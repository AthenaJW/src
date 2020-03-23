import java.io.*;
import java.util.*;

public class CowJog {
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("cowjog.in"));
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int T = Integer.parseInt(temp[1]);
		boolean[] early = new boolean[N];
		long[][] segments = new long[N][2];
		Comparator<Event> c = new Comparator<Event>()
		{
			public int compare(Event o1, Event o2)
			{
				return (int) (o1.x-o2.x);
			}
		};
		PriorityQueue<Event> events = new PriorityQueue<Event>(c);
		long x1, x2, v;
		for (int i = 0; i < N; i++)
		{
			temp = br.readLine().split(" ");
			x1 = Long.parseLong(temp[0]);
			v = Long.parseLong(temp[1]);
			x2 = v*T+x1;
			events.add(new Event(x1, i, 0));
			events.add(new Event(x2, i, 1));
			segments[i] = new long[] {x1, x2};
		}
		TreeMap<Long, Integer> before = new TreeMap<Long, Integer>(); // maps y value to endpoint
		Event current;
		int groups = N; long x; int ID, type; long key;
		Set<Long> tail;
		while (!events.isEmpty())
		{
			current = events.remove();
			x = current.x; ID = current.ID; type = current.type;
			//System.out.println(before);
			if (type == 0)
			{
				tail = before.tailMap(segments[ID][1]).keySet();
				for (Iterator<Long> iterator = tail.iterator(); iterator.hasNext();)
				{
					key = iterator.next();
					if (early[before.get(key)])
						continue;
					early[before.get(key)] = true;
					iterator.remove();
					groups--;
				}
				before.put(segments[ID][1], ID);
			}
			else if (!early[ID])
				before.remove(x);
		}
		//System.out.println(groups);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowjog.out")));
		pw.println(groups);
		pw.close();
		br.close();
	}
	static class Event
	{
		public long x; 
		public int ID; 
		public int type; // 0 for left, 1 for right;
		public Event(long x, int ID, int type)
		{
			this.x = x;
			this.ID = ID;
			this.type = type;
		}
		public String toString() 
		{
			return x + " " + ID + " " + type;
		}
	}
}