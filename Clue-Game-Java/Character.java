//Merwan M
//Computer Science III
//This program defines and creates a character
//Character.java
//04/19/2020

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
public class Character
{
    public final int INITPOSX = Clue.BOARD_WIDTH / 2;
    public final int INITPOSY = Clue.BOARD_HEIGHT / 2 + 1;
    private String name;
    protected int currentPosX;
    protected int currentPosY;
    protected ArrayList<Card> deck;

    /**
     *Constructs a character
     *
     * @param name the name of the character
     */
    public Character(String name)
    {
        this.name = name;
        this.currentPosX = INITPOSX;
        //Just to the right of the center
        this.currentPosY = INITPOSY;
        this.deck = new ArrayList<>();
    }

    public ArrayList<Card> getDeck()
    {
        return this.deck;
    }

    public void addCard(Card c)
    {
        this.deck.add(c);
    }

    public int getCurrentPosX()
    {
        return currentPosX;
    }

    public int getCurrentPosY()
    {
        return currentPosY;
    }

    public void setCurrentPosX(int newPos)
    {
        this.currentPosX = newPos;
    }

    public void setCurrentPosY(int newPos)
    {
        this.currentPosY = newPos;
    }

    public String toString()
    {
        return "";
    }

    public boolean equals(Object other)
    {
        if(other instanceof Character)
        {
            Character c = (Character) other;
            return c.name.equals(this.name);
        }
        return false;
    }

    public int roll(Random r)
    {
        return r.nextInt(6) + 1;
    }

    /**
     * Asks the user to make their suspect guesses
     * @param input Scanner to take input from the user
     * @param accuse Whether the user wants to accuse (the final accusation that ends the game) or suspect
     * @return All the user's guesses
     */
    public ArrayList<String> suspect(Scanner input, String accuse)
    {
        ArrayList<String> suspected = new ArrayList<String>();
        System.out.println("It is time to " + accuse + "!");

        System.out.print("Which person would you like to " + accuse + "? ");
        suspected.add(input.next());
        System.out.println();
        input.nextLine();

        System.out.print("With which weapon? ");
        suspected.add(input.next());
        System.out.println();
        input.nextLine();

        System.out.print("In which room? ");
        suspected.add(input.next());
        System.out.println();
        return suspected;
    }

    /**
     * Makes the user show a Card if they have one to the AI
     * @param input Scanner to take input from the user
     * @param askedFor ArrayList of cards that were suspected by the AI
     * @return The shown card
     */
    public Card showCard(Scanner input, ArrayList<Card> askedFor)
    {
        boolean foundACard = false;
        for(Card wanted : askedFor)
        {
            if(this.deck.contains(wanted))
            {
                foundACard = true;
            }
        }

        System.out.println("The AI suspected:");
        for(Card card : askedFor)
        {
            System.out.println(card.getIdentifier() + ": " + card + "\t");
        }

        if(!foundACard)
        {
            System.out.println("but you don't have any of the asked cards here.");
            return null;
        }

        ArrayList<Card> allOffer = new ArrayList<>();
        for(Card c : this.deck)
        {
            if(askedFor.contains(c))
            {
                allOffer.add(c);
            }
        }

        System.out.println("Type your answer using the words Suspect, Weapon, or Room");

        String userChoice = input.next();

        return getUserCard(allOffer, userChoice);
    }

    private Card getUserCard(ArrayList<Card> allOffer, String userWant)
    {
        for (Card c : allOffer) {
            if (c.getIdentifier().toLowerCase().equals(userWant.toLowerCase())) {
                return c;
            }
        }
        return null;
    }
}