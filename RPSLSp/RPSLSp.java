//Merwan M
//Computer Science Section C
//This program creates a rock-paper-scissors game(with more options) and reports statistics at the end.
//RPSLSp.java
//12/7/19

import java.util.*; 
import java.io.*;

public class RPSLSp
{
   /**
   *The name of the file to use for the game
   */
   public static String FILENAME = "battles15.txt";
   
   /**
   *Runs the entire game
   */
   public static void main(String[] args)
   {  
      Scanner input = new Scanner(System.in);
      Scanner fileScanner = MyUtils.getRobustScanner(input, FILENAME, "", "");
      Random r = new Random();
      if(errorChecking(input, fileScanner))
      {
          printIntro();
          playAgain(r, input, fileScanner);     
      }
      
      else
      {
         System.out.println("There is an error in " + FILENAME + ", ending game.");
      }      
   }
   
   /**
   *Checks for all the possible errors that could occur within the file
   *
   *@param fileScanner the scanner to scan the file
   *@param input the scanner to get input from the user
   *@return true if there are no errors, but false if there are errors
   */
   public static boolean errorChecking(Scanner input, Scanner fileScanner)
   {
      fileScanner = MyUtils.getRobustScanner(input, FILENAME, "", "");
      if(fileScanner.hasNextInt())
      {
         int expected = fileScanner.nextInt();
         int numChoices = 0;
         while(!fileScanner.next().contains(":"))
         {
            numChoices++;
         }
         
         if(numChoices > expected)
         {
            System.out.println("Just FYI, there are too many choices in the file.");
         }
         
         else if(numChoices < expected)
         {
            System.out.println("There are not enough choices in the file.");
            return false;
         }
         
         int numBattles = 0;
         int expectedBattles = expected*expected;
         while(fileScanner.hasNext())
         {
            numBattles++;
            fileScanner.nextLine();
         }
         
         if(numBattles < expectedBattles)
         {
            System.out.println("Missing some battle information in the file.");
            return false;
         }
      }
      
      else
      {
         System.out.println("Missing number of choices.");
         return false;
      }
      return true;
   }
   
   /**
   *Prints the intro for the game
   */
   public static void printIntro()
   {
      System.out.println("Losing is the best type of winning unless you lose");
      System.out.println();
   }
   
   /**
   *Prints the possible options for the user to pick from
   *
   *@param fileScanner the Scanner used to parse the file
   *@param input the scanner to get input from the user
   */
   public static void printOptions(Scanner input, Scanner fileScanner)
   {
      fileScanner = MyUtils.getRobustScanner(input, FILENAME, "", "");
      int numChoices = fileScanner.nextInt();
      String[] words = available(input, fileScanner);
      for(int i = 1; i < numChoices+1; i++)
      {
         String word = words[i-1];
         word = word.replace("-", " ");
         System.out.println(i + ". " + word);
      }
   }
   
   /**
   *Creates an array of all the available options
   *
   *@param input the Scanner to get input from the user
   *@param fileScanner the scanner to get input from the user
   *@return array of strings that represents the available options
   */
   public static String[] available(Scanner input, Scanner fileScanner)
   {
      fileScanner = MyUtils.getRobustScanner(input, FILENAME, "", "");
      int numChoices = fileScanner.nextInt();
      String[] choices = new String[numChoices];
      for(int i = 0; i < numChoices; i++)
      {
         choices[i] = fileScanner.next();
      }
      return choices;
   }
   
   /**
   *Gets the user and computer's choice
   *
   *@param r the Random object used to get a random number
   *@param fileScanner the scanner to read the file
   *@param input the scanner to get input from the user
   *@param userOrComputer the String used to identify the identity that is choosing a weapon
   *@return the chosen weapon
   */
   public static String choice(Random r, Scanner fileScanner, Scanner input, String userOrComputer)
   {
      fileScanner = MyUtils.getRobustScanner(input, FILENAME, "", "");
      int options = fileScanner.nextInt();
      int num;
      String[] choices = available(input, fileScanner);
      if(userOrComputer.equals("user"))
      {  
         num = MyUtils.getNumber(input, "Choose your weapon (1-" + options + "): ", 1, options);
      }
      
      else
      {
         num =  r.nextInt(options)+1;
      }
      
      String choice = choices[num-1];
      return choice;
   }
   
   /**
   *Reads the file and saves the winner and verb for the current outcome
   *
   *@param fileScanner the scanner used to read the file
   *@return array of strings that contains the winner and verb
   */
   public static String[] getObjs(Scanner fileScanner)
   {
      String word = fileScanner.next();
      String firstObj = word.substring(0, word.indexOf(':'));
      String secondObj = word.substring(word.indexOf(':')+1, word.length()); //Plus One is to not include the colon when separating words
      String winner = "";
      String verb = "";
      if(firstObj.equals(secondObj))
      {
         winner = fileScanner.next();
         verb = "";
      }
      
      else
      {
         winner = fileScanner.next();
         verb = fileScanner.next();
      }
      String[] objAndVerb = new String[4]; //It will always be 4 because I'm only storing 4 things - word before colon, word after colon, winner, and verb
      
      objAndVerb[0] = firstObj;
      objAndVerb[1] = secondObj;
      objAndVerb[2] = winner;
      objAndVerb[3] = verb;
      return objAndVerb;
   }
   
   /**
   *Creates and fills a table to hold winners/verbs
   *@param fileScanner the scanner used to scan the file
   *@param input the scanner to get input from the user
   *@param winnerOrVerb the string to determine whether the table will be filled with winnners or verbs
   *@return the 2d array of strings/the filled table
   */
   public static String[][] fillTable(Scanner fileScanner, Scanner input, String winnerOrVerb)
   {
      fileScanner = MyUtils.getRobustScanner(input, FILENAME, "", "");
      int numOptions = fileScanner.nextInt(); 
      String[][] table = new String[numOptions][numOptions];
      for(int row = 0; row < table.length; row++)
      {
         table[row][row] = "ties";
      }
      
      String[] options = available(input, fileScanner);  
      fileScanner.nextLine();
      fileScanner.nextLine();
      while(fileScanner.hasNext())
      {
         String[] objs = getObjs(fileScanner);
         int row = 0;
         int col = 0;
         
         for(int i = 0; i < options.length; i++)
         {
            if(objs[0].equals(options[i]))
            {
               row = i;
            }
            
            if(objs[1].equals(options[i]))
            {
               col = i;
            }
         }
         if(winnerOrVerb.equals("winner"))
         {
            table[row][col] = objs[2];
         }
         
         else
         {
            table[row][col] = objs[3];
         }
      }
      return table;
   }
   
   /**
   *Plays exactly one game with the user
   *
   *@paramr the random object used to get a random number
   *@param input the scanner used to get input from the user
   *@param fileScanner the scanner used to scan the file
   *@return array of strings containing the winner of the battle, the user's choice, and the computer's choice
   */
   public static String[] playOne(Random r, Scanner input, Scanner fileScanner)
   {
      printOptions(input, fileScanner);
     
      String userChoice = choice(r, fileScanner, input, "user");
      String compChoice = choice(r, fileScanner, input, "computer");
      
      String[] options = available(input, fileScanner);
      String[][] winnerTable = fillTable(fileScanner, input, "winner");
      String[][] verbTable = fillTable(fileScanner, input, "verb");
      String[] choices = new String[3]; //Will only be 3, one for the winner, one for user choice, and one for computer choice
      int index1 = 0;
      int index2 = 0;
      for(int i = 0; i < options.length; i++)
      {
         if(userChoice.equals(options[i]))
         {
            index1 = i;
            userChoice = userChoice.replace("-", " ");
         }
         
         if(compChoice.equals(options[i]))
         {
            index2 = i;
            compChoice = compChoice.replace("-", " ");
         }
      }
      if(index1 == index2)
      {  
         winnerTable[index1][index2] = "ties";
         verbTable[index1][index2] = "ties";
         choices[0] = "tie";
         System.out.println("User (" + userChoice + ") " + verbTable[index1][index2] + " Computer (" + compChoice + ")");
      }
      
      else if(userChoice.equals(winnerTable[index1][index2]))
      {
         System.out.println("User (" + userChoice + ") " + verbTable[index1][index2] + " Computer (" + compChoice + ")");
         choices[0] = "user";
      }
      
      else
      {
         System.out.println("Computer (" + compChoice + ") " + verbTable[index1][index2] + " User (" + userChoice + ")");
         choices[0] = "comp";
      }
      choices[1] = userChoice;
      choices[2] = compChoice;
      return choices;
   }
   
   /**
   *Plays multiple games with the user
   *
   *@param r the random object used to get a random number
   *@param input the scanner used to get input from the user
   *@param fileScanner the scanner uesd to scan the file
   */
   public static void playAgain(Random r, Scanner input, Scanner fileScanner)
   {
      int userWin = 0;
      int compWin = 0;
      int tied = 0;
      String[] options = available(input, fileScanner);
      int[] numTimesChosenUser = new int[options.length];
      int[] numTimesChosenComp = new int[options.length];
      String[] stats;
      while(true)
      {
         stats = playOne(r, input, fileScanner);
         if(stats[0].equals("tie")) 
         {
            tied+=1;
         }
         
         else if(stats[0].equals("user"))
         {
            userWin += 1;
         }
         
         else if(stats[0].equals("comp"))
         {
            compWin += 1;
         } 
         
         for(int i = 0; i < options.length; i++)
         {
            options[i] = options[i].replace("-", " ");
            if(stats[1].equals(options[i]))
            {
               numTimesChosenUser[i]++;
            }
            
            if(stats[2].equals(options[i]))
            {
               numTimesChosenComp[i]++;
            }
         }
         
         System.out.print("Battle again (yes/no)? ");
         String answer = input.nextLine();
         if(answer.startsWith("y") || answer.startsWith("Y"))
         {
            System.out.println();
            fileScanner = MyUtils.getRobustScanner(input, FILENAME, "", "");
         }
         
         else
         {
            break;
         }
      }
      System.out.println();
      statistics(input, fileScanner, numTimesChosenUser, numTimesChosenComp);
      
      System.out.println();
      grammarTime(compWin, "The", " computer", "won", ",");
      grammarTime(userWin, "the", " user", "won", ",");
      grammarTime(tied, "and they", "", "tied", ".");
   }
   
   /**
   *Formats the grammar to work even for 1 time
   *
   *@param numTimes the number of wins/ties.
   *@param firstWords the beginning of each sentence
   *@param userComp whether the sentence is for user or computer
   *@param verb whether there was a win or a tie
   *@param end the end of the sentence(comma or period)
   */
   public static void grammarTime(int numTimes, String firstWords, String userComp, String verb, String end)
   {
      if(numTimes == 1)
      {
         System.out.println(firstWords + userComp + " " + verb + " " + numTimes + " time" + end);
      }
      
      else
      {
         System.out.println(firstWords + userComp + " " + verb + " " + numTimes + " times" + end);
      }
   }
   
   /**
   *Formats the statistics in an efficient way
   *
   *@param options the array of strings that represents number of options
   *@param word the string to represent either user or computer
   *@param userComp array of integers to represent the number of times that an options was chosen
   */
   public static void printTableStats(String[] options, String word, int[] userComp)
   {
      System.out.printf("%8s ", word);
      for(int i = 0; i < options.length; i++)
      {
         System.out.printf("%8d ", userComp[i]);
      }
      System.out.println();
   }
   
   /**
   *Prints the statistics for the game
   *
   *@param input the scanner used to get input from the user
   *@param numUser the array of ints used to determine how many times each weapon was chosen(user)
   *@param numComp the array of ints used to determine how many times each weapon was chosen(comp)
   */
   public static void statistics(Scanner input, Scanner fileScanner, int[] numUser, int[] numComp)
   {
      String[] options = available(input, fileScanner);
   
      System.out.print("         ");
      for(int i = 0; i < options.length; i++)
      {
         options[i] = options[i].replace("-", " ");
         System.out.printf("%8s ", options[i]);
      }
      
      System.out.println();
      System.out.print("         ");
      for(int j = 0; j < options.length; j++)
      {
         System.out.printf("%8s ", "--------");
      }
      System.out.println();
      printTableStats(options, "Computer", numComp);
      printTableStats(options, "User", numUser);
   }
}