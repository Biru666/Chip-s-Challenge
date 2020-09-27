package nz.ac.vuw.ecs.swen225.gp20.persistence;

import java.util.Map;

import nz.ac.vuw.ecs.swen225.gp20.maze.Location;
import nz.ac.vuw.ecs.swen225.gp20.maze.Maze;

public class SaveGame {
	Maze maze;
	int chipNum;
	Location[][] currentState;
	Map<String, Integer> inventory;

	public SaveGame(Maze maze, Location[][] currentState) {
		this.maze = maze;
		this.currentState = currentState;
		this.chipNum = maze.getChap().getChips();
		this.inventory = maze.getChap().getInventory();
	}
	
	private void save() {
		
	}  
	
}
