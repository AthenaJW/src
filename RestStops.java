import java.io.*;
import java.util.*;
public class RestStops {
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("reststops.in"));
		String[] temp = br.readLine().split(" ");
		int length = Integer.parseInt(temp[0]);
		int numStops = Integer.parseInt(temp[1]);
		int rJohn = Integer.parseInt(temp[2]);
		int rBessie = Integer.parseInt(temp[3]);
		long relRate = rJohn - rBessie;
		long[][] stops = new long[numStops][2];
		for (int i = 0; i < numStops; i++)
		{
			temp = br.readLine().split(" ");
			stops[i][0] = Integer.parseInt(temp[0]);
			stops[i][1] = Integer.parseInt(temp[1]);
		}
		long[] maxes = new long[numStops];
		long max = 0;
		for (int i = numStops-1; i > -1; i--)
		{
			if (stops[i][1] > max)
				max = stops[i][1];
			maxes[i] = max;
		}
		long tastiness = 0;
		long previousStop = 0;
		for (int i = 0; i < numStops-1; i++)
		{
			if (maxes[i+1] != maxes[i])
			{
				tastiness+= maxes[i] * (stops[i][0] - previousStop)*relRate;
				previousStop = stops[i][0];
			}
		}
		tastiness += maxes[stops.length-1]*(stops[stops.length-1][0] - previousStop)*relRate;
		//System.out.println(tastiness);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("reststops.out")));
		pw.println(tastiness);
		pw.close();
		br.close();
	}
}
