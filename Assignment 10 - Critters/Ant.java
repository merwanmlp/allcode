//Merwan M
//Computer Science III
//This program defines an Ant
//Ant.java
//03/03/2020

import java.awt.*;

public class Ant extends Critter
{
   private boolean walkSouth;
   private boolean currentlyEast;
   
   /**
   *Constructs an ant
   *
   *@param walkSouth to determine whether ant should go southwest or southeast
   */
   public Ant(boolean walkSouth)
   {
      this.walkSouth = walkSouth;
      this.currentlyEast = true;
   }
   
   @Override
   public Direction getMove()
   {
      currentlyEast = !currentlyEast;  
      if(walkSouth && !currentlyEast)
      {
         return Direction.SOUTH;
      }
      
      else if(!walkSouth && !currentlyEast)
      {
         return Direction.NORTH;
      }
      
      return Direction.EAST;
   }
   
   @Override
   public Color getColor()
   {
      return Color.RED;
   }
   
   @Override
   public boolean eat()
   {
      return true;
   }
   
   @Override
   public Attack fight(String opponent)
   {
      return Attack.SCRATCH;
   }
   
   @Override
   public String toString()
   {
      return "%";
   }
}