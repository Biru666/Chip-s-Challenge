package nz.ac.vuw.ecs.swen225.gp20.application;

import java.awt.event.ActionEvent;

public class GameController {
	private MazeView mazePanel;
	private GameInfoView gameInfo;
	private GameInfoRenderer gameInfoRenderer;

	public SwingAction startLevel1(ActionEvent e) {
		System.out.println("start level 1");
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
