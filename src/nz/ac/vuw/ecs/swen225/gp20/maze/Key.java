package nz.ac.vuw.ecs.swen225.gp20.maze;

public class Key extends Tile {
	Variation variation;

	public Key(TileName tileName, boolean canMoveOn, boolean canPickup, Variation variation) {
		super(tileName, canMoveOn, canPickup);
	}

}
