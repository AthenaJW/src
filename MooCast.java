import java.io.*;
import java.util.*;
public class MooCast {
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("moocast.in"));
		double tol = 0.0001;
		int num = Integer.parseInt(br.readLine());
		int[][] moocast = new int[num][3];
		String[] temp;
		ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < num; i++)
		{
			temp = br.readLine().split(" ");
			moocast[i][0] = Integer.parseInt(temp[0]);
			moocast[i][1] = Integer.parseInt(temp[1]);
			moocast[i][2] = Integer.parseInt(temp[2]);
			edges.add(new ArrayList<Integer>());
		}
		double distance;
		for (int i = 0; i < num; i++)
		{
			for (int j = 0; j < num; j++)
			{
				if (i == j)
					continue;
				distance = Math.sqrt(Math.pow(moocast[i][0] - moocast[j][0], 2)+Math.pow(moocast[i][1] - moocast[j][1], 2));
				if (distance < moocast[i][2] || Math.abs(distance-moocast[i][2]) < tol)
					edges.get(i).add(j);
			}
		}
		boolean[] beenThere;
		Stack<Integer> toVisit = new Stack<Integer>();
		int current;
		ArrayList<Integer> currentEdges;
		int max = 0;
		int total;
		for (int i = 0; i < num; i++)
		{
			beenThere = new boolean[num];
			System.out.println("Pushing: " + i);
			toVisit.push(i);
			beenThere[i] = true;
			total = 0;
			while (!toVisit.empty())
			{
				current = toVisit.pop();
				total++;
				currentEdges = edges.get(current);
				for (int e: currentEdges)
				{
					if (!beenThere[e])
					{
						toVisit.push(e);
						beenThere[e] = true;
					}
				}
			}
			System.out.println(total);
			if (total > max)
				max = total;
		}
		//System.out.println(max);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("moocast.out")));
		pw.println(max);
		pw.close();
		br.close();
	}
}
