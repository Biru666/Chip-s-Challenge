package nz.ac.vuw.ecs.swen225.gp20.maze;

public class Door extends Tile {

	Variation variation;

	public Door(TileName tileName, boolean canMoveOn, boolean canPickup, Variation variation) {
		super(tileName, canMoveOn, canPickup);
		this.variation = variation;
	}

}
