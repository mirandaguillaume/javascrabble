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
	private Node [] children;

	/** Constructeur du noeud 
	 * 
	 * @param s mot à mettre dans le noeud
	 */
	public Node(String s) {
		this.mot=s;
		this.children = null;
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
			for (int i=0;(i<children.length) && !find;i++)
			{
				if(children[i].getMot()==s)
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
		Node n = null;
		try
		{	 
			n=children[i];
		}
		catch(NullPointerException | ArrayIndexOutOfBoundsException e)
		{
			n=new Node("\0");
		}
		return n;
	}

	/**
	 *  Ajoute un mot en fils
	 * @param s Mot à ajouter
	 * @return Indice du fils ajouté
	 */
	public synchronized int addChild(String s)
	{
		Node [] aux;
		int ind;
		try
		{
			aux = new Node[children.length+1];
			for (int i=0;i<children.length;i++)
				aux[i]=children[i];
			aux[children.length]=new Node(s);
			ind=children.length;
		}
		catch(NullPointerException ex)
		{
			aux=new Node[1];
			aux[0]=new Node(s);
			ind=0;
		}
		children=aux;
		return ind;
	}

	/**
	 * Override the toString method
	 */
	public String toString()
	{
		String rep=mot;
		int nb;
		try { nb = children.length; }
		catch (NullPointerException e)
		{ nb = 0; }
		for (int i=0;i<nb;i++)
			mot+=" Fils n°"+Integer.toString(i)+":"+children[i].toString();
		return rep;
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
