package nz.ac.vuw.ecs.swen225.gp20.renderer;

import java.applet.AudioClip; 
import java.io.*; 
import java.applet.Applet; 
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;


public class Music{
	    
	private String name = "bgm.wav";
	private File f = new File("music/"+name); 
	private URL url; 
	private URI uri;
	private  AudioClip clip; 
	
	void setMusic(String name){
       this.name=name;
   }
  public Music(){     
    try
    {  
       uri=f.toURI();
       url = uri.toURL();
       clip = Applet.newAudioClip(url); 
       clip.loop();
       System.out.println("music on");
   }
    catch (MalformedURLException e) { 
           e.printStackTrace(); 
           System.out.println("error");
       }
   }
  public void stopMusic(){
      clip.stop();
  }
  public void playMusic()
  {
      clip.play();
  }
  public void loopMusic()
  {
      clip.loop();
  }
	 


	
}
