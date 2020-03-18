import java.io.*;
import java.util.*;
/**Tip: when you think there could be integer over/underflow, do anything
 * to keep the numbers as small as possible */
public class CowSteeplechase 
{
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("cowjump.in"));
		int N = Integer.parseInt(br.readLine());
		Segment[] segments = new Segment[N];
		String[] temp;
		for (int i = 0; i < N; i++)
		{
			temp = br.readLine().split(" ");
			segments[i] = new Segment(Integer.parseInt(temp[0]), 
					Integer.parseInt(temp[1]), Integer.parseInt(temp[2]),
					Integer.parseInt(temp[3]));
		}
		Comparator<Event> c = new Comparator<Event>()
		{
			public int compare(Event o1, Event o2)
			{
				return (int)(o1.x-o2.x);
			}
		};
		PriorityQueue<Event> events = new PriorityQueue<Event>(c);
		for (int id = 0; id < segments.length; id++)
		{
			events.add(new Event(segments[id].p.x, id, 0));
			events.add(new Event(segments[id].q.x, id, 1));
		}
		TreeMap<Long, Integer> active = new TreeMap<Long, Integer>();
		//y value to ID
		Event e;
		int id1 = 0, id2 = 0;
		int topseg, botseg;
		while (!events.isEmpty())
		{
			e = events.remove();
			if (e.type == 0)
			{
				if (active.ceilingKey(segments[e.ID].p.y) != null)
				{
					topseg = active.ceilingEntry(segments[e.ID].p.y).getValue();
					if (intersect(segments[topseg], segments[e.ID]))
					{
						id1 = topseg;
						id2 = e.ID;
						break;
					}
				}
				if (active.floorKey(segments[e.ID].p.y) != null)
				{
					botseg = active.floorEntry(segments[e.ID].p.y).getValue();
					if (intersect(segments[botseg], segments[e.ID]))
					{
						id1 = botseg;
						id2 = e.ID;
						break;
					}
				}
				active.put(segments[e.ID].p.y, e.ID);
			}
			else
			{
				active.remove(segments[e.ID].p.y);
				if (active.ceilingKey(segments[e.ID].p.y) != null&&active.floorKey(segments[e.ID].p.y)!=null)
				{
					topseg = active.ceilingEntry(segments[e.ID].p.y).getValue();
					botseg = active.floorEntry(segments[e.ID].p.y).getValue();
					if (intersect(segments[topseg], segments[botseg]))
					{
						id1 = topseg;
						id2 = botseg;
						break;
					}
				}
			}
		}
		//candidates id1 and id2
		boolean is1 = false; boolean is2 = false;
		for (int id = 0; id < N; id++)
		{
			if (id == id1 || id == id2)
				continue;
			if (intersect(segments[id], segments[id1]))
				is1 = true;
			if (intersect(segments[id], segments[id2]))
				is2 = true;
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowjump.out")));
		//System.out.println(segments[id1] + " " + segments[id2]);
		if (!is1 && !is2)
		{
			//System.out.println(Math.min(id1,  id2)+1);
			pw.println(Math.min(id1, id2)+1);
		}
		else if (is1)
		{
			//System.out.println(id1+1);
			pw.println(id1+1);
		}
		else if (is2)
		{
			//System.out.println(id2+1);
			pw.println(id2+1);
		}
		pw.close();
		br.close();
	}
	static class Event
	{
		public long x; 
		public int ID, type; // 0 for left, 1 for right;
		public Event(long x, int ID, int type)
		{
			this.x = x;
			this.ID = ID;
			this.type = type;
		}
		public String toString() {
			return x + " " + ID + " " + type;
		}
	}
	static class Segment
	{
		public Point p, q;
		public Segment(long xstart, long ystart, long xend, long yend)
		{
			if (xstart < xend) {
				p = new Point(xstart, ystart);
				q = new Point(xend, yend);
			}
			else {
				q = new Point(xstart, ystart);
				p = new Point(xend, yend);
			}
		}
		public String toString()
		{
			return "P: " + p.toString() + " Q: " + q.toString();
		}
	}
	static class Point
	{
		public long x, y;
		public Point(long x, long y) 
		{ this.x = x; this.y = y; }
		public String toString()
		{
			return x + " " + y;
		}
	}
	public static int cross(Point p1, Point p2)
	{
		return Long.signum((p1.x*p2.y) - (p1.y*p2.x));//just need the sign els
		//you will overflow the long in the intersect function
	}
	public static boolean intersect(Segment o1, Segment o2)
	{
		Point p1q1 = new Point(o1.q.x-o1.p.x, o1.q.y-o1.p.y);
		Point p1p2 = new Point(o2.p.x-o1.p.x, o2.p.y-o1.p.y);
		Point p1q2 = new Point(o2.q.x-o1.p.x, o2.q.y-o1.p.y);
		Point q2p2 = new Point(o2.p.x-o2.q.x, o2.p.y-o2.q.y);
		Point q2q1 = new Point(o1.q.x-o2.q.x, o1.q.y-o2.q.y);
		Point q2p1 = new Point(-p1q2.x, -p1q2.y);
		return cross(p1q1, p1p2)*cross(p1q1, p1q2)<0 && cross(q2p2, q2q1)*cross(q2p2, q2p1)<0;
	}
}
