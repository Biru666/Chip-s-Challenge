package nz.ac.vuw.ecs.swen225.gp20;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * GUI for the whole game.
 * 
 * @author Phoenix Xie
 *
 */
public class GUI extends JFrame implements ComponentListener {
  // Nothing important
  private static final long serialVersionUID = 1L;


  // Dimension of the frame, based on screen size
  private static Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
  static int SCREEN_SIZE = (Math.min(screenDimension.width, screenDimension.height));
  static int CANVAS_SIZE = SCREEN_SIZE;
  static int DASHBOARD_WIDTH = SCREEN_SIZE / 3;

  // Main component fields.
  private Canvas canvas;
  private Dashboard dashboard;
  private JMenuBar menuBar;

  /**
   * Constructor: Creates a new JFrame and sets preferred sizes.
   * Creates and adds all relevant GUI components then redraws.
   */
  public GUI() {
    //Create & init the frame.
    setPreferredSize(screenDimension.getSize());
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    setResizable(true);
    setMinimumSize(new Dimension(SCREEN_SIZE / 5, SCREEN_SIZE / 5));
    setVisible(true);

    // Add components.
    setupComponents();

    pack();

    // Render.
    redraw();
  }

  /**
   * Testing invocation point to check GUI.
   *
   * @param args (ignored).
   */
  public static void main(String[] args) {
    GUI gui = new GUI();
    System.out.printf("Screen width %d, height %d\n", SCREEN_SIZE, SCREEN_SIZE);
    System.out.printf("Dashboard width %d, height %d\n", DASHBOARD_WIDTH, CANVAS_SIZE);
  }

  /**
   * Sets up GridBagLayout with all screen components.
   */
  public void setupComponents() {
    // Add MenuBar.
    menuBar = new MenuOptions();
    setJMenuBar(menuBar);

    // Set Layout
    int padding = SCREEN_SIZE / 11;
    setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();


    // Create & Set Canvas.
    canvas = new Canvas();
    constraints.insets = new Insets(padding, padding, padding, padding / 2);
    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.weightx = 3;
    constraints.weighty = 3;
    constraints.fill = GridBagConstraints.BOTH;
    add(canvas, constraints);

    // Create & Set Dashboard.
    dashboard = new Dashboard();
    constraints.insets = new Insets(padding, padding / 2, padding, padding);
    constraints.gridx = 3;
    constraints.gridy = 0;
    constraints.weightx = 1;
    constraints.weighty = 3;
    constraints.fill = GridBagConstraints.BOTH;
    add(dashboard, constraints);
  }



  /**
   * Redraws the GUI JFrame.
   * * Revalidates.
   * * Repaints.
   */
  public void redraw() {
    revalidate();
    repaint();

  }


  /**
   * Resize dimensions and redraw window on
   * screen resize.
   * @param e event.
   */
  @Override
  public void componentResized(ComponentEvent e) {
    screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
    SCREEN_SIZE = (Math.min(screenDimension.width, screenDimension.height));
    CANVAS_SIZE = SCREEN_SIZE;
    DASHBOARD_WIDTH = SCREEN_SIZE / 3;

    setupComponents();
    redraw();
  }

  /**
   * Overridden but not utilized.
   * @param e event.
   */
  @Override
  public void componentMoved(ComponentEvent e) {

  }

  /**
   * Overridden but not utilized.
   * @param e
   */
  @Override
  public void componentShown(ComponentEvent e) {

  }

  /**
   * Overridden but not utilized.
   * @param e
   */
  @Override
  public void componentHidden(ComponentEvent e) {

  }
}
