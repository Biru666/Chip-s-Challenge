package nz.ac.vuw.ecs.swen225.gp20.application;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

/**
 * 
 * @author Wang Conglang 300472254
 *
 */
public class MazeView extends JPanel {
	private static final long serialVersionUID = 1L;
	private GameController controller;
	public MazeView() {
		setBackground(Color.YELLOW);
		setPreferredSize(new Dimension(500, 500));
	}
	public void setController(GameController controller) {
		this.controller = controller;
	}
}
