//Merwan M
//Computer Science III
//This program defines a Board
//Board.java
//04/19/2020

import java.util.ArrayList;
import java.util.Scanner;

import static java.util.Arrays.fill;

public class Board
{
    private String[][] theBoard;

    /**
     * Constructs a board
     * @param width Width of the board
     * @param height Height of the board
     */
    public Board(int width, int height)
    {
        this.theBoard = new String[width][height];
        for(int row = 0; row < this.theBoard.length; row++)
        {
            fill(this.theBoard[row], " ");
        }
    }

    /**
     * Gets the board
     * @return the 2d board
     */
    public String[][] getTheBoard()
    {
        return this.theBoard;
    }

    public int getWidth()
    {
        return this.theBoard.length;
    }

    public int getHeight()
    {
        return this.theBoard[0].length;
    }

    @Override
    public String toString()
    {
        String toReturn = "";
        for(int row = 0; row < this.theBoard.length; row++)
        {
            for(int col = 0; col < this.theBoard[0].length; col++)
            {
                toReturn += "|" + this.theBoard[row][col];
            }
            toReturn += "|\n";
        }
        return toReturn;
    }

    public void putPlayer(String name, int placeX, int placeY)
    {
        this.theBoard[placeX][placeY] = name;
    }

    public void removePlayer(int placeX, int placeY)
    {
        this.theBoard[placeX][placeY] = " ";
    }

    /**
     * Moves a player a certain amount of spaces in any direction based on their choice
     * @param input the scanner to take input from the user
     * @param diceRoll the number that was rolled
     * @return the final coordinates of the user's position
     */
    public int[] move(Scanner input, int diceRoll, Character c)
    {
        System.out.println("Remember, the number of spaces that you move MUST equal " + diceRoll);
        ArrayList<Integer> userNum = new ArrayList<>();

        //This is to make sure that user is not backtracking
        boolean isForward;
        boolean isLeft;
        int forward = 0;
        int back = 0;
        int left = 0;
        int right = 0;
        //Keep reprompting to make sure that their numbers add to the dice roll
        while(true)
        {
            userNum.clear();
            forward = 0;
            back = 0;
            left = 0;
            right = 0;

            forward = MyUtils.getNumber(input, "How many spaces forward would you like to move? ", 0, Math.min(c.getCurrentPosX(), diceRoll));
            userNum.add(forward);
            isForward = forward != 0;
            System.out.println();

            if(!isForward)
            {
                back = MyUtils.getNumber(input, "How many spaces backward would you like to move? ", 0, Math.min(getWidth() - c.getCurrentPosX() - 1, diceRoll));
                System.out.println();
            }
            userNum.add(back);

            left = MyUtils.getNumber(input, "How many spaces left would you like to move? ", 0, Math.min(c.getCurrentPosY(), diceRoll));
            userNum.add(left);
            isLeft = left != 0;
            System.out.println();

            if(!isLeft)
            {
                right = MyUtils.getNumber(input, "How many spaces right would you like to move? ", 0, Math.min(getHeight() - c.getCurrentPosY() - 1, diceRoll));
                System.out.println();
            }
            userNum.add(right);

            int addedUp = forward + back + left + right;
            if(addedUp == diceRoll)
            {
                break;
            }
            System.out.println("All of your numbers have to add up to your dice roll! Try again.");
        }

        //Loop through arrays and find directions and move in said direction
        int finalPosX = c.getCurrentPosX();
        int finalPosY = c.getCurrentPosY();

        finalPosX -= userNum.get(0);
        finalPosX += userNum.get(1);
        finalPosY -= userNum.get(2);
        finalPosY += userNum.get(3);


        removePlayer(c.getCurrentPosX(), c.getCurrentPosY());
        this.theBoard[finalPosX][finalPosY] = c.toString();
        c.setCurrentPosX(finalPosX);
        c.setCurrentPosY(finalPosY);
        int[] finalPos = new int[2]; //Only x and y
        finalPos[0] = finalPosX;
        finalPos[1] = finalPosY;
        System.out.println(this.toString());
        return finalPos;
    }
}

