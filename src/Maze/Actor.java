package Maze;

public class Actor {

	protected ActorName actorName;
	protected Location location;

	public Actor(ActorName actorName, Location location) {
		this.actorName = actorName;
		this.location = location;
	}

	public void move(Location newLocation) {
		switch (actorName) {
		case CHAP:
			((Chap) this).move(newLocation);
		default:

		}

	}

}