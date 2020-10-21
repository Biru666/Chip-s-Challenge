package nz.ac.vuw.ecs.swen225.gp20.maze;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * The BotMannager class handles all other Actors thats not Chap ,and will
 * update there movements based on its own pattern. The bots shouldn't be beside
 * each-other, since they will kill each other on contact.
 * 
 * @author wangding1 300422014
 *
 */
public class BotMannager {
	private Maze maze;
	private HashSet<Actor> actors;

	/**
	 * The constructor takes a Set of actors which are all unique and the maze
	 * 
	 * @param actors - HashSet of actors
	 * @param maze   - Maze object
	 */
	public BotMannager(HashSet<Actor> actors, Maze maze) {
		this.actors = actors;
		this.maze = maze;
	}

	/**
	 * Advance the game by 1 tick, which will update all other actors positions
	 * based on there own algorithms.
	 */
	public void tick() {
		// no other actors
		if (actors.isEmpty())
			return;

		// moves all actors
		ArrayList<Actor> turn = new ArrayList<>(actors); // bots turn

		// Since we cant remove lists in a forloop, we can remove it in while loop
		while (!turn.isEmpty()) {
			Actor a = turn.get(0);

			// if actor was dead
			if (a.isDead()) {
				actors.remove(a);
				turn.remove(a);
				continue;
			}

			// Bot actor
			if (a instanceof Bot) {
				Bot bot = (Bot) a;
				maze.move(a, bot.left());
				while (maze.getAction() == Action.WALL)
					maze.move(a, bot.right());
			}

			// if actor just died
			if (a.isDead())
				actors.remove(a);
			turn.remove(a);
		}
	}

}
