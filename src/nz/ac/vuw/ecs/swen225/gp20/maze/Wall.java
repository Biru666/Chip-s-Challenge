package nz.ac.vuw.ecs.swen225.gp20.maze;

/**
 * Wall class represents a Tile that cannot be moved on to.
 * 
 * @author wangding1 300422014
 *
 */
public class Wall extends Tile {

	/**
	 * @param tileName  - Enum TileName
	 * @param location  - Location
	 * @param canMoveOn - boolean
	 * @param canPickUp - booleansb
	 */
	public Wall(TileName tileName, Location location, boolean canMoveOn, boolean canPickUp) {
		super(tileName, location, canMoveOn, canPickUp);
	}

	@Override
	protected Action interact(Actor actor) {
		if (actor instanceof Chap)
			return Action.WALL;
		return null;
	}

}
