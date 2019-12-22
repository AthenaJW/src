import java.io.*;
import java.util.*;

public class Convention2 {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader("convention2.in"));
		int num = Integer.parseInt(br.readLine());
		Comparator<Cow> compareByArrival = new Comparator<Cow>()
		{
			//negative if this object is less
			//zero if equal
			//positive if greater
			public int compare(Cow c1, Cow c2)
			{
				return c1.getArrivalTime() - c2.getArrivalTime();
			}
		};
		PriorityQueue<Cow> cowTimes = new PriorityQueue<Cow>(num, compareByArrival);
		String[] temp;
		for (int i = 0; i < num; i++)
		{
			temp = br.readLine().split(" ");
			cowTimes.add(new Convention2().new Cow(i, Integer.parseInt(temp[0]), Integer.parseInt(temp[1])));
		}
		Comparator<Cow> compareByPriority = new Comparator<Cow>()
		{
			public int compare(Cow c1, Cow c2)
			{
				return c1.getPriority() - c2.getPriority();
			}
		};
		PriorityQueue<Cow> waitingQueue = new PriorityQueue<Cow>(1, compareByPriority);
		int timestamp = 0;
		Cow outCow;
		int max = 0;
		while (!cowTimes.isEmpty() || !waitingQueue.isEmpty())
		{
			while (cowTimes.peek()!=null)
			{
				if (cowTimes.peek().getArrivalTime() <= timestamp)
				{
					waitingQueue.add(cowTimes.poll());
				}
				else
					break;
			}
			if (!waitingQueue.isEmpty())
			{
				outCow = waitingQueue.poll();
				if (timestamp - outCow.getArrivalTime() > max)
					max = timestamp - outCow.getArrivalTime();
				timestamp = timestamp + outCow.getDuration();
			}
			else
			{
				if (cowTimes.isEmpty())
					break;
				timestamp = cowTimes.peek().getArrivalTime();	
			}
			//System.out.println(cowTimes);
			//System.out.println(waitingQueue);
			//System.out.println(timestamp);
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("convention2.out")));
		pw.println(max);
		pw.close();
		
		//System.out.println(max);
		br.close();
	}
	public class Cow
	{
		private int priority;
		private int time_arrive;
		private int duration;
		public Cow(int p, int a, int t)
		{
			priority = p;
			time_arrive = a;
			duration = t;
		}
		public int getPriority()
		{
			return priority;
		}
		public int getArrivalTime()
		{
			return time_arrive;
		}
		public int getDuration()
		{
			return duration;
		}
		//for debugging
		public String toString()
		{
			return "\nPriority: "+ priority + "\nArrival: " + time_arrive + "\nDuration: " + duration;
		}
	}
}
