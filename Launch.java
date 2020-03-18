import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Launch {
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner scan = new Scanner(new File("launch.dat"));
		int n = Integer.parseInt(scan.nextLine());
		
		for(int i = 0; i < n; i++) {
		    int k = Integer.parseInt(scan.nextLine());
		    System.out.println(k);
			HashMap<String, Integer> m = new HashMap<String,Integer>();
			for(int j = 0; j < k; j++) {
				String o = scan.nextLine();
				String[] arr = o.substring(0,o.length()-6).split(" - ");
				m.put(arr[0],Integer.parseInt(arr[1]));
			}
			Set<String> val = m.keySet();
			Iterator<String> p = val.iterator();
			String op = p.next();
			int min = m.get(op);
			String ct = "";
			while(p.hasNext()) {
				ct = p.next();
				if(m.get(ct)<25) {
					p.remove();
					continue;
				}
				else if(m.get(ct)<min) {
					op = ct;
					min = m.get(ct);
					
				}
				else if(m.get(ct).equals(min)) {
					if(Integer.parseInt(ct.substring(ct.length()-3)) < Integer.parseInt(op.substring(op.length()-3))) {
						op = ct;
						p.remove();
					}
				}
			}
			System.out.println("Launch Date: "+ ct);
		}
	}
}
