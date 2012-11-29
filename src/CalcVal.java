/**
 * Implémente le calcul threadé des valeurs
 * @author miranda
 * @version 1.0
 */
public class CalcVal implements Runnable {

	private Node n;
	private Sac s;
	private int ind;

	public CalcVal(Node n,Sac s,int ind) {
		// TODO Auto-generated constructor stub
		this.n=n;
		this.s=s;	
		this.ind=ind;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Thread [] t = new Thread [n.getNbChildren()];
		Sac [] all = new Sac [n.getNbChildren()];
		int nbOccurrences = n.getNbChildren();
		if (n.IsMot()) nbOccurrences++;
		for (int i=0;i<n.getNbChildren();i++)
			t[i] = new Thread( new CalcVal(n.getChild(i),all[i],ind+1));
		for (int i=0;i<t.length;i++)
		{
			try {
				t[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			s.add(all[i]);
		}
		s.add(n.getMot().charAt(ind),nbOccurrences);
	}

}
