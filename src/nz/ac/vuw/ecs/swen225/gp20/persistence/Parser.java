package nz.ac.vuw.ecs.swen225.gp20.persistence;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import nz.ac.vuw.ecs.swen225.gp20.maze.ActorName;
import nz.ac.vuw.ecs.swen225.gp20.maze.Location;
import nz.ac.vuw.ecs.swen225.gp20.maze.TileName;
import nz.ac.vuw.ecs.swen225.gp20.maze.Variation;

public class Parser {
	private JSONArray mapArray;
	public Location[][] map;

	public Parser(String filename) {
		load(filename);
	}

	private void load(String filename) {
		JSONParser parser = new JSONParser();
		try {
			mapArray = (JSONArray) parser.parse(new FileReader(filename));
			// for getting the column number
			JSONArray firstArray = (JSONArray) mapArray.get(0);

			int row = mapArray.size();
			int col = firstArray.size();

			map = new Location[row][col];
			for (int i = 0; i < mapArray.size(); i++) {
				JSONArray objectArray = (JSONArray) mapArray.get(i);
				for (int j = 0; j < objectArray.size(); j++) {
					String object = objectArray.get(j).toString();
					switch (object) {
					// Chap's initial place
					case "0":
						map[i][j] = new Location(i, j, ActorName.CHAP);
						break;
					// wall tile
					case "2":
						map[i][j] = new Location(i, j, TileName.WALL);
						break;
					// chips
					case "3":
						map[i][j] = new Location(i, j, TileName.CHIP);
						break;
					// Yellow Key
					case "4":
						map[i][j] = new Location(i, j, TileName.KEY, Variation.YELLOW);
						break;
					// Red Key
					case "5":
						map[i][j] = new Location(i, j, TileName.KEY, Variation.RED);
						break;
					// Green Key
					case "6":
						map[i][j] = new Location(i, j, TileName.KEY, Variation.GREEN);
						break;
					// Blue Key
					case "7":
						map[i][j] = new Location(i, j, TileName.KEY, Variation.BLUE);
						break;
					// Yellow Door
					case "8":
						map[i][j] = new Location(i, j, TileName.DOOR, Variation.YELLOW);
						break;
					// Red Door
					case "9":
						map[i][j] = new Location(i, j, TileName.DOOR, Variation.RED);
						break;
					// Green Door
					case "10":
						map[i][j] = new Location(i, j, TileName.DOOR, Variation.GREEN);
						break;
					// Blue Door
					case "11":
						map[i][j] = new Location(i, j, TileName.DOOR, Variation.BLUE);
						break;
					// Gate
					case "12":
						map[i][j] = new Location(i, j, TileName.GATE);
						break;
					// Portal
					case "13":
						map[i][j] = new Location(i, j, TileName.EXIT);
						break;
					// Help
					case "14":
						map[i][j] = new Location(i, j, TileName.INFO);
						break;
					// Bug
					case "15":
						map[i][j] = new Location(i, j, ActorName.BOT);
						break;
					// Sand
					case "34":
						map[i][j] = new Location(i, j);
					default:
						map[i][j] = new Location(i, j);
						break;
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
