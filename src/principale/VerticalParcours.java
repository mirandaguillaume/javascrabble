package Principale;

public class VerticalParcours implements Runnable {

	private Plateau p;
	private Mot aTester;
	private Jeton [] main;
	
	public VerticalParcours(Plateau p, Mot aTester, Jeton[] main) {
		super();
		this.p = p;
		this.aTester = aTester;
		this.main = main;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String mot = "\0";
		Character c;
		GrowableThreadArray tab = new GrowableThreadArray();
		for (int i=0;i<p.getTaillePlateau();i++)
			for (int j=0;j<p.getTaillePlateau();j++) {
				c = p.getCase(i,j).getJeton().getLettre();
				if (c!='\0')
					mot+=c.toString();
				else if (mot.length()>1) {
					tab.add(new Thread ( new SearchMot(p.getDico().getListe(),mot,ok)));
					mot="\0";
				}
				else 
					mot="\0";
			}
	}

	public void test(){
		
	}
}
