/**
 * 
 */

/**
 * @author Guillaume
 *
 */
public class Tree {

	private Node root;
	/**
	 * @param args
	 */
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void addMot(String s)
	{
		Node tmp=root;
		for (int i=0;i<s.length();i++)
			{
			int n=tmp.searchChild(s.substring(0, i));
			tmp=tmp.getChild(n);
			
			}
	}
	
	public Tree() {
		super();
		root = new Node("\0");
	}

}
