/**
 * Implémente l'arbre représentant le dictionnaire
 * @author Guillaume
 * @version 0.5
 */
public class Tree {

	/** 
	 * Attribut qui contient la racine de l'arbre
	 */
	private Node root;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tree a=new Tree();
		a.addMot("a");
		a.addMot("b");
	}
	
	/**
	 * Sert à ajouter un mot dans l'arbre
	 * @param s Le mot à ajouter
	 */
	public void addMot(String s)
	{
		Node tmp=root;
		int n;
		for (int i=0;i<s.length();i++)
			{
			n=tmp.searchChild(s.substring(0, i+1));
			if (n == -1)
				n=tmp.addChild(s.substring(0,i+1));
			tmp=tmp.getChild(n);
			}
	}
	
	public boolean searchMot(String mot)
	{
		Node tmp=root;
		int n;
		boolean find=true;
		String sub = null;
		for (int i=0;(i<mot.length()) && find;i++)
		{
			sub = mot.substring(0,i+1);
			if ((n=tmp.searchChild(sub)) != -1)
				tmp=tmp.getChild(n);
			else find=false;
		}
		return find;
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
