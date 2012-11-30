package Principale;

/**
 * Implémente le calcul des occurences en mode threadé
 * @author guillaume
 * @version 1.0
 */
public class CalcVal {

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

	public void run() {
		// TODO Auto-generated method stub
		CalcVal [] t = new CalcVal [n.getNbChildren()];
		Sac [] all = new Sac [n.getNbChildren()];
		for (int i=0;i<n.getNbChildren();i++)
		{
			all[i]=new Sac();
			t[i]=new CalcVal(n.getChild(i),all[i],ind+1);
			t[i].run();
			s.add(all[i]);
		}
		if (n.getMot()!="\0")
			s.add(n.getMot().charAt(ind),n.getNbChildren());
		if (n.getIsMot())
			s.add(n.getMot().charAt(ind),1);
	}
}
