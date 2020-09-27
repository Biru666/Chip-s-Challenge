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

	private HashMap<String, Integer> inventory;
	private int chips;
	private int totalChips;

	/**
	 * Our protagonist constructor
	 * 
	 * @param actorName - Enum actorName
	 * @param loaction  - Location
	 */
	public Chap(ActorName actorName, Location loaction) {
		super(actorName, loaction);
		this.inventory = new HashMap<>();
		this.chips = 0;
	}

	@Override
	public Action interact(Tile t, Actor a) {
		// collided with another actor, kill each other
		if (a != null) {
			kill();
			a.kill();
			return Action.DIE;
		}
		// has a tile
		if (t != null) {
			return t.interact(this);
		}
		return Action.MOVE;
	}

	/**
	 * Sets the total chips
	 */
	public void setTotalChips(int totChips) {
		this.totalChips = totChips;
	}

	/**
	 * @return the total chips
	 */
	public int getTotalChips() {
		return totalChips;
	}

	/**
	 * @return the chips
	 */
	public int getChips() {
		return chips;
	}

	/**
	 * Add one chip
	 */
	public void addChips() {
		chips++;
	}

	/**
	 * @return the inventory
	 */
	public HashMap<String, Integer> getInventory() {
		return inventory;
	}

	/**
	 * Adds an item based on a custom string
	 * 
	 * @param s - String key
	 */
	public void addItem(String s) {
		// alredy has the key, +1 extra count
		if (inventory.containsKey(s))
			inventory.put(s, inventory.get(s) + 1);
		// no key yet, adds 1
		else
			inventory.put(s, 1);
	}

	/**
	 * Removes an Item from the map with a custom String
	 * 
	 * @param s - String key
	 */
	public void removeItem(String s) {
		if (inventory.get(s) > 1)
			inventory.put(s, inventory.get(s) - 1);
		else
			inventory.remove(s);
	}

}
