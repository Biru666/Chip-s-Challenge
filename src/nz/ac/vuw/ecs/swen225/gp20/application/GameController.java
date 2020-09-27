package nz.ac.vuw.ecs.swen225.gp20.application;

import java.awt.event.ActionEvent;

import nz.ac.vuw.ecs.swen225.gp20.maze.Direction;
import nz.ac.vuw.ecs.swen225.gp20.maze.Maze;
import nz.ac.vuw.ecs.swen225.gp20.persistence.Parser;

public class GameController {
	private MazeView mazePanel;
	private GameInfoView gameInfo;
	private GameInfoRenderer gameInfoRenderer;
	private Maze maze = new Maze();

	public SwingAction startLevel1(ActionEvent e) {
		System.out.println("start level 1");
		Parser parser = new Parser(GameController.class.getResource("levels/level1.json").getPath());
		
		// Maze - get a new Maze from level 1.
		GameInfoModel gameInfoModel = new GameInfoModel();
		gameInfoModel.setLevel(3);
		gameInfoRenderer.render(gameInfoModel);
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

	public SwingAction move(Direction direction) {
		try {
			System.out.println("moving " + direction);
			maze.move(maze.getChap(), direction);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public void setMazePanel(MazeView mazePanel) {
		this.mazePanel = mazePanel;
	}

	public void setGameInfo(GameInfoView gameInfo) {
		this.gameInfo = gameInfo;
	}

	public void setGameInfoRenderer(GameInfoRenderer gameInfoRenderer) {
		this.gameInfoRenderer = gameInfoRenderer;
	}

}
