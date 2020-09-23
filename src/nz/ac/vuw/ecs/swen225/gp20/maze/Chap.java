package nz.ac.vuw.ecs.swen225.gp20.maze;

import java.util.HashMap;

/**
 * Chap class which extends Actor which is our main protagonist. Chap will be
 * able to tranverse through-out the maze, gather chips and finish each level.
 * 
 * @author wangding1
 *
 */
public class Chap extends Actor {

	protected HashMap<String, Integer> inventory = new HashMap<>();
	protected int chips = 0;

	public Chap(ActorName actorName, Location loaction) {
		super(actorName, loaction);
	}

	public void move(Location newLocation) {
		Tile tile = newLocation.tile;

		if (tile == null) {
			updatePosition(newLocation);
		} else if (tile.canMoveOn == true) {

		}

	}

	private void updatePosition(Location newLocation) {

	}

//	public boolean moveTo(Location newL) {
//
//		if (newL.getTile() == null) {
//			move(newL);
//			return true;
//
//		}
//		switch (newL.getTile().tileName) {
//		case WALL:
//			return false;
//
//		case BLUE_KEY:
//			move(newL);
//			takeItem();
//			return true;
//
//		case GREEN_KEY:
//			move(newL);
//			takeItem();
//			return true;
//
//		case CHIP:
//			move(newL);
//			takeItem();
//			return true;
//
//		case BLUE_DOOR:
//			if (inventory.containsKey(TileName.BLUE_KEY)) {
//				removeItem(TileName.BLUE_KEY);
//				move(newL);
//				super.location.setTile(null);
//				return true;
//
//			} else
//				return false;
//
//		case GREEN_DOOR:
//			if (inventory.containsKey(TileName.GREEN_KEY)) {
//				removeItem(TileName.GREEN_KEY);
//				move(newL);
//				super.location.setTile(null);
//				return true;
//
//			} else
//				return false;
//
//		case GATE:
//			if (inventory.containsKey(TileName.CHIP) && inventory.get(TileName.CHIP) >= totalChip) {
//				move(newL);
//				super.location.setTile(null);
//				return true;
//
//			} else
//				return false;
//
//		case EXIT:
//			move(newL);
//			return true;
//
//		default:
//			break;
//		}
//		return false;
//
//	}
//
//	private void removeItem(TileName tileName) {
//		int count = inventory.get(tileName);
//		if (count > 1) {
//			inventory.put(tileName, count - 1);
//		} else
//			inventory.remove(tileName);
//	}
//
//	private void takeItem() {
//		Location l = super.location;
//
//		TileName t = l.getTile().tileName;
//		l.setTile(null);
//
//		if (inventory.containsKey(t))
//			inventory.put(t, inventory.get(t) + 1);
//		else
//			inventory.put(t, 1);
//
//	}
//
//	private void move(Location newL) {
//		Location oldL = super.location;
//
//		newL.setActor(this);
//		oldL.setActor(null);
//		super.location = newL;
//	}

}
