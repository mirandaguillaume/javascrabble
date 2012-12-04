package Dico;

import java.util.ArrayList;

/**
 * Implémente le noeud de l'arbre du dictionnaire
 * @author guillaume
 *
 */
public class Node {

	/** 
	 * Est le mot contenu dans le noeud
	 */
	private String mot;

	/**
	 *  Contient tous les fils du noeud courant
	 */
	ArrayList<Node> children;
	
	@SuppressWarnings("unused")
	private boolean isMot;

	private boolean ok;
	/** Constructeur du noeud 
	 * 
	 * @param s mot à mettre dans le noeud
	 */
	public Node(String s) {
		this.mot=s;
		this.children = new ArrayList<Node>();
		isMot=false;
	}

	public void setIsMot(boolean b){isMot=b;}
	
	public boolean getIsMot() {return ok;}
	
	public int getNbChildren()
	{
		return children.size();
	}
	
	public String getMot() {return mot;}

	/** 
	 * Recherche un fils avec le mot s à l'intérieur
	 * @param s Le mot à rechercher
	 * @return Retourne l'indice du noeud contenant le mot
	 */
	public int searchChild(String s)
	{
		int ind = -1;
		boolean find = false;
		try {
			for (int i=0;(i<children.size()) && !find;i++)
			{
				if(children.get(i).getMot().equals(s))
				{
					ind = i;
					find = true;
				}
			}
		}
		catch (NullPointerException e)
		{ind=-1;}
		return ind;
	}

	/** 
	 * Renvoie le fils associé à la case i
	 * @param i Le numéro du fils voulu 
	 * @return Le fils i du noeud courant
	 */
	public Node getChild(int i)
	{
		return children.get(i);
	}

	/**
	 *  Ajoute un mot en fils
	 * @param s Mot à ajouter
	 * @return Indice du fils ajouté
	 */
	public int addChild(String s)
	{
		Node n = new Node(s);
		children.add(n);
		return children.lastIndexOf(n);
	}
	
	/**
	 * @see java.lang.Object#toString()
	 **/
	@Override
	public String toString() {
		return "Node [mot=" + mot + "]";
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Node n,m;
		n= new Node("a");
		m= new Node("b");
		n.addChild("c");
		System.out.println(n);
		System.out.println(m);
		System.out.println(n.getChild(1));
	}

}
