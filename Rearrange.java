import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Rearrange {
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner fin = new Scanner(new File("rearrange.dat"));
		while(fin.hasNextLine()) {
			if(!fin.hasNext()) {
				continue;
			}
			String[] u = fin.nextLine().split(" ");
			for(int i = 0;i<u.length;i++) {
				if(u[i].length()==1 || u[i].length()==2) {
					System.out.print(u[i]+" ");
				}
				else if(u[i].length()%2 == 0){
					char[] a = u[i].toCharArray();
					char temp = a[a.length-1];
					a[a.length-1] = a[a.length/2];
					a[a.length/2] = temp;
					temp = a[0];
					a[0] = a[a.length/2-1];
					a[a.length/2-1] = temp;
					for(char j:a) {
						System.out.print(j);
					}
					System.out.print(" ");
				}
				else {
					char[] a = u[i].toCharArray();
					char temp = a[0];
					a[0] = a[a.length-1];
					a[a.length-1] = temp;
					for(char j:a) {
						System.out.print(j);
					}
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}
}
