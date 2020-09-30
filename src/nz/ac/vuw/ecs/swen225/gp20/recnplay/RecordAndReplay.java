package nz.ac.vuw.ecs.swen225.gp20.recnplay;

import java.awt.Desktop.Action;
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
	private String fileName;
	private ArrayList<KeyEvent> actions = new ArrayList<KeyEvent>();
	private boolean isRecording = false;

	public void startNewRecord(String name) {
		this.actions.clear();
		this.isRecording = true;
		this.fileName = name;
	}
	
	public void addAction(KeyEvent e) {
		if(isRecording) {
			actions.add(e);
		}
	}
	
	public void saveRecording() {
		if(isRecording) {
			JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
			for (int i = 0; i < actions.size(); ++i) {
		        JsonObjectBuilder builder = Json.createObjectBuilder()
		            .add("move", actions.get(i).toString());
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
	
	public boolean isRecording() {
		if(isRecording) return true;
		return false;
	}
	
	public ArrayList<KeyEvent> getActions() {
		return actions;
	}
	
	public String getFileName() {
		return fileName;
	}
	
}
