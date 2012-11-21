
public class Node {

	//Attributes
	private char c;
	private Node [] children;
	
	//Constructor
	public Node(char c) {
		children = new Node[27];
		System.out.println("Je construit le noeud"); this.c=c;
			}
	
	//Methods
	public char getC() {return c;}
	
	public Node getChild(int i)
	{
		if (i<children.length) return children[i];
		return new Node('!');
	}
	
	public void addChild(char c)
	{
		int n;
		System.out.println("J'affiche le nombre d'enfants");
		try{n=children.length;}
		catch(NullPointerException ex)
		{n=0;}
		if (n<=27)
		{
			System.out.println("Je vais le rajouter");
			children[n-1]=new Node(c);
			System.out.println("Ajouté");
		}
	}
		
	public String toString()
	{
		return Character.toString(c)+" Nb: "+children.length;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Node n,m;
		n= new Node('a');
		m= new Node('b');
		n.addChild('c');
		System.out.println(n);
		System.out.println(m);
		System.out.println(n.getNext(26));
	}

	public int searchChild(char charAt) {
		// TODO Auto-generated method stub
		return 0;
	}

}
