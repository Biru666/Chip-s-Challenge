package nz.ac.vuw.ecs.swen225.gp20.maze;

/**
 * Tile class representing a single tile thats in a location.
 * 
 * @author wangding1 300422014
 *
 */
public abstract class Tile {

	private TileName tileName;
	private Location location;
	private boolean moveOn;
	private boolean pickUp;

	/**
	 * Constructor for creating an Tile on a single location
	 * 
	 * @param tileName - Name of the tile.
	 * @param location - Location of the tile.
	 * @param moveOn   - boolean to check if an entity can move on.
	 * @param pickUp   - boolean to check of Chap can pick it up.
	 */
	public Tile(TileName tileName, Location location, boolean moveOn, boolean pickUp) {
		this.tileName = tileName;
		this.location = location;
		this.moveOn = moveOn;
		this.pickUp = pickUp;
	}

	/**
	 * Performs different interactions based on the current child class of Tile
	 * 
	 * @param actor - Actor
	 */
	protected abstract void interact(Actor actor);

	/**
	 * Destoryes the current tile by deleting its refrences
	 */
	public void kill() {
		location.setTile(null);
		location = null;
	}

	/**
	 * Create an String based on the tile name and variation
	 * 
	 * @param variation
	 * @return
	 */
	public String createItemName(Variation variation) {
		return tileName.toString() + " " + variation.toString();
	}

	/**
	 * @return the tileName
	 */
	public TileName getTileName() {
		return tileName;
	}

	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
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
