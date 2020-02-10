import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Inscribe {
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner fin = new Scanner(new File("inscribe.dat"));
		int num;
		int letters;
		char[][] matrix;
		while(fin.hasNextLine())
		{
			num = Integer.parseInt(fin.nextLine());
			letters = num/2 + 1;
			matrix = new char[num][num];
			for (int i = 0; i < letters; i++)
			{
				for (int j = i; j < num-i; j++)
				{
					for (int k = i; k < num-i; k++)
					{
						matrix[j][k] = (char)(64 + letters - i);
					}
				}
			}
			for (char[] row: matrix)
			{
				for (char el: row)
					System.out.print(el);
				System.out.println();
			}
			System.out.println();
		}
		
	}
}
