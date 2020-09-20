package nz.ac.vuw.ecs.swen225.gp20.application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @author Wang Conglang 300472254
 *
 */
public class Main extends JFrame {
	private static final long serialVersionUID = 1L;
	private MazePanel mazePanel = new MazePanel();
	private GameInfo gameInfo = new GameInfo();

	public Main() {
	}
	private void init() {
		setTitle("Chap's Challenge");
		setSize(800, 600);
		setJMenuBar(new Menu());
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationByPlatform(true);
		JPanel mazeContainer = new JPanel();
		mazeContainer.setBackground(Color.GREEN);
		mazeContainer.add(mazePanel);
		this.add(mazeContainer);
		JPanel infoContainer = new JPanel();
		infoContainer.setBackground(Color.GREEN);
		infoContainer.add(gameInfo);
		infoContainer.setPreferredSize(new Dimension(270, 600));
		this.add(infoContainer, BorderLayout.EAST);
	}
	public static void main(String[] args) {
		Main main = new Main();
		main.init();
		main.setVisible(true);
	}
}
