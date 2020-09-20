package nz.ac.vuw.ecs.swen225.gp20.application;

public class GameInfoModel implements Model {
	private int level;
	private int time;
	private int chipsLeft;
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getChipsLeft() {
		return chipsLeft;
	}
	public void setChipsLeft(int chipsLeft) {
		this.chipsLeft = chipsLeft;
	}
}
