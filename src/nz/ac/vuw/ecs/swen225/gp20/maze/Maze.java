package nz.ac.vuw.ecs.swen225.gp20.maze;

public class Maze {

	private Chap chap;
	private Location[][] locations;

	public Maze(Location[][] locations) {
		this.locations = locations;
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
