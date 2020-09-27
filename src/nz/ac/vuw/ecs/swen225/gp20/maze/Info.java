package nz.ac.vuw.ecs.swen225.gp20.maze;

public class Info extends Tile {

	public Info(TileName tileName, Location location, boolean moveOn, boolean pickUp) {
		super(tileName, location, moveOn, pickUp);
	}

	@Override
	protected void interact(Actor actor) {
		if (actor instanceof Chap) {
			display();
		}
	}

	private void display() {
		// TODO Auto-generated method stub
		
	}

}
