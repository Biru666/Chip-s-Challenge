package nz.ac.vuw.ecs.swen225.gp20.maze;

public class Bot extends Actor {

	/**
	 * Chap actor constructor
	 * 
	 * @param actorName - Enum actorName
	 * @param loaction  - Location
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

}
