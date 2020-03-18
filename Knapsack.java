import java.io.*;
import java.util.*;

public class Knapsack {
  public static void main(String[] args) throws FileNotFoundException {
    Scanner scan = new Scanner(new File("knapsack.dat")); //File Scanner    
    int count = scan.nextInt();
    Scanner fin; //Separate Scanner for String for easy parsing
    for(int i=0;i<count;i++){
      ArrayList<Integer> wt = new ArrayList<Integer>(); // List of weights
      ArrayList<Double> val = new ArrayList<Double>();  // List of values. I could've used a map with weights as keyset and values as the objects but that wouldnt work for cases where there are duplicate weights involved.
      int max_weight = scan.nextInt(); // Max weight value
      scan.nextLine(); //Gets rid of /n character in the file
      fin = new Scanner(scan.nextLine()); // Scanner for line with values and weights
      fin.useDelimiter("[:\\s]"); // Delimiters to use to get the numbers
      while(fin.hasNext()){ //Adds weights and values to their respective lists
        wt.add(fin.nextInt());
        val.add(fin.nextDouble()); 
      }
      double[][] table = new double[wt.size()][max_weight+1]; // Creates 2d Array of possible knapsack values with weight as rows and columns representing each object being added
      int swap = -1; 
      while(!(swap==0)){ // Implements Bubble Sort to sort the weight list and makes sure value list still corresponds. If using a map, Collections.sort works too
        swap = 0;
        for(int k = 0;k<wt.size()-1;k++){
          int temp  = 0;
          double tem = 0;
          if(wt.get(k)>wt.get(k+1)){
            swap++;
            temp = wt.get(k+1);
            wt.set(k+1,wt.get(k));
            wt.set(k, temp);
            tem = val.get(k+1);
            val.set(k+1,val.get(k));
            val.set(k, tem);
          }
        }
      }
      System.out.println(wt);
      System.out.println(val);
      for(int r = 0; r<table.length;r++){ 
        for(int c = 0;c<table[r].length;c++){
          if(r==0 && c>=wt.get(r)){
            table[r][c] = val.get(r);
          }
          else if(c<wt.get(r) && r!=0){ //Checks if there is enough max weight to add a new item if not it keeps value from previous column
            table[r][c] = table[r-1][c];
          }
          else if(c>=wt.get(r)){
            table[r][c] = Math.max(table[r-1][c], table[r-1][c-wt.get(r)]+val.get(r)); //Determines whether adding the given item would be more profitable than keeping the current value 
          }
        }
      }
      System.out.println(Arrays.deepToString(table)); // Prints knapsack table/2d Array
      System.out.println(table[table.length-1][table[0].length-1]); //The value in the bottom rightmost corner will always be the maximum amt
    }
  }
}
