package nz.ac.vuw.ecs.swen225.gp20.maze;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MazeValidTest {

	@Test
	void test_setLocation() {
		Maze m = new Maze();
		m.setLevel(makeLocation());
		assertTrue(m.getChap() != null);
		assertTrue(m.getAction() == null);
	}

	@Test
	void test_moveChap() {
		Maze m = new Maze();
		m.setLevel(makeLocation());
		Location l = m.getChap().getLocation();
		m.moveChap(Direction.NORTH);
		assertTrue(m.getChap().getLocation() != l);

	}

	@Test
	void test_stopOnWall() {
		// Maze m = makeMaze();
		// m.toString();
	}

	/**
	 * Helper method for constructing a basic maze
	 * 
	 * 
	 * @return initialzed maze
	 */
	private Location[][] makeLocation() {
		int row = 10;
		int col = 10;
		Location[][] l = new Location[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				Location loc = null;
				// Wall tile
				if (i == 0 || i == row - 1 || j == 0 || j == col - 1)
					loc = new Location(j, j, TileName.WALL, null);
				// empty tile
				else {
					loc = new Location(j, j);
				}
				l[i][j] = loc;
			}
		}
		l = addTiles(l);
		return l;
	}

	/**
	 * Adding some hard coded loactions for testing
	 * 
	 * @param loc 2D array of Loacations
	 * @return 2D array of Locations
	 */
	private Location[][] addTiles(Location[][] loc) {
		// door and key
		loc[1][1] = new Location(1, 1, TileName.DOOR, Variation.BLUE);
		loc[2][1] = new Location(2, 1, TileName.KEY, Variation.BLUE);

		// Chips
		loc[4][1] = new Location(4, 1, TileName.CHIP, null);
		loc[4][2] = new Location(4, 2, TileName.CHIP, null);

		// Gate
		loc[6][6] = new Location(6, 6, TileName.GATE, null);

		// Chap
		loc[5][5] = new Location(5, 5, ActorName.CHAP);

		// Info
		loc[5][6] = new Location(5, 6, TileName.INFO, null);

		// exit
		loc[6][7] = new Location(5, 7, TileName.GATE, null);

		return loc;
	}

}
