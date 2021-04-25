import java.util.ArrayList;
import java.util.Random;

public class Stilton extends Character
{
    public Stilton()
    {
        super("Geronimo Stilton");
    }

    public void displayGuiltyOne(ArrayList<Card> guilty)
    {
        System.out.println(guilty.get(0));
    }

    @Override
    public String toString()
    {
        return "G";
    }

    @Override
    public int roll(Random r)
    {
        return r.nextInt(4) + 1;
    }
}
