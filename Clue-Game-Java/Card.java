public class Card
{
    private String name;
    private String identifier;

    /**
     * Constructs a Card
     * @param name Name of the card
     * @param identifier String representing whether card is room, weapon, or suspect
     */
    public Card(String name, String identifier)
    {
        this.name = name;
        this.identifier = identifier;
    }

    public String getName()
    {
        return this.name;
    }

    public String getIdentifier()
    {
        return this.identifier;
    }

    public String toString()
    {
        return this.name;
    }

    @Override
    public boolean equals(Object other)
    {
        if(other instanceof Card)
        {
            Card c = (Card) other;
            return c.name.equals(this.name);
        }
        return false;
    }
}
