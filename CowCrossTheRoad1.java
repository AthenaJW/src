import java.io.*;
import java.util.*;

public class CowCrossTheRoad1 {
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("helpcross.in"));
		String[] temp = br.readLine().split(" ");
		int numchickens = Integer.parseInt(temp[0]);
		int numcows = Integer.parseInt(temp[1]);
		Comparator<Event> c = new Comparator<Event>()
		{
			public int compare(Event o1, Event o2)
			{
				return o1.start-o2.start;
			}
		};
		PriorityQueue<Event> events = new PriorityQueue<Event>(c);
		for (int i = 0; i < numchickens; i++)
			events.add(new CowCrossTheRoad1().new Event(Integer.parseInt(br.readLine()), -1, 2));
		Interval current;
		Interval[] intervals = new Interval[numcows];
		int start, end;
		for (int i = 0; i < numcows; i++)
		{
			temp = br.readLine().split(" ");
			start = Integer.parseInt(temp[0]);
			end = Integer.parseInt(temp[1]);
			current = new CowCrossTheRoad1().new Interval(start, end, i);
			intervals[i] = current;
			events.add(new CowCrossTheRoad1().new Event(start, i, 0));
			events.add(new CowCrossTheRoad1().new Event(end, i, 1));
		}
		Comparator<Interval> c2 = new Comparator<Interval>()
		{
			public int compare(Interval o1, Interval o2)
			{
				return o1.end-o2.end; // which one ends first
			}
		};
		PriorityQueue<Interval> inUse = new PriorityQueue<Interval>(c2);
		Event e;
		int count = 0;
		boolean[] earlyRemoval = new boolean[numcows];
		while (!events.isEmpty())
		{
			e = events.remove();
			if (e.type == 0)
				inUse.add(intervals[e.ID]);
			if (e.type == 1 && !earlyRemoval[e.ID])
				inUse.remove();
			if (e.type == 2)
			{
				if (!inUse.isEmpty())//if there is a cow available waiting for chicken
				{
					current = inUse.remove();
					earlyRemoval[current.ID] = true;
					count++;
				}
			}
		}
		//System.out.println(count);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("helpcross.out")));
		pw.println(count);
		pw.close();
		br.close();
	}
	public class Interval
	{
		int start;
		int end;
		int ID;
		public Interval() {}
		public Interval(int start, int end, int ID)
		{
			this.start = start;
			this.end = end;
			this.ID = ID;
		}
	}
	public class Event
	{
		int start;
		int ID;
		int type;
		public Event() {}
		public Event(int start, int ID, int type)
		{
			this.start = start;
			this.ID = ID;
			this.type = type;
		}
	}
}
