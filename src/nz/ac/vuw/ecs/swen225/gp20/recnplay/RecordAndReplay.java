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



/**
 * Class to record game play and replay it.
 * @author Jasmine Liang
 *
 */
public class RecordAndReplay {
	private static String fileName;
	private static ArrayList<KeyEvent> actions = new ArrayList<KeyEvent>();
	private static boolean isRecording = false;
	private static String gameState;

	/**
	 * To start a new record
	 * @param name the file name to save
	 */
	public static void startNewRecord(String name) {
		actions.clear();
		isRecording = true;
		fileName = name;
		gameState = getGameState();
	}
	
	/**
	 * Add actions to the action list
	 * @param e the actions need to save
	 */
	public static void addAction(KeyEvent e) {
		if(isRecording) {
			actions.add(e);
		}
	}
	
	/**
	 * Save recording to json file
	 */
	public static void saveRecording() {
		if(isRecording) {
			/** if using the saveGame in persistence package
			 * SaveGame s = new SaveGame(maze, currentState);
			 * gameState = s.save();
			*/
			JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
			// add actions to array builder
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
			// add game state and action array to a new builder
			JsonObjectBuilder builder = Json.createObjectBuilder()
					.add("game", gameState)
			        .add("moves", arrayBuilder);
			// write text to json file
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
	
	/**
	 * Load a recording
	 * @param name
	 */
	public static void loadRecording(String name) {
		fileName = name;
		//load game state
		
		// load actions
		JsonObject object = null;
		try {
	        BufferedReader reader = new BufferedReader(new FileReader(fileName));
	        JsonReader jsonReader = Json.createReader(new StringReader(reader.readLine()));
	        reader.close();
	        object = jsonReader.readObject();
	      } catch (IOException e) {
	        System.out.println("Error reading file: " + e);
	        return;
	      }
		 JsonArray movesJson = object != null ? object.getJsonArray("moves") : null;
	      if (movesJson != null) {
	    	  System.out.println("success");
	    	  for (int i = 0; i < movesJson.size(); ++i) {
	              JsonObject object2 = movesJson.getJsonObject(i);
	              String direction = object2.getString("move");
	              switch (direction) {
	                case "West":
	                  actions.add(KeyEvent.VK_LEFT, null);
	                  break;
	                case "East":
	                  actions.add(KeyEvent.VK_RIGHT, null);
	                  break;
	                case "North":
	                  actions.add(KeyEvent.VK_UP, null);
	                  break;
	                case "South":
	                  actions.add(KeyEvent.VK_DOWN, null);
	                  break;
	                default:
	                  break;
	              }
	            }
	      }
		
	}
	
	/**
	 * Get the game state
	 * @return
	 */
	private static String getGameState() {
		String gamestate = "";
		Location[][]map = null;
		Parser parser = new Parser("levels/level1.json");
		map = parser.map;
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				String gs = "";
				if(j==0 && i!=0) {
					gamestate+="]\n[";
				}
				if(j==0 && i==0) {
					gamestate+="[";
				}
				Location object = map[i][j];
				//System.out.print(object.toString() + " ");
				// Blank
				if(object.toString().equals("_")) {
					gs = "1, ";
					gamestate = add(gamestate,gs);
					continue;
				}
				// get tile name of the cell
				TileName tileName = null;
				if(object.getTile()!=null) {
					tileName = object.getTile().getTileName();
				}
				// get actor name of the cell
				ActorName actorName = null;
				if(object.getActor()!=null) {
					actorName = object.getActor().getActorName();
					switch(actorName) {
					// Chap
					case CHAP:
						gs = "0, ";
						gamestate = add(gamestate,gs);
						continue;
					// Bot
					case BOT:
						gs = "15, ";
						gamestate = add(gamestate,gs);
						continue;
					}
				}
				switch (tileName) {
				// Wall
				case WALL:
					gs = "2, ";
					gamestate = add(gamestate,gs);
					continue;
				// Chip
				case CHIP:
					gs = "3, ";
					gamestate = add(gamestate,gs);
					continue;
				// Key
				case KEY:
					Key k = (Key) object.getTile();
					Variation v = k.getVariation();
					switch(v) {
					case YELLOW:
						gs = "4, ";
						gamestate = add(gamestate,gs);
						continue;
					case RED:
						gs = "5, ";
						gamestate = add(gamestate,gs);
						continue;
					case GREEN:
						gs = "6, ";
						gamestate = add(gamestate,gs);
						continue;
					case BLUE:
						gs = "7, ";
						gamestate = add(gamestate,gs);
						continue;
					}
				// Door
				case DOOR:
					Door d = (Door) object.getTile();
					Variation v2 = d.getVariation();
					switch(v2) {
					case YELLOW:
						gs = "8, ";
						gamestate = add(gamestate,gs);
						continue;
					case RED:
						gs = "9, ";
						gamestate = add(gamestate,gs);
						continue;
					case GREEN:
						gs = "10, ";
						gamestate = add(gamestate,gs);
						continue;
					case BLUE:
						gs = "11, ";
						gamestate = add(gamestate,gs);
						continue;
					}
				// Gate
				case GATE:
					gs = "12, ";
					gamestate = add(gamestate,gs);
					continue;
				// Portal
				case EXIT:
					gs = "13, ";
					gamestate = add(gamestate,gs);
					continue;
				// Help
				case INFO:
					gs = "14, ";
					gamestate = add(gamestate,gs);
					continue;
				}
			}
			if(i==map.length-1) {
				gamestate += "]";
			}
		}
		return gamestate;
	}
	
	/**
	 * Help method for adding two strings
	 * @param s1
	 * @param s2
	 * @return 
	 */
	private static String add(String s1, String s2) {
		return s1+s2;
	}
	
	/**
	 * Get isRecording
	 * @return
	 */
	public static boolean isRecording() {
		if(isRecording) return true;
		return false;
	}
	
	/**
	 * Get actions list
	 * @return
	 */
	public ArrayList<KeyEvent> getActions(){
		return actions;
	}
	
	/**
	 * Get file name
	 * @return
	 */
	public String getFileName() {
		return fileName;
	}
	
}
