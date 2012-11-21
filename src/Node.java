
public class Node {

	//Attributes
	private String mot;
	private Node [] children;
	
	//Constructor
	public Node(String s) {
		System.out.println("Je construit le noeud"); this.mot=s;
			}
	
	//Methods
	public String getMot() {return mot;}
	
	public int searchChild(String s)
	{
		int ind = -1;
		boolean find = false;
		for (int i=0;(i<children.length) && !find;i++)
		{
			if(children[i].getMot()==s)
			{
				ind = i;
				find = true;
			}
		}
		return ind;
	}
	
	public Node getChild(int i)
	{
		Node n = null;
		try
		{	 
				n=children[i];
		}
		catch(NullPointerException ex)
		{
			n=new Node("\0");
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			n = new Node("\0");
		}
		return n;
	}
	
	public int addChild(String s)
	{
		Node [] aux;
		int ind;
		System.out.println("J'affiche le nombre d'enfants");
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
		
	public String toString()
	{
		int nb;
		try { nb = children.length; }
		catch (NullPointerException e)
		{ nb = 0; }
		return mot+" Nb: "+nb;
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
