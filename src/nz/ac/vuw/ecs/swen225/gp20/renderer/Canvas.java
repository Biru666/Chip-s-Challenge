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
  private Tile[][] gameTiles = null;
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
		if(gameTiles == null || width == 0 || height == 0) { return; }
		for(int i = 0; i < VIEW_SIZE; i++) {
			for(int j = 0; j < VIEW_SIZE; j++) {
				displayTiles[i][j].setIcon(new ImageIcon(getImage(gameTiles[i][j])));
			}
		}
	}

  private Image getImage(Tile name) {
	String tilename = name.getTileName().toString();
	//TODO  needs to be fix for color choose
	//String color = name.getTileName() ;
	String s = tilename+".png";
	
	URL url = getClass().getResource("swen-225-project/icons/" + s);
    Image icon = new ImageIcon(url).getImage();
    
    return icon.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
	
}

	public void setGameTiles(Tile[][] tiles) {
	this.gameTiles = tiles;
}

}
