//Merwan M
//Computer Science III
//This program defines an option that the user has to play as - Sherlock Holmes
//Holmes.java
//04/19/2020

import java.util.Random;

public class Holmes extends Character
{
    /**
     * Constructs a Sherlock Holmes
     */
    public Holmes()
    {
        super("Detective Sherlock Holmes");
    }

    @Override
    public String toString()
    {
        return "S";
    }

    @Override
    public int roll(Random r)
    {
        return r.nextInt(11) + 2;
    }
}