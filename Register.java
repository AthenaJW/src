import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Register {
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner fin = new Scanner(new File("register.dat"));
		String[] temp;
		double money;
		double total;
		double tol = 0.005;
		for (int i = 0; i < 3; i++)
		{
			temp = fin.nextLine().split(" ");
			money = Double.parseDouble(temp[0]);
			total = 0;
			total += Double.parseDouble(temp[1])*20;
			total += Double.parseDouble(temp[2])*10;
			total += Double.parseDouble(temp[3])*5;
			total += Double.parseDouble(temp[4]);
			total += Double.parseDouble(temp[5])*.25;
			total += Double.parseDouble(temp[6])*.1;
			total += Double.parseDouble(temp[7])*.05;
			total += Double.parseDouble(temp[8])*.01;
			if (Math.abs(money - total) < tol)
				System.out.println("Correct\n");
			else if (money > total)
				System.out.printf("Missing $%.2f\n", (money - total));
			else
				System.out.printf("Over $%.2f\n", (total - money));
		}
	}
}
