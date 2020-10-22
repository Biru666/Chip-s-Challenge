package nz.ac.vuw.ecs.swen225.gp20.maze;

/**
 * Actor class which represents an entity that may move. When two actors collide
 * with each other, both of them will be killed before interacting with the tile
 * it stands on.
 * 
 * @author wangding1 300422014
 *
 */
public abstract class Actor {

	private boolean dead = false;
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
	 * @param direction   - Direction enum
	 */
	public Action move(Location newLocation, Direction direction) {
		Actor a = newLocation.getActor();
		Tile t = newLocation.getTile();
		Action action = interact(t, a, direction);
		t = newLocation.getTile();

		// if the actor no longer has a location reference then its dead
		if (location == null)
			return action;
		if (t == null || t.canMoveOn())
			doMove(newLocation);
		return action;
	}

	/**
	 * Manages the interaction between the current actor and new location actors and
	 * tiles.
	 * 
	 * @param t         - Tile object
	 * @param a         - Actor object
	 * @param direction - Direction enum
	 * @return Action enum
	 */
	protected abstract Action interact(Tile t, Actor a, Direction direction);

	/**
	 * Kills an actor by remove its reference
	 */
	public void kill() {
		dead = true;
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
	 * @return location object
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @return actorName enum
	 */
	public ActorName getActorName() {
		return actorName;
	}

	/**
	 * Returns the state of weather chap is dead
	 * 
	 * @return dead boolean
	 */
	public boolean isDead() {
		return dead;
	}

	/**
	 * Sets chap to dead state
	 */
	public void setDead() {
		dead = true;
	}

	/**
	 * To string method for testing
	 */
	public String toString() {
		String s = "";
		switch (actorName) {
		case CHAP:
			s = "C";
			break;
		case BOT:
			s = "B";
			break;
		default:
			s = "X";
		}
		return s;
	}

}