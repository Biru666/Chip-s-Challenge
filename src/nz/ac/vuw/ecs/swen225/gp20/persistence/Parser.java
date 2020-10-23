package nz.ac.vuw.ecs.swen225.gp20.persistence;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.json.JsonValue.ValueType;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import nz.ac.vuw.ecs.swen225.gp20.maze.ActorName;
import nz.ac.vuw.ecs.swen225.gp20.maze.Location;
import nz.ac.vuw.ecs.swen225.gp20.maze.TileName;
import nz.ac.vuw.ecs.swen225.gp20.maze.Variation;

/**
 * Load the game map to an 2d-array.
 * 
 * @author Biru Lin 300456889
 *
 */
public class Parser {
	/**
	 * the map for game.
	 */
	public Location[][] map;

	/**
	 * remaining game time.
	 */
	public int Time;

	/**
	 * the number of chips Chap've collected.
	 */
	public int leftChips;

	/**
	 * the level of saved game.
	 */
	public int currLevel;

	/**
	 * the inventory for Chap.
	 */
	public Map<String, Integer> inventory;

	/**
	 * Constructor for Parser.
	 * 
	 * @param filename
	 */
	public Parser(String filename) {
		load(filename);
	}

	/**
	 * load the given file.
	 * 
	 * @param filename
	 */
	private void load(String filename) {
		FileReader file;
		JsonArray arr;
		int row = 0;
		int col = 0;
		try {
			file = new FileReader(filename);
			JsonParser parser = Json.createParser(file);
			Event event = parser.next();
			arr = parser.getArray();
			row = arr.size();
			for (JsonValue v : arr) {
				if (v.getValueType() == ValueType.OBJECT)
					row--;
			}
			col = arr.get(0).asJsonArray().size();
			// load the map part
			loadMap(row, col, arr);
			// load the information part
			loadInfo(arr);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void loadInfo(JsonArray arr) {
		for (JsonValue obj : arr) {
			if (obj.getValueType() == ValueType.OBJECT) {
				Time = obj.asJsonObject().getInt("Time");
				leftChips = obj.asJsonObject().getInt("NumofChips");
				currLevel = obj.asJsonObject().getInt("Level");
				if (obj.asJsonObject().containsKey("Inventory")) {
					for (JsonValue obs : obj.asJsonObject().get("Inventory").asJsonArray()) {
						for(String keys : obs.asJsonObject().keySet()) {
							inventory.put(keys, obs.asJsonObject().getInt(keys));
						}
					}
				}
			}
		}
	}

	/**
	 * load map
	 * 
	 * @param row : int
	 * @param col : int
	 * @param arr : jsonarray
	 */
	private void loadMap(int row, int col, JsonArray arr) {
		map = new Location[row][col];
		for (int i = 0; i < row; i++) {
			JsonArray rowArr = arr.get(i).asJsonArray();
			for (int j = 0; j < rowArr.asJsonArray().size(); j++) {
				int obj = rowArr.getInt(j);
				switch (obj) {
				// Chap's initial place
				case 0:
					map[i][j] = new Location(i, j, ActorName.CHAP);
					break;
				// wall tile
				case 2:
					map[i][j] = new Location(i, j, TileName.WALL);
					break;
				// chips
				case 3:
					map[i][j] = new Location(i, j, TileName.CHIP);
					break;
				// Yellow Key
				case 4:
					map[i][j] = new Location(i, j, TileName.KEY, Variation.YELLOW);
					break;
				// Red Key
				case 5:
					map[i][j] = new Location(i, j, TileName.KEY, Variation.RED);
					break;
				// Green Key
				case 6:
					map[i][j] = new Location(i, j, TileName.KEY, Variation.GREEN);

					break;
				// Blue Key
				case 7:
					map[i][j] = new Location(i, j, TileName.KEY, Variation.BLUE);
					break;
				// Yellow Door
				case 8:
					map[i][j] = new Location(i, j, TileName.DOOR, Variation.YELLOW);
					break;
				// Red Door
				case 9:
					map[i][j] = new Location(i, j, TileName.DOOR, Variation.RED);
					break;
				// Green Door
				case 10:
					map[i][j] = new Location(i, j, TileName.DOOR, Variation.GREEN);
					break;
				// Blue Door
				case 11:
					map[i][j] = new Location(i, j, TileName.DOOR, Variation.BLUE);
					break;
				// Gate
				case 12:
					map[i][j] = new Location(i, j, TileName.GATE);
					break;
				// Portal
				case 13:
					map[i][j] = new Location(i, j, TileName.EXIT);
					break;
				// Level 1 Help
				case 14:
					map[i][j] = new Location(i, j, TileName.INFO);
					break;
				// Level 2 Help
				case 17:
					map[i][j] = new Location(i, j, TileName.INFO);
					break;
				// Bug
				case 15:
					map[i][j] = new Location(i, j, ActorName.BOT);
					break;
				// Lava
				case 16:
					map[i][j] = new Location(i, j, TileName.LAVA);
					break;
				default:
					map[i][j] = new Location(i, j);
					break;
				}
			}
		}
	}
}
