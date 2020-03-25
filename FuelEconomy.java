import java.io.*;
import java.util.*;

public class FuelEconomy {
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("fuel.in"));
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int G = Integer.parseInt(temp[1]);
		int B = Integer.parseInt(temp[2]);
		int D = Integer.parseInt(temp[3]);
		long start, end; int price;
		
		Comparator<Event> c = new Comparator<Event>()
		{
			public int compare(Event o1, Event o2)
			{
				return (int) (o1.x-o2.x);
			}
		};
		PriorityQueue<Event> events = new PriorityQueue<Event>(c);
		long[][] segments = new long[N+2][2];
		for (int i = 1; i < N+1; i++)
		{
			temp = br.readLine().split(" ");
			start = Integer.parseInt(temp[0]);
			price = Integer.parseInt(temp[1]);
			end = Math.min(start + G, D);
			segments[i] = new long[] {start, end};
			events.add(new Event(start, price, 0, i));
			events.add(new Event(end, price, 1, i));
		}
		segments[0] = new long[] {0, B};
		segments[N+1] = new long[] {D, D+1};
		events.add(new Event(0, 0, 0, 0));
		events.add(new Event(B, 0, 1, 0));
		events.add(new Event(D, 0, 0, N+1));
		events.add(new Event(D+1, 0, 1, N+1)); //extra segment for edge cases
		
		TreeMap<Integer, Integer> active = new TreeMap<Integer, Integer>(); //sort by price point;
		Event current;
		long x; int type;
		int lastmin = 0; long minstart = 0; int minID = 0; int ID;
		ArrayList<long[]> solution = new ArrayList<long[]>();
		while (!events.isEmpty())
		{
			current = events.remove();
			x = current.x; price = current.price; type = current.type;
			ID = current.ID;
			if (type == 0)
				active.put(price, ID);
			else
				active.remove(price);
			if (!active.isEmpty() && active.firstEntry().getValue() != minID)
			{
				solution.add(new long[] {minstart, Math.min(segments[minID][1], x), lastmin});
				lastmin = active.firstEntry().getKey();
				minstart = x;
				minID = active.firstEntry().getValue();
			}
		}
		long ans = 0;
		long lastend = 0;
		for (long[] segment: solution)
		{
			if (segment[0] != lastend)
			{
				ans = -1;
				break;
			}
			ans += (segment[1]-segment[0])*segment[2];
			lastend = segment[1];
		}
		//System.out.println(ans);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("fuel.out")));
		pw.println(ans);
		pw.close();
		br.close();
	}
	static class Event
	{
		public long x; 
		public int price;
		public int type;
		public int ID;
		public Event(long x, int price, int type, int ID)
		{
			this.x = x;
			this.price = price;
			this.type = type;
			this.ID = ID;
		}
		public String toString() 
		{
			return x + " " + price + " " + type + " " + ID;
		}
	}
}
