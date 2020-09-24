package nz.ac.vuw.ecs.swen225.gp20.maze;

/**
 * Actor class which represents an entity that may move.
 * 
 * @author wangding1
 *
 */
public abstract class Actor {

	protected ActorName actorName;
	protected Location location;

	/**
	 * Constructor for creating an actor on a single location.
	 * 
	 * @param actorName - Enum for type of actor.
	 * @param location  - Location in which the actor is currently on.
	 */
	public Actor(ActorName actorName, Location location) {
		this.actorName = actorName;
		this.location = location;
	}

	/**
	 * Moves the actor to the new Location
	 * 
	 * @param newLocation - exsisting Location
	 */
	public void move(Location newLocation) {
		switch (actorName) {
		case CHAP:
			((Chap) this).move(newLocation);
		default:

		}

	}

}