//Merwan M
//Computer Science III
//This program represents a deck of cards (Clue cards, not regular cards)
//Deck.java
//04/19/2020

import java.util.ArrayList;
import java.util.Collections;

public class Deck
{
    private ArrayList<Card> deck;

    public Deck()
    {
        this.deck = new ArrayList<>();
    }

    public String toString()
    {
        String toReturn = "";
        for(Card c : this.deck)
        {
            //Getting rid of the final comma
            if(this.deck.get(this.deck.size() - 1).equals(c))
            {
                toReturn += c.toString();
                break;
            }
            toReturn += c.toString() + ", ";
        }
        return toReturn;
    }
    public static void shuffle(ArrayList<Card> deck)
    {
        Collections.shuffle(deck);
    }

    public ArrayList<Card> getDeck()
    {
        return this.deck;
    }

    public void addCard(Card c)
    {
        this.deck.add(c);
    }

    /**
     * Randomly chooses one person, room, and weapon that the murder was committed in
     * @return the ArrayList containing the guilty cards
     */
    public ArrayList<Card> theGuilty()
    {
        //The guilty
        ArrayList<Card> guilty = new ArrayList<>();
        //Separate into three piles - the characters, the weapons, and the rooms
        ArrayList<Card> characters = new ArrayList<>();
        ArrayList<Card> weapons = new ArrayList<>();
        ArrayList<Card> rooms = new ArrayList<>();
        //Sort through the identifiers to divide the cards and add them to respective ArrayLists
        for(Card c : this.deck)
        {
            if(c.getIdentifier().equals("Suspect"))
            {
                characters.add(c);
            }

            else if(c.getIdentifier().equals("Weapon"))
            {
                weapons.add(c);
            }

            else
                rooms.add(c);
        }

        shuffle(characters);
        shuffle(weapons);
        shuffle(rooms);

        //Take the top card (i.e. the end of the list, and add to guilty
        Card criminal = characters.get(characters.size() - 1);
        guilty.add(criminal);
        this.deck.remove(criminal);

        Card weapon = weapons.get(weapons.size()-1);
        guilty.add(weapon);
        this.deck.remove(weapon);

        Card room = rooms.get(rooms.size() - 1);
        guilty.add(room);
        this.deck.remove(room);

        return guilty;
    }

    public Card dealCard()
    {
        Card c = this.deck.get(this.deck.size()-1);
        this.deck.remove(this.deck.size()-1);
        return c;
    }
}

