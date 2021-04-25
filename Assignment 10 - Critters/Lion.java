//Merwan M
//Computer Science III
//This program defines a lion
//Lion.java
//03/03/2020

import java.awt.*;

public class Lion extends Critter
{
   private boolean currentlyWest;
   private boolean isAtCorner;
   private int numWait;
   
   //I'm lazy, so these are just so I don't have to type
   //Direction.WHATEVER every single time
   protected Direction NORTH = Direction.NORTH;
   protected Direction SOUTH = Direction.SOUTH;
   protected Direction EAST = Direction.EAST;
   protected Direction WEST = Direction.WEST;
   protected Direction CENTER = Direction.CENTER;
   
   /**
   *Constructs a lion
   */
   public Lion()
   {
      this.currentlyWest = true;
      this.isAtCorner = false;
      this.numWait = 10;
   }
   
   @Override
   public boolean eat()
   {
      //Here's my thinking: I get a point for every piece of food I eat and lose a point
      //if I get eaten. So if I eat every piece of food I will always gain 0 or 1 points
      //which isn't too bad.
      return true;
   }
   
   @Override
   public Attack fight(String opponent)
   {
      //If it's an ant
      if(opponent.equals("%"))
      {
         return Attack.ROAR;
      }
      
      //If it's a bird/vulture
      else if((opponent.equals("V") || opponent.equals(">") || opponent.equals("^") 
               || opponent.equals("<")) 
               && !(this.toString().equals("%")))
      {
         return Attack.SCRATCH;
      }
      
      //If it's a stone
      else if(opponent.equals("S"))
      {
         return Attack.POUNCE;
      }
      
      else
      {
         try
         {
            int hippo = Integer.parseInt(opponent);
         }
         
         catch(NumberFormatException e)
         {
            return Attack.POUNCE;
         }
      }
      
      return Attack.SCRATCH;
   }
   
   @Override
   public Color getColor()
   {
      return Color.BLUE;
   }
   
   @Override
   public Direction getMove()
   {
      //Trying to get them all to the same place to mate
      if(super.getX() == 0 && super.getY() == 0)
      {
         //But I still want them to move around and kill other critters
         //so it can do that for some time
         this.numWait--;
         if(this.numWait <= 0)
         {
            this.numWait = 10;
            return NORTH;  
         }
         return CENTER;
      }
      
      if(super.getX() == 0 && !(super.getY() == 0))
      {
         return NORTH;
      }
      
      else if(!(super.getX() == 0) && super.getY() == 0)
      {
         return WEST;
      }
      
      else
      {
         if(this.currentlyWest)
         {
            this.currentlyWest = false;
            return NORTH;
         }
         this.currentlyWest = true;
         return WEST;
      }
   }
   
   @Override
   public String toString()
   {
      //If there is a neighboring critter, use their string method instead.
      //If multiple critters are surrounding me, I'm probably screwed anyway,
      //but the string method will be changed to the first critter found
      if(super.getNeighbor(NORTH).equals(".") || super.getNeighbor(SOUTH).equals(".")
         || super.getNeighbor(EAST).equals(".") || super.getNeighbor(WEST).equals("."))
      if(super.getNeighbor(NORTH) == null)
      {
         if(super.getNeighbor(SOUTH) == null)
         {
            if(super.getNeighbor(WEST) == null)
            {
               if(super.getNeighbor(EAST) == null)
               {
                  return "";
               }
               return super.getNeighbor(EAST).toString();
            }
            return super.getNeighbor(WEST).toString();
         }
         return super.getNeighbor(SOUTH).toString();
      }
      return super.getNeighbor(NORTH).toString();
   }   
}   
