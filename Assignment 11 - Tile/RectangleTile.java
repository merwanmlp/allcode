//Merwan M
//Computer Science III
//Defines a tile in the shape of a rectangle
//RectangleTile.java
//03/28/2020

import java.awt.Graphics;
import java.awt.Color;

public class RectangleTile extends Tile
{
   /** 
   *Constructs a new rectangular shaped tile with the given coordinates, size, and color. 
   *
   *@param x The top left x coordinate of the bounding box of this tile
   *@param y The top left y coordinate of the bounding box of this tile
   *@param width The width of the bounding box of this tile
   *@param height The height of the bounding box of this tile
   *@param color The fill color of this tile
   */
   public RectangleTile(int x, int y, int width, int height, Color c)
   {
      super(x, y, width, height, c);
   }
   
   @Override
   public void draw(Graphics g)
   {
      g.setColor(super.getColor());
      g.fillRect(super.getX(), super.getY(), super.getWidth(), super.getHeight());
      g.setColor(Color.BLACK);
      g.drawRect(super.getX(), super.getY(), super.getWidth(), super.getHeight());
   }
   
   @Override
   public boolean isHit(int x, int y)
   {
      int topLeftX = super.getX();
      int topLeftY = super.getY();
      int width = super.getWidth();
      int height = super.getHeight();
      int bottomRightX = topLeftX + width;
      int bottomRightY = topLeftY + height;
      return x >= topLeftX && x <= bottomRightX && y >= topLeftY && y <= bottomRightY;
   }
}