package nz.ac.vuw.ecs.swen225.gp20.application;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;

/**
 * 
 * @author Wang Conglang 300472254
 *
 */
public class Menu extends JMenuBar {
	private static final long serialVersionUID = 1L;
	private GameController controller;
	private JFrame window;
	private JDialog helpPage;

	public Menu() {
		// Main menus.
		JMenu game = new JMenu("Game");
		JMenu options = new JMenu("Options");
		JMenu level = new JMenu("Level");
		JMenu help = new JMenu("Help");
		this.add(game);
		this.add(options);
		this.add(level);
		this.add(help);
		// Menu Items in Game.
		JMenuItem startLevel1 = new JMenuItem("Start Level 1");
		JMenuItem resumeSavedGame = new JMenuItem("Resume A Saved Game");
		JMenuItem startLastUnfinishedGame = new JMenuItem("Start Last Unfinished Level");
		JMenuItem exitNoSaving = new JMenuItem("Discard Level and Exit");
		JMenuItem saveAndExit = new JMenuItem("Save and Exit");
		game.add(startLevel1);
		game.add(resumeSavedGame);
		game.add(startLastUnfinishedGame);
		game.add(exitNoSaving);
		game.add(saveAndExit);
		registerStartLevel1Action(startLevel1);
		registerResumeSavedGameAction(resumeSavedGame);
		registerStartLastUnfinishedGameAction(startLastUnfinishedGame);
		registerExitNoSavingAction(exitNoSaving);
		registerSaveAndExitgAction(saveAndExit);
		// Menu Items in Options
		JMenuItem pause = new JMenuItem("Pause");
		options.add(pause);
		registerPauseAction(pause);
		// Menu Items in Help.
		JMenuItem rules = new JMenuItem("Rules");
		help.add(rules);
		registerRules(rules);
	}

	/**
	 * Init Help Page Dialog
	 */
	public void initHelpPage() {
		String text = "";

		BufferedReader br = null;
		try {
			InputStream is = Menu.class.getResourceAsStream("/nz/ac/vuw/ecs/swen225/gp20/application/Rules.html");
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			br = new BufferedReader(isr);
			StringBuilder sb = new StringBuilder();
			while (true) {
				String line = br.readLine();
				if (line == null) {
					break;
				}
				sb.append(line).append("\n");
			}
			text = sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		JEditorPane ep = new JEditorPane();
		ep.setEditable(false);
		JScrollPane sp = new JScrollPane(ep);
		HTMLEditorKit kit = new HTMLEditorKit();
		ep.setEditorKit(kit);

		Document doc = kit.createDefaultDocument();
		ep.setDocument(doc);
		ep.setText(text);
		helpPage = new JDialog(window);
		helpPage.add(sp);
		helpPage.setSize(800, 800);
	}

	private void registerRules(JMenuItem rules) {
		Action rulesAction = new AbstractAction("Rules") {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				helpPage.setVisible(true);
			}
		};
		rules.setAction(rulesAction);
	}

	private void registerPauseAction(JMenuItem pause) {
		Action pauseAction = new AbstractAction("Pause") {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.pause();
			}
		};
		pauseAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0));
		pause.setAction(pauseAction);
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
						"The current level will not be saved. Do you want to quit anyway?", "Confirm Quit",
						JOptionPane.YES_NO_OPTION);
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
				controller.startLevel1();
			}
		};
		startLevel1Action.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_1, KeyEvent.CTRL_DOWN_MASK));
		startLevel1.setAction(startLevel1Action);
	}

	private void registerResumeSavedGameAction(JMenuItem resumeSavedGame) {
		Action resumeSavedGameAction = new AbstractAction("Resume A Saved Game") {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.resumeSavedGame();
			}
		};
		resumeSavedGameAction.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK));
		resumeSavedGame.setAction(resumeSavedGameAction);
	}

	private void registerStartLastUnfinishedGameAction(JMenuItem startLastUnfinishedGame) {
		Action startLastUnfinishedGameAction = new AbstractAction("Start Last Unfinished Level") {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.startLastUnfinishedGame();
			}
		};
		startLastUnfinishedGameAction.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
		startLastUnfinishedGame.setAction(startLastUnfinishedGameAction);
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
