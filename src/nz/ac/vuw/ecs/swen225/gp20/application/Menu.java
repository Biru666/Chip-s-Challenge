package nz.ac.vuw.ecs.swen225.gp20.application;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

/**
 * 
 * @author Wang Conglang 300472254
 *
 */
public class Menu extends JMenuBar {
	private static final long serialVersionUID = 1L;
	public Menu() {
		JMenu game = new JMenu("Game");
		JMenu options = new JMenu("Options");
		JMenu level = new JMenu("Level");
		JMenu help = new JMenu("Help");
		this.add(game);
		this.add(options);
		this.add(level);
		this.add(help);
	}
}
