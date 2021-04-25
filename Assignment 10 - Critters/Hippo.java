//Merwan M
//Computer Science III
//This program defines a Hippo
//Hippo.java
//03/13/2020

import java.awt.*;
import java.util.*;

public class Hippo extends Critter
{
   private int hunger;
   private Direction lastMove;
   
   protected int numSteps = 1;
   protected int eaten = 0;
   
   /**
   *Constructs a Hippo
   *
   *@param hunger the level of hunger that the hippo has
   */
   public Hippo(int hunger)
   {
      this.hunger = hunger;
   }
   
   @Override
   public Direction getMove()
   {
      if(numSteps == 1)
      {
         Random r = new Random(); //Only four directions
         int direction = r.nextInt(4) + 1;
         
         if(direction == 1)
         {
            this.lastMove = Direction.NORTH;
         }
         
         else if(direction == 2)
         {
            this.lastMove = Direction.SOUTH;
         }
         
         else if(direction == 3)
         {
            this.lastMove = Direction.EAST;
         }
         
         else if(direction == 4)
         {
            this.lastMove = Direction.WEST;
         } 
      }
      
      else if(numSteps == 5)
      {
         numSteps = 0;
      }
      numSteps++;
      return this.lastMove;
   }
   
   @Override
   public Color getColor()
   {
      if(this.eat())
      {
         return Color.GRAY;
      }
      return Color.WHITE;
   }
   
   @Override
   public boolean eat()
   {
      if(eaten < this.hunger)
      {
         eaten++;
         return true;
      }
      return false;
   }
   
   @Override
   public Attack fight(String opponent)
   {
      if(this.eat())
      {
         return Attack.SCRATCH;
      }
      return Attack.POUNCE;
   }
   
   @Override
   public String toString()
   {
      int numLeft = this.hunger - eaten;
      return "" + numLeft;
   }
}