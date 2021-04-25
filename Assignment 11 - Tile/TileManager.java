//Merwan M
//Computer Science III
//Adds, deletes, raises, draws, and shuffles tiles. 
//TileManager.java
//03/24/2020

import java.awt.*;
import java.util.*;

public class TileManager
{
   private ArrayList<Tile> tiles;
   
   /**
   *Constructs the tile list
   */
   public TileManager()
   {
      this.tiles = new ArrayList<>();
   }
   
   /**
   *Adds a tile to the end of the list
   *
   *@param tile the Tile to add to the list
   *@return whether the addition was successful(whether the tile was null or not)
   */
   public boolean addTile(Tile tile)
   {
      if(tile == null)
      {
         return false;
      }
      
      this.tiles.add(tile);
      return true;
   }
   
   /**
   *Draws all the tiles in the list
   *
   *@param g: the graphics to draw the tile
   */
   public void drawAll(Graphics g)
   {
      for(Tile t : this.tiles)
      {
         t.draw(g);
      }
   }
   
   /**
   *Moves the topmost tile found at the specified position to the top of the list
   *
   *@param x the x coordinate of the specified position(int)
   *@param y the y coordinate of the specified position(int)
   */
   public void raise(int x, int y)
   {
      Tile t = findDeleteTile(x, y, false);
      if(t != null) 
      {
         this.tiles.add(t);
      }
   }
   
   /**
   *Moves the topmost tile found at the specified position to the bottom of the list
   *
   *@param x the x-coordinate of the specified position(int)
   *@param y the y-coordinate of the specified position(int)
   */
   public void lower(int x, int y)
   {
      Tile t = findDeleteTile(x, y, false);
      if(t != null)
      {
         this.tiles.add(0, t);
      }
   }
   
   /**
   *Deletes the topmost tile found at the specified position
   *
   *@param x the x-coordinate of the specified position(int)
   *@param y the y-coordinate of the specified position(int)
   */
   public void delete(int x, int y)
   {
      findDeleteTile(x, y, false);
   }
   
   /**
   *Deletes all the tiles found at the specified position
   *
   *@param x the x-coordinate of the specified position(int)
   *@param y the y-coordinate of the specified position(int)
   */
   public void deleteAll(int x, int y)
   {
      findDeleteTile(x, y, true);
   }
   
   /**
   *Shuffles all the tiles in the list randomly and moves them to different locations
   *
   *@param width the width of the drawing panel
   *@param height the height of the drawing panel
   */
   public void shuffle(int width, int height)
   {
      Collections.shuffle(this.tiles);
      Random r = new Random();
      for(Tile t : this.tiles)
      {
         int xCoor = r.nextInt(width - t.getWidth());
         int yCoor = r.nextInt(height - t.getHeight());
         t.setX(xCoor);
         t.setY(yCoor);
      }
   }
   
   /**
   *Finds and deletes the tile at the specified location
   *
   *@param x the x-coordinate of the specified position(int)
   *@param y the y-coordinate of the specified position(int)
   *@param deleteAll boolean to decide whether to delete all the tiles at the specified position
   *@return the tile found at the position or null if there is no tile found
   */
   private Tile findDeleteTile(int x, int y, boolean deleteAll)
   {
      for(int i = this.tiles.size()-1; i >= 0; i--)
      {
         Tile t = this.tiles.get(i);
         if(t.isHit(x, y))
         {
            this.tiles.remove(t);
            if(!deleteAll)
            {
               //Returning it anyway just in case it needs to be added again to the list
               return t;
            }
         }
      }
      return null;
   }
}