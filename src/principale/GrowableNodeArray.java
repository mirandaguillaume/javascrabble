package Principale;

import Dico.Node;

/**
 * Classe qui implémente le tableau dynamique de Node
 * @author guillaume
 * @version 1.0
 */
public class GrowableNodeArray {

	/** Le tableau de Node */
	private Node [] tab;
	
	/** Constructeur qui initialise le tableau à null */
	public GrowableNodeArray() {
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
	 * Fonction pour ajouter un élément dans le tableau de Node
	 * @param t Le Node à rajouter
	 */
	public void add (Node t)
	{
		Node [] aux = new Node [length()+1];
		for (int i=0;i<length();i++)
			aux[i]=tab[i];
		aux[length()]=t;
		tab = aux;
	}
	
	public Node getNode(int i) {
		try {
			return tab[i];
		} catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
			return new Node("\0");
		}
	}
}
