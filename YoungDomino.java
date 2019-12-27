import java.io.*;

public class YoungDomino {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		String[] temp = br.readLine().split(" ");
		long black = 0;
		long white = 0;
		int column;
		for (int i = 0; i < num; i++)
		{
			column = Integer.parseInt(temp[i]);
			black += column/2;
			white += column/2;
			if (column%2 == 1)
			{
				if (i%2 == 0)
					black++;
				else
					white++;
			}
		}
		System.out.println(Math.min(black, white));
	}
}
