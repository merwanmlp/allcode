//Merwan M
//Computer Science Section C
//This program gets a valid input from the user.
//MyUtils.java
//12/6/19

import java.util.*;
import java.io.*;

public class MyUtils
{
   /**
   *Gets a scanner to scan a file without throwing exceptions
   *
   *@param filename which is the string to attempt to read the file from
   *@return the scanner which scans the file
   */   
   public static Scanner getFileScanner(String filename) 
   {
      File f = new File(filename);
      Scanner fileScan = null;
      try
      {
         fileScan = new Scanner(f);
      }
      catch(FileNotFoundException e)
      {
         System.out.print("File not found. Try again: ");
      }
      return fileScan;
   }
   
   /**
   *Keeps asking user for a filename until they give something that exists
   *
   *@param input the scanner to read user input
   *@param filename the name that the user inputs
   *@location the place where the file is located
   *@param ending the String that is the type of file, i.e. ".txt", ".in" etc
   *@return the scanner used to scan the file
   */
   public static Scanner getRobustScanner(Scanner input, String filename, String location, String ending)
   {
      Scanner fileScan = MyUtils.getFileScanner(location + filename + ending);
      boolean correctFileName = false;
      while(!correctFileName)
      {
         if(fileScan == null)
         {
            filename = input.next();
            fileScan = MyUtils.getFileScanner(location + filename + ending);
         }
         
         else
         {
            correctFileName = true;
         }
      }
      return fileScan;
   }
   
   /**
   *Creates a new file
   *
   *@param filename the string which will be the name of the file
   *@return the printstream to write/create/read from the file
   */
   public static PrintStream createOutputFile(String filename) 
   {
      File file = new File(filename);
      PrintStream stream = null;
      try
      {
         stream = new PrintStream(file);
      }
      catch(FileNotFoundException e)
      {
         System.out.println("File not found. Try again:");
      }
      return stream;  
   }
   
   /**
   *Gets a valid integer input from the user
   *
   *@param input the scanner to take in user input
   *@param prompt the string to print when asking user for their guess.
   *@param min the lowest number of the guessing range
   *@param max the highest number of the guessing range.
   *@return the valid user input.
   */
   public static int getNumber(Scanner input, String prompt, int min, int max)
   {
      int nextNum= 0;
      while(true)
      {
         System.out.print(prompt);
         if(input.hasNextInt())
         {
            nextNum = input.nextInt();
            
            if(nextNum < min || nextNum > max)
            {
               System.out.println("Your number needs to be between " + min + " and " + max + ".");
               input.nextLine();
            }
            
            else
            {
               input.nextLine();
               return nextNum;
            }
         }
         
         else
         {
            System.out.println("You must enter an *integer* between " + min + " and " + max + ".");
            input.nextLine();
         } 
      }
   }
   
   /**
   *Gets a valid string input from the user
   *
   *@param input the scanner to take in user input
   *@param toPrint the string to print when asking user
   *@return the robust string
   */
   public static String getString(Scanner input, String toPrint)
   {
      while(true)
      {
         System.out.print(toPrint);
         String returning = input.nextLine();
         returning = returning.trim();
         if(returning.equals(""))
         {
            System.out.println("Input is not valid, you need to enter some text");
         }
         
         else
         {
            return returning;
         }
      }
   }
}