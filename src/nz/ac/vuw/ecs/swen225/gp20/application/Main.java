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
	private GameController controller = new GameController();
	private MazeView mazeView = new MazeView();
	private GameInfoView gameInfoView = new GameInfoView();
	private Menu menu = new Menu();

	public Main() {
	}

	private void init() {
		setTitle("Chap's Challenge");
		setSize(800, 600);
		setJMenuBar(menu);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationByPlatform(true);
		JPanel mazeContainer = new JPanel();
		mazeContainer.setBackground(Color.GREEN);
		mazeContainer.add(mazeView);
		this.add(mazeContainer);
		JPanel infoContainer = new JPanel();
		infoContainer.setBackground(Color.GREEN);
		infoContainer.add(gameInfoView);
		infoContainer.setPreferredSize(new Dimension(270, 600));
		this.add(infoContainer, BorderLayout.EAST);
	}

	private void equiptController() {
		menu.setController(controller);
		mazeView.setController(controller);
		gameInfoView.setController(controller);
		controller.setMazePanel(mazeView);
		controller.setGameInfo(gameInfoView);
		GameInfoRendererImpl gameInfoRenderer = new GameInfoRendererImpl();
		gameInfoRenderer.setView(gameInfoView);
		controller.setGameInfoRenderer(gameInfoRenderer);
	}
	public static void main(String[] args) {
		Main main = new Main();
		main.init();
		main.equiptController();
		main.setVisible(true);
	}
}
