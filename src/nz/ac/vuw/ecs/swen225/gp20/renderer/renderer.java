package nz.ac.vuw.ecs.swen225.gp20.renderer;

/**
 * The rendering module is responsible for providing a simple 2D view
 * of the maze, to be embedded in the application. It is updated after each move.
 * 
 * @author Phoenix Xie
 *
 */


import javax.swing.JPanel;

import nz.ac.vuw.ecs.swen225.gp20.application.*;
import nz.ac.vuw.ecs.swen225.gp20.maze.*;
import nz.ac.vuw.ecs.swen225.gp20.persistence.*;

import java.util.Observable;
import java.util.Observer;



public class renderer {
	private Canvas canvas;
	private Main main;
	
	Tile[][] maze;
	
	
	public renderer(Main game) {
		canvas= new Canvas();
		
		}

		
			
		
	}



