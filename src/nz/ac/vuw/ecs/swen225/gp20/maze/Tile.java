package nz.ac.vuw.ecs.swen225.gp20.maze;

/**
 * Tile class representing a single tile thats in a location.
 * 
 * @author wangding1 300422014
 *
 */
public abstract class Tile {

	protected TileName tileName;
	protected boolean canMoveOn;
	protected boolean canPickUp;

	/**
	 * Constructor for creating an Tile on a single location
	 * 
	 * @param tileName  - Name of the tile.
	 * @param canMoveOn - boolean to check if an entity can move on.
	 * @param canPickUp - boolean to check of Chap can pick it up.
	 */
	public Tile(TileName tileName, boolean canMoveOn, boolean canPickUp) {
		this.tileName = tileName;
		this.canMoveOn = canMoveOn;
		this.canPickUp = canPickUp;
	}

}
