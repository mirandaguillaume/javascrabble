package principale;

/**
 * @author Cid's computer
 *
 */

/**
 * Le nombre de lettre est calcul�
 * en focntion d'un dictionnaire
 * Ce dictionnaire doit �tre sous 
 * la forme d'un arbre.
 */
public class Jeton{
	
	private char lettre;
	private int score;
	
	/**
	 * constructeurs
	 * @param lettre
	 * @param score
	 */
	public Jeton(char lettre, int score) {
		this.lettre = lettre;
		this.score=score;
	}
	
	public Jeton(){
		lettre='\0';
		score=0;
	}
	
	/**
	 * accesseurs
	 */
	
	public void set(char lettre, int score){
		this.lettre=lettre;
		this.score=score;
	}
	
	public char get_lettre(){
		return lettre;
	}
	
	public int get_score(){
		return score;
	}
	
	public int compareTo(Jeton j) {
		return ((int)lettre)-((int)j.lettre);
	}
	
	public boolean equals(Jeton j) {
		return lettre==j.lettre;
	}
	
	public String toString() {
		return "Lettre:"+lettre+" Score :"+score;
	}

}
