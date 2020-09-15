package Maze;

public class Key extends Tile {
	Variation variation;

	public Key(TileName tileName, boolean canMoveOn, boolean canPickup, Variation variation) {
		super(tileName, canMoveOn, canPickup);
	}

}
