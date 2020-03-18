import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Forest {
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner fin = new Scanner(new File("forest.dat"));
		String[] temp = fin.nextLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int[] heights = new int[N];
		int max = 0;
		for (int i = 1; i <= N; i++)
		{
			heights[i-1] = Integer.parseInt(temp[i]);
			max = Math.max(heights[i-1], max);
		}
		if (max == 0)
			return;
		int[][] isTree = new int[(max+1)*2 + 1][N];
		int boardY = isTree.length;
		for (int i = 0; i < N; i++)
		{
			if (heights[i] == 0)
				continue;
			isTree[boardY-1][i] = 3;
			isTree[boardY-2][i] = 3;
			for (int j = 0; j < heights[i]; j++)
			{
				isTree[boardY-3 - (j*2)][i] = 2;
				isTree[boardY-3 - (j*2 + 1)][i] = 1;
			}
			isTree[boardY-3 - (heights[i]*2)][i] = 4;
		}
		String four = "  /\\   ";
		String one =  " /  \\  ";
		String two =  "/_  _\\ ";
		String three = "  ||   ";
		String zero = "       ";
		for (int[] row: isTree)
		{
			for (int cell:row)
			{
				if (cell == 0)
					System.out.print(zero);
				if (cell == 1)
					System.out.print(one);
				if (cell == 2)
					System.out.print(two);
				if (cell == 3)
					System.out.print(three);
				if (cell == 4)
					System.out.print(four);
			}
			System.out.println();
		}
	}
}
