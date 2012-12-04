package Principale;

/**
 * @author Cid's computer
 *
 * Chaque Case permet de contenir
 * une lettre ainsi qu'un bonus possible
 * s'elle est � telle coordonn�e
 */
public class Case {

	public enum Bonus{lettrecomptedouble, lettrecomptetriple, motcomptedouble, motcomptetriple, none };
	
	private char lettre;
	private int pt;
	private Jeton j;
	private Bonus bonus;

	/**
	 * Constructeur
	 * @param bonus
	 */
	public Case(Bonus bonus){
		this.bonus=bonus;
		lettre='\0';
		pt=0;
		j=new Jeton();
	}

	/**
	 * �quivaut � un constructeur
	 * @param bonus
	 * 
	 * remet � z�ro la case. �quivalent d'une destrcution,
	 * construction
	 */
	public void reset(Bonus bonus) {
		this.bonus=bonus;
		lettre='\0';
		pt=0;
		j=new Jeton();
	}
	
	/**
	 * Accesseurs
	 * Les set ne sont pas n�cessaire car on ne peut placer
	 * qu'un seul Jeton par Case (fait avec la m�thode placer(:Jeton)
	 * et le bonus est initialiser dans le constructeur de Case.
	 * Il n'y a pas de raison qu'il puisse changer au cour
	 * de la partie !
	 */
	
	public char get_lettre(){
		return Jeton.getLettre();
	}
	
	public int get_pt(){
		switch(bonus){
		case lettrecomptedouble:
			return Jeton.getScore()*2;
		case lettrecomptetriple:
			return Jeton.getScore()*3;
		case motcomptedouble:
		case motcomptetriple:
		case none:
			return Jeton.getScore();
		default:
			throw new GameException("Bonus mal initialis�",new GameException("Le bonus initialis� est inconnu !"));
		}
	}
	
	public Bonus get_bonus() {
		return bonus;
	}
	
	/**
	 * 
	 * @param jeton
	 * @return
	 * 
	 * Cette m�thode place un jeton
	 * et retourne le nombre de point.
	 *  
	 * jette une GameException si la case est
	 * d�j� investie ou si le bonus � mal �t�
	 * initialis�.
	 * 
	 * @throws GameException soit -1 si bug
	 * ou 0 si la case est d�j� prise.
	 *  
	 */
	public int placer(Jeton jeton) throws GameException{
		if(lettre=='\0'){
			j.set(lettre, score);
			lettre=jeton.get_lettre();
			pt=jeton.get_score();
			jeton.prendre(1);
			switch(bonus){
			case lettrecomptedouble :
				return pt*2;
			case lettrecomptetriple :
				return pt*3;
			case motcomptedouble :
			case motcomptetriple :
			case none :
				return pt;
			default:
				throw new GameException("Bonus mal initialis�",new GameException("Le bonus initialis� est inconnu !"));
			}
		}
		else
			throw new GameException("Case non disponible !",new GameException("Appel de la m�thode Case.placer(:Jeton) alors que la Case contient d�j� une lettre"),0);
	}
}
