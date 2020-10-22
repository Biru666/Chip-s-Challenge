package nz.ac.vuw.ecs.swen225.gp20.maze;

/**
 * The Bot class which extends Actor and will move by itself using BotMannager.
 * This bot will constantly move by sticking to its left wall.
 * 
 * @author wangding1 300422014
 *
 */
public class Bot extends Actor {
	Direction dir = Direction.SOUTH;

	/**
	 * Chap actor constructor
	 * 
	 * @param actorName - ActorName enum
	 * @param loaction  - Location object
	 */
	public Bot(ActorName actorName, Location loaction) {
		super(actorName, loaction);
	}

	@Override
	protected Action interact(Tile t, Actor a, Direction dir) {
		// collided with another actor, kill each other
		if (a != null) {
			kill();
			a.kill();
			return Action.BOT_DIE;
		}
		// has a tile
		if (t != null) {
			return t.interact(this); // performs actor tile interaction
		}
		return Action.BOT_MOVE;
	}

	/**
	 * Returns the direction enum to its Left
	 * 
	 * @return Direction enum to its left
	 */
	public Direction left() {
		dir = dir.left(dir);
		return dir;
	}

	/**
	 * Returns the direction enum to its Right
	 * 
	 * @return Direction enum to its Right
	 */
	public Direction right() {
		dir = dir.right(dir);
		return dir;
	}

}
