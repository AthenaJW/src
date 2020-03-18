import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Phobos {
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner scan = new Scanner(new File("phobos.dat"));
		scan.nextLine();
		while(scan.hasNextLine())
		{
			String s = scan.nextLine();
			char[]list = new char[s.length()];
			for (int i = s.length()-1; i>=0; i--)
			{
				list[s.length()-1-i] = s.charAt(i);
			}
			int count0 = 0;
			int count1 = 0;
			for(int i = 0;i<list.length;i++)
			{
				if(list[i] == 'A')
					count0++;
				if(list[i] == 'E')
					count0++;
				if(list[i] == 'I')
					count0++;
				if(list[i] == 'O')
					count0++;
				if(list[i] == 'U')
					count0++;
				if(list[i] == ' ')
					count1++;
			}
			if(2*count0<list.length-count1)
			{
				for(int i = 0; i<list.length; i++)
				{
					if(list[i] == 'A')
						list[i] = 'E';
					else if(list[i] == 'E')
						list[i] = 'I';
					else if(list[i] == 'I')
						list[i] = 'O';
					else if(list[i] == 'O')
						list[i] = 'U';
					else if(list[i] == 'U')
						list[i] = 'A';
					else if(list[i] == 'B')
						list[i] = 'Z';
					else if(list[i] == 'F')
						list[i] = 'D';
					else if(list[i] == 'J')
						list[i] = 'H';
					else if(list[i] == 'P')
						list[i] = 'N';
					else if(list[i] == 'V')
						list[i] = 'T';
					else if(list[i] == ' ');
					else
						list[i]--;
				}
			}
			else
			{
				for(int i = 0; i<list.length; i++)
				{
					if(list[i] == 'A')
						list[i] = 'U';
					else if(list[i] == 'E')
						list[i] = 'A';
					else if(list[i] == 'I')
						list[i] = 'E';
					else if(list[i] == 'O')
						list[i] = 'I';
					else if(list[i] == 'U')
						list[i] = 'O';
					else if(list[i] == 'Z')
						list[i] = 'B';
					else if(list[i] == 'D')
						list[i] = 'F';
					else if(list[i] == 'H')
						list[i] = 'J';
					else if(list[i] == 'N')
						list[i] = 'P';
					else if(list[i] == 'T')
						list[i] = 'V';
					else if(list[i] == ' ');
					else
						list[i]++;
				}
			}
			
			for(int i = 0; i<list.length; i++)
			{
				System.out.print(list[i]);
			}
			System.out.println();
			
			
			
			
			
		}
	}
}
