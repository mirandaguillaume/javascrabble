package Principale;
/**
 * Classe qui implémente le tableau dynamique de thread
 * @author guillaume
 * @version 1.0
 */
public class GrowableThreadArray {

	/** Le tableau de thread */
	private Thread [] tab;
	
	/** Constructeur qui initialise le tableau à null */
	public GrowableThreadArray() {
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
	 * Fonction pour ajouter un élément dans le tableau de thread
	 * @param t Le thread à rajouter
	 */
	public void add (Thread t)
	{
		Thread [] aux = new Thread [length()+1];
		for (int i=0;i<length();i++)
			aux[i]=tab[i];
		aux[length()]=t;
		tab = aux;
	}
}
