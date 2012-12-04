package Principale;

import java.util.ArrayList;

public class VerticalSearch implements Runnable {

	private Plateau p;
	private Mot finalMot;
	private Main main;
	
	public VerticalSearch(Plateau p, Mot finalMot,Main main) {
		// TODO Auto-generated constructor stub
		this.p=p;
		this.finalMot=finalMot;
		this.main=main;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String mot = "\0";
		Character c;
		ArrayList<Thread> tab = new ArrayList<Thread>();
		for (int i=0;i<p.getTaillePlateau();i++)
			for (int j=0;j<p.getTaillePlateau();j++) {
				c = p.getCase(i,j).getJeton().getLettre();
				if (c!='\0')
					mot+=c.toString();
				else if (mot.length()>1) {
					tab.add(new Thread ( new TestMot(p,new Mot(mot),main)));
					mot="\0";
				}
				else 
					mot="\0";
			}
	}
}
