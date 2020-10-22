package nz.ac.vuw.ecs.swen225.gp20.persistence;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.json.Json;
import javax.json.JsonArray;
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
		JsonArray arr;
		int row = 0;
		int col = 0;
		try {
			file = new FileReader(filename);
			JsonParser parser = Json.createParser(file);
			Event event = parser.next();
			arr = parser.getArray();
			row = arr.size();
			col = arr.get(0).asJsonArray().size();
			map = new Location[row][col];
			for (int i = 0; i < row; i++) {
				JsonArray rowArr = arr.get(i).asJsonArray();
				for (int j = 0; j < col; j++) {
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
					// Level 1 Help
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
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
