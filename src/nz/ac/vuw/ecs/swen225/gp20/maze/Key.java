package nz.ac.vuw.ecs.swen225.gp20.maze;

public class Key extends Tile {
	Variation variation;

	public Key(TileName tileName, Location location, boolean canMoveOn, boolean canPickup, Variation variation) {
		super(tileName, location, canMoveOn, canPickup);
	}

	@Override
	protected void interact(Actor actor) {
		if (actor instanceof Chap) {
			((Chap) actor).addItem(createItemName(variation));
			kill();
		}
	}

}
