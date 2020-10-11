package nz.ac.vuw.ecs.swen225.gp20.renderer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.net.URL;

import nz.ac.vuw.ecs.swen225.gp20.application.*;
import nz.ac.vuw.ecs.swen225.gp20.maze.Actor;
import nz.ac.vuw.ecs.swen225.gp20.maze.ActorName;
import nz.ac.vuw.ecs.swen225.gp20.maze.Location;
import nz.ac.vuw.ecs.swen225.gp20.maze.Tile;


/**
 * @author phoenix
 *
 */
public class Canvas extends JPanel {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  public static final int VIEW_SIZE = 9;
  
  private JLabel[][] displayTiles = new JLabel[VIEW_SIZE][VIEW_SIZE];
  private Location[][] map = null;
  //private Location chapL=null;
  
  private double width = 500; 
  private double height = 500;

  /**
   * 
   */
  public Canvas() {
	  this.setLayout(new GridLayout(9, 9, 2, 2));
	  
	  //Initialize the tile name to be display
	  for(int i = 0; i < VIEW_SIZE; i++) {
			for(int j = 0; j < VIEW_SIZE; j++) {				
				displayTiles[i][j] = new JLabel();
				this.add(displayTiles[i][j]);
			}
		}
	  
	
	  this.setVisible(true);
    
  }
  
  public void paintTiles() {
		if(map == null || width == 0 || height == 0) { return; }
		for(int i = 0; i < VIEW_SIZE; i++) {
			for(int j = 0; j < VIEW_SIZE; j++) {

				displayTiles[i][j].setIcon(new ImageIcon(getImage(map[i][j])));
			}
		}
		//displayTiles[5][5].setIcon(new ImageIcon(getChapImage()));
	}
  
  

  private Image getImage(Location l) {
	  if(l.getActor() != null && l.getActor().getActorName()==ActorName.CHAP) {
			URL url = getClass().getResource("/CHAP.png");
		    Image icon = new ImageIcon(url).getImage();
		    
		    return icon.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
			  }
 if (l == null || l.getTile() == null && l.getActor()==null) {
		  return new ImageIcon("/floor.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
	  }
//	  if(l.getTile() != null&&l.getActor()==null) {
	String name = l.getTile().getTileName().toString();
	
	//TODO  needs to be fix for color choose
	//String color = l.getTile().getVariation().toString();
	//String color = name.getTileName() ;
	String s = name+".png";
	
	URL url = getClass().getResource("/" + s);
    Image icon = new ImageIcon(url).getImage();
    
    return icon.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
//	  }
	  
//delete the prouv one if work
//	  return new ImageIcon("/floor.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
}
  
  private Image getChapImage() {
	  //String name = chap.getActorName().toString();
	  String s = "CHAP.png";
	  URL url = getClass().getResource("/" + s);
	  Image icon = new ImageIcon(url).getImage();
	  
	return icon.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
  }

	public void setGameTiles(Location[][] tiles) {
	this.map = tiles;
	paintTiles();
//	revalidate();
}

}
