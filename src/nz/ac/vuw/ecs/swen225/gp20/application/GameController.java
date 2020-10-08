package nz.ac.vuw.ecs.swen225.gp20.application;

import java.awt.event.ActionEvent;

import javax.swing.JPanel;

import nz.ac.vuw.ecs.swen225.gp20.maze.Direction;
import nz.ac.vuw.ecs.swen225.gp20.maze.Location;
import nz.ac.vuw.ecs.swen225.gp20.maze.Maze;
import nz.ac.vuw.ecs.swen225.gp20.maze.Tile;
import nz.ac.vuw.ecs.swen225.gp20.persistence.Parser;
import nz.ac.vuw.ecs.swen225.gp20.renderer.renderer;

public class GameController {
	private JPanel mazePanel;
	private GameInfoView gameInfo;
	private GameInfoRenderer gameInfoRenderer;
	private renderer mazeRenderer;
	private Maze maze = new Maze();

	public SwingAction startLevel1(ActionEvent e) {
		System.out.println("start level 1");
		Parser parser = new Parser("levels/level1.json");
		maze.setLevel(parser.map);
		
		// Maze - get a new Maze from level 1.
		GameInfoModel gameInfoModel = new GameInfoModel();
		gameInfoModel.setLevel(1);
		gameInfoModel.setTime(100);
		gameInfoRenderer.render(gameInfoModel);
		gameInfoRenderer.countdown();
		renderMap();
		return null;
	}

	public SwingAction saveLevel() {
		System.out.println("saving level");
		// Maze - get a new Maze from level 1.
		GameInfoModel gameInfoModel = new GameInfoModel();
		gameInfoModel.setLevel(2);
		gameInfoRenderer.render(gameInfoModel);
		return null;
	}

	public SwingAction resumeSavedGame(ActionEvent e) {
		System.out.println("Resuming a saved game.");
		Parser parser = new Parser("levels/level1.json");
		maze.setLevel(parser.map);

		// Maze - get a new Maze from level 1.
		GameInfoModel gameInfoModel = new GameInfoModel();
		gameInfoModel.setLevel(3);
		gameInfoModel.setTime(50);
		gameInfoRenderer.render(gameInfoModel);
		gameInfoRenderer.countdown();
		return null;
	}

	public SwingAction startLastUnfinishedGame(ActionEvent e) {
		System.out.println("Start last unfinished game.");
		Parser parser = new Parser("levels/level1.json");
		maze.setLevel(parser.map);

		// Maze - get a new Maze from level 1.
		GameInfoModel gameInfoModel = new GameInfoModel();
		gameInfoModel.setLevel(4);
		gameInfoModel.setTime(100);
		gameInfoRenderer.render(gameInfoModel);
		gameInfoRenderer.countdown();
		return null;
	}

	public SwingAction move(Direction direction) {
		try {
			System.out.println("moving " + direction);
			maze.moveChap(direction);
			renderMap();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public SwingAction pause() {
		System.out.println("pre pause");
		gameInfoRenderer.pause();
		System.out.println("post pause");
		return resume();
	}

	private Tile[][] mazeTiles() {
		Location[][] locations = maze.getLocation();
		Tile[][] tiles = new Tile[locations.length][locations[0].length];
		for (int i = 0; i < locations.length; i++) {
			for (int j = 0; j < locations[i].length; j++) {
				tiles[i][j] = locations[i][j].getTile();
			}
		}
		return tiles;
	}

	private void renderMap() {
		mazeRenderer.setMaze(mazeTiles());
		mazeRenderer.playerPos = maze.getChap().getLocation();
		mazeRenderer.Corner();
	}

	public SwingAction resume() {
		return null;
	}

	public void setMazePanel(JPanel mazePanel) {
		this.mazePanel = mazePanel;
	}

	public void setGameInfo(GameInfoView gameInfo) {
		this.gameInfo = gameInfo;
	}

	public void setGameInfoRenderer(GameInfoRenderer gameInfoRenderer) {
		this.gameInfoRenderer = gameInfoRenderer;
	}

	public void setMazeRenderer(renderer mazeRenderer) {
		this.mazeRenderer = mazeRenderer;
	}

}
