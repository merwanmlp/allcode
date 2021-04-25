// Authors: Marty Stepp and Stuart Reges
//
// This class defines the methods necessary for a critter to be part of the simulation.
// Your critter animal classes 'extend' this class to add to its basic functionality.
//
// YOU DON'T NEED TO EDIT THIS FILE FOR YOUR ASSIGNMENT.
//
import java.awt.*; // for Color

public abstract class Critter {
	// The following five methods are the ones you should override in your subclasses.

        /**
        * Decides whether this critter eats
        * @return whether to eat or not
        */
	public boolean eat() {
		return false;
	}

        /**
        * Decides how to attack a given opponent
        * @param the toString of the opponent Critter
        * @return which attack to use on the opponent
        */
	public Attack fight(String opponent) {
		return Attack.FORFEIT;
	}

        /**
        * Decides what color this Critter is
        * @return the color this Critter should show
        */
	public Color getColor() {
		return Color.BLACK;
	}

        /**
        * Decides which direction this Critter should move
        * @return the direction to move
        */
	public Direction getMove() {
		return Direction.CENTER;
	}

        @Override
	public String toString() {
		return "?";
	}


    // I use these fields to implement the methods below such as getX and getNeighbor.
        private int x;
        private int y;
        private int width;
        private int height;
        private boolean alive = true;
        private boolean awake = true;
        private final String[] neighbors = {" ", " ", " ", " ", " "};

	// constants for directions
	public static enum Direction {
		NORTH, SOUTH, EAST, WEST, CENTER
	};

	// constants for fighting
	public static enum Attack {
		ROAR, POUNCE, SCRATCH, FORFEIT
	};

	// The following methods are provided to get information about the critter.
	// Technically the critter could call setWhatever() on itself,
	// but the game model ignores this anyway, so it's useless to do so.
	// These methods are declared 'final' so you can't override them.

	/**
        * Returns the height of the game simulation world.
        * @return the height of the world
        */
	public final int getHeight() {
		return height;
	}

	/**
        * Returns the animal that is 1 square in the given direction away
	* from this animal.  A blank space, " ", signifies an empty square.
        * @return the toString of the neighboring Critter
        */
	public final String getNeighbor(Direction direction) {
		return neighbors[direction.ordinal()];
	}

	/** 
        * Gets the width of the world
        * @return the width of the game simulation world.
        */
	public final int getWidth() {
		return width;
	}

	/** 
        * Gets this animal's current x-coordinate.
        * @return the x coordinate
        */
	public final int getX() {
		return x;
	}

	/**
        * Returns this animal's current y-coordinate.
        * @return the y coordinate
        */
	public final int getY() {
		return y;
	}
	
	/**
        * Returns true if this animal is currently alive.
	* This will return false if this animal has lost a fight and died.
        * @return whether the Critter is currently alive
        */
	public final boolean isAlive() {
		return alive;
	}

	/** Returns true if this animal is currently awake.
	* This will temporarily return false if this animal has eaten too much food
	* and fallen asleep.
        * @return if the Critter is awake
        */
	public final boolean isAwake() {
		return awake;
	}

	/** Sets whether or not this animal is currently alive.
	* This method is called by the simulator and not by your animal itself.
        * @param alive new value for whether the Critter is alive
        */
	public final void setAlive(boolean alive) {
		this.alive = alive;
	}

	/** Sets whether or not this animal is currently awake.
	* This method is called by the simulator and not by your animal itself.
        * @param awake new value for whether the Critter is awake
        */
	public final void setAwake(boolean awake) {
		this.awake = awake;
	}

	/** Sets the height of the game simulation world to be the given value,
	* so that future calls to getHeight will return this value.
	* This method is called by the simulator and not by your animal itself.
        * @param height the new height
        */
	public final void setHeight(int height) {
		this.height = height;
	}

	/** Sets the neighbor of this animal in the given direction to be the given value,
	* so that future calls to getNeighbor in that direction will return this value.
	* This method is called by the simulator and not by your animal itself.
        * @param direction the direction of the neighbor
        * @param value the value of the neighbor
        */
	public final void setNeighbor(Direction direction, String value) {
		neighbors[direction.ordinal()] = value;
	}

	/** Sets the width of the game simulation world to be the given value.
	* so that future calls to getWidth will return this value.
	* This method is called by the simulator and not by your animal itself.
        * @param width the new width
        */
	public final void setWidth(int width) {
		this.width = width;
	}

	/** Sets this animal's memory of its x-coordinate to be the given value.
	* so that future calls to getX will return this value.
	* This method is called by the simulator and not by your animal itself.
        * @param x the new x value
        */
	public final void setX(int x) {
		this.x = x;
	}

	/** Sets this animal's memory of its y-coordinate to be the given value.
	* so that future calls to getY will return this value.
	* This method is called by the simulator and not by your animal itself.
        * @param y the new y value
        */
	public final void setY(int y) {
		this.y = y;
	}

	// These methods are provided to inform you about the result of fights, sleeping, etc.
	// You can override these methods in your Lion to be informed of these events.

	/** called when you win a fight against another animal */
	public void win() {}

	/** called when you lose a fight against another animal, and die */
	public void lose() {}

	/** called when your animal is put to sleep for eating too much food */
	public void sleep() {}

	/** called when your animal wakes up from sleeping */
	public void wakeup() {}

	/** called when the game world is reset */
	public void reset() {}
	
	/** called when your critter mates with another critter */
	public void mate() {}
	
	/** called when your critter is done mating with another critter */
	public void mateEnd() {}
}
