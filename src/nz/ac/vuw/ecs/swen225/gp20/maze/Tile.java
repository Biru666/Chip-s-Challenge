package nz.ac.vuw.ecs.swen225.gp20.maze;

/**
 * Tile class representing a single tile thats in a location.
 * 
 * @author wangding1 300422014
 *
 */
public abstract class Tile {

	private TileName tileName;
	private boolean moveOn;
	private boolean pickUp;

	/**
	 * Constructor for creating an Tile on a single location
	 * 
	 * @param tileName  - Name of the tile.
	 * @param canMoveOn - boolean to check if an entity can move on.
	 * @param canPickUp - boolean to check of Chap can pick it up.
	 */
	public Tile(TileName tileName, boolean moveOn, boolean pickUp) {
		this.tileName = tileName;
		this.moveOn = moveOn;
		this.pickUp = pickUp;
	}

	/**
	 * @return the tileName
	 */
	public TileName getTileName() {
		return tileName;
	}

	/**
	 * @return the moveOn boolean
	 */
	public boolean canMoveOn() {
		return moveOn;
	}

	/**
	 * @return the pickUp boolean
	 */
	public boolean canPickUp() {
		return pickUp;
	}

}
