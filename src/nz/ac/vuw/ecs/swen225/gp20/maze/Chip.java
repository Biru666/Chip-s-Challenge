package nz.ac.vuw.ecs.swen225.gp20.maze;

/**
 * Chip class represents the chips that chap can collect inorder to open the
 * final game and progress to the next level.
 * 
 * @author wangding1 300422014
 *
 */
public class Chip extends Tile {

	/**
	 * Chip constructor
	 * 
	 * @param tileName  - Enum TileName
	 * @param location  - Location
	 * @param canMoveOn - boolean
	 * @param canPickup - boolean
	 */
	public Chip(TileName tileName, Location location, boolean canMoveOn, boolean canPickup) {
		super(tileName, location, canMoveOn, canPickup);
	}

	@Override
	protected void interact(Actor actor) {
		if (actor instanceof Chap) {
			((Chap) actor).addChips();
			kill();
		}
	}
}
