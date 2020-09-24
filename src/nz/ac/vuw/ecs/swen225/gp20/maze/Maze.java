package nz.ac.vuw.ecs.swen225.gp20.maze;

import java.util.HashMap;

/**
 * Maze class which stores the current level infomation of the game
 * 
 * @author wangding1
 *
 */
public class Maze {

	private Location[][] locations; // 2D array of maze locations (row,col)
	private HashMap<String, Integer> inventory;
	private int chips;
	private Chap chap;

	/**
	 * Sets the current level of the maze with all fields being reset.
	 * 
	 * @param locations - 2d Array of Locations
	 */
	public void setLevel(Location[][] locations) {
		this.locations = locations;
		this.inventory = new HashMap<>();
		this.chips = 0;
		this.chap = findChap(locations);
		chap.setMaze(this);
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
	private Chap findChap(Location[][] level) {
		// for each row array
		for (Location[] row : level) {

			// for each location in row
			for (Location loc : row) {

				// if actor is chap, set chap
				if (loc.actor.actorName == ActorName.CHAP)
					return (Chap) loc.actor;
			}
		}
		return null;
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
