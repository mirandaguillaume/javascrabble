package Principale;

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
	private int quantite;
	
	/**
	 * constructeurs
	 * @param lettre
	 * @param score
	 * @param quantite
	 */
	public Jeton(char lettre, int score, int quantite) {
		this.lettre = lettre;
		this.score=score;
		this.quantite = quantite;
	}
	
	public Jeton(){
		lettre='\0';
		score=0;
		quantite=0;
	}
	
	public void add (Jeton other)
	{
		quantite+=other.quantite;
	}
	
	public void add(int occurrences)
	{
		quantite+=occurrences;
	}
	/**
	 * accesseurs
	 */
	
	public void set(char lettre, int score, int quantite){
		this.lettre=lettre;
		this.score=score;
		this.quantite=quantite;
	}
	
	public char get_lettre(){
		return lettre;
	}
	
	public int get_score(){
		return score;
	}
	
	/**
	 * quantite n'a volontairement pas d'accesseur.
	 * la classe auto-g�re celle-ci
	 * quantite est simplement utilis� dans prendre(int) : boolean
	 */
	
	/**
	 * 
	 * @param quantite
	 * @return la quantite restante
	 */
	public int prendre(int quantite)throws GameException{
		if(this.quantite-quantite>=0){
			this.quantite=this.quantite-quantite;
			return this.quantite;
		}
		else throw new GameException("ERREUR : Quantite de jeton demand� trop �lev�",new GameException("RAISON : tentative d'affectation n�gative � quantite dans la classe Jeton"));
	}
}
