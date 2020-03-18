import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MostValue {
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner fin = new Scanner(new File("most_value.dat"));
		while(fin.hasNextLine()) {
			String[] s = fin.nextLine().split(" ");
			int[] arr = new int[s.length];
			for(int i=0;i<arr.length;i++) {
				arr[i] = Integer.parseInt(s[i]);
			}
			int sum = 0;
			int num = 0;
			int count = 0;
			for(int i=0;i<arr.length;i++) {
				int tempcount = 0;
				sum += arr[i];
				for(int n:arr) {
					if(n == arr[i]) {
						tempcount++;
					}
				}
				if(tempcount > count) {
					count = tempcount;
					num = arr[i];
				}
			}
			System.out.println(num + " accounts for "+(num*count)+" of the sets total value of "+sum);
		}
	}
}
