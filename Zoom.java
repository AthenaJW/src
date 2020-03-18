import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Zoom {
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner scan = new Scanner(new File("zoom.dat"));
		
		int n = scan.nextInt();
		scan.nextLine();
		int length;
		int[][] output;
		int[][] pixel;
		String[] temp;
		double sum;
		for (int i = 0; i < n; i++)
		{
			length = scan.nextInt(); scan.nextLine();
			pixel = new int[length][length];
			output = new int[length/2][length/2];
			for (int a = 0; a < length; a++)
			{
				temp = scan.nextLine().split(" ");
				for (int b = 0; b < length; b++)
					pixel[a][b] = Integer.parseInt(temp[b]);
			}
			
			for (int a = 0; a < length; a+= 2)
				for (int b = 0; b < length; b += 2)
				{
					sum = 0;
					for (int c = a; c < a+2; c++)
						for (int d = b; d < b+2; d++)
							sum += pixel[c][d];
					output[a/2][b/2] = (int)Math.round(sum/4);
				}
			for (int a = 0; a < output.length; a++)
			{
				for (int b = 0; b < output.length; b++)
					System.out.print(output[a][b] + " ");
				System.out.println();
			}
			System.out.println();
		}
		
		//scan.close();
	}
}
