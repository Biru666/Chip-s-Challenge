package nz.ac.vuw.ecs.swen225.gp20.application;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 * 
 * @author Wang Conglang 300472254
 *
 */
public class Menu extends JMenuBar {
	private static final long serialVersionUID = 1L;
	private GameController controller;

	public Menu() {
		JMenu game = new JMenu("Game");
		JMenu options = new JMenu("Options");
		JMenu level = new JMenu("Level");
		JMenu help = new JMenu("Help");
		this.add(game);
		this.add(options);
		this.add(level);
		this.add(help);
		JMenuItem startLevel1 = new JMenuItem("Start Level 1");
		game.add(startLevel1);
		Action startLevel1Action = new AbstractAction("Start Level 1") {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.startLevel1(e);
			}
		};
		startLevel1Action.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_1, KeyEvent.CTRL_DOWN_MASK));
		startLevel1.setAction(startLevel1Action);
	}

	public GameController getController() {
		return controller;
	}

	public void setController(GameController controller) {
		this.controller = controller;
	}

}
