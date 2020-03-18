import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AlbumLength {
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner fin = new Scanner(new File("album_length.dat"));
		int N = fin.nextInt(); fin.nextLine();
		String[] temp;
		String time;
		String[] minSecs;
		int min, sec; 
		int totmin = 0;
		int totsec = 0;
		for (int i = 0; i < N; i++)
		{
			temp = fin.nextLine().split(" ");
			time = temp[temp.length - 1];
			minSecs = time.split(":");
			min = Integer.parseInt(minSecs[0]);
			sec = Integer.parseInt(minSecs[1]);
			totmin += min;
			totsec += sec;
		}
		totmin += totsec / 60;
		totsec %= 60;
		System.out.println("The album length is " + totmin + ":" + totsec);
	}
}
