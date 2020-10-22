package nz.ac.vuw.ecs.swen225.gp20.persistence;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonValue;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

import nz.ac.vuw.ecs.swen225.gp20.maze.ActorName;
import nz.ac.vuw.ecs.swen225.gp20.maze.Location;
import nz.ac.vuw.ecs.swen225.gp20.maze.TileName;
import nz.ac.vuw.ecs.swen225.gp20.maze.Variation;

public class Parser {
	public Location[][] map;

	public Parser(String filename) {
		load(filename);
	}

	private void load(String filename) {
		FileReader file;
		try {
			file = new FileReader(filename);
			JsonParser parser = Json.createParser(file);
			Event event = parser.next();
			if (event == Event.START_ARRAY) {
				JsonArray arr = parser.getArray();
				int row = arr.size();
				int col = arr.get(0).asJsonArray().size();
				map = new Location[row][col];
				int i = 0;
				int j = 0;
				while (parser.hasNext()) {
					Event object = parser.next();
					if (object == Event.VALUE_NUMBER) {
						int key = parser.getInt();
						switch (key) {
						// Chap's initial place
						case 0:
							map[i][j] = new Location(i, j, ActorName.CHAP);
							i++;
							j++;
							break;
						// wall tile
						case 2:
							map[i][j] = new Location(i, j, TileName.WALL, null);
							i++;
							j++;
							break;
						// chips
						case 3:
							map[i][j] = new Location(i, j, TileName.CHIP, null);
							i++;
							j++;
							break;
						// Yellow Key
						case 4:
							map[i][j] = new Location(i, j, TileName.KEY, Variation.YELLOW);
							i++;
							j++;
							break;
						// Red Key
						case 5:
							map[i][j] = new Location(i, j, TileName.KEY, Variation.RED);
							i++;
							j++;
							break;
						// Green Key
						case 6:
							map[i][j] = new Location(i, j, TileName.KEY, Variation.GREEN);
							i++;
							j++;
							break;
						// Blue Key
						case 7:
							map[i][j] = new Location(i, j, TileName.KEY, Variation.BLUE);
							i++;
							j++;
							break;
						// Yellow Door
						case 8:
							map[i][j] = new Location(i, j, TileName.DOOR, Variation.YELLOW);
							i++;
							j++;
							break;
						// Red Door
						case 9:
							map[i][j] = new Location(i, j, TileName.DOOR, Variation.RED);
							i++;
							j++;
							break;
						// Green Door
						case 10:
							map[i][j] = new Location(i, j, TileName.DOOR, Variation.GREEN);
							i++;
							j++;
							break;
						// Blue Door
						case 11:
							map[i][j] = new Location(i, j, TileName.DOOR, Variation.BLUE);
							i++;
							j++;
							break;
						// Gate
						case 12:
							map[i][j] = new Location(i, j, TileName.GATE, null);
							i++;
							j++;
							break;
						// Portal
						case 13:
							map[i][j] = new Location(i, j, TileName.EXIT, null);
							i++;
							j++;
							break;
						// Help
						case 14:
							map[i][j] = new Location(i, j, TileName.INFO, null);
							i++;
							j++;
							break;
						// Bug
						case 15:
							map[i][j] = new Location(i, j, ActorName.BOT);
							i++;
							j++;
							break;
						// Lava
						case 16:
							map[i][j] = new Location(i, j);
							i++;
							j++;
							break;
						default:
							map[i][j] = new Location(i, j);
							i++;
							j++;
							break;
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
