package nz.ac.vuw.ecs.swen225.gp20.maze;

/**
 * Maze class which stores the current level infomation of the game
 * 
 * @author wangding1 300422014
 *
 */
public class Maze {

	private Location[][] locations; // 2D array of maze locations (row,col)
	private Chap chap;
	private Action action;

	/**
	 * Sets the current level of the maze with all fields being reset.
	 * 
	 * @param locations - 2d Array of Locations
	 */
	public void setLevel(Location[][] locations) {
		this.locations = locations;
		this.chap = findChap(locations);
	}

	/**
	 * Moves chap from invoking move method
	 * 
	 * @param direction - Direction enum
	 */
	public void moveChap(Direction direction) {
		move(chap, direction);
	}

	/**
	 * Moves the desierd actor in a given direction
	 * 
	 * @param actor     - Actor object
	 * @param direction - Direction enum
	 */
	public void move(Actor actor, Direction direction) {
		int currentX = actor.getLocation().getX();
		int currentY = actor.getLocation().getY();
		action = Action.INVALID;
		Location newL = null;

		switch (direction) {
		case NORTH:
			if ((newL = getValidLocation(currentX, currentY - 1)) != null)
				action = actor.move(newL);
			break;
		case SOUTH:
			if ((newL = getValidLocation(currentX, currentY + 1)) != null)
				action = actor.move(newL);
			break;
		case EAST:
			if ((newL = getValidLocation(currentX + 1, currentY)) != null)
				action = actor.move(newL);
			break;
		case WEST:
			if ((newL = getValidLocation(currentX - 1, currentY)) != null)
				action = actor.move(newL);
			break;
		}
	}

	/**
	 * Helper method for checking 2d array bounds
	 * 
	 * @param newX - x axis
	 * @param newY - y axis
	 * @return newL - new Location
	 */
	private Location getValidLocation(int newX, int newY) {
		Location newL = null;
		if ((newX >= 0 && newX <= locations[0].length) && (newY >= 0 && newY <= locations.length))
			newL = locations[newX][newY];
		return newL;
	}

	/**
	 * Helper method for finding Chap in the current level
	 */
	private Chap findChap(Location[][] level) {
		Chap c = null;
		int totChips = 0;
		// for each row array
		for (Location[] row : level) {

			// for each location in row
			for (Location loc : row) {

				// if actor is chap, set chap
				if (loc.getActor().getActorName() == ActorName.CHAP)
					c = (Chap) loc.getActor();
				if (loc.getTile().getTileName() == TileName.CHIP)
					totChips++;
			}
		}
		if (c != null)
			c.setTotalChips(totChips);
		return c;
	}

	/**
	 * @return the chap
	 */
	public Chap getChap() {
		return chap;
	}

	public Action getAction() {
		return action;
	}

	/**
	 * testing needs
	 * 
	 * @param args - testing
	 */
	public static void main(String[] args) {
		new Maze();
	}

}
