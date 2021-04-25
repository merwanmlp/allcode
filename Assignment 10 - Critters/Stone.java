// Authors: Marty Stepp, Stuart Reges, Amanda O'Neal
//
// Stone objects are displayed as S and always stay put.
// They always pick ROAR in a fight.
//
import java.awt.*;

public class Stone extends Critter {
	@Override
	public Attack fight(String opponent) {
		return Attack.ROAR;    // good ol' ROAR... nothing beats that...except for pounce!
	}
        
	@Override
	public Color getColor() {
		return Color.GRAY;     // stones are gray in color
	}
        
	@Override
	public String toString() {
		return "S";            // the game displays a stone as an S
	}
}
