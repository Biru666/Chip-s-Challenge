package nz.ac.vuw.ecs.swen225.gp20.recnplay;

import java.awt.Desktop.Action;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

/**
 * Class to record game play and replay it.
 * @author Jasmine Liang
 *
 */
public class RecordAndReplay {
	private String name, level;
	private ArrayList<Action> actions = new ArrayList<Action>();
	private boolean isRecording = false;

	public void startNewRecord(String level) {
		this.actions.clear();
		this.isRecording = true;
		this.level = level;
	}
	
	public void addAction(Action a) {
		if(this.isRecording) {
			this.actions.add(a);
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
			          .add("levle", this.level)
			          .add("moves", arrayBuilder);
			 try (Writer writer = new StringWriter()) {
			        Json.createWriter(writer).write(builder.build());
			        try {
			          BufferedWriter bw = new BufferedWriter(new FileWriter("record"));
			          bw.write(writer.toString());
			          bw.close();
			        } catch (IOException e) {
			          throw new Error("Failed to save moves");
			        }
			      } catch (IOException e) {
			        throw new Error("Failed to save moves");
			      }

			      isRecording = false;
			
		}
	}
}
