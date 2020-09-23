package nz.ac.vuw.ecs.swen225.gp20.maze;

/**
 * Maze class which stores the current level infomation of the game
 * 
 * @author wangding1
 *
 */
public class Maze {

	private Location[][] locations = null; // row,col
	private Chap chap = null;

	public Maze() {
	}

	/**
	 * Sets the current level of the maze
	 * 
	 * @param locations - 2d Array of Locations
	 */
	public void setLevel(Location[][] locations) {
		this.locations = locations;
		findChap();
	}

	/**
	 * Moves the desierd actor in a given direction
	 * 
	 * @param actor     - Actor object
	 * @param direction - Direction enum
	 */
	public void move(Actor actor, Direction direction) {

		int currentX = actor.location.x;
		int currentY = actor.location.y;

		switch (direction) {
		case NORTH:
			if (isValidLocation(currentX, currentY - 1))
				actor.move(locations[currentY - 1][currentX]);
			break;
		case SOUTH:
			if (isValidLocation(currentX, currentY + 1))
				actor.move(locations[currentY + 1][currentX]);
			break;
		case EAST:
			if (isValidLocation(currentX + 1, currentY))
				actor.move(locations[currentY][currentX + 1]);
			break;
		case WEST:
			if (isValidLocation(currentX - 1, currentY))
				actor.move(locations[currentY][currentX - 1]);
			break;
		}
	}

	/**
	 * Helper method for checking 2d array bounds
	 * 
	 * @param newX - x axis
	 * @param newY - y axis
	 * @return true/false
	 */
	private boolean isValidLocation(int newX, int newY) {
		return (newX >= 0 && newX <= locations[0].length) && (newY >= 0 && newY <= locations.length);

	}

	/**
	 * Helper method for finding Chap in the current level
	 */
	private void findChap() {
		// for each row array
		for (Location[] row : locations) {

			// for each location in row
			for (Location loc : row) {

				// if actor is chap, set chap
				if (loc.actor.actorName == ActorName.CHAP)
					chap = (Chap) loc.actor;
			}
		}
	}

	public static void main(String[] args) {
		new Maze();
	}

}
