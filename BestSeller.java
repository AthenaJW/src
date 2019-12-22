import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class BestSeller {
	public static void main(String[] args)
	{
		Scanner fin = null;
		try 
		{
			fin = new Scanner(new File("BestSeller.dat"));
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		fin.useDelimiter(",");
		HashMap<String, Integer> itemToQty = new HashMap<String, Integer>();
		HashMap<String, Double> itemToProfit = new HashMap<String, Double>();
		String item;
		int qty;
		double price, profit;
		while(fin.hasNextLine())
		{
			item = fin.next();
			qty = fin.nextInt();
			price = Double.parseDouble(fin.nextLine().substring(2));
			profit = qty*price;
			itemToQty.put(item, qty);
			itemToProfit.put(item, profit);
		}
		List<Map.Entry<String, Double>> ordered_list = new ArrayList<Map.Entry<String, Double>>(itemToProfit.entrySet());
		Comparator<Map.Entry<String, Double>> comparer= new Comparator<Map.Entry<String, Double>>()
		{
			public int compare(Map.Entry<String, Double> e1, Map.Entry<String, Double> e2)
			{
				double p1 = e1.getValue();
				double p2 = e2.getValue();
				int qty1 = itemToQty.get(e1.getKey());
				int qty2 = itemToQty.get(e2.getKey());
				String item1 = e1.getKey();
				String item2 = e2.getKey();
				if (Math.abs(p1-p2)>=0.01)
				{
					return -Double.compare(p1, p2); //descending
				}
				else
				{
					if (qty1 != qty2)
						return -Integer.compare(qty1, qty2); //descending
					else
						return item1.compareTo(item2); //ascending
				}
			}
		};
		Collections.sort(ordered_list, comparer);
		for (Map.Entry<String, Double> entry: ordered_list)
		{
			System.out.printf("$%.2f - %s/%d\n", entry.getValue(), entry.getKey(), itemToQty.get(entry.getKey()));
		}
	}
}
