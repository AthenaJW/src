import java.io.*;
import java.util.*;

public class CowAteMyHomework 
{
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("homework.in"));
		int num = Integer.parseInt(br.readLine());
		double tolerance = 0.00001;
		String[] temp;
		temp = br.readLine().split(" ");
		Question[] sortedList = new Question[num];
		Question[] orderedList = new Question[num];
		Question tempQ;
		for (int i = 0; i < num; i++)
		{
			tempQ = new Question(Integer.parseInt(temp[i]));
			sortedList[i] = tempQ;
			orderedList[i] = tempQ;
		}
		Comparator<Question> c = new Comparator<Question>()
		{
			public int compare(Question q1, Question q2)
			{
				return q1.getScore()-q2.getScore();
			}
		};
		Arrays.sort(sortedList, c);
		double average = 0;
		for (int i = 0; i < num; i++)
		{
			average += sortedList[i].getScore();
		}
		int lowest = sortedList[0].getScore();
		average = (average-lowest)/(num-1);
		double max = 0;
		//System.out.println(average);
		ArrayList<Integer> answers = new ArrayList<Integer>();
		int pointer = 0;
		orderedList[0].check();
		for (int i = 0; i < orderedList.length-2; i++)
		{
			orderedList[i].check();
			if (orderedList[i].getScore() == lowest)
			{
				while(++pointer < num-1 && sortedList[pointer].isChecked())
					continue;
				lowest = sortedList[pointer].getScore();
				average = (average*(num-i-1)-lowest)/(num-i-2);
			}
			else
			{
				average = (average * (num-i-1) - orderedList[i].getScore())/(num-i-2);
			}
			//System.out.println(average);
			if (average - max > tolerance)
			{
				max = average;
				answers = new ArrayList<Integer>();
				answers.add(i+1);
			}
			else if (average - max <= tolerance && average-max > 0)
			{
				answers.add(i+1);
			}
			//for (Question el: orderedList)
			//	System.out.println(el);
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("homework.out")));
		for (int el: answers)
			pw.println(el);
		pw.close();
		br.close();
	}
	public static class Question
	{
		private int score;
		private boolean checked;
		public Question(int score)
		{
			this.score = score;
			checked = false;
		}
		public int getScore()
		{
			return score;
		}
		public boolean isChecked()
		{
			return checked;
		}
		public void check()
		{
			checked = true;
		}
		public String toString()
		{
			return ("Score: " + score + " Checked: " +  checked);
		}
	}
}
