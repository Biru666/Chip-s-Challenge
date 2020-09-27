package nz.ac.vuw.ecs.swen225.gp20.maze;

public class Gate extends Tile {

	public Gate(TileName tileName, Location location, boolean canMoveOn, boolean canPickup) {
		super(tileName, location, canMoveOn, canPickup);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Action interact(Actor actor) {
		if (actor instanceof Chap) {
			asd
			kill();
			return Action.DOOR;
		}
		return null;
	}

}
