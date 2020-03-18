import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RestPerm {
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] temp;
		int num;
		int[] b;
		int[] a;
		int[] perm; boolean foundPerm;
		outer: for (int i = 0; i < N; i++)
		{
			num = Integer.parseInt(br.readLine());
			temp = br.readLine().split(" ");
			b = new int[num];
			a = new int[2*num];
			perm = new int[2*num];
			for (int j = 0; j < num; j++)
			{
				b[j] = Integer.parseInt(temp[j]);
				a[j*2] = b[j];
				perm[2*j] = 2 * j + 1;
				perm[2*j + 1] = 2 * j + 2;
			}
			for (int val: b)
			{
				perm[val-1] = 0;
			}
			for (int val: perm)
			{
				if (val == 0)
					continue;
				foundPerm = false;
				for (int j = 0; j < a.length; j += 2)
				{
					if (a[j+1] == 0 && val > a[j])
					{
						foundPerm = true;
						a[j+1] = val;
						break;
					}
				}
				if (!foundPerm)
				{
					System.out.println("-1");
					continue outer;
				}
			}
			for (int el: a)
				System.out.print(el + " ");
			System.out.println();
		}
	}
}
