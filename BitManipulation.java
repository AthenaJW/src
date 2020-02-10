
public class BitManipulation {
	public static void main(String[] args)
	{
		//checking if on
		byte b = 6;//110
		System.out.println(b & 1<<1); //010
		//turning on a bit
		b = 0;
		System.out.println(b | 1<<1);
		int x = 46342;
		int y = 46342;
		long z = x*y;
		System.out.println(z); // adding or doing
		//mathematical operations on ints in a long variable, it gets interpreted as integer 
	}
}
