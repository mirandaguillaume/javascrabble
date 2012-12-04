package Principale;

import Principale.Plateau.Coord;
import Principale.Plateau.Direction;

public class Mot {

	private Direction d;
	private Coord c;
	private String mot;
	private int score;

	/** Constructeur sans paramètre de la classe */
	public Mot() {
		// TODO Auto-generated constructor stub
		this("\0",0);
	}

	public Mot(String s) {
		this(s,0);
	}

	/** Constructeur avec le mot et le score du mot */
	public Mot(String mot, int score) {
		super();
		this.mot=mot;
		this.score = score;
	}

	public int length(){
		return mot.length();
	}
	
	/**
	 * @return the d
	 */
	public Direction getD() {
		return d;
	}

	/**
	 * @param d the d to set
	 */
	public void setD(Direction d) {
		this.d = d;
	}

	/**
	 * @return the c
	 */
	public Coord getC() {
		return c;
	}

	/**
	 * @param c the c to set
	 */
	public void setC(Coord c) {
		this.c = c;
	}

	/**
	 * @return the mot
	 */
	public String getMot() {
		return mot;
	}

	/**
	 * @param mot the mot to set
	 */
	public void setMot(String mot) {
		this.mot = mot;
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}


}
