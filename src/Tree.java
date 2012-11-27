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
			if ((n=tmp.searchChild(s.substring(0, i))) == -1)
				n=tmp.addChild(s);
			tmp=tmp.getChild(n);
			}
	}
	
	public boolean searchMot(String mot)
	{
		Node tmp=root;
		int n;
		boolean find=true;
		for (int i=0;(i<mot.length()) && find;i++)
		{
			if ((n=tmp.searchChild(mot.substring(0,i))) != -1)
				tmp=tmp.getChild(n);
			else find=false;
		}
		return find;
	}
	
	@Override
	public String toString() {
		return "Tree [root=" + root.toString() + "]";
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
