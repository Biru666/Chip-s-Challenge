package nz.ac.vuw.ecs.swen225.gp20.application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JFrame;
import javax.swing.JPanel;

import nz.ac.vuw.ecs.swen225.gp20.maze.Direction;

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
		menu.setWindow(this);
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
		this.setFocusable(true);
		this.addKeyListener(new KeyAdapter() {
			private AtomicBoolean isBusy = new AtomicBoolean(false);
			@Override
			public void keyPressed(KeyEvent e) {
				new Thread(() -> {
					if (isBusy.compareAndSet(false, true)) {
						switch (e.getKeyCode()) {
						case KeyEvent.VK_UP:
							controller.move(Direction.NORTH);
							break;
						case KeyEvent.VK_DOWN:
							controller.move(Direction.SOUTH);
							break;
						case KeyEvent.VK_LEFT:
							controller.move(Direction.WEST);
							break;
						case KeyEvent.VK_RIGHT:
							controller.move(Direction.EAST);
							break;
						default:
							break;
						}
						isBusy.set(false);
					}
				}).start();
			}
		});
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
