package nz.ac.vuw.ecs.swen225.gp20.maze;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MazeValidTest {

	@Test
	void test_setLocation() {
		Maze m = makeMaze();

		// Checks
		assertTrue(m.getChap() != null);
		assertTrue(m.getAction() == null);
	}

	@Test
	void test_moveChap() {
		Maze m = makeMaze();

		// Moves
		Location l = m.getChap().getLocation();
		m.moveChap(Direction.NORTH);

		// Checks
		assertTrue(l != m.getChap().getLocation()); // Valid move, location can't be the same
		assertTrue(m.getAction() == Action.MOVE);
	}

	@Test
	void test_moveChapInfo() {
		Maze m = makeMaze();

		// Moves
		Location l = m.getChap().getLocation();
		m.moveChap(Direction.EAST);

		// Checks
		assertTrue(l != m.getChap().getLocation()); // Valid move, location can't be the same
		assertTrue(m.getAction() == Action.INFO);

	}

	@Test
	void test_moveChapWall() {
		Maze m = makeMaze();

		// Moves
		m.moveChap(Direction.SOUTH);
		Location l = m.getChap().getLocation(); // Location just b4 checking
		m.moveChap(Direction.SOUTH);

		// Checks
		assertTrue(l == m.getChap().getLocation()); // can't move, location have to be the same
		assertTrue(m.getAction() == Action.WALL);

	}

	/**
	 * Helper method for constructing a basic maze
	 * 
	 * 
	 * @return initialed maze
	 */
	private Maze makeMaze() {
		Maze m = new Maze();
		int x = 8;
		int y = 8;
		Location[][] l = new Location[x][y];
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				Location loc = null;
				// Wall tile
				if (i == 0 || i == x - 1 || j == 0 || j == y - 1)
					loc = new Location(i, j, TileName.WALL, null);
				// empty tile
				else {
					loc = new Location(i, j);
				}
				l[i][j] = loc;
			}
		}
		l = addTiles(l);
		m.setLevel(l);
		return m;
	}

	/**
	 * Adding some hard coded locations for testing (x,y)
	 * 
	 * @param loc 2D array of Locations
	 * @return 2D array of Locations
	 */
	private Location[][] addTiles(Location[][] loc) {
		// Chap
		loc[5][5] = new Location(5, 5, ActorName.CHAP);

		// door and key
		loc[1][1] = new Location(1, 1, TileName.DOOR, Variation.BLUE);
		loc[2][1] = new Location(2, 1, TileName.KEY, Variation.BLUE);

		// Chips
		loc[4][1] = new Location(4, 1, TileName.CHIP, null);
		loc[4][2] = new Location(4, 2, TileName.CHIP, null);

		// Gate
		loc[6][6] = new Location(6, 6, TileName.GATE, null);

		// Info
		loc[5][6] = new Location(5, 6, TileName.INFO, null);

		// exit
		loc[6][7] = new Location(6, 7, TileName.EXIT, null);

		return loc;
	}

}
