package nz.ac.vuw.ecs.swen225.gp20.maze;

/**
 *  Maze class which stores the current level infomation of the game
 *  
 * @author wangding1
 *
 */
public class Maze {

	private Location[][] locations;
	private Chap chap;


	public Maze() {
	}
	
	public void setLevel(Location[][] locations) {
		this.locations=locations;
	}

	public void move(Actor actor, Direction direction) {

		int currentX = actor.location.x;
		int currentY = actor.location.y;

		switch (direction) {
		case NORTH:
			if (validLocation(currentX, currentY - 1))
				actor.move(locations[currentY - 1][currentX]);
			break;
		case SOUTH:
			if (validLocation(currentX, currentY + 1))
				actor.move(locations[currentY + 1][currentX]);
			break;
		case EAST:
			if (validLocation(currentX + 1, currentY))
				actor.move(locations[currentY][currentX + 1]);
			break;
		case WEST:
			if (validLocation(currentX - 1, currentY))
				actor.move(locations[currentY][currentX - 1]);
			break;
		}
	}

	private boolean validLocation(int newX, int newY) {
		return (newX >= 0 && newX <= locations[0].length) && (newY >= 0 && newY <= locations.length);

	}

	public static void main(String[] args) {
		new Maze(null);
	}

}
