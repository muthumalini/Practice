package com.java.practice;

import java.util.Random;
import java.util.Scanner;

public class GameOfLife {
   
  public static void main(String[] args){
	  String userInput;
	  Scanner input=new Scanner(System.in);
	  do{
		  
		  System.out.println("Enter the number of rows for the world");
		  int row=input.nextInt();
		  System.out.println("Enter the number of columns for the world");
		  int column=input.nextInt();
		  boolean[][] presentWorld = getCurrentWorld(row,column);
		  displayWorld(presentWorld);
		  presentWorld = generateNextGeneration(presentWorld);
		  displayWorld(presentWorld);
		  System.out.println("Do you want to play again? yes/no");
		  userInput=input.next();
		  
	  }while(userInput.equals("yes"));
	  input.close();
    }
  

public static boolean[][] getCurrentWorld(int numrow, int numcol){
	
      boolean[][] currWorld = new boolean[numrow][numcol];
      Random random = new Random();
      for(int row = 0; row < numrow; row++)
          for(int col = 0; col < numcol; col++)
          {
          		currWorld[row][col] = random.nextBoolean();
              
          }
      return currWorld;
		
  }
    
    public static boolean[][] generateNextGeneration(boolean[][] presentWorld){
    	int countOfNeighbours = 0;
        boolean[][] newWorld = new boolean[presentWorld.length][presentWorld[0].length];
        for(int row = 0; row < presentWorld.length; row++){
            for(int col = 0; col < presentWorld[0].length; col++){
            	countOfNeighbours = findNumOfNeighbours(presentWorld, row, col);
                if(validateRules(countOfNeighbours, presentWorld[row][col]) )
                    newWorld[row][col] = true;
            }
        }
        return newWorld;
    }
    
    public static boolean validateRules(int countOfNeighbours, boolean isAlive){
        if( isAlive && (countOfNeighbours == 2 || countOfNeighbours == 3))
            return true;
        else if (!isAlive && countOfNeighbours == 3)
            return true;
        else
            return false;
    }

    private static int findNumOfNeighbours(boolean[][] presentWorld, int row, int col) {
        int neighbourCount = presentWorld[row][col] ? -1 : 0;   //if alive that cell should be removed from the neighbour count
        for(int r = row - 1; r <= row + 1; r++)
            for(int c = col - 1; c <= col + 1; c++)
            {
            	if( validateArrayIndex(presentWorld, r, c) && presentWorld[r][c] )
            		neighbourCount++;
            	
            }
        return neighbourCount;
    }

    private static boolean validateArrayIndex(boolean[][] presentWorld, int r, int c) {
         boolean isValid = r >= 0 && r < presentWorld.length && c >= 0 && c < presentWorld[0].length;
         return isValid;
    }
    
    public static void displayWorld(boolean[][] presentWorld){
        String cellValue = "";
        for(int r = 0; r < presentWorld.length; r++){
            for(int c = 0; c < presentWorld[0].length; c++)
                if(presentWorld[r][c] == true)
                	cellValue += "x";
                else
                	cellValue += "-";
            cellValue += "\n";
        }
        System.out.println(cellValue);
    }

    
    
}
