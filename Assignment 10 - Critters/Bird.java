//Merwan M
//Computer Science III
//This program defines a Bird
//Bird.java
//03/12/2020

import java.awt.*;

public class Bird extends Critter
{
   private Direction lastMove;
   
   protected int numTimes = 0;
   
   /**
   *Constructs a bird
   */
   public Bird()
   {
      this.lastMove = Direction.NORTH;
   }
   
   @Override
   public Direction getMove()
   {
      if(this.lastMove == Direction.NORTH && numTimes == 3)
      {
         numTimes = 0;
         this.lastMove = Direction.EAST;
      }
      
      else if(this.lastMove == Direction.EAST && numTimes == 3)
      {
         numTimes = 0;
         this.lastMove = Direction.SOUTH;
      }
      
      else if(this.lastMove == Direction.SOUTH && numTimes == 3)
      {
         numTimes = 0;
         this.lastMove = Direction.WEST;
      }
      
      else if(this.lastMove == Direction.WEST && numTimes == 3)
      {
         numTimes = 0;
         this.lastMove = Direction.NORTH;
      }
      numTimes++;
      return this.lastMove;
   }
   
   @Override
   public Color getColor()
   {
      return Color.BLUE;
   }
   
   @Override
   public boolean eat()
   {
      return false;
   }
   
   @Override
   public Attack fight(String opponent)
   {  
      if(opponent.equals("%"))
      {
         return Attack.ROAR;
      }
      return Attack.POUNCE;
   }
   
   @Override
   public String toString()
   {
      if(this.lastMove == Direction.EAST)
      {
         return ">";
      }
      
      else if(this.lastMove == Direction.SOUTH)
      {
         return "V";
      }
      
      else if(this.lastMove == Direction.WEST)
      {
         return "<";
      }
      
      return "^";
   }
   
   
}

