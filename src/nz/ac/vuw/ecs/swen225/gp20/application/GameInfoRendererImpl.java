package nz.ac.vuw.ecs.swen225.gp20.application;

import javax.swing.SwingUtilities;

public class GameInfoRendererImpl implements GameInfoRenderer {
	private GameInfoView view;
	@Override
	public void render(Model model) {
		GameInfoModel gameInfoModel = (GameInfoModel)model;
		int level = gameInfoModel.getLevel();
		SwingUtilities.invokeLater(() -> {
			view.updateLevelText("" + level);
		});
	}
	public GameInfoView getView() {
		return view;
	}
	public void setView(GameInfoView view) {
		this.view = view;
	}

}
