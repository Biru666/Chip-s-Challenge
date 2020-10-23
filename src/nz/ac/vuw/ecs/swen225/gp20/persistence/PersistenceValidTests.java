package nz.ac.vuw.ecs.swen225.gp20.persistence;

import org.junit.Test;

import nz.ac.vuw.ecs.swen225.gp20.maze.ActorName;
import nz.ac.vuw.ecs.swen225.gp20.maze.Location;
import nz.ac.vuw.ecs.swen225.gp20.maze.Maze;
import nz.ac.vuw.ecs.swen225.gp20.maze.TileName;

public class PersistenceValidTests {
	@Test
	public void test_save() {
		Maze m = maze();
		SaveGame saveGame = new SaveGame(m, 1);
		
	}

	private Maze maze() {
		Maze m = new Maze();
		int x = 3;
		int y = 3;
		Location[][] loc = new Location[x][y];
		loc[0][0] = new Location(0, 0, TileName.WALL);
		loc[0][1] = new Location(0, 1, TileName.WALL);
		loc[0][2] = new Location(0, 2, TileName.WALL);
		loc[1][0] = new Location(1, 0, TileName.WALL);
		loc[1][1] = new Location(1, 1, ActorName.CHAP);
		loc[1][2] = new Location(1, 2, TileName.WALL);
		loc[2][0] = new Location(2, 0, TileName.WALL);
		loc[2][1] = new Location(2, 1, TileName.WALL);
		loc[2][2] = new Location(2, 2, TileName.WALL);
		m.setLevel(loc);
		return m;
	}
}
