package csula.cs4660.exercises;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Introduction Java exercise to read file
 */
public class FileRead {
    private int[][] numbers;
    /**
     * Read the file and store the content to 2d array of int
     * @param file read file
     */
    public FileRead(File file) {
        // TODO: read the file content and store content into numbers
          Scanner inFile1;
		try {
			inFile1 = new Scanner(file).useDelimiter(",\\s*");
			// List<String> temps = new ArrayList<String>();
				numbers = new int[5][8];
			    // while loop
			    while (inFile1.hasNext()) {
			      String token1 = inFile1.next();
			      String[] str = token1.split("\n");
			      int i = 0;
			      for (String string : str) {
			    	  
			    	  String[] str1 =string.split(" ");
			    	  int j =0;
			    	  for (String string2 : str1) {
			    		  numbers[i][j] += Integer.parseInt(string2);
			    		  j+=1;
			    	}
			    	  i += 1;
				}
			    }
			    inFile1.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    // Original answer used LinkedList, but probably preferable to use ArrayList in most cases
    // List<String> temps = new LinkedList<String>();
   
	
    }
    public static void main(String args[]){
    	FileRead obj = new FileRead(new File("/Users/mohan/Documents/AI/src/main/resources/array.txt"));
    }

    /**
     * Read the file assuming following by the format of split by space and next
     * line. Display the sum for each line and tell me
     * which line has the highest mean.
     *
     * lineNumber starts with 0 (programming friendly!)
     */
    public int mean(int lineNumber) {
    	int sum =0;
    	for(int i : numbers[lineNumber]){
    		sum += i;
    	}
    	sum = sum / numbers[lineNumber].length;
        return sum;
    }

    public int max(int lineNumber) {
    	int max =0;
    	int current = 0;
    	for(int i : numbers[lineNumber]){
    	current = i;
    	if(current > max){
    		max = i;
    	}
    	}
        return max;
    }

    public int min(int lineNumber) {
    	int min =0;
    	int current = 0;
    	for(int i : numbers[lineNumber]){
    	current = i;
    	if(current < min){
    		min = i;
    	}
    	}
        return min;
    }

    public int sum(int lineNumber) {
    	int sum =0;
    	for(int i : numbers[lineNumber]){
    		sum += i;
    	}
        return sum;
    }
}