package nz.ac.vuw.ecs.swen225.gp20.recnplay;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;

import nz.ac.vuw.ecs.swen225.gp20.persistence.Parser;
import nz.ac.vuw.ecs.swen225.gp20.application.GameController;
import nz.ac.vuw.ecs.swen225.gp20.maze.ActorName;
import nz.ac.vuw.ecs.swen225.gp20.maze.Door;
import nz.ac.vuw.ecs.swen225.gp20.maze.Location;
import nz.ac.vuw.ecs.swen225.gp20.maze.Key;
import nz.ac.vuw.ecs.swen225.gp20.maze.Tile;
import nz.ac.vuw.ecs.swen225.gp20.maze.TileName;
import nz.ac.vuw.ecs.swen225.gp20.maze.Variation;

//import nz.ac.vuw.ecs.swen225.gp20.maze.Direction;


/**
 * Class to record game play and replay it.
 * @author Jasmine Liang
 *
 */
public class RecordAndReplay {
	private static String fileName;
	private static ArrayList<KeyEvent> actions = new ArrayList<KeyEvent>();
	private static boolean isRecording = false;

	public static void startNewRecord(String name) {
		actions.clear();
		isRecording = true;
		fileName = name;
	}
	
	public static void addAction(KeyEvent e) {
		if(isRecording) {
			actions.add(e);
		}
	}
	
	public static void saveRecording() {
		if(isRecording) {
			String gameState = null;
			/** if using the saveGame in persistence package
			 * SaveGame s = new SaveGame(maze, currentState);
			 * gameState = s.save();
			*/
			//gameState = getGameState();
			JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
			for (int i = 0; i < actions.size(); ++i) {
				KeyEvent e = actions.get(i);
				String action = null;
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					action = "North";
					break;
				case KeyEvent.VK_DOWN:
					action = "South";
					break;
				case KeyEvent.VK_LEFT:
					action = "West";
					break;
				case KeyEvent.VK_RIGHT:
					action = "East";
					break;
				default:
					break;
				}
		        JsonObjectBuilder builder = Json.createObjectBuilder()
		            .add("move", action);
		        arrayBuilder.add(builder.build());
		      }
			JsonObjectBuilder builder = Json.createObjectBuilder()
					.add("game", gameState)
			        .add("moves", arrayBuilder);
			
			Writer writer = new StringWriter();
			Json.createWriter(writer).write(builder.build());
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
			    bw.write(writer.toString());
			    bw.close();
			    } catch (IOException e) {
			      throw new Error("Failed to save moves");
			    }
			isRecording = false;
			
		}
	}
	
	
	public void loadRecording() {
		
	}
	
	private static String getGameState() {
		String gamestate = null;
		Location[][]map = null;
		Parser parser = new Parser(GameController.class.getResource("/level1.json").getPath());
		map = parser.map;
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				String gs = null;
				Location object = map[i][j];
				TileName tileName = object.getTile().getTileName();
				ActorName actorName = object.getActor().getActorName();
				switch(actorName) {
				case CHAP:
					gs = "1";
					gamestate = add(gamestate,gs);
					break;
				case BOT:
					gs = "15";
					gamestate = add(gamestate,gs);
				}
				switch (tileName) {
				// wall tile
				case WALL:
					gs = "2";
					gamestate = add(gamestate,gs);
					break;
				// chips
				case CHIP:
					gs = "3";
					gamestate = add(gamestate,gs);
					break;
				// Key
				case KEY:
					Key k = (Key) object.getTile();
					Variation v = k.getVariation();
					switch(v) {
					case YELLOW:
						gs = "4";
						gamestate = add(gamestate,gs);
						break;
					case RED:
						gs = "5";
						gamestate = add(gamestate,gs);
						break;
					case GREEN:
						gs = "6";
						gamestate = add(gamestate,gs);
						break;
					case BLUE:
						gs = "7";
						gamestate = add(gamestate,gs);
						break;
					}
				// Door
				case DOOR:
					Door d = (Door) object.getTile();
					Variation v2 = d.getVariation();
					switch(v2) {
					case YELLOW:
						gs = "8";
						gamestate = add(gamestate,gs);
						break;
					case RED:
						gs = "9";
						gamestate = add(gamestate,gs);
						break;
					case GREEN:
						gs = "10";
						gamestate = add(gamestate,gs);
						break;
					case BLUE:
						gs = "11";
						gamestate = add(gamestate,gs);
						break;
					}
				// Gate
				case GATE:
					gs = "12";
					gamestate = add(gamestate,gs);
					break;
				// Portal
				case EXIT:
					gs = "13";
					gamestate = add(gamestate,gs);
					break;
				// Help
				case INFO:
					gs = "14";
					gamestate = add(gamestate,gs);
					break;
				// Sand
				default:
					gs = "34";
					gamestate = add(gamestate,gs);
					break;
				}
			}
		}
		return gamestate;
	}
	
	private static String add(String s1, String s2) {
		return s1+s2;
	}
	
	public static boolean isRecording() {
		if(isRecording) return true;
		return false;
	}
	
	public ArrayList<KeyEvent> getActions(){
		return actions;
	}
	
	public String getFileName() {
		return fileName;
	}
	
}
