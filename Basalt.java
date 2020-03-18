import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Basalt {
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner scan = new Scanner(new File("basalt.dat"));
		ArrayList<Double> arr = new ArrayList<Double>();
		int n = scan.nextInt();
		for(int i = 0 ; i < n; i++) {
			arr.add(scan.nextDouble());
		}
		Collections.sort(arr);
		System.out.println(arr.get(0));
		System.out.println(arr.get(arr.size()-1));
	}
}
