import java.io.*;
import java.util.Scanner;
public class SleepyCowSorting 
{
	public static void main(String[] args) throws IOException
	{
		Scanner scan = new Scanner(new File("sleepy.in"));
		int num = scan.nextInt();
		scan.nextLine();
		int[] sequence = new int[num];
		for (int i = 0; i<num; i++)
		{
			sequence[i] = scan.nextInt();
		}
		int output = 0;
		//SOLUTION: Get the last relatively out of place numbers. Like, the sequence is supposed to be increasing, so the last pair of numbers that you see are actually 
		//decreasing in order, you know those have to be re inserted, and to get to them - to be able to to re insert them you have to go through 0---> n, so it's just the index of the 
		//last pair of incorrectly ordered numbers. Even if the rest of the "ordered" sequence is not in exact order, cows can be reinserted to achieve the exact order.
		for (int i = num-1; i>0; i--)
		{
			if (sequence[i-1] > sequence[i])
			{
				output = i;
				break;
			}
		}
		PrintWriter bw = new PrintWriter(new BufferedWriter(new FileWriter("sleepy.out")));
		bw.println(output);
		bw.close();
		scan.close();
	}
}
