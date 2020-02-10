import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Sale {
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner fin = new Scanner(new File("sale.dat"));
		
		
		
		double[] nums = new double[3];
				
		for(int i = 0; i < 3; i++) {
			nums[i] = fin.nextDouble();
		}
		int small = 0;
		if (nums[1] < nums[0] && nums[1] < nums[2])
			small = 1;
		else if(nums[2] < nums[0] && nums[2] < nums[1]) {
			small = 2;
		}
		nums[small] /= 2;
		
		double sum = 0; 
		for(double d: nums) {
			sum += d;
		}
		sum *= 1.0825; 
		
		
		System.out.printf("%.2f", sum);
		
		
		
		fin.close();
	}
}
