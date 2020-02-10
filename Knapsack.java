import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Knapsack {
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner scan = new Scanner(new File("dryrun.dat"));
		int num = scan.nextInt();
		scan.nextLine();
		int[] weight = new int[num];
		int[] value = new int[num];
		
		scan.close();
	}
	public int val(int id, int remW, int[] weight, int[] value)
	{
		
		if (remW == 0)
			return 0;
		if (id == weight.length)
			return 0;
		if (weight[id] > remW)
			return val(id + 1, remW, weight, value);
		if (weight[id] <= remW)
		{
			return Math.max(val(id + 1, remW, weight, value), val(id + 1, remW - weight[id], weight, value));
		}
		return -1;
	}
}
