package nz.ac.vuw.ecs.swen225.gp20.maze;

/**
 * Maze class which stores the current level information of the game
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
	 * @param locations - 2d Array of Locations (x,y)
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
		int currentRow = actor.getLocation().getRow();
		int currentCol = actor.getLocation().getCol();
		action = Action.INVALID;
		Location newL = null;

		switch (direction) {
		case NORTH:
			if ((newL = getValidLocation(currentCol, currentRow - 1)) != null)
				action = actor.move(newL,direction);
			break;
		case SOUTH:
			if ((newL = getValidLocation(currentCol, currentRow + 1)) != null)
				action = actor.move(newL,direction);
			break;
		case EAST:
			if ((newL = getValidLocation(currentCol + 1, currentRow)) != null)
				action = actor.move(newL,direction);
			break;
		case WEST:
			if ((newL = getValidLocation(currentCol - 1, currentRow)) != null)
				action = actor.move(newL,direction);
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
	private Location getValidLocation(int newY, int newX) {
		Location newL = null;
		if ((newX >= 0 && newX <= locations[0].length) && (newY >= 0 && newY <= locations.length))
			newL = locations[newX][newY];
		return newL;
	}

	/**
	 * Helper method for finding Chap and setting the total chips in the current
	 * level.
	 */
	private Chap findChap(Location[][] level) {
		Chap c = null;
		int totChips = 0;
		// for each row array
		for (Location[] row : level) {

			// for each location in row
			for (Location loc : row) {
				Actor a = loc.getActor();
				Tile t = loc.getTile();

				// if actor is chap, set chap
				if (a != null && a.getActorName() == ActorName.CHAP)
					c = (Chap) a;

				if (t != null && t.getTileName() == TileName.CHIP)
					totChips++;
			}
		}
		if (c != null)
			c.setTotalChips(totChips);
		return c;
	}

	/**
	 * Returns Chap
	 * 
	 * @return chap - Chap object
	 */
	public Chap getChap() {
		return chap;
	}

	/**
	 * Returns the action after a move
	 * 
	 * @return Action enum
	 */
	public Action getAction() {
		return action;
	}

	/**
	 * Returns the 2d array of Locations
	 * 
	 * @return locations - 2d Array of Location objects
	 */
	public Location[][] getLocation() {
		return locations;
	}

	/**
	 * Maze toString for debugging
	 */
	public String toString() {
		String s = "";
		// i == x
		for (int i = 0; i < locations[0].length; i++) {
			// j == y
			for (int j = 0; j < locations.length; j++) {
				s += locations[i][j].toString() + " ";
			}
			s += "\n";
		}
		return s;
	}

//	/**
//	 * testing needs
//	 * 
//	 * @param args - testing
//	 */
//	public static void main(String[] args) {
//		new Maze();
//	}

}
