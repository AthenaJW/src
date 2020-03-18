import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CardSort {
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner fin = new Scanner(new File("card_sort.dat"));
		String[] temp;
		int attack, defense;
		String name;
		ArrayList<Card> cards = new ArrayList<Card>();
		while (fin.hasNextLine())
		{
			temp = fin.nextLine().split("/");
			name = temp[0];
			attack = Integer.parseInt(temp[1]);
			defense = Integer.parseInt(temp[2]);
			cards.add(new CardSort().new Card(attack, defense, name));
		}
		Comparator<Card> c = new Comparator<Card>()
		{
			public int compare(Card o1, Card o2)
			{
				if (o1.power != o2.power)
					return o2.power-o1.power;
				else if (o1.attack != o2.attack)
					return o2.attack - o1.attack;
				else
					return (o1.name).compareTo(o2.name);
			}
		};
		cards.sort(c);
		for (Card card: cards)
			System.out.printf("%d (%s/%d/%d)\n", card.power, card.name, card.attack, card.defense);
	}
	public class Card
	{
		int attack, defense, power;
		String name;
		public Card(int a, int d, String n)
		{
			attack = a;
			defense = d;
			power = a+d;
			name = n;
		}
	}
}
