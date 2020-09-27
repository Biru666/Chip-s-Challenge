package nz.ac.vuw.ecs.swen225.gp20.maze;

/**
 * Location class which represents a single grid in the game
 * 
 * @author wangding1 300422014
 *
 */
public class Location {

	// 2D array coordinates
	private int x, y;

	// Grid componants
	private Tile tile;
	private Actor actor;

	/**
	 * Empty tile constructor.
	 * 
	 * @param x - the x coordinate in the 2D array.
	 * @param y - the y coordinate in the 2D array.
	 */
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
		tile = null;
		actor = null;

	}

	/**
	 * Defualt Tile constructor.
	 * 
	 * @param x         - the x coordinate in the 2D array.
	 * @param y         - the y coordinate in the 2D array.
	 * @param tileName  - the specified Enum TileName.
	 * @param variation - the specified Enum variation, null if no need.
	 */
	public Location(int x, int y, TileName tileName, Variation variation) {
		this.x = x;
		this.y = y;
		this.actor = null;

		// Creating Tile object: Tile("name","canMoveOn",canPickUp","variation")
		switch (tileName) {
		case WALL:
			this.tile = new Wall(tileName, this, false, false);
			break;
		case CHIP:
			this.tile = new Chip(tileName, this, true, true);
			break;
		case KEY:
			this.tile = new Key(tileName, this, true, true, variation);
			break;
		case DOOR:
			this.tile = new Door(tileName, this, false, false, variation);
			break;
		case GATE:
			this.tile = new Gate(tileName, this, false, false);
			break;
		case EXIT:
			this.tile = new Exit(tileName, this, true, false);
			break;
		case INFO:
			this.tile = new Info(tileName, this, true, false);
			break;
		default:
			this.tile = null;
		}
	}

	/**
	 * Default Actor Location constructor.
	 * 
	 * @param x         - the x coordinate in the 2D array
	 * @param y         - the y coordinate in the 2D array
	 * @param actorName - the specified Enum ActorName
	 */
	public Location(int x, int y, ActorName actorName) {
		this.x = x;
		this.y = y;
		this.tile = null;

		// Creating actor object
		switch (actorName) {
		case CHAP:
			this.actor = new Chap(actorName, this);
			break;
		default:
			this.actor = null;
		}
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @return the tile
	 */
	public Tile getTile() {
		return tile;
	}

	/**
	 * @param tile the tile to set
	 */
	public void setTile(Tile tile) {
		this.tile = tile;
	}

	/**
	 * @return the actor
	 */
	public Actor getActor() {
		return actor;
	}

	/**
	 * @param actor the actor to set
	 */
	public void setActor(Actor actor) {
		this.actor = actor;
	}

	/**
	 * testing for x and y location
	 */
	public String toString() {
		return x + " " + y + "\n";
	}

}
