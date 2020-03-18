import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Stairs {
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner scan = new Scanner(new File("stairs.dat"));
		
		int n = scan.nextInt();
		scan.nextLine();
		String[] temp;
		int size;
		char align;
		char material;
		String s;
		for (int i = 0; i < n; i++)
		{
			temp = scan.nextLine().split(" ");
			size = Integer.parseInt(temp[0]);
			align = temp[1].charAt(0);
			material = temp[2].charAt(0);
			for (int a = 0; a < size; a++)
			{
				s = "";
				for (int b = 0; b  < size; b++)
				{
					if (align == 'R')
					{
						if (b <= a)
							s = material + s;
						else
							s = " " + s;
					}
					else
					{
						if (b <= a)
							s = s + material;
						else
							s = s + " "; 
					}
				}
				System.out.println(s);
			}
		}
		
		//scan.close();
	}
}
