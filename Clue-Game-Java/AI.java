//Merwan M
//Computer Science III
//This program represents the AI for the game of Clue
//AI.java
//05/21/2020

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


//IT SHOULD BE NOTED THAT THE AI IS REALLY BAD AT THE GAME IF EACH PLAYER ONLY RECEIVES ONE CARD
//ITS WHOLE STRATEGY IS BASED OFF OF HAVING TWO OR MORE CARDS
public class AI
{
    private ArrayList<Card> deck;
    private ArrayList<Card> haveToSuspect;
    private ArrayList<Card> guilty;
    private ArrayList<Card> deckGuilty;
    public final int INITPOSX = Clue.BOARD_WIDTH / 2;
    public final int INITPOSY = Clue.BOARD_HEIGHT / 2 - 1;
    private int posX;
    private int posY;


    public final int MAX_DICE_ROLL = 6;
    public AI(Deck d)
    {
        this.deck = new ArrayList<>();
        this.guilty = new ArrayList<>();
        this.deckGuilty = new ArrayList<>();

        //Essentially, haveToSuspect will contain everything not in the AI's deck or in the guilty deck
        this.haveToSuspect = new ArrayList<>();
        for(Card c : d.getDeck())
        {
            this.haveToSuspect.add(c);
        }

        this.posX = INITPOSX;
        //Just to the left of the center of the board
        this.posY = INITPOSY;
    }

    public String toString()
    {
        return "A";
    }
    public int getPosX()
    {
        return this.posX;
    }

    public int getPosY()
    {
        return this.posY;
    }

    public void addCardToDeck(Card c)
    {
        this.deck.add(c);
        this.deckGuilty.add(c);
    }

    public void initializeSuspect()
    {
        for(int i = 0; i < this.deck.size(); i++)
        {
            if(this.haveToSuspect.contains(this.deck.get(i)))
            {
                this.haveToSuspect.remove(this.deck.get(i));
                i--;
            }
        }
    }

    public ArrayList<Card> turn(Character c, Scanner input, Random r, Board b)
    {
        move(r, b);

        if(Clue.isInRoom(this.posX, this.posY))
        {
            if(canAccuse())
            {
                return accuse();
            }
            suspect(c, input);
            b.removePlayer(this.posX, this.posY);
            this.posX = INITPOSX;
            this.posY = INITPOSY;
            b.putPlayer("A", this.posX, this.posY);
        }
        return null;
    }

    private void organizeGuiltyList()
    {
        Card[] organized = new Card[3];
        for(Card c : this.guilty)
        {
            if(c.getIdentifier().equals("Suspect"))
            {
                organized[0] = c;
            }

            else if(c.getIdentifier().equals("Weapon"))
            {
                organized[1] = c;
            }

            else
            {
                organized[2] = c;
            }
        }

        for(int i = 0; i < organized.length; i++)
        {
            System.out.println(this.guilty.size());
            this.guilty.set(i, organized[i]);
        }
    }

    public void suspect(Character ch, Scanner input)
    {
        boolean foundASuspect = false;
        boolean foundAWeapon = false;
        boolean foundARoom = false;

        ArrayList<Card> toSuspect = new ArrayList<>();

        //Each cycle of the loop only adds one card to toSuspect
        for(Card c : this.deckGuilty)
        {
            foundASuspect = addToSuspect(toSuspect, c, foundASuspect, "Suspect");

            foundAWeapon = addToSuspect(toSuspect, c, foundAWeapon, "Weapon");

            foundARoom = addToSuspect(toSuspect, c, foundARoom, "Room");

            if(toSuspect.size() >= 2)
            {
                break;
            }
        }

        //toSuspect will always have a length of 2 or below before this loop
        for(int i = 0; i < this.haveToSuspect.size(); i++)
        {
            Card c = this.haveToSuspect.get(i);
            String identifier = c.getIdentifier();

            if(foundASuspect && identifier.equals("Suspect"))
            {
                continue;
            }

            else if(foundAWeapon && identifier.equals("Weapon"))
            {
                continue;
            }

            else if(foundARoom && identifier.equals("Room"))
            {
                continue;
            }

            if(toSuspect.size() == 3)
            {
                break;
            }

            toSuspect.add(c);
            if(identifier.equals("Suspect"))
            {
                foundASuspect = true;
            }

            else if(identifier.equals("Weapon"))
            {
                foundAWeapon = true;
            }

            else if(identifier.equals("Room"))
            {
                foundARoom = true;
            }
            this.haveToSuspect.remove(c);
        }

        Card found = ch.showCard(input, toSuspect);
        if(found == null)
        {
            for(Card wanted : toSuspect)
            {
                if (!this.deckGuilty.contains(wanted))
                {
                    this.guilty.add(wanted);
                    //So the AI doesn't actually have a guilty card in its deck, but it pretends it does because it knows
                    //for a fact that the user doesn't have it, so for its strategy of picking two cards that the user doesn't
                    //have to work, AI has to pretend that the card is there. Thats the function of deckGuilty
                    //Adding it to the front to avoid only suspecting two cards
                    this.deckGuilty.add(0, wanted);
                }
            }
        }
    }

    private boolean addToSuspect(ArrayList<Card> toSuspect, Card c, boolean found, String desired)
    {
        if(!found)
        {
            if(c.getIdentifier().equals(desired))
            {
                toSuspect.add(c);
                return true;
            }
        }
        return found;
    }

    public Card showCard(String asked)
    {
        for(Card c : this.deck)
        {
            String newC = c.getName();
            if(c.getName().contains(" "))
            {
                newC = c.getName().substring(0, c.getName().indexOf(" "));
            }
            if(asked.equals(newC))
            {
                return c;
            }
        }
        return null;
    }

    public boolean canAccuse()
    {
        return this.guilty.size() == 3;
    }

    public ArrayList<Card> accuse()
    {
        organizeGuiltyList();
        return this.guilty;
    }

    public void move(Random r, Board b)
    {
        //The way this works is that the AI commits to a direction until they are on the same row/column, then it switches
        //to a different direction.
        int diceRoll = roll(r);
        b.removePlayer(this.posX, this.posY);

        int[] closest = getClosestRoom(b);

        int distToEdgeX = closest[0] - this.posX;
        int distToEdgeY = closest[1] - this.posY;
        int[] newCoord;
        if(distToEdgeX == 0)
        {
            newCoord = moveGeneral(diceRoll, distToEdgeY, this.posY, this.posX);
            this.posY = newCoord[0];
            this.posX = newCoord[1];

        }

        else
        {
            newCoord = moveGeneral(diceRoll, distToEdgeX, this.posX, this.posY);
            this.posX = newCoord[0];
            this.posY = newCoord[1];
        }
        b.putPlayer("A", this.posX, this.posY);
    }

    /**
     * Moves the AI in coord1's direction
     * @param diceRoll Number that was rolled on dice
     * @param distToEdge Distance to edge in the direction of coord1
     * @param coord1
     * @param coord2
     * @return
     */
    private int[] moveGeneral(int diceRoll, int distToEdge, int coord1, int coord2)
    {
        if(Math.abs(distToEdge) < diceRoll)
        {
            coord1 += distToEdge;
            int distLeftToTravel = diceRoll - Math.abs(distToEdge);

            while(distLeftToTravel > 0)
            {
                //Just sort of move back and forth between edge and right next to the edge
                coord1 -= 1;
                coord1 += 1;
                distLeftToTravel -= 2;
            }

            if(distLeftToTravel == 1)
            {
                //Normally this wouldn't work, but I'm assuming that the board is a square
                if(!Clue.isInRoom(coord1, coord2))
                {
                    coord2--;
                }

                else
                {
                    if(coord2 == 0)
                    {
                        coord2++;
                    }

                    else
                    {
                        coord2--;
                    }
                }
            }
        }

        else
        {
            coord1 += diceRoll;
        }
        //Only gonna have x and y.
        int[] toReturn = new int[2];
        toReturn[0] = coord1;
        toReturn[1] = coord2;
        return toReturn;
    }

    private int[] getClosestRoom(Board b)
    {
        int roomX = Clue.ROOM_SIZEX;
        int roomY = Clue.ROOM_SIZEY;
        double mag1 = Math.sqrt(Math.pow((posY - (0+roomY)), 2) + Math.pow((posX - (0+roomX)), 2));
        double mag2 = Math.sqrt(Math.pow((posY - (b.getHeight() - 1 - roomY)), 2) + Math.pow((posX - (0+roomX)), 2));
        double mag3 = Math.sqrt(Math.pow((posY - (0+roomY)), 2) + Math.pow((posX - (b.getWidth() - 1 - roomX)), 2));
        double mag4 = Math.sqrt(Math.pow((posY - (b.getHeight() - 1 - roomY)), 2) + Math.pow((posX - (b.getWidth() - 1 - roomX)), 2));

        double[] mags = {mag1, mag2, mag3, mag4};
        Arrays.sort(mags);

        //Taking the lowest magnitude, but since it's a sorted array, we don't know which one that corresponds to
        double theMag = mags[0];

        int[] toReturn = new int[2];
        //This next section is clunky, I know. I feel like there's an easier way to do this
        if(Math.abs(theMag - mag1) < 0.0001)
        {
            toReturn[0] = 0;
            toReturn[1] = 0;
        }

        else if(Math.abs(theMag - mag2) < 0.0001)
        {
            toReturn[0] = 0;
            toReturn[1] = b.getHeight() - 1;
        }

        else if(Math.abs(theMag - mag2) < 0.0001)
        {
            toReturn[0] = b.getWidth() - 1;
            toReturn[1] = 0;
        }

        else
        {
            toReturn[0] = b.getWidth() - 1;
            toReturn[1] = b.getHeight() - 1;
        }
        return toReturn;
    }

    private int roll(Random r)
    {
        return r.nextInt(MAX_DICE_ROLL) + 1;
    }
}
