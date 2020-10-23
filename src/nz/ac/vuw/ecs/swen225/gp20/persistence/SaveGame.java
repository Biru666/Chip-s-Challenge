package nz.ac.vuw.ecs.swen225.gp20.persistence;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonParser.Event;
import javax.swing.Timer;

import nz.ac.vuw.ecs.swen225.gp20.application.GameController;
import nz.ac.vuw.ecs.swen225.gp20.application.GameStatus;
import nz.ac.vuw.ecs.swen225.gp20.maze.ActorName;
import nz.ac.vuw.ecs.swen225.gp20.maze.Door;
import nz.ac.vuw.ecs.swen225.gp20.maze.Key;
import nz.ac.vuw.ecs.swen225.gp20.maze.Location;
import nz.ac.vuw.ecs.swen225.gp20.maze.Maze;
import nz.ac.vuw.ecs.swen225.gp20.maze.Tile;
import nz.ac.vuw.ecs.swen225.gp20.maze.TileName;
import nz.ac.vuw.ecs.swen225.gp20.maze.Variation;

/**
 * Save the current game states like map, inventory, level info and time. Export
 * as an formatted .json file.
 * 
 * @author Biru Lin 300456889
 *
 */
public class SaveGame {
	private Maze maze;
	private int currLevel;
	private Location[][] currentMap;

	/**
	 * Constructor of SaveGame Class.
	 * 
	 * @param maze
	 * @param currLevel
	 * @param filename
	 */
	public SaveGame(Maze maze, int currLevel) {
		this.maze = maze;
		this.currLevel = currLevel;
		this.currentMap = maze.getLocation();
	}

	/**
	 * Write a new json file for current map using Writer in Javax.json.
	 * 
	 * @param filename
	 * @param time
	 */
	public void save(String filename, int time) {
		Map<String, Integer> inventory = maze.getChap().getInventory();
		int leftChips = maze.getChap().getTotalChips() - maze.getChap().getChips();

		// map writer
		JsonWriter jw = null;
		try {
			FileWriter fw = new FileWriter("levels/" + filename);
			
			jw = Json.createWriter(fw);
			JsonArrayBuilder rows = Json.createArrayBuilder();
			for (int i = 0; i < currentMap.length; i++) {
				JsonArrayBuilder cols = Json.createArrayBuilder();
				for (int j = 0; j < currentMap[i].length; j++) {
					jsonValues(i, j, cols);
				}
				rows.add(cols.build());
			}

			// level, time and chip writer
			JsonObjectBuilder infoBuilds = Json.createObjectBuilder();
			infoBuilds.add("Time", time);
			infoBuilds.add("NumOfChips", leftChips);
			infoBuilds.add("Level", currLevel);
			rows.add(infoBuilds);

			// inventory writer
			JsonObjectBuilder inv = Json.createObjectBuilder();
			JsonArrayBuilder invArr = Json.createArrayBuilder();
			for (String itemName : inventory.keySet()) {	
				JsonObjectBuilder invBuild = Json.createObjectBuilder();
				invBuild.add(itemName, inventory.get(itemName));
				invArr.add(invBuild);
			}
			inv.add("Inventory", invArr);
			rows.add(inv);

			JsonArray arr = rows.build();
			jw.writeArray(arr);
			jw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Assign the corresponding values to tiles and actors.
	 * 
	 * @param cols: Array Builder for each array
	 * @param i:    row index.
	 * @param j:    column index.
	 */
	private void jsonValues(int i, int j, JsonArrayBuilder cols) {
		if (currentMap[i][j].getActor() != null) {
			ActorName actorName = currentMap[i][j].getActor().getActorName();
			switch (actorName) {
			case CHAP:
				cols.add(0);
				break;
			default:
				cols.add(15);
				break;
			}
		}
		if (currentMap[i][j].getTile() != null) {
			TileName tileName = currentMap[i][j].getTile().getTileName();
			switch (tileName) {
			case CHIP:
				cols.add(3);
				break;
			case DOOR:
				Door d = (Door) currentMap[i][j].getTile();
				Variation colors = d.getVariation();
				switch (colors) {
				case BLUE:
					cols.add(11);
					break;
				case GREEN:
					cols.add(10);
					break;
				case RED:
					cols.add(9);
					break;
				default:
					cols.add(8);
					break;
				}
				break;
			case KEY:
				Key k = (Key) currentMap[i][j].getTile();
				Variation var = k.getVariation();
				switch (var) {
				case BLUE:
					cols.add(7);
					break;
				case GREEN:
					cols.add(6);
					break;
				case RED:
					cols.add(5);
					break;
				default:
					cols.add(4);
					break;
				}
				break;
			case EXIT:
				cols.add(13);
				break;
			case GATE:
				cols.add(12);
				break;
			case INFO:
				if (currLevel == 1) {
					cols.add(14);
				} else {
					cols.add(17);
				}
				break;
			case LAVA:
				cols.add(16);
				break;
			case WALL:
				cols.add(2);
				break;
			default:
				break;
			}
		} else {
			cols.add(1);
		}

	}

}
