package nz.ac.vuw.ecs.swen225.gp20.maze;

public class Door extends Tile {

	Variation variation;

	public Door(TileName tileName, Location location, boolean canMoveOn, boolean canPickup, Variation variation) {
		super(tileName, location, canMoveOn, canPickup);
		this.variation = variation;
	}

	@Override
	protected Action interact(Actor actor) {
		if (actor instanceof Chap) {
			Chap c = (Chap) actor;
			String item = createItemName(TileName.KEY, variation);
			if (c.getInventory().containsKey(item)) {
				c.removeItem(item);
				kill();
				return Action.DOOR;
			}
			return Action.WALL;
		}
		return null;
	}

}
