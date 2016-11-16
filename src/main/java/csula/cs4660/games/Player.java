package csula.cs4660.games;
import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {
    private long startTime;
    
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int board[][] = new int[20][30];
        // game loop
        while (true) {
            int N = in.nextInt(); // total number of players (2 to 4).
            int P = in.nextInt(); // your player number (0 to 3).
            for (int i = 0; i < N; i++) {
                int X0 = in.nextInt(); // starting X coordinate of lightcycle (or -1)
                int Y0 = in.nextInt(); // starting Y coordinate of lightcycle (or -1)
                int X1 = in.nextInt(); // starting X coordinate of lightcycle (can be the same as X0 if you play before this player)
                int Y1 = in.nextInt(); // starting Y coordinate of lightcycle (can be the same as Y0 if you play before this player)
                board[X1][Y1] = i;
                if(i != P){ 
                    System.err.println(X0+"-"+Y0+"-"+X1+"-"+Y1);
                }
            }
            //X1 x axis left decrement and right Increment
            //Y1 decrement for UP and INcrement for DOWN
            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");
            debugBoard(board);
            System.out.println("RIGHT"); // A single line with UP, DOWN, LEFT or RIGHT
        }
       
    }
    private static void debugBoard(int[][] Board){
         for(int i=0; i < 20;i ++){
            for(int j=0; j < 30;j ++){
                System.out.print(Board[i][j]+ " ");
                }
                System.out.println();
            }
        }
}