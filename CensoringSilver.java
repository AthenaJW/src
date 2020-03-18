import java.io.*;
import java.util.*;

public class CensoringSilver {
	public static void main(String[] args) throws IOException, InterruptedException
	{
		BufferedReader br = new BufferedReader(new FileReader("4.in"));
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String output = br.readLine();
		char[] censor = br.readLine().toCharArray();
		int[] b = new int[censor.length+1];			// back table where we have all the reset j values, i.e. if you mess up at position i, b[i] is the j you should now check
		int[] reset = new int[output.length()+1]; 	// reset table serves as the back table for the entire string. if you arrive at position i, reset[i] is the j you should assume to check
		int j = -1; 								//j is -1 or else the algorithm will find the string is equal to itself. obviously
		int i = 0;
		b[0] = -1;
		//preprocessing
		while (i < censor.length)
		{
			while (j >= 0 && censor[i] != censor[j]) //while different, reset j to it's back table value. this is not zero if the back table value is also a prefix of the whole string
				j = b[j];
			i++; j++; 								//advance the pointers if they are the same
			b[i] = j; 								//save the back table value 
		}
		
		i = 0; j = 0; //actually doing KMP
		while (i < output.length())
		{
			while (j >= 0 && output.charAt(i) != censor[j]) //while the values are different, reset it to the back table value.
				j = b[j];
			i++; j++; 										//else advance the pointers
			reset[i] = j; 									//save the current value of j into the back table
			if (j == censor.length) 						//if j has advanced to the end
			{	
				output = output.substring(0, i - censor.length) + output.substring(i); //remove the instance of the string
				i -= censor.length; 						//move i back to the last character we have reset information on
				if (i < 0) i = 0; 							//just in case i goes negative
				j = reset[i]; 								//reset j to the reset value at i
			}
		}

		//PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("censor.out")));
		//pw.println(output);
		//pw.close();
		System.out.println(output);
		br.close();
	}
}