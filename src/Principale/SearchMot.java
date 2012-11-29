package Principale;

/**
 * Classe pour la recherche threadée d'un mot dans le dictionnaire
 * @author guillaume
 * @version 1.0
 */
public class SearchMot implements Runnable {

	/** Le mot à rechercher */
	private String mot;
	
	/** Le booleen qui renvoie la valeur voulue */
	private Boolean ok;
	
	/** L'arbre dans lequel rechercher */
	private Tree t;
	
	/** 
	 * Contructeur de la classe
	 * @param t L'arbre dans lequel rechercher
	 * @param mot Le mot à rechercher
	 * @param ok Le booleen qui renvoie la valeur voulue
	 */
	public SearchMot(Tree t,String mot,Boolean ok) {
		// TODO Auto-generated constructor stub
		this.t=t;
		this.mot=mot;
		this.ok=ok;
	}

	/**
	 * Override the run method from the runnable interface 
	 * @see Runnable#run() 
	 */
	@Override
	
	public void run() {
		// TODO Auto-generated method stub
		ok.setB(t.searchMot(mot));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
