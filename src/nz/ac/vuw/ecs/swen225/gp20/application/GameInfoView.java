package nz.ac.vuw.ecs.swen225.gp20.application;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * 
 * @author Wang Conglang 300472254
 *
 */
public class GameInfoView extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final String LABEL_STYLE = " style=\"color: red; font-size: 24px;\"";
	private GameController controller;
	private JTextField levelText;
	private JTextField timeText;
	private JTextField chipsLeftText;
	public GameInfoView() {
		setBackground(Color.YELLOW);
		setPreferredSize(new Dimension(250, 500));
		setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 10));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JLabel levelLabel = new JLabel("<html><br/><br/><div"+LABEL_STYLE+">LEVEL</div></html>");
		this.add(levelLabel);
		levelText = new JTextField("1");
		levelText.setEditable(false);
		levelText.setPreferredSize(new Dimension(250, 50));
		levelText.setMaximumSize(levelText.getPreferredSize());
		levelText.setHorizontalAlignment(SwingConstants.RIGHT);
		levelText.setFont(new Font(Font.SERIF, Font.BOLD, 28));
		this.add(levelText);
		JLabel timeLabel = new JLabel("<html><br/><br/><div"+LABEL_STYLE+">TIME</div></html>");
		this.add(timeLabel);
		timeText = new JTextField("100");
		timeText.setEditable(false);
		timeText.setPreferredSize(new Dimension(250, 50));
		timeText.setMaximumSize(levelText.getPreferredSize());
		timeText.setHorizontalAlignment(SwingConstants.RIGHT);
		timeText.setFont(new Font(Font.SERIF, Font.BOLD, 28));
		this.add(timeText);
		JLabel chipsLeftLabel = new JLabel("<html><br/><br/><div"+LABEL_STYLE+">CHIPS<br/>LEFT</div></html>");
		this.add(chipsLeftLabel);
		chipsLeftText = new JTextField("10");
		chipsLeftText.setEditable(false);
		chipsLeftText.setPreferredSize(new Dimension(250, 50));
		chipsLeftText.setMaximumSize(levelText.getPreferredSize());
		chipsLeftText.setHorizontalAlignment(SwingConstants.RIGHT);
		chipsLeftText.setFont(new Font(Font.SERIF, Font.BOLD, 28));
		this.add(chipsLeftText);
		
	}
	public void setController(GameController controller) {
		this.controller = controller;
	}
	public void updateLevelText(String value) {
		levelText.setText(value);
	}
}
