import java.io.*;
import java.util.*;
public class Stampede {
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("stampede.in"));
		int N = Integer.parseInt(br.readLine());
		Comparator<Event> c = new Comparator<Event>()
		{
			public int compare(Event o1, Event o2)
			{
				return (int) (o1.t-o2.t);//yval, start time, end time
			}
		};
		PriorityQueue<Event> events = new PriorityQueue<Event>(c);
		String[] temp; long x, y, v, t1, t2;
		for (int i = 0; i < N; i++)
		{
			temp = br.readLine().split(" ");
			x = Integer.parseInt(temp[0]);
			y = Integer.parseInt(temp[1]);
			v = Integer.parseInt(temp[2]);
			x *= -v;
			t1 = x-v; t2 = x;
			events.add(new Event(t1, y, 0));
			events.add(new Event(t2, y, 1));
		}
		TreeMap<Long, Long> before = new TreeMap<Long, Long>(); // maps y value to endpoint
		Event current; int type;
		long curtime;
		HashSet<Long> seen = new HashSet<Long>();
		while (!events.isEmpty())
		{
			curtime = events.peek().t;
			while (!events.isEmpty() && events.peek().t == curtime)
			{
				current = events.remove();
				y = current.y; t1 = current.t; type = current.type; curtime = t1;
				if (type == 0)
					before.put(y, t1);
				else
					before.remove(y);
			}
			if (!before.isEmpty())
				seen.add(before.firstKey()); //each cow has unique y value. 
			//# of different min y values gives us different cows we've seen
			//just counting when the min changes risks double counting cows
			//HashSet O(1) check
		}
		//System.out.println(seen.size());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("stampede.out")));
		pw.println(seen.size());
		pw.close();
		br.close();
	}
	static class Event
	{
		public long t, y; 
		public int type; // 0 for left, 1 for right;
		public Event(long t, long y, int type)
		{
			this.t = t;
			this.y = y;
			this.type = type;
		}
		public String toString() {
			return t + " " + y + " " + type;
		}
	}
}
