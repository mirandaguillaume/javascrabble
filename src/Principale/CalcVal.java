package Principale;

/**
 * Implémente le calcul des occurences en mode threadé
 * @author guillaume
 * @version 1.0
 */
public class CalcVal implements Runnable {

	private Node n;
	private Sac s;
	private int ind;
	/**
	 * Constructeur de la classe
	 * @param n Le noeud d'origine
	 * @param s Le sac dans lequel calculer
	 * @param ind L'indice du caractère
	 */
	public CalcVal(Node n,Sac s,int ind) {
		// TODO Auto-generated constructor stub
		this.n=n;
		this.s=s;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Thread [] t = new Thread [n.getNbChildren()];
		Sac [] all = new Sac [n.getNbChildren()];
		for (int i=0;i<n.getNbChildren();i++)
		{
			all[i]=new Sac();
			t[i]=new Thread (new CalcVal(n.getChild(i),all[i],ind+1));
			t[i].start();
		}
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
		if (n.getMot()!="\0")
			s.add(n.getMot().charAt(ind),n.getNbChildren());
	}
}
