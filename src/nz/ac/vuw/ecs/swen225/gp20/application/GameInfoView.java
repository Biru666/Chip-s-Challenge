package nz.ac.vuw.ecs.swen225.gp20.application;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

/**
 * 
 * @author Wang Conglang 300472254
 *
 */
public class GameInfoView extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final String LABEL_STYLE = " style=\"color: red; font-size: 18px;\"";
	private static final int TEXT_FIELD_HEIGHT = 40;
	private Main window;
	private JDialog dialog;
	private GameController controller;
	private JTextField levelText;
	private JTextField timeText;
	private JTextField chipsLeftText;
	private JTextField keysCollectedText;
	public GameInfoView() {
		setBackground(Color.YELLOW);
		setPreferredSize(new Dimension(250, 500));
		setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 10));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JLabel levelLabel = new JLabel("<html><br/><br/><div"+LABEL_STYLE+">LEVEL</div></html>");
		this.add(levelLabel);
		levelText = new JTextField("1");
		levelText.setEditable(false);
		levelText.setPreferredSize(new Dimension(250, TEXT_FIELD_HEIGHT));
		levelText.setMaximumSize(levelText.getPreferredSize());
		levelText.setHorizontalAlignment(SwingConstants.RIGHT);
		levelText.setFont(new Font(Font.SERIF, Font.BOLD, 28));
		levelText.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK), "");
		this.add(levelText);
		JLabel timeLabel = new JLabel("<html><br/><br/><div"+LABEL_STYLE+">TIME</div></html>");
		this.add(timeLabel);
		timeText = new JTextField("100");
		timeText.setEditable(false);
		timeText.setPreferredSize(new Dimension(250, TEXT_FIELD_HEIGHT));
		timeText.setMaximumSize(levelText.getPreferredSize());
		timeText.setHorizontalAlignment(SwingConstants.RIGHT);
		timeText.setFont(new Font(Font.SERIF, Font.BOLD, 28));
		timeText.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK), "");
		this.add(timeText);
		JLabel chipsLeftLabel = new JLabel("<html><br/><br/><div"+LABEL_STYLE+">CHIPS<br/>LEFT</div></html>");
		this.add(chipsLeftLabel);
		chipsLeftText = new JTextField("10");
		chipsLeftText.setEditable(false);
		chipsLeftText.setPreferredSize(new Dimension(250, TEXT_FIELD_HEIGHT));
		chipsLeftText.setMaximumSize(levelText.getPreferredSize());
		chipsLeftText.setHorizontalAlignment(SwingConstants.RIGHT);
		chipsLeftText.setFont(new Font(Font.SERIF, Font.BOLD, 28));
		chipsLeftText.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK), "");
		this.add(chipsLeftText);
		JLabel keysCollectedLabel = new JLabel("<html><br/><br/><div"+LABEL_STYLE+">KEYS<br/>COLLECTED</div></html>");
		this.add(keysCollectedLabel);
		keysCollectedText = new JTextField("0");
		keysCollectedText.setEditable(false);
		keysCollectedText.setPreferredSize(new Dimension(250, TEXT_FIELD_HEIGHT));
		keysCollectedText.setMaximumSize(levelText.getPreferredSize());
		keysCollectedText.setHorizontalAlignment(SwingConstants.RIGHT);
		keysCollectedText.setFont(new Font(Font.SERIF, Font.BOLD, 28));
		keysCollectedText.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK), "");
		this.add(keysCollectedText);
	}
	public void setController(GameController controller) {
		this.controller = controller;
	}
	public void setLevelText(String value) {
		levelText.setText(value);
	}
	public String getLevelText() {
		return levelText.getText();
	}
	public String getTimeText() {
		return timeText.getText();
	}
	public void setTimeText(String timeText) {
		this.timeText.setText(timeText);
	}
	public String getChipsLeftText() {
		return chipsLeftText.getText();
	}
	public void setChipsLeftText(String chipsLeftText) {
		this.chipsLeftText.setText(chipsLeftText);
	}
	public void setWindow(Main window) {
		this.window = window;
	}
	public void showPauseDialog() {
		JOptionPane.showMessageDialog(window, "Game Paused. ESC or click on OK to resume.");
	}
}
