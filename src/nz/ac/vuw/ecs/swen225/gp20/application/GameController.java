package nz.ac.vuw.ecs.swen225.gp20.application;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import nz.ac.vuw.ecs.swen225.gp20.maze.Action;
import nz.ac.vuw.ecs.swen225.gp20.maze.Direction;
import nz.ac.vuw.ecs.swen225.gp20.maze.Maze;
import nz.ac.vuw.ecs.swen225.gp20.persistence.Parser;
import nz.ac.vuw.ecs.swen225.gp20.renderer.renderer;

/**
 * 
 * @author Wang Conglang 300472254
 *
 */
public class GameController {
	private static Map<Integer, Integer> LEVEL_TIME = new HashMap<>();
	static {
		LEVEL_TIME.put(1, 100);
		LEVEL_TIME.put(2, 60);
		LEVEL_TIME.put(3, 90);
		LEVEL_TIME.put(4, 200);
		LEVEL_TIME.put(5, 250);
	}
	private GameInfoRenderer gameInfoRenderer;
	private renderer mazeRenderer;
	private Maze maze = new Maze();
	private GameStatus status = GameStatus.NOT_STARTED;
	private int currentLevel = 1;

	public void startLevel1() {
		startLevel(currentLevel = 1);
	}

	public void saveLevel() {
		
	}

	public void resumeSavedGame() {
		startLevel(1);
	}

	public void startLastUnfinishedGame() {
		startLevel(currentLevel);
	}

	public void startNextLevel() {
		startLevel(++currentLevel);
	}

	public boolean hasNextLevel() {
		return Files.exists(Paths.get("levels/level" + (currentLevel + 1) + ".json"));
	}

	public void move(Direction direction) {
		if (status != GameStatus.LEVEL_STARTED) {
			return;
		}
		try {
			maze.moveChap(direction);
			Action action = maze.getAction();
			System.out.println("action = " + action);
			if (action != Action.WALL) {
				renderMap();
				if (action == Action.ITEM
						|| action == Action.DOOR) {
					GameInfoModel model = new GameInfoModel();
					model.setChipsLeft(maze.getChap().getTotalChips() - maze.getChap().getChips());
					Map<String, Integer> inventory = maze.getChap().getInventory();
					model.setInventory(inventory);
					gameInfoRenderer.update(model);
				} else if (action == Action.EXIT) {
					status = GameStatus.LEVEL_FINISHED;
					gameInfoRenderer.levelFinished();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void pause() {
		// maze.pause();
		gameInfoRenderer.pause();
		resume();
	}

	public void timeout() {
		status = GameStatus.LEVEL_FINISHED;
	}

	private void startLevel(int level) {
		Parser parser = new Parser("levels/level" + level + ".json");
		maze.setLevel(parser.map);

		// Maze - get a new Maze from level 1.
		GameInfoModel gameInfoModel = new GameInfoModel();
		gameInfoModel.setLevel(level);
		gameInfoModel.setTime(LEVEL_TIME.get(level));
		gameInfoModel.setChipsLeft(maze.getChap().getTotalChips() - maze.getChap().getChips());
		gameInfoRenderer.render(gameInfoModel);
		gameInfoRenderer.countdown(() -> timeout());
		renderMap();
		status = GameStatus.LEVEL_STARTED;
	}

	private void renderMap() {
		mazeRenderer.setMaze(maze.getLocation());
		mazeRenderer.playerPos = maze.getChap().getLocation();
		mazeRenderer.Corner();
	}

	public void resume() {
		// maze.resume();
	}

	public void setGameInfoRenderer(GameInfoRenderer gameInfoRenderer) {
		this.gameInfoRenderer = gameInfoRenderer;
	}

	public void setMazeRenderer(renderer mazeRenderer) {
		this.mazeRenderer = mazeRenderer;
	}

}
