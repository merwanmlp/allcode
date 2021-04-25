//Merwan M
//Computer Science III
//This program defines a Vulture
//Vulture.java
//Pi Day - 03/14/2020

import java.awt.*;

public class Vulture extends Bird
{
   private boolean hungry;
   
   /**
   *Constructs a vulture
   */
   public Vulture()
   {
      this.hungry = true;
   }
   
   @Override
   public Color getColor()
   {
      return Color.BLACK;
   }
   
   @Override
   public boolean eat()
   {
      return this.hungry;
   }
   
   @Override
   public Attack fight(String opponent)
   {
      this.hungry = false;
      return super.fight(opponent);
   }
}