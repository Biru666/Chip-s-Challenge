package nz.ac.vuw.ecs.swen225.gp20.maze;

public class Lava extends Tile {

	/**
	 * Constructor which creates the Lava tile.
	 * 
	 * @param tileName - tileName enum
	 * @param location - location object
	 * @param moveOn   - if the tile can be moved on
	 * @param pickUp   - if the tile can be picked up
	 */
	public Lava(TileName tileName, Location location, boolean moveOn, boolean pickUp) {
		super(tileName, location, moveOn, pickUp);
	}

	@Override
	protected Action interact(Actor actor) {
		actor.kill();
		if (actor instanceof Chap)
			return Action.DIE;
		return null;
	}

}
