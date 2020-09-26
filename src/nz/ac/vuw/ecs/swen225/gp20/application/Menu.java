package nz.ac.vuw.ecs.swen225.gp20.application;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 * 
 * @author Wang Conglang 300472254
 *
 */
public class Menu extends JMenuBar {
	private static final long serialVersionUID = 1L;
	private GameController controller;
	private JFrame window;

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
		JMenuItem exitNoSaving = new JMenuItem("Discard Level and Exit");
		JMenuItem saveAndExit = new JMenuItem("Save and Exit");
		game.add(startLevel1);
		game.add(exitNoSaving);
		game.add(saveAndExit);
		registerStartLevel1Action(startLevel1);
		registerExitNoSavingAction(exitNoSaving);
		registerSaveAndExitgAction(saveAndExit);
	}

	private void registerSaveAndExitgAction(JMenuItem saveAndExit) {
		Action saveAndExitAction = new AbstractAction("Save and Exit") {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.saveLevel();
				JOptionPane.showMessageDialog(window, "Current Level is saved.");
				System.exit(0);
			}
		};
		saveAndExitAction.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
		saveAndExit.setAction(saveAndExitAction);
	}

	private void registerExitNoSavingAction(JMenuItem exitNoSaving) {
		Action exitNoSavingAction = new AbstractAction("Discard Level and Exit") {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(window,
						"The current level will not be saved. Do you want to quit anyway?", "Confirm Quit", JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		};
		exitNoSavingAction.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
		exitNoSaving.setAction(exitNoSavingAction);
	}

	private void registerStartLevel1Action(JMenuItem startLevel1) {
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

	public void setWindow(JFrame window) {
		this.window = window;
	}

}
