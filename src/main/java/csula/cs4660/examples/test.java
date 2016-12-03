package csula.cs4660.examples;



public class test {

	public static void main(String[] args) {
		int arr[][] = new int[20][30];
		long startTime = System.nanoTime();
    	int copy [][] = deepCopyIntMatrix(arr);
		long endTime = System.nanoTime();
		System.err.println("Took "+(endTime - startTime) + " ns");
	}
	
	public static int[][] deepCopyIntMatrix(int[][] input) {
	    if (input == null)
	        return null;
	    int[][] result = new int[input.length][];
	    for (int r = 0; r < input.length; r++) {
	        result[r] = input[r].clone();
	    }
	    return result;
	}

}
