package nz.ac.vuw.ecs.swen225.gp20.application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class GameInfoRendererImpl implements GameInfoRenderer {
	private GameInfoView view;
	private Timer timer;
	@Override
	public void render(Model model) {
		GameInfoModel gameInfoModel = (GameInfoModel)model;
		int level = gameInfoModel.getLevel();
		int time = gameInfoModel.getTime();
		view.setLevelText("" + level);
		view.setTimeText("" + time);

	}
	@Override
	public void countdown() {
		if (timer != null) {
			timer.stop();
			timer = null;
		}
		CountdownAction actionListener = new CountdownAction(Integer.parseInt(view.getTimeText()));
		timer = new Timer(1000, actionListener);
		timer.start();
	}

	@Override
	public void pause() {
		if (timer != null) {
			timer.stop();
			view.showPauseDialog();
			timer.start();
		}
	}

	public GameInfoView getView() {
		return view;
	}
	public void setView(GameInfoView view) {
		this.view = view;
	}

	private class CountdownAction implements ActionListener {
		private AtomicInteger seconds;
		public CountdownAction(int seconds) {
			this.seconds = new AtomicInteger(seconds);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			SwingUtilities.invokeLater(() -> {
				int timeleft = seconds.addAndGet(-1);
				if (timeleft >= 0) {
					GameInfoRendererImpl.this.view.setTimeText("" + timeleft);
					System.out.println("time set " + timeleft);
				} else {
					timer.stop();
					timer = null;
					System.out.println("stopped");
				}
			});
		}
	}
}
