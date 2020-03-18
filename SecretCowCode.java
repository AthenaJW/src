import java.io.*;

public class SecretCowCode {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("cowcode.in"));
		//generate code
		String[] temp = br.readLine().split(" ");
		String s = temp[0];
		long index = Long.parseLong(temp[1])-1;
		System.out.println(getChar(s, index));
		//PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowcode.out")));
		//pw.println(getChar(s, index));
		//pw.close();
		br.close();
	}
	public static char getChar(String s, long index)
	{
		if (index < s.length())
			return s.charAt((int)index);
		long checkpoint = s.length();
		while (checkpoint < index)
			checkpoint *= 2;
		//two times the largest s*2^n less than index
		index = (checkpoint/2)-(checkpoint-index)-1;
		System.out.println(index);
		return getChar(s, index);
	}
}
