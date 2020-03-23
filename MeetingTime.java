import java.io.*;
import java.util.*;
public class MeetingTime {
	static boolean[][] bessie;
	static boolean[][] elsie;
	static ArrayList<ArrayList<Integer>> Adjbess;
	static ArrayList<ArrayList<Integer>> Adjels;
	static ArrayList<ArrayList<Integer>> weightb;
	static ArrayList<ArrayList<Integer>> weighte;
	static int N;
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("meeting.in"));
		String[] temp = br.readLine().split(" ");
		N = Integer.parseInt(temp[0]);
		int M = Integer.parseInt(temp[1]);
		Adjbess = new ArrayList<ArrayList<Integer>>();
		Adjels = new ArrayList<ArrayList<Integer>>();
		weightb = new ArrayList<ArrayList<Integer>>();
		weighte = new ArrayList<ArrayList<Integer>>();
		bessie = new boolean[N][M*100];
		elsie = new boolean[N][M*100];
		for (int i = 0; i < N; i++)
		{
			Adjbess.add(new ArrayList<Integer>());
			Adjels.add(new ArrayList<Integer>());
			weightb.add(new ArrayList<Integer>());
			weighte.add(new ArrayList<Integer>());
		}
		int node1, node2, weight1, weight2;
		for (int i = 0; i < M; i++)
		{
			temp = br.readLine().split(" ");
			node1 = Integer.parseInt(temp[0])-1;
			node2 = Integer.parseInt(temp[1])-1;
			weight1 = Integer.parseInt(temp[2]);
			weight2 = Integer.parseInt(temp[3]);
			Adjbess.get(node1).add(node2);
			weightb.get(node1).add(weight1);
			Adjels.get(node1).add(node2);
			weighte.get(node1).add(weight2);
		}
		bessiestates(0, 0);
		elsiestates(0, 0);
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("meeting.out")));
		boolean found = false;
		for (int i = 0; i < bessie[0].length; i++)
		{
			if (bessie[N-1][i] && elsie[N-1][i])
			{
				//System.out.println(i);
				found = true;
				pw.println(i);
				break;
			}
		}
		if (!found)
			pw.println("IMPOSSIBLE");

		pw.close();
		br.close();
	}
	public static void bessiestates(int barn, int time)
	{
		if (bessie[barn][time])
			return;
		bessie[barn][time] = true;
		if (barn == N-1)
			return;
		for (int i = 0; i < Adjbess.get(barn).size(); i++)
			bessiestates(Adjbess.get(barn).get(i), time + weightb.get(barn).get(i));
	}
	public static void elsiestates(int barn, int time)
	{
		if (elsie[barn][time])
			return;
		elsie[barn][time] = true;
		if (barn == N-1)
			return;
		for (int i = 0; i < Adjels.get(barn).size(); i++)
			elsiestates(Adjels.get(barn).get(i), time + weighte.get(barn).get(i));
	}
}
