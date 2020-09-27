package nz.ac.vuw.ecs.swen225.gp20.maze;

/**
 * Key class for creating the key Tile which can be picked up and unlocks
 * certain doors
 * 
 * @author wangding1 300422014
 *
 */
public class Key extends Tile {
	Variation variation;

	public Key(TileName tileName, Location location, boolean canMoveOn, boolean canPickup, Variation variation) {
		super(tileName, location, canMoveOn, canPickup);
	}

	@Override
	protected Action interact(Actor actor) {
		if (actor instanceof Chap) {
			((Chap) actor).addItem(createItemName(TileName.KEY, variation));
			kill();
			return Action.ITEM;
		}
		return null;
	}

}
