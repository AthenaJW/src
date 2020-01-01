import java.util.*;
//how many ways can you place 8 queens on a board without them attacking each other
//recursive backtracking: build the solution incrementally, when it doesn't work, go back to the last point it was right
//it allows you to cut off all the other possibilities down that branch if you cut the bud off early. saves you iterations
public class EightQueens {
	public static void main(String[] args) throws InterruptedException
	{
		Stack<int[]> checkpoints = new Stack<int[]>();
		int[] temp;
		for (int i = 0; i < 8; i ++)
		{
			temp = new int[2];
			temp[0] = i; //coord[0] ---> row
			temp[1] = 0; //coord[1] ---> col
			checkpoints.add(temp);
		}
		int[] currentSol = new int[8];
		int row, col;
		boolean isSolution;
		int count = 0;
		while (!checkpoints.isEmpty())
		{
			temp = checkpoints.pop();
			row = temp[0];
			col = temp[1];
			isSolution = true;
			for (int i = 0; i < col; i++)
			{
				if (currentSol[i] == row || Math.abs(currentSol[i] - row) == Math.abs(i - col))
				{
					isSolution = false;
					break;
				}
			}
			if (isSolution)
			{
				if (col == 7)
					count++;
				else
				{
					for (int i = 0; i < 8; i ++)
					{
						temp = new int[2];
						temp[0] = i; //coord[0] ---> row
						temp[1] = col + 1; //coord[1] ---> col
						checkpoints.add(temp);
					}
					currentSol[col] = row;
				}
			}
		}
		System.out.println(count);
	}
}
