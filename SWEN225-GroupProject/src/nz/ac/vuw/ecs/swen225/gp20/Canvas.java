package nz.ac.vuw.ecs.swen225.gp20;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;


/**
 * @author phoenix
 *
 */
public class Canvas extends JPanel {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;


  /**
   * 
   */
  public Canvas() {
    setPreferredSize(new Dimension(GUI.CANVAS_SIZE, GUI.CANVAS_SIZE));

    
    setBackground(Color.red);
  }

}
