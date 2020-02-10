import java.io.*;
import java.util.*;
public class CitiesAndStates {
	public static void main(String[] args) throws IOException
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("citystate.in"));
		HashMap<String, Integer> names = new HashMap<String, Integer>();
		int num = Integer.parseInt(br.readLine());
		String[] temp;
		String key;
		for (int i = 0; i < num; i++)
		{
			temp = br.readLine().split(" ");
			key = temp[0].substring(0, 2) + temp[1]; //smart key value for hashmap
			if (names.containsKey(key))
				names.put(key, names.get(key) + 1);
			else
				names.put(key, 1);
		}
		System.out.println(names);
		ArrayList<String> keys = new ArrayList<String>(names.keySet());
		int total = 0;
		for (String k: keys)
		{
			if (names.containsKey(k.substring(2)+k.substring(0, 2)) && (k.substring(2)+k.substring(0, 2)).compareTo(k)!= 0)
			{
				total += names.get(k) * names.get(k.substring(2) + k.substring(0, 2));
			}
		}
		//System.out.println(total/2);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("citystate.out")));
		pw.println(total/2);
		pw.close();
		br.close();
	}
}
