package nz.ac.vuw.ecs.swen225.gp20;

import javax.swing.*;
import java.awt.*;

/**
 * @author phoenix
 *
 */
public class MenuOptions extends JMenuBar {

  /**
   * Constructor: Creates MenuBar and adds all game relevant
   * components.
   */
  public MenuOptions() {
    // Set the Size of the Control panel
    setPreferredSize(new Dimension(GUI.SCREEN_SIZE, GUI.SCREEN_SIZE / 30));
    setFont(new Font("Serif", Font.BOLD, 18));

    // Create the file menu
    JMenu file = new JMenu("File");

    JMenuItem closeItem = new JMenuItem("Close");
    closeItem.addActionListener(arg0 -> System.exit(0));
    file.add(closeItem);

    // Add components
    add(file);

    // TODO: Remove me.
    setBackground(Color.CYAN);
  }
}
