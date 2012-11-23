/**
 * Implémente l'arbre représentant le dictionnaire
 * @author Guillaume
 *
 */
public class Tree {

	/** 
	 * Attribut qui contient la racine de l'arbre
	 */
	private Node root;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * Sert à ajouter un mot dans l'arbre
	 * @param s Le mot à ajouter
	 */
	public void addMot(String s)
	{
		Node tmp=root;
		for (int i=0;i<s.length();i++)
			{
			int n=tmp.searchChild(s.substring(0, i));
			tmp=tmp.getChild(n);
			
			}
	}
	
	/**
	 * Constructeur de la classe
	 * Crée un noeud contenant le caractère vide pour la racine
	 */
	public Tree() {
		super();
		root = new Node("\0");
	}

}
