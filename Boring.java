import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Boring {
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner scan = new Scanner(new File("boring.dat"));
		int n = scan.nextInt();
		String[] arr;
		scan.nextLine();
		b : for(int i = 0; i < n; i++) {
			arr = scan.nextLine().split(" ");
			for(String k : arr) {
				if(k.equalsIgnoreCase("space") || k.equalsIgnoreCase("boring")) {
					System.out.println("true");
					continue b;
				}
			}
			System.out.println("false");
		}
		
		
	}
}
