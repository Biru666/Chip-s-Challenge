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
	 * Constructor for creating an actor on a single location. xx "wghjjkhjh
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
	 * @param newLocation - exsisting Location
	 */
	public void move(Location newLocation) {
		Actor a = newLocation.getActor();
		Tile t = newLocation.getTile();
		interact(t, a);

		// if the actor nolonger exsist
		if (location.getActor() == null)
			return;
		if (t == null)
			doMove(newLocation);
		else if (t.canMoveOn())
			doMove(newLocation);
	}

	/**
	 * Manages the interation between actors and tiles
	 * 
	 * @param t - Tile
	 * @param a - Actor
	 */
	private void interact(Tile t, Actor a) {

		// collided with another actor, kill each other
		if (a != null) {
			kill();
			a.kill();
		}
		// has a tile
		else if (t != null) {
			t.interact(this);
		}
	}

	/**
	 * Kills an actor by remove its refrence
	 * 
	 */
	private void kill() {
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

}