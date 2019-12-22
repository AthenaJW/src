import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BeautifulString {
	public static void main(String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int num;
		num = Integer.parseInt(reader.readLine());
		String input;
		String output;
		char previous = 'x';
		char current;
		char cand;
		for (int i = 0; i<num; i++)
		{
			output = "";
			previous = 'x';
			input = reader.readLine();
			for (int j = 0; j<input.length(); j++)
			{
				current = input.charAt(j);
				if (current == previous)
				{
					output = "-1";
					break;
				}
				if (current == '?')
				{
					cand = (char)(97 + (previous-97+1)%3);	
					if (j < input.length()-1 && input.charAt(j+1) != '?')
					{
						if (input.charAt(j+1) == cand)
							cand = (char)(97 + (cand-97+1)%3);	 
					}
					output = output + cand;
				}
				else
					output = output + current;
				previous = output.charAt(j);
			}
			System.out.println(output);
		}
		
	}
}
