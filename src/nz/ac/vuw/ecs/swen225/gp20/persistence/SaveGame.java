package nz.ac.vuw.ecs.swen225.gp20.persistence;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonWriter;

import nz.ac.vuw.ecs.swen225.gp20.application.GameController;
import nz.ac.vuw.ecs.swen225.gp20.application.GameStatus;
import nz.ac.vuw.ecs.swen225.gp20.maze.Location;
import nz.ac.vuw.ecs.swen225.gp20.maze.Maze;

public class SaveGame {
	Maze maze;
	GameController gameCon = new GameController();
	GameStatus currStat;
	Location[][] currentMap;

	public SaveGame(Maze maze, String filename) {
		this.maze = maze;
		this.currentMap = maze.getLocation();
		this.currStat = gameCon.getStatus();
		save(filename);
	}

	private void save(String filename) {
		if (currStat == GameStatus.LEVEL_PAUSED) {
			try (FileWriter fw = new FileWriter("levels/" + filename); 
				 JsonWriter jw = Json.createWriter(fw);) {
				JsonBuilderFactory jbFac = Json.createBuilderFactory(null);
				JsonArrayBuilder rows = jbFac.createArrayBuilder();
				for(Location[] r : currentMap) {
					JsonArrayBuilder cols = jbFac.createArrayBuilder();
					for(Location lc : r) {
						Class<?> classofLoc =  lc.getClass();
						System.out.println(classofLoc);
					}
					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
