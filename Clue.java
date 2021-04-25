//Merwan M
//Computer Science III
//This program plays the game Clue with the user
//Clue.java
//04/19/2020

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

//IT SHOULD BE NOTED THAT THIS GAME DOESN'T WORK FOR MORE THAN TWO PLAYERS

public class Clue
{
    public static ArrayList<Card> theGuilty = new ArrayList<>();
    public static final int BOARD_HEIGHT = 15;
    public static final int BOARD_WIDTH = 15;
    public static final int ROOM_SIZEX = 3;
    public static final int ROOM_SIZEY = 3;
    public static final int NUM_OPTIONS = 3;
    public static void main(String[] args)
    {
        printIntro();
        System.out.println();
        Scanner in = new Scanner(System.in);
        Board b = new Board(BOARD_WIDTH, BOARD_HEIGHT);
        Random r = new Random();

        Deck d = addAllToDeck();
        System.out.println("The deck is:");
        System.out.println(d);
        System.out.println();

        AI artificial = new AI(d);
        theGuilty = d.theGuilty();

        System.out.println("Which character would you like to play as? ");
        System.out.println("1. Holmes - Gets to roll two dice instead of 1.");
        System.out.println("2. Stilton - You sniff out the suspect even before the investigation, but you can only roll up to 4.");
        System.out.println("3 - Dev Path");
        System.out.print("Type your number here: ");
        System.out.println();
        int picked = MyUtils.getNumber(in, "", 1, NUM_OPTIONS);
        Character userPick = new Character("This doesn't matter");

        if(picked == 1)
        {
            userPick = new Holmes();
            System.out.println("Since you're playing as Sherlock himself, you get to roll two virtual dice instead of one!");
        }

        else if(picked == 2)
        {
            userPick = new Stilton();
            System.out.println("Geronimo Stilton can sniff out the suspect automatically! However, ");
            System.out.println("you are a mouse, so you move slower than the average human. You can only");
            System.out.println("roll up to 4. Anyway, here is the murderer.");
            ((Stilton) userPick).displayGuiltyOne(theGuilty);
        }

        else if(picked == 3)
        {
            System.out.println("Wow. What a loser. I cannot believe that you actually took the dev path");
            System.out.println("You literally gave up BEFORE THE GAME STARTED");
            System.out.println("For that... absolutely nothing will happen to you.");
            System.out.println("But still, don't cheat.");
            System.out.println("Anyway, because the developer made this as an absolute troll option, here are the guilty");
            System.out.println(theGuilty);
            System.exit(0);
        }
        addAll(userPick, artificial, d);
        play(artificial, in, r, b, userPick);
    }

    public static void addAll(Character userPick, AI artificial, Deck d)
    {
        //Dealing cards to user and AI
        while(d.getDeck().size() > 0)
        {
            Card c = d.dealCard();
            userPick.addCard(c);

            if(d.getDeck().size() == 0)
            {
                break;
            }

            Card aic = d.dealCard();
            artificial.addCardToDeck(aic);
        }
        artificial.initializeSuspect();
    }

    /**
     * Adds all the existing cards to the deck and shuffles
     * @return The shuffled deck with all the cards
     */
    public static Deck addAllToDeck()
    {
        Deck fakeDeck = new Deck();
        fakeDeck.addCard(new Mustard());
        fakeDeck.addCard(new Scarlett());
        //fakeDeck.addCard(new Peacock());

        fakeDeck.addCard(new Gun());
        fakeDeck.addCard(new Candlestick());
        //fakeDeck.addCard(new Rope());

        fakeDeck.addCard(new Conservatory());
        //fakeDeck.addCard(new Library());
        //fakeDeck.addCard(new Hall());
        Deck.shuffle(fakeDeck.getDeck());
        return fakeDeck;
    }
    /**
     * Prints the intro to the game
     */
    public static void printIntro()
    {
        System.out.println("Welcome to the modified game of Clue!");
        System.out.println("This game's rules are fairly similar to that of the game Clue, with a few adjustments");
        System.out.println("The board is also slightly different than normal and the rooms are located in the four corners of the board");
        System.out.println("Also, to avoid exploitation, every time you enter a room, you get reset back to your initial position.");
        System.out.println("Note: You can 'trick' the AI by simply lying when you have to show a card. Don't do that though, it's cheating.");
        System.out.println("If you don't know how to play Clue, I'm sure you'll pick it up eventually. Or, you can read the official instructions");
        System.out.println("Everything else will be shown as the game goes on. Good luck!");
    }

    public static void showUserCards(Character c)
    {
        System.out.println("Your cards are: ");
        for(int i = 0; i < c.getDeck().size(); i++)
        {
            //This if statement only exists to get rid of the final comma
            if(i == c.getDeck().size() - 1)
            {
                System.out.println(c.getDeck().get(i));
                break;
            }

            System.out.print(c.getDeck().get(i) + ", ");
        }
    }

    /**
     * Plays the whole game
     * @param art the artificial intelligence opponent
     * @param input the Scanner to take user input
     * @param r Random object for dice rolls
     * @param b Board to use as the virtual board
     * @param c Character that user is playing as
     */
    public static void play(AI art, Scanner input, Random r, Board b, Character c)
    {
        System.out.println();

        while(true)
        {
            System.out.println();
            b.putPlayer(c.toString(), c.getCurrentPosX(), c.getCurrentPosY());
            b.putPlayer(art.toString(), art.getPosX(), art.getPosY());
            System.out.println(b.toString());
            System.out.println();

            showUserCards(c);

            System.out.println();
            System.out.println("It is your turn to roll.");
            System.out.print("Type anything to roll: ");
            input.next();
            input.nextLine();
            int roll = c.roll(r);
            System.out.println("You rolled a " + roll + "! Where would you like to move?");
            int[] newPos = b.move(input, roll, c);
            c.setCurrentPosX(newPos[0]);
            c.setCurrentPosY(newPos[1]);
            int userPosX = c.getCurrentPosX();
            int userPosY = c.getCurrentPosY();

            if (isInRoom(userPosX, userPosY))
            {
                //The program will exit if user accuses
                ArrayList<String> suspected = accuse(c, input);
                boolean hasShownCard = false;
                for(String s : suspected)
                {
                    Card shown = art.showCard(s);
                    if(shown != null)
                    {
                        hasShownCard = true;
                        System.out.println("The card that has been shown is " + shown);
                        break;
                    }
                }

                if(!hasShownCard)
                {
                    System.out.println("The AI has nothing to show you.");
                }
                b.removePlayer(c.getCurrentPosX(), c.getCurrentPosY());
                c.setCurrentPosY(c.INITPOSY);
                c.setCurrentPosX(c.INITPOSX);
            }

            ArrayList<Card> aiTurn = art.turn(c, input, r, b);
            if(aiTurn != null)
            {
                if(art.canAccuse()){
                    if (isRightAI(theGuilty, aiTurn))
                    {
                        System.out.println("Sorry, the AI won! You had a mediocre attempt. Here are the real answers:");
                    }

                    else
                    {
                        System.out.println("Well the AI messed up somewhere, so you win by default. Don't get too cocky though :)");
                    }

                    for (Card correct : theGuilty)
                    {
                        System.out.println(correct.getIdentifier() + ": " + correct.getName());
                    }

                    System.exit(0);
                }
            }
        }
    }

    public static boolean isInRoom(int userPosX, int userPosY)
    {
        //There is absolutely no need to read this whole line
        return (userPosX <= 0+ROOM_SIZEX && userPosY <= 0+ROOM_SIZEY) || (userPosX <= 0+ROOM_SIZEX && userPosY >= BOARD_HEIGHT - 1 - ROOM_SIZEY) || (userPosX >= BOARD_WIDTH - 1-ROOM_SIZEX && userPosY <= 0+ROOM_SIZEY) || (userPosX >= BOARD_WIDTH - 1-ROOM_SIZEX && userPosY >= BOARD_HEIGHT - 1-ROOM_SIZEY);
    }

    public static boolean isRightAI(ArrayList<Card> guilty, ArrayList<Card> susp)
    {
        return guilty.equals(susp);
    }

    public static boolean isRight(ArrayList<Card> guilty, ArrayList<String> suspects)
    {
        for(int i = 0; i < guilty.size(); i++)
        {
            if(guilty.get(i).getName().indexOf(' ') != -1)
            {
                if (!(guilty.get(i).getName().substring(0, guilty.get(i).getName().indexOf(' ')).equals(suspects.get(i))))
                {
                    return false;
                }
            }

            else
            {
                if(!(guilty.get(i).getName().equals(suspects.get(i))))
                {
                    return false;
                }
            }
        }
        return true;
    }

    public static ArrayList<String> accuse(Character c, Scanner input)
    {
        System.out.println("You have entered a room!");
        showUserCards(c);
        System.out.print("Would you like to accuse (Y/N)? ");
        String accuse = input.next();
        ArrayList<String> theGuessed;
        if(accuse.toLowerCase().charAt(0) == 'y')
        {
            theGuessed = c.suspect(input, "accuse");
            boolean correct = isRight(theGuilty, theGuessed);
            if(correct)
            {
                System.out.println("You Won!");
            }

            else
            {
                System.out.println("Well, you lost. Here are the real answers.");
                for(Card card : theGuilty)
                {
                    System.out.println(card.getIdentifier() + ": " + card + "\t");
                }
            }

            System.exit(0);

        }

        else
        {
            theGuessed = c.suspect(input, "suspect");
        }
        return theGuessed;
    }
}