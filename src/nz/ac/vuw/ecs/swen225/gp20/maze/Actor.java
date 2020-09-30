package nz.ac.vuw.ecs.swen225.gp20.maze;

/**
 * Actor class which represents an entity that may move.
 * 
 * @author wangding1 300422014
 *
 */
public abstract class Actor {

	private ActorName actorName;
	private Location location;

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
	 * Moves the actor to the new Location if its valid
	 * 
	 * @param newLocation - existing Location
	 */
	public Action move(Location newLocation) {
		Actor a = newLocation.getActor();
		Tile t = newLocation.getTile();
		Action action = interact(t, a);
		t = newLocation.getTile();

		// if the actor no longer exist
		if (location.getActor() == null)
			return action;
		if (t == null || t.canMoveOn())
			doMove(newLocation);
		return action;
	}

	/**
	 * Manages the interaction between actors and tiles
	 * 
	 * @param t - Tile
	 * @param a - Actor
	 * @return Action enum
	 */
	protected abstract Action interact(Tile t, Actor a);

	/**
	 * Kills an actor by remove its reference
	 * 
	 */
	public void kill() {
		location.setActor(null);
		location = null;
	}

	/**
	 * A valid move, moves the Actor into the new Location.
	 * 
	 * @param newLocation - new valid Location object
	 */
	private void doMove(Location newLocation) {
		newLocation.setActor(this);
		location.setActor(null);
		location = newLocation;
	}

	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @return the actorName
	 */
	public ActorName getActorName() {
		return actorName;
	}

	public String toString() {
		String s = "";
		switch (actorName) {
		case CHAP:
			s = "C";
			break;
		default:
			s = "X";
		}
		return s;
	}

}