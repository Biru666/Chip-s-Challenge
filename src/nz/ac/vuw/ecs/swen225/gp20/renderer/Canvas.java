package nz.ac.vuw.ecs.swen225.gp20.renderer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
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
  private Main GUI = new Main();
  private JLabel[][] displayTiles = new JLabel[VIEW_SIZE][VIEW_SIZE];
  private Tile[][] gameTiles = null;
	
  private double width = 500; 
  private double height = 500;

  /**
   * 
   */
  public Canvas() {
    
  }

}
