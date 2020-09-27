package nz.ac.vuw.ecs.swen225.gp20.maze;

public class Exit extends Tile {

	public Exit(TileName tileName, Location location, boolean canMoveOn, boolean canPickup) {
		super(tileName, location, canMoveOn, canPickup);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Action interact(Actor actor) {
		if (actor instanceof Chap) {
			return Action.EXIT;
		}
		return null;
	}

}
