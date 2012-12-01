package Principale;
/**
 * Classe qui implémente le tableau dynamique de String
 * @author guillaume
 * @version 1.0
 */
public class GrowableStringArray {

	/** Le tableau de String */
	private String [] tab;
	
	/** Constructeur qui initialise le tableau à null */
	public GrowableStringArray() {
		tab=null;
	}
	
	/**
	 * Fonction qui renvoie la taille du tableau
	 * @return Taille du tableau
	 */
	public int length() {
		try {
			return tab.length;
		} catch (NullPointerException e) {
			return 0;
		}
	}
	
	/**
	 * Fonction pour ajouter un élément dans le tableau de String
	 * @param t Le String à rajouter
	 */
	public void add (String t)
	{
		String [] aux = new String [length()+1];
		for (int i=0;i<length();i++)
			aux[i]=tab[i];
		aux[length()]=t;
		tab = aux;
	}
}