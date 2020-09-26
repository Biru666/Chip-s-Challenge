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
	 * Interacts with the next Location with this current actor.
	 * 
	 * @param newLocation - exsisting Location
	 */
	public abstract void interact(Location newLocation);

	/**
	 * Moves the actor to the new Location if its valid
	 * 
	 * @param newLocation - exsisting Location
	 */
	public void move(Location newLocation) {
		interact(newLocation);
		
		Tile tile = newLocation.getTile();
		if (tile == null)
			doMove(newLocation);
		else if (tile.canMoveOn())
			doMove(newLocation);
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