package nz.ac.vuw.ecs.swen225.gp20.application;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Timer;

import nz.ac.vuw.ecs.swen225.gp20.maze.Action;
import nz.ac.vuw.ecs.swen225.gp20.maze.Direction;
import nz.ac.vuw.ecs.swen225.gp20.maze.Info;
import nz.ac.vuw.ecs.swen225.gp20.maze.Location;
import nz.ac.vuw.ecs.swen225.gp20.maze.Maze;
import nz.ac.vuw.ecs.swen225.gp20.persistence.Parser;
import nz.ac.vuw.ecs.swen225.gp20.persistence.SaveGame;
import nz.ac.vuw.ecs.swen225.gp20.recnplay.RecordAndReplay;
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
	private Timer tickTimer = null;

	private GameInfoModel gameInfoModel = new GameInfoModel(); ////
	
	public void startLevel1() {
		startLevel(currentLevel = 1);
	}

	public void saveLevel() {
		SaveGame sg = new SaveGame(maze, currentLevel);
		sg.save("SavedMap.json", 30);
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
			if (action == Action.DIE) {
				status = GameStatus.LEVEL_FINISHED;
				mazeRenderer.chapDie();
				gameInfoRenderer.chapDie();
				return;
			} else if (action != Action.WALL) {
				renderMap();
				if (action == Action.ITEM || action == Action.DOOR) {
					GameInfoModel model = new GameInfoModel();
					model.setChipsLeft(maze.getChap().getTotalChips() - maze.getChap().getChips());
					Map<String, Integer> inventory = maze.getChap().getInventory();
					model.setInventory(inventory);
					gameInfoRenderer.update(model);
				} else if (action == Action.EXIT) {
					status = GameStatus.LEVEL_FINISHED;
					gameInfoRenderer.levelFinished();
				} else if (action == Action.INFO) {
					Location[][] locations = maze.getLocation();
					for (Location[] loc : locations) {
						for (Location l : loc) {
							if (l.getTile() instanceof Info) {
								Info info = (Info) l.getTile();
								System.out.println(info.getInfo());
								gameInfoRenderer.popupInfo(info.getInfo());

							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void pause() {
		if (tickTimer != null) {
			tickTimer.stop();
		}
		gameInfoRenderer.pause();
		resume();
	}
	
	public void stopCountdown() { ////
		if (tickTimer != null) {
			tickTimer.stop();
		}
		gameInfoRenderer.stopCountdown();
	}

	public void timeout() {
		status = GameStatus.LEVEL_FINISHED;
	}

	private void startLevel(int level) {
		Parser parser = new Parser("levels/level" + level + ".json");
		maze.setLevel(parser.map);

		// Maze - get a new Maze from level 1.
		gameInfoModel.setLevel(level);
		gameInfoModel.setTime(LEVEL_TIME.get(level));
		gameInfoModel.setChipsLeft(maze.getChap().getTotalChips() - maze.getChap().getChips());
		gameInfoRenderer.render(gameInfoModel);
		gameInfoRenderer.countdown(() -> timeout());
		renderMap();
		status = GameStatus.LEVEL_STARTED;
		if (tickTimer == null) {
			tickTimer = new Timer(1000, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (maze != null && status == GameStatus.LEVEL_STARTED) {
						maze.tick();
						if (maze.getChap() == null || maze.getChap().isDead()) {
							status = GameStatus.LEVEL_FINISHED;
							mazeRenderer.chapDie();
							gameInfoRenderer.chapDie();
						} else {
							renderMap();
						}
					} else {
						tickTimer.stop();
					}
				}
			});
		}
		tickTimer.restart();
	}

	private void renderMap() {
		mazeRenderer.setMaze(maze.getLocation());
		mazeRenderer.playerPos = maze.getChap().getLocation();
		mazeRenderer.Corner();
	}

	public void startRecording() { //
		RecordAndReplay.startNewRecord("record.json", this);
		System.out.println("start record");
	}
	
	public void saveRecording() { //
		RecordAndReplay.saveRecording(this);
		System.out.println("save recording");
	}
	
	public void loadRecording() { //
		RecordAndReplay.loadRecording("record.json", this);
		System.out.println("load recording");
	}
	
	public void stepReplay() { //
		RecordAndReplay.stepReplay(this);
		System.out.println("step replay");
	}
	
	public void autoReplay() {
		RecordAndReplay.autoReplay(this);
		System.out.println("auto replay");
	}
	
	public void oneSpeed() { //
		RecordAndReplay.setDelay(1);
		System.out.println("1.0 speed replay");
	}
	
	public void halfSpeed() { //
		RecordAndReplay.setDelay(0.5);
		System.out.println("0.5 speed replay");
	}
	
	public void twiceSpeed() { //
		RecordAndReplay.setDelay(2);
		System.out.println("2.0 speed replay");
	}
	

	public void resume() {
		if (tickTimer != null) {
			tickTimer.start();
		}
		
	}

	public GameStatus getStatus() {
		return status;
	}

	public void setStatus(GameStatus status) {
		this.status = status;
	}

	public void setGameInfoRenderer(GameInfoRenderer gameInfoRenderer) {
		this.gameInfoRenderer = gameInfoRenderer;
	}

	public void setMazeRenderer(renderer mazeRenderer) {
		this.mazeRenderer = mazeRenderer;
	}

	public void setMaze(Maze m) { //
		this.maze = m;
	}
	
	public void setInfoModel(GameInfoModel model) { ////
		this.gameInfoModel = model;
	}
	
	
	public Maze getMaze() { //
		return this.maze;
	}
	
	public renderer getRenderer() { //
		return this.mazeRenderer;
	}
	
	public GameInfoRenderer getInfoRenderer() { ////
		return this.gameInfoRenderer;
	}
	
	public GameInfoModel getInfoModel() { ////
		return this.gameInfoModel;
	}

	/**
	 * 
	 * Getter of GameStatus for testing
	 *
	 */
	public GameStatus getGameStatus() {
		return this.status;
	}
}
