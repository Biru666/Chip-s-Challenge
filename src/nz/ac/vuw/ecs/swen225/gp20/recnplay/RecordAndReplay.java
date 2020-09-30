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
	
	public static boolean isRecording() {
		if(isRecording) return true;
		return false;
	}
	
}
