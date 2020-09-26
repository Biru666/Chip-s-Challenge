package nz.ac.vuw.ecs.swen225.gp20.persistence;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import nz.ac.vuw.ecs.swen225.gp20.maze.Location;



public class Parser {
	JSONArray mapArray;
	Map<Object, Location> map;
	
	public Parser(String filename) {
		load(filename);
	}
	
	private void load(String filename) {
		JSONParser parser = new JSONParser();
		try {
			mapArray = (JSONArray) parser.parse(new FileReader(filename));
			for(int i = 0; i < mapArray.size(); i++) {
				JSONArray objectArray = (JSONArray) mapArray.get(i);
				for(int j = 0; j < objectArray.size(); j++) {
					
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
