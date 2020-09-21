package nz.ac.vuw.ecs.swen225.gp20.maze;

public class Tile {

	protected TileName tileName;
	protected boolean canMoveOn;
	protected boolean canPickUp;

	public Tile(TileName tileName, boolean canMoveOn, boolean canPickUp) {
		this.tileName = tileName;
		this.canMoveOn = canMoveOn;
		this.canPickUp = canPickUp;
	}

}
