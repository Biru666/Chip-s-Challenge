package nz.ac.vuw.ecs.swen225.gp20.maze;

/**
 * Location class which represents a single grid in the game
 * 
 * @author wangding1
 *
 */
public class Location {

	// 2D array coordinates
	int x, y;

	// Grid componants
	Tile tile;
	Actor actor;

	/**
	 * Empty tile constructor
	 * 
	 * @param x - the x coordinate in the 2D array
	 * @param y - the y coordinate in the 2D array
	 */
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
		tile = null;
		actor = null;

	}

	/**
	 * Defualt Tile Location constructor
	 * 
	 * @param x        - the x coordinate in the 2D array
	 * @param y        - the y coordinate in the 2D array
	 * @param tileName - the specified Enum TileName
	 */
	public Location(int x, int y, TileName tileName, Variation variation) {
		this.x = x;
		this.y = y;
		this.actor = null;
		this.tile = makeTile(tileName, variation);
	}

	private Tile makeTile(TileName tileName, Variation variation) {

		switch (tileName) {

		case WALL:
			return new Wall(tileName, false, false);
		case CHIP:
			return new Chip(tileName, true, true);
		case KEY:
			return new Key(tileName, true, true, variation);
		case DOOR:
			return new Door(tileName, false, false, variation);

		case GATE:
			return new Gate(tileName, false, false);

		case EXIT:
			return new Exit(tileName, true, false);

		}
		return null;
	}

	/**
	 * Default Actor Location constructor
	 * 
	 * @param x         - the x coordinate in the 2D array
	 * @param y         - the y coordinate in the 2D array
	 * @param actorName - the specified Enum ActorName
	 */
	public Location(int x, int y, ActorName actorName) {
		this.x = x;
		this.y = y;
		this.tile = null;
		this.actor = makeActor(actorName);
	}

	private Actor makeActor(ActorName actorName) {
		switch (actorName) {
		case CHAP:
			return new Chap(actorName, this);
		}
		return null;
	}

	
	
	
	public String toString() {
		return x + " " + y + "\n";
	}

//	/**
//	 * Location constructor for both Tile and Actor
//	 * 
//	 * @param x         - the x coordinate in the 2D array
//	 * @param y         - the y coordinate in the 2D array
//	 * @param tileName  - the specified Enum TileName
//	 * @param actorName - the specified Enum ActorName
//	 */
//	public Location(int x, int y, Tile tile, Actor actor) {
//		this.x = x;
//		this.y = y;
//		this.tile = tile;
//		this.actor = actor;
//	}

}
