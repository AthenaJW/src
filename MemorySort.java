import java.util.*;


import java.io.File;
import java.io.FileNotFoundException;
public class MemorySort {
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner fin = new Scanner(new File("memory_sort.dat"));
		ArrayList<Company> companies = new ArrayList<Company>();
		String[] temp;
		Company c;
		Comparator<Company> comparator = new Comparator<Company>()
		{
			public int compare(Company o1, Company o2)
			{
				if (o1.name.compareTo(o2.name)!=0)
					return o1.name.compareTo(o2.name);
				else if (o1.bytes != o2.bytes)
					return o2.bytes - o1.bytes;
				else if (o1.size != o2.size)
					return o2.size - o1.size;
				return o2.speed - o1.speed;
					
			}
		};
		while(fin.hasNextLine())
		{
			temp = fin.nextLine().split(",");
			c = new MemorySort().new Company();
			c.name = temp[0];
			c.size = Integer.parseInt(temp[1].substring(0, temp[1].length()-2));
			c.speed = Integer.parseInt(temp[2].substring(0,temp[2].length()-3));
			if (temp[1].substring(temp[1].length()-2).compareTo("MB") == 0)
				c.bytes = 0;
			else
				c.bytes = 1;
			companies.add(c);
		}
		Collections.sort(companies, comparator);
		for (Company el: companies)
			System.out.println(el);
	}
	public class Company
	{
		public String name;
		public int size;
		public int speed;
		public int bytes;
		public Company()
		{
		}
		public String toString()
		{
			String s = (name + " - (" + size);
			if (bytes == 0)
				s = s+"MB";
			else
				s = s+"GB";
			s = s+"/" + speed + "MHz)";
			return s;
		}
	}
}
