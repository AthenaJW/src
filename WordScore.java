import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
public class WordScore {
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner fin = new Scanner(new File("word_score.dat"));
		
		while(fin.hasNextLine()) {
			String[] words;
			
			words = fin.nextLine().split(" ");
			int[] scores = new int[words.length];

			
			for(int i = 0; i < words.length; i++) {
				String w = words[i].toLowerCase();
				int score = 0;
				score += w.length() / 2;
				for(int j = 0; j < w.length(); j++) {
					if(w.charAt(j) == 'z') {
						score += 2;
					}
					if(w.charAt(j) == 'x' || w.charAt(j) == 'q') {
						score += 3;
					}
					if(w.charAt(j) == 'a' || w.charAt(j) == 'e'||w.charAt(j) == 'i'||w.charAt(j) == 'o'||w.charAt(j) == 'u') {
						score += 1;
					}
				}
				scores[i] = score;
			}
			
			int largest = 0;
			for(int y = 0; y < words.length; y++) {
				if(scores[y] > scores[largest]) {
					largest = y;
				}
			}
			
			System.out.println(words[largest] + " - " + scores[largest]);
		}
		
		fin.close();
	}
}
